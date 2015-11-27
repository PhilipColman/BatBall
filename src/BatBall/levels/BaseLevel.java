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

package BatBall.levels;

import BatBall.main.Game;
import BatBall.objects.Brick;
import BatBall.objects.Objects;

public abstract class BaseLevel {

    protected final String name;
    protected final int upgradeWegith;
    protected Brick bricks[];
    protected Objects objects;
    protected int startTime;

    public BaseLevel(String name, Brick[] bricks, int upgradeWegith, Game game) {
        this.name = name;
        this.bricks = bricks;
        this.upgradeWegith = upgradeWegith;
        //this.objects = game.getObjects();
    }

    protected void loadLevel() {
        for (Brick brick : bricks) {
            objects.addObject(brick);
        }
    }

    protected void unloadLevel() {

    }
}
