/**
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

import BatBall.input.KeyInput;
import BatBall.input.MouseInput;
import BatBall.levels.BaseLevel;
import BatBall.states.End;
import BatBall.states.Level;
import BatBall.states.Menu;
import BatBall.states.State;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable {

    private int ups = 0, fps = 0;
    private boolean running = false;
    private int updates = 0, frames = 0;
    private Window window;

    private Thread thread;

    private State state = State.Menu;
    private Menu menu;
    private Level level;
    private End end;
    private int progress = 0;

    private BaseLevel gameLevel;

    private static Main instance;

    public Main() {
        MouseInput mouseInput = new MouseInput();

        menu = new Menu();
        level = new Level();

        addKeyListener(new KeyInput());
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
        addMouseWheelListener(mouseInput);

        window = new Window(this);
        start();
    }

    public static void main(String[] args) {
        instance = new Main();

    }

    @Override
    public void run() {
        window.requestFocus();

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
            if (running) {
                render();
                frames++;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                fps = frames;
                frames = 0;
                ups = updates;
                updates = 0;
            }
        }
        stop();
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

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
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
            case Level:
                level.render(g);
                break;
            case Game:
                gameLevel.render(g);
                break;
            case End:
                end.render(g);
                break;
        }

        g.setFont(new Font("arial", Font.PLAIN, 12));
        g.setColor(Color.white);
        g.drawString(String.format("UPS: %1$d \nFPS: %2$d", ups, fps), 10, 10);

        g.dispose();
        bs.show();
    }

    private void update() {
        if (window.isActive()) {
            switch (state) {
                case Menu:
                    menu.update();
                    break;
                case Level:
                    level.update();
                    break;
                case Game:
                    gameLevel.update();
                    break;
                case End:
                    end.update();
                    break;
                case Exiting:
                    System.exit(0);
                    break;
            }
        }
    }

    public static Main getInstance() {
        return instance;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setGameLevel(BaseLevel Level) {
        this.gameLevel = Level;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setEnd(End end) {
        this.end = end;
    }

    public BaseLevel getGameLevel() {
        return gameLevel;
    }
}
