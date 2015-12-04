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

package BatBall.states.menuItems;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MenuLabel extends MenuItem {
    public MenuLabel(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setFont(new Font("arial", Font.PLAIN, 50));
        g.setColor(Color.white);
        g.drawString(text, x - g.getFontMetrics().stringWidth(text) / 2 + (width / 2), y);
    }
}
