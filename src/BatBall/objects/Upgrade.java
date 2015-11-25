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

import java.awt.Color;
import java.awt.Graphics;

public class Upgrade extends GameObject {

    private long end;
    private long length;
    private Effects effects;
    private final Bat bat;
    private boolean running;
    private final Upgrades id;
    private boolean update = true;


    public Upgrade(Upgrades name, int x, int y, Game game) {
        super(name.toString(), x, y, 64, 64, 1, null, game);
        this.bat = game.getBat();
        this.running = false;
        this.id = name;
        switch (name) {
            case Speed:
                Speed();
                break;
            case Extend:
                Extend();
                break;
            case ExtraBalls:
                ExtraBalls();
                break;
        }
        speedY = baseSpeed;
    }

    private void Speed() {
        this.color = Color.yellow;
        this.effects = Effects.Ball;
        this.length = 60 * 1000;
    }

    private void Extend() {
        this.color = Color.red;
        this.effects = Effects.Bat;
        this.length = 30 * 1000;
    }

    private void ExtraBalls(){
        this.color = Color.blue;
        this.effects = Effects.Ball;
        this.length = 1 * 1000;
    }

    @Override
    public void update() {
        move();
        hitBat();
        remove();
        if (running) {
            if (System.currentTimeMillis() <= this.end) {
                if(update){
                    runEffect();
                }

            } else {
                running = false;
                bat.removeUpgrade(this);
                removeEffect();
            }
        }
    }

    private void runEffect(){
        switch (id) {
            case Speed:
                for (int i = 0; i < objects.getSize(); i++) {
                    GameObject temp = objects.getObject(i);
                    if (temp.getName().equals("ball")) {
                        temp.setBaseSpeed(6);
                        temp.updateSpeed();
                        update = false;
                    }
                }
                break;
            case Extend:
                bat.setWidth(300);
                bat.setX(bat.getX() - 50);
                update = false;
                break;
            case ExtraBalls:
                Ball.setBalls(Ball.getBalls()+2);
                Ball ball = new Ball(bat.getX() + bat.getWidth() / 2 - 16, bat.getY() - 32, game);
                ball.setDocked(false);
                objects.addObject(ball);
                ball = new Ball(bat.getX() + bat.getWidth() / 2 - 16, bat.getY() - 32, game);
                ball.setDocked(false);
                objects.addObject(ball);
                break;
        }

    }

    private void removeEffect() {
        switch (id) {
            case Speed:
                for (int i = 0; i < objects.getSize(); i++) {
                    GameObject temp = objects.getObject(i);
                    if (temp.getName().equals("ball")) {
                        temp.setBaseSpeed(5);
                        temp.updateSpeed();
                    }
                }
                break;
            case Extend:
                bat.setWidth(200);
                bat.setX(bat.getX() + 50);
                break;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    private void hitBat() {
        if (getBounds().intersects(bat.getBounds())) {
            if (bat.upgradeCount() == 0) {
                bat.addUpgrade(this);
                this.running = true;
            } else {
                for (int i = 0; i < bat.upgradeCount(); i++) {
                    Upgrade temp = bat.getUpgrade(i);
                    if (temp.id.equals(this.id)) {
                        temp.setEnd(System.currentTimeMillis() + this.length);
                    } else {
                        bat.addUpgrade(this);
                        this.running = true;
                    }
                }
            }
            objects.removeObject(this);
        }
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public Effects getEffects() {
        return effects;
    }

    public long getEnd() {
        return end;
    }

    public enum Upgrades {
        Speed,
        Extend,
        ExtraBalls
    }

    public enum Effects {
        Ball,
        Bat
    }
}
