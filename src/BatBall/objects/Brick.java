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

import BatBall.Main.Game;

import java.awt.Color;
import java.awt.Graphics;

public class Brick extends GameObject {

    private boolean hard;
    private final boolean upgrade;
    private Upgrade _upgrade;

    public Brick(int x, int y, int width, int height, Color color, boolean hard, Game game) {
        super("brick", x, y, height, width, 0, color, game);
        this.upgrade = random.nextBoolean();
        this.hard = hard;
        if (hasUpgrade()) {
            _upgrade = new Upgrade(Upgrade.Upgrades.values()[random.nextInt(Upgrade.Upgrades.values().length)], x + width / 2, y + height / 2, game);
        }
    }


    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        drawBounds(g);
    }

    public boolean isHard() {
        return hard;
    }

    public boolean hasUpgrade() {
        return upgrade;
    }

    public void setHard(boolean hard) {
        this.hard = hard;
    }

    public Upgrade getUpgrade() {
        return _upgrade;
    }

}
