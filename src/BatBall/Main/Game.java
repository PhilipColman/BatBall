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

package BatBall.Main;

import BatBall.objects.Objects;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

    private boolean running = false;
    private int frames = 0;
    private int fps, ups;
    private int updates;

    private Window window;
    private Thread thread;

    private Objects objects;

    public Game() {
        window = new Window(this);
        start();
    }

    public static void main(String[] args){
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
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
                updates++;
            }
            if(running){
                render();
                frames++;
            }
            if(System.currentTimeMillis() - timer > 1000) {
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
        if(bs == null){
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

    private synchronized void start(){
        running = true;
        this.thread = new Thread(this, "Main Thread");
        thread.start();
    }

    private synchronized void stop(){
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
}
