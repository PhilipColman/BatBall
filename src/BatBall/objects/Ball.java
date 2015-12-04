/**
 * BatBall is a basic bat ball game.
 * Copyright (C) 2015  philip
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package BatBall.objects;

import BatBall.input.KeyInput;
import BatBall.levels.BaseLevel;
import BatBall.main.Main;
import BatBall.main.Window;
import BatBall.states.End;
import BatBall.states.State;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Ball extends GameObject {

	private boolean docked;
	private final Bat bat;
	private BaseLevel level;

	public Ball(int x, int y, BaseLevel level) {
		super("ball", x, y, 32, 32, 4, Color.white, level);
		this.level = level;
		this.objects = level.getObjects();
		this.bat = level.getBat();
		this.docked = true;
	}

	@Override
	public void update() {
		if (docked)
			docked = !(KeyInput.getKeys()[KeyEvent.VK_SPACE] || KeyInput.getKeys()[KeyEvent.VK_LEFT] || KeyInput.getKeys()[KeyEvent.VK_RIGHT] || KeyInput.getKeys()[KeyEvent.VK_A] || KeyInput.getKeys()[KeyEvent.VK_D]);

		if (!docked) {
			if(speedX == 0 && speedY == 0) {
				speedX = random.nextBoolean() ? 1 : -1 * baseSpeed;
				speedY = random.nextBoolean() ? 1 : -1 * baseSpeed;
			}
			move();
		}
		walls();
		hitBat();
		hitBrick();
		remove();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, width, height);
		drawBounds(g);
	}

	private void hitBat() {
		if(getBounds().intersects(bat.getBounds())) {
			speedY *= -1;
		}
	}

	private void hitBrick() {
		for (int i = 0; i < objects.getSize(); i++) {
			GameObject temp = objects.getObject(i);
			if(temp.name.equals("brick")) {
				if(getBounds().intersects(temp.getBounds())) {
					Brick brick = (Brick) objects.getObject(i);
					if(brick.isHard()) {
						brick.setHard(false);
					} else {
						level.setBricksLeft(level.getBricksLeft() - 1);
						level.setScore(level.getScore() + 10);
						objects.removeObject(brick);
					}
					speedY *= -1;
					break;
				}
			}
		}
	}

	@Override
	protected void walls() {
		if(y <= 0)
			speedY *= -1;

		if(x <= 0 || x >= Window.getWindowWidth() - 32)
			speedX *= -1;
	}

	@Override
	protected void remove() {
		if(y > Window.getWindowHeight()) {
			if (level.getBalls() == 1 && level.getLifes() > 0) {
				level.setLifes(level.getLifes() - 1);
				level.setScore(level.getScore() > 50 ? (level.getScore() - 50) : 0);
				objects.addObject(new Ball(bat.getX() + bat.getWidth() / 2 - 16, bat.getY() - 32, level));
			} else if (level.getBalls() > 1)
				level.setBalls(level.getBalls() - 1);
			else {
				State.End.setData(false);
				Main.getInstance().setEnd(new End());
				Main.getInstance().setState(State.End);
			}
			objects.removeObject(this);
		}
	}
}
