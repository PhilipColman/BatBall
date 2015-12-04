/**
 * BatBall is a basic bat ball game.
 * Copyright (C) 2015  philip
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package BatBall.levels;

import BatBall.main.Main;
import BatBall.main.Window;
import BatBall.objects.Ball;
import BatBall.objects.Bat;
import BatBall.objects.Brick;
import BatBall.objects.Objects;
import BatBall.states.End;
import BatBall.states.State;

import java.awt.Font;
import java.awt.Graphics;

public abstract class BaseLevel {

    private static final int levels = 1;

    protected Brick[] bricks;
    private final int upgradeWeight;
    private Objects objects;
    private Bat bat;
    private int bricksLeft;
    private int score = 0;
    private long time = 0;
    protected String Clock = "";
    private final int level;
    private int lifes = 2;
    private int balls = 1;


    public BaseLevel(int upgradeWeight, int level) {
        this.level = level;
        this.upgradeWeight = upgradeWeight;
        this.objects = new Objects();
        this.bat = new Bat(this);
        objects.addObject(bat);
    }

    protected void loadLevel() {
        this.bricksLeft = this.bricks.length;
        for (int i = 0; i < bricksLeft; i++) {
            objects.addObject(bricks[i]);
        }
        objects.addObject((new Ball(bat.getX() + bat.getWidth() / 2 - 16, bat.getY() - 32, this)));
    }

    public void update() {
        objects.update();
        if (bricksLeft == 0) {
            State.End.setData(true);
            Main.getInstance().setEnd(new End());
            Main.getInstance().setProgress(level);
            Main.getInstance().setState(State.End);
        }
        time++;
        long clock = (time) / 60;
        String Time = clock / 60 + ":" + (clock % 60 < 10 ? ("0" + clock % 60) : clock % 60);
        Clock = String.format("Lifes:  %3$d         Score:  %1$d       Time:  %2$s", score, Time, lifes + 1);
    }

    public void render(Graphics g) {
        objects.render(g);
        g.setFont(new Font("arial", Font.PLAIN, 30));
        g.drawString(Clock, Window.getWindowWidth() - 10 - g.getFontMetrics().stringWidth(Clock), 35);
    }

    public Bat getBat() {
        return bat;
    }

    public int getUpgradeWeight() {
        return upgradeWeight;
    }

    public Objects getObjects() {
        return objects;
    }

    public int getBricksLeft() {
        return bricksLeft;
    }

    public void setBricksLeft(int bricksLeft) {
        this.bricksLeft = bricksLeft;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getClock() {
        return Clock;
    }

    public int getLevel() {
        return level;
    }

    public static boolean levelExists(int i) {
        return i <= levels;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public static int getLevels() {
        return levels;
    }
}
