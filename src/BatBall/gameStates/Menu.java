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


import BatBall.main.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Menu {

	public void render(Graphics g) {
		String labels[] = new String[]{"Play", "Quit"};

		int width = 400;
		int height = 64;
		int x = BatBall.main.Window.getWindowWidth() / 2 - (width / 2);
		int y = BatBall.main.Window.getWindowHeight() / 2 - ((labels.length - 1) * (height + 20)) / 2;

		Font font = new Font("arial", Font.PLAIN, 50);
		Font font2 = new Font("arial", Font.PLAIN, 30);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Menu", x - g.getFontMetrics().stringWidth("Menu") / 2 + (width / 2), y - 50);

		g.setFont(font2);

		for (int i = 0; i < labels.length; i++) {
			g.setColor(Color.black);
			g.fillRect(x, y, width, height);
			g.setColor(Color.white);
			g.drawRect(x, y, width, height);
			g.drawString(labels[i], x - (g.getFontMetrics().stringWidth(labels[i]) / 2) + width / 2, y + height / 2 + 10);
			if(new Rectangle(x, y, width, height).intersects(new Rectangle(Game.getInstance().getMouseInput().getX(),Game.getInstance().getMouseInput().getY(),1,1))) {

				if(labels[i].equals("Play")){
					Game.getInstance().setPlay(new Play());
					Game.getInstance().getKeyInput().linkBat();
					Game.getInstance().setState(GameState.Game);
				}

				else if(labels[i].equals("Quit"))
					System.exit(0);

				System.out.println(labels[i]);
				Game.getInstance().getMouseInput().setX(0);
				Game.getInstance().getMouseInput().setY(0);
			}
			y = y + height + 20;
		}
	}
}
