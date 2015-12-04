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
package BatBall.states;

import BatBall.levels.BaseLevel;
import BatBall.main.Window;
import BatBall.states.menuItems.LevelButton;

import java.awt.Graphics;

public class Level extends BaseState {

    private LevelButton[] levelButtons;

    public Level() {
        levelButtons = new LevelButton[BaseLevel.getLevels()];


        int width = (Window.getWindowWidth() - (11 * 20)) / 10;
        final int xStart = 20;
        int x = xStart;
        int y = Window.getWindowHeight() / 2 - (4 * (width + 20)) / 2;

        for (int i = 0; i < levelButtons.length; i++) {
            levelButtons[i] = new LevelButton(x, y, width, width, (i + 1), i == 0);
            x += (width + 20);
            if ((i + 1) % 10 == 0) {
                y += (width + 20);
                x = xStart;
            }
        }
    }


    @Override
    public void update() {
        for (LevelButton levelButton : levelButtons) {
            levelButton.update();
        }
    }

    @Override
    public void render(Graphics g) {
        for (LevelButton levelButton : levelButtons) {
            levelButton.render(g);
        }
    }


}
