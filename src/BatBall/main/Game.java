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

import BatBall.input.KeyInput;
import BatBall.objects.Ball;
import BatBall.objects.Bat;
import BatBall.objects.Brick;
import BatBall.objects.Objects;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private int frames = 0;
    private int fps, ups;
    private int updates = 0;

    private final Window window;
    private Thread thread;

    private final Objects objects;
    private final Bat bat;

    public Game() {
        bat = new Bat(this);
        objects = new Objects();
        objects.addObject(bat);
        objects.addObject(new Ball(bat.getX() + bat.getWidth() / 2 - 16, bat.getY() - 32, this));
        this.addKeyListener(new KeyInput(this));

        Color[] colors = new Color[]{
                Color.red, //1
                Color.blue, //2
                Color.cyan, //3
                Color.DARK_GRAY, //4
                Color.magenta, //5
                Color.orange, //6
        };

        boolean[] hard = new boolean[]{
                true, //1
                true, //2
                true, //3
                false, //4
                false, //5
                false //6
        };
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 20; x++) {
                objects.addObject(new Brick(x * (Window.getWindowWidth()) / 20, y * 30, Window.getWindowWidth() / 20, 30, colors[y], hard[y], this));
            }
        }

        window = new Window(this);

        start();
    }

    public static void main(String[] args) {
        new Game();
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
                window.setTitle(String.format("Bat and Ball   |   UPS: %1$d  FPS: %2$d", ups, fps));
            }
        }
        stop();
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

        objects.render(g);

        g.dispose();
        bs.show();
    }

    private void update() {
        objects.update();
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

    public Objects getObjects() {
        return objects;
    }

    public Bat getBat() {
        return bat;
    }
}
