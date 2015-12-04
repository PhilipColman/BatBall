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
package BatBall.levels;

import BatBall.main.Window;
import BatBall.objects.Brick;

import java.awt.Color;

public class Level1 extends BaseLevel {

    public Level1() {
        super(0, 1);

        Color[] colors = new Color[]{
                Color.red, //1
                Color.blue, //2
                Color.cyan, //3
                Color.DARK_GRAY, //4
        };

        int brick = 10;
        int brickHeight = 40;
        int topOffset = 50;
        Brick bricks[] = new Brick[colors.length * brick];

        for (int y = 0; y < colors.length; y++) {
            for (int x = 0; x < brick; x++) {
                bricks[x + y * brick] = new Brick(x * (Window.getWindowWidth()) / brick, y * brickHeight + topOffset, Window.getWindowWidth() / brick, brickHeight, colors[y], false, this);
            }
        }

        this.bricks = bricks;
        loadLevel();
    }
}
