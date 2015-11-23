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

package BatBall.input;

import BatBall.Main.Game;
import BatBall.objects.Ball;
import BatBall.objects.Bat;
import BatBall.objects.GameObject;
import BatBall.objects.Objects;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private boolean[] keyDown = new boolean[]{false, false, false, false};
    private final Objects objects;
    private final Bat bat;

    public KeyInput(Game game) {
        this.bat = game.getBat();
        this.objects = game.getObjects();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_ESCAPE:
                System.exit(1);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                bat.setSpeedX(-bat.getBaseSpeed());
                keyDown[0] = true;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                bat.setSpeedX(bat.getBaseSpeed());
                keyDown[1] = true;
                break;
        }

        for (int i = 0; i < objects.getSize(); i++) {
            GameObject temp = objects.getObject(i);
            if (temp.getName().equals("ball")) {
                if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    Ball ball = (Ball) objects.getObject(i);
                    ball.setDocked(false);
                }
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                keyDown[0] = false;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                keyDown[1] = false;
                break;
        }


        if (!keyDown[0] && !keyDown[1]) bat.setSpeedX(0);

        if (!keyDown[0] && keyDown[1]) bat.setSpeedX(bat.getBaseSpeed());
        if (keyDown[0] && !keyDown[1]) bat.setSpeedX(-bat.getBaseSpeed());
    }

}
