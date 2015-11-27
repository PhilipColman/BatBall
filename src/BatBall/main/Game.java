/**
 * Created by philip on 23/11/15.
 * <p/>
 * BatBall is a basic bat ball game.
 * Copyright (C) 2015  philip
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package BatBall.main;

import BatBall.gameStates.GameState;
import BatBall.gameStates.Menu;
import BatBall.gameStates.Play;
import BatBall.input.KeyInput;
import BatBall.input.MouseInput;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	private boolean running = false;
	private int frames = 0;
	private int updates = 0;

	private static Game instance;

	private final Window window;
	private Thread thread;

	private GameState state = GameState.Menu;
	private final Menu menu;
	private Play play;

	private final MouseInput mouseInput;
	private final KeyInput keyInput;

	public static void main(String[] args) {
		instance = new Game();
	}

	public Game() {

		mouseInput = new MouseInput();
		menu = new Menu();
		keyInput = new KeyInput();


		addKeyListener(keyInput);
		addMouseListener(mouseInput);

		window = new Window(this);

		start();
	}



	@Override
	public void run() {
		this.requestFocus();

		long lastTime = System.nanoTime();
		double amountOfUpdates = 60.0;
		double ns = 1000000000 / amountOfUpdates;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				delta--;
				updates++;
			}
			if(running) {
				render();
				frames++;
			}
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				int fps = frames;
				frames = 0;
				int ups = updates;
				updates = 0;
				window.setTitle(String.format("Bat and Ball   |   UPS: %1$d  FPS: %2$d", ups, fps));
			}
		}
		stop();
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWindowWidth(), Window.getWindowWidth());

		switch (state) {
			case Menu:
				menu.render(g);
				break;
			case Game:
				play.render(g);
				break;
			case Options:
				break;
			case LevelScreen:
				break;
			case LevelEnd:
				break;
		}

		g.dispose();
		bs.show();
	}

	private void update() {
		switch (state) {
			case Menu:
				break;
			case Game:
				play.update();
				break;
			case Options:
				break;
			case LevelScreen:
				break;
			case LevelEnd:
				break;
		}
	}

	private synchronized void start() {
		running = true;
		this.thread = new Thread(this, "Main Thread");
		thread.start();
	}

	private synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public MouseInput getMouseInput() {
		return mouseInput;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public void setPlay(Play play) {
		this.play= play;
	}

	public Play getPlay() {
		return play;
	}

	public KeyInput getKeyInput() {
		return keyInput;
	}

	public static Game getInstance() {
		return instance;
	}
}
