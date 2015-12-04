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
import BatBall.main.Main;
import BatBall.main.Window;
import BatBall.states.menuItems.LevelButton;
import BatBall.states.menuItems.MenuButton;
import BatBall.states.menuItems.MenuItem;
import BatBall.states.menuItems.MenuLabel;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class End extends BaseState {

    private boolean win;

    private List<MenuItem> items = new ArrayList<MenuItem>();

    public End() {
        win = State.End.getData();
        int width = 400;
        int height = 64;
        int x = BatBall.main.Window.getWindowWidth() / 2 - (width / 2);

        items.add(new MenuLabel(x, 60, width, height, win ? "Level Complet" : "Game Over"));

        int y = Window.getWindowHeight() / 2 - 100;

        if (win) {

            items.add(new MenuLabel(x, y, width, height, "Your Score: " + Main.getInstance().getGameLevel().getScore()));
            items.add(new MenuLabel(x, y + 100, width, height, "Your Time: " + Main.getInstance().getGameLevel().getClock().substring(Main.getInstance().getGameLevel().getClock().length() - 4)));

            int level = Main.getInstance().getGameLevel().getLevel();

            items.add(new LevelButton(BaseLevel.levelExists(level + 1) ? x - width / 2 - 50 : x, y + 200, width, height, level, "Play Again"));
            if (BaseLevel.levelExists(level + 1))
                items.add(new LevelButton(x + width / 2 + 50, y + 200, width, height, level + 1, "Next Level"));
        } else {
            items.add(new LevelButton(x, y + 200, width, height, Main.getInstance().getGameLevel().getLevel(), "Try Again"));
        }
        items.add(new MenuButton(x - width / 2 - 50, y + 300, width, height, "Main Menu", State.Menu));
        items.add(new MenuButton(x + width / 2 + 50, y + 300, width, height, "Select Level", State.Level));

    }

    @Override
    public void update() {
        for (MenuItem item : items) {
            item.update();
        }
    }

    @Override
    public void render(Graphics g) {
        for (MenuItem item : items) {
            item.render(g);
        }
    }
}
