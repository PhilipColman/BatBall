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

import BatBall.input.MouseInput;
import BatBall.levels.Level1;
import BatBall.main.Main;
import BatBall.states.State;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class LevelButton extends MenuButton {

    private final int level;
    private boolean unlocked;

    public LevelButton(int x, int y, int width, int height, int level, boolean unlocked) {
        super(x, y, width, height, ((Integer) level).toString(), null);
        this.level = level;
        this.unlocked = unlocked;
    }

    public LevelButton(int x, int y, int width, int height, int level, String text) {
        super(x, y, width, height, text, null);
        this.level = level;
        this.unlocked = true;
    }

    @Override
    public void update() {
        mouseOver = MouseInput.mouseOver(x, y, width, height);
        if (MouseInput.buttonClicked(x, y, width, height)) {
            loadLevel();
        }
        if (!unlocked) {
            if (Main.getInstance().getProgress() == level - 1)
                unlocked = true;
        }
    }

    @Override
    public void render(Graphics g) {
        if (unlocked) {
            g.setColor(mouseOver ? Color.white : Color.black);
            g.fillRect(x, y, width, height);
            g.setColor(mouseOver ? Color.black : Color.white);
            g.drawRect(x, y, width, height);
            g.setFont(new Font("arial", Font.PLAIN, 30));
            g.drawString(text, x - (g.getFontMetrics().stringWidth(text) / 2) + width / 2, y + height / 2 + 10);
        }
    }

    private void loadLevel() {
        switch (level) {
            case 1:
                Main.getInstance().setGameLevel(new Level1());
                Main.getInstance().setState(State.Game);
        }
    }
}
