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
import BatBall.main.Window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import static BatBall.input.KeyInput.*;

public class Bat extends GameObject {

	public Bat(BaseLevel level) {
		super("bat", 0, 0, 20, 200, 4, Color.gray, level);
		x = Window.getWindowWidth() / 2 - width / 2;
		y = Window.getWindowHeight() - 100 - height;
	}

	@Override
	public void update() {

		speedX = (getKeys()[KeyEvent.VK_A] || getKeys()[KeyEvent.VK_LEFT]) ? -1 * baseSpeed :
				(getKeys()[KeyEvent.VK_D] || getKeys()[KeyEvent.VK_RIGHT]) ? baseSpeed : 0;
		move();
		x = clampToWindow(x, 32, Window.getWindowWidth() - width - 32);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.lightGray);
		g.drawRect(x, y, width, height);
		drawBounds(g);
	}


	@Override
	protected Rectangle getBounds() {
		return new Rectangle(x, y, width, 1);
	}
}
