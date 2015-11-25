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
import java.awt.Rectangle;
import java.util.LinkedList;

public class Bat extends GameObject {

    private LinkedList<Upgrade> currentUpgrades = new LinkedList<Upgrade>();

    public Bat(Game game) {
        super("bat", 0, 0, 20, 200, 4, Color.gray, game);
        x = Window.getWindowWidth() / 2 - width / 2;
        y = Window.getWindowHeight() - 100 - height;
    }

    @Override
    public void update() {
        move();
        x = clampToWindow(x, 32, Window.getWindowWidth() - width - 32);

        for (int i = 0; i < currentUpgrades.size(); i++) {
            currentUpgrades.get(i).update();
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.lightGray);
        g.drawRect(x, y, width, height);
        drawBounds(g);
    }

    private int clampToWindow(int var, int min, int max) {
        if (var >= max) return max;
        else if (var <= min) return min;
        else return var;
    }

    @Override
    protected Rectangle getBounds() {
        return new Rectangle(x, y, width, 1);
    }

    public void addUpgrade(Upgrade upgrade) {
        currentUpgrades.add(upgrade);
    }

    public int upgradeCount() {
        return currentUpgrades.size();
    }

    public void removeUpgrade(Upgrade upgrade) {
        currentUpgrades.remove(upgrade);
    }

    public Upgrade getUpgrade(int i) {
        return currentUpgrades.get(i);
    }

}
