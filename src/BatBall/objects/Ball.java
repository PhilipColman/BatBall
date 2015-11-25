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

package BatBall.objects;

import BatBall.main.Game;
import BatBall.main.Window;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameObject {

    private final Objects objects;
    private boolean docked;
    private final Bat bat;
    private static int balls = 1, left = 99;

    public Ball(int x, int y, Game game) {
        super("ball", x, y, 32, 32, 4, Color.white, game);
        this.docked = true;
        this.bat = game.getBat();
        this.objects = game.getObjects();
        //System.out.print("Ball Made");
    }

    @Override
    public void update() {

        if (!isDocked()) {
            if (speedX == 0 && speedY == 0) {
                speedX = random.nextBoolean() ? 1 : -1 * baseSpeed;
                speedY = random.nextBoolean() ? 1 : -1 * baseSpeed;
            }
            move();
        }
        walls();
        hitBat();
        hitBrick();
        remove();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height);
        drawBounds(g);
    }

    private void hitBat() {
        if (getBounds().intersects(bat.getBounds())) {
            speedY *= -1;
        }
    }

    private void hitBrick() {
        for (int i = 0; i < objects.getSize(); i++) {
            GameObject temp = objects.getObject(i);
            if (temp.name.equals("brick")) {
                if (getBounds().intersects(temp.getBounds())) {
                    Brick brick = (Brick) objects.getObject(i);
                    if (brick.isHard()) {
                        brick.setHard(false);
                    } else {
                        if (brick.hasUpgrade())
                            objects.addObject(brick.getUpgrade());
                        objects.removeObject(brick);
                    }
                    speedY *= -1;
                    break;
                }
            }
        }
    }

    @Override
    protected void walls() {
        if (y <= 0)
            speedY *= -1;

        if (x <= 0 || x >= Window.getWindowWidth() - 32)
            speedX *= -1;
    }

    @Override
    protected void remove() {
        if (y > Window.getWindowHeight()) {
            if (balls == 1 && left > 0) {
                left--;
                objects.addObject(new Ball(bat.getX() + bat.getWidth() / 2 - 16, bat.getY() - 32, game));
            } else if (balls > 1)
                balls--;
            objects.removeObject(this);
            for (int index = 0; index < bat.upgradeCount(); index++) {
                Upgrade tempUpgrade = bat.getUpgrade(index);
                if (tempUpgrade.getEffects() == Upgrade.Effects.Ball) {
                    bat.removeUpgrade(tempUpgrade);
                }
            }
        }
    }

    public boolean isDocked() {
        return docked;
    }

    public void setDocked(boolean docked) {
        this.docked = docked;
    }


    public static void setBalls(int balls) {
        Ball.balls = balls;
    }

    public static int getBalls() {
        return balls;
    }

}
