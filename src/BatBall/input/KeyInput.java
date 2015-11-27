/**
 * Created by philip on 23/11/15.
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

package BatBall.input;

import BatBall.gameStates.GameState;
import BatBall.main.Game;
import BatBall.objects.Ball;
import BatBall.objects.Bat;
import BatBall.objects.GameObject;
import BatBall.objects.Objects;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private boolean[] keyDown = new boolean[]{false, false, false, false};
	private Objects objects;
	private Bat bat;

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
			case KeyEvent.VK_ESCAPE:
				if(Game.getInstance().getState().equals(GameState.Menu))
					System.exit(1);
				else
					Game.getInstance().setState(GameState.Menu);
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				bat.setSpeedX(-bat.getBaseSpeed());
				keyDown[0] = true;
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				bat.setSpeedX(bat.getBaseSpeed());
				keyDown[1] = true;
				break;
		}

		for (int i = 0; i < objects.getSize(); i++) {
			GameObject temp = objects.getObject(i);
			if(temp.getName().equals("ball")) {
				if(key == KeyEvent.VK_SPACE || key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
					Ball ball = (Ball) objects.getObject(i);
					ball.setDocked(false);
				}
			}
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				keyDown[0] = false;
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				keyDown[1] = false;
				break;
		}


		if(!keyDown[0] && !keyDown[1]) bat.setSpeedX(0);

		if(!keyDown[0] && keyDown[1]) bat.setSpeedX(bat.getBaseSpeed());
		if(keyDown[0] && !keyDown[1]) bat.setSpeedX(-bat.getBaseSpeed());
	}

	public void linkBat(){
		objects = Game.getInstance().getPlay().getObjects();
		for(int i = 0; i<objects.getSize(); i++){
			if(objects.getObject(i).getName().equals("bat")){
				bat = (Bat)objects.getObject(i);
				break;
			}
		}
	}

}
