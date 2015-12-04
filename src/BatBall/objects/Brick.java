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

package BatBall.objects;


import BatBall.levels.BaseLevel;

import java.awt.Color;
import java.awt.Graphics;

public class Brick extends GameObject {

	private boolean hard;

	public Brick(int x, int y, int width, int height, Color color, boolean hard, BaseLevel level) {
		super("brick", x, y, height, width, 0, color, level);
		this.hard = hard;
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

	public void setHard(boolean hard) {
		this.hard = hard;
	}
}
