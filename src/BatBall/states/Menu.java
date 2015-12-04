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

import BatBall.states.menuItems.MenuButton;
import BatBall.states.menuItems.MenuItem;
import BatBall.states.menuItems.MenuLabel;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Menu extends BaseState {

    private List<MenuItem> items = new ArrayList<MenuItem>();

    public Menu() {
        String labels[] = new String[]{"Play", "Quit"};
        State states[] = new State[]{State.Level, State.Exiting};
        int width = 400;
        int height = 64;
        int x = BatBall.main.Window.getWindowWidth() / 2 - (width / 2);
        int y = BatBall.main.Window.getWindowHeight() / 2 - ((labels.length - 1) * (height + 20)) / 2;
        items.add(new MenuLabel(x, y - 50, width, height, "Menu"));

        for (int i = 0; i < labels.length; i++) {
            items.add(new MenuButton(x, y, width, height, labels[i], states[i]));
            y = y + height + 20;
        }
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
