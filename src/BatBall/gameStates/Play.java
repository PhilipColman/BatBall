/**
 * Created by philip on 25/11/15.
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

package BatBall.gameStates;

import BatBall.main.Window;
import BatBall.objects.Ball;
import BatBall.objects.Bat;
import BatBall.objects.Brick;
import BatBall.objects.Objects;

import java.awt.Color;
import java.awt.Graphics;


public class Play {

	private final Bat bat;
	private Objects objects;

	public Play() {
		objects = new Objects();
		bat = new Bat(this);

		objects.addObject(bat);
		objects.addObject(new Ball(bat.getX() + bat.getWidth() / 2 - 16, bat.getY() - 32, this));

		Color[] colors = new Color[]{
				Color.red, //1
				Color.blue, //2
				Color.cyan, //3
				Color.DARK_GRAY, //4
				Color.magenta, //5
				Color.orange, //6
		};

		boolean[] hard = new boolean[]{
				true, //1
				true, //2
				true, //3
				false, //4
				false, //5
				false //6
		};
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 20; x++) {
				objects.addObject(new Brick(x * (Window.getWindowWidth()) / 20, y * 30, Window.getWindowWidth() / 20, 30, colors[y], hard[y], this));
			}
		}
	}

	public void update() {
		objects.update();
	}

	public void render(Graphics g) {
		objects.render(g);
	}

	public Bat getBat() {
		return bat;
	}

	public Objects getObjects() {
		return objects;
	}
}
