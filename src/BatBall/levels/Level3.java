/**
 * Created by Dijkstra on 06/12/2015 at 00:10.
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
package BatBall.levels;

import BatBall.main.Window;
import BatBall.objects.Brick;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Level3 extends BaseLevel {

	public Level3() {
		super(3, 3);

		Color[] colors = new Color[]{
				Color.black,
				Color.red, //1
				Color.blue, //2
				Color.cyan, //3
				Color.DARK_GRAY, //4
				Color.orange,
				Color.WHITE,
				Color.magenta,
				Color.green,
				Color.pink
		};

		boolean[] hard = new boolean[]{
				true,
				true,
				true,
				false,
				false,
				false,
				false,
				false,
				false,
				false
		};

		int brick = 10;
		int brickHeight = 40;
		int topOffset = 50;

		int width = Window.getWindowWidth() / brick;

		List<Brick> bricks = new ArrayList<Brick>();

		for (int y = 0; y < colors.length; y++) {
			for (int x = 0; x < brick; x++) {
					bricks.add(new Brick(x * width, y * brickHeight + topOffset, width, brickHeight, colors[y], hard[y], this));
			}
			brick --;
		}

		this.bricks = bricks.toArray(new Brick[bricks.size()]);
		loadLevel();
	}
}
