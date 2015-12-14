/**
 * Created by Dijkstra on 04/12/2015 at 23:19.
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
package BatBall.objects.upgrade;

import BatBall.levels.BaseLevel;
import BatBall.main.Window;
import BatBall.objects.Ball;
import BatBall.objects.Bat;
import BatBall.objects.GameObject;
import BatBall.objects.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Upgrade{

	private int x, y;
	private final int width, height;
	private final UpgradeID id;
	private long elasped;
	private long lenght;
	private Bat bat;
	private Effects effects;
	private boolean running;
	private boolean update;
	private Color color;
	private BaseLevel level;
	private Objects objects;
	private UpgradeManager upgradeManager;

	public Upgrade(UpgradeID id, int x, int y, BaseLevel level) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.width = 50;
		this.height = 50;
		this.bat = level.getBat();
		this.running = false;
		this.update = true;
		this.level = level;
		this.update = true;
		this.objects = level.getObjects();
		this.upgradeManager = level.getUpgradeManager();

		loadUpgrade();
	}

	private void loadUpgrade() {
		switch (id) {
			case Extend:
				this.lenght = 30;
				this.color = Color.red;
				this.effects = Effects.Bat;
				break;
			case Speed:
				this.lenght = 60;
				this.color = Color.yellow;
				this.effects = Effects.Ball;
				break;
			case Sticky:
				this.lenght = 20;
				this.color = Color.green;
				this.effects = Effects.Ball;
				break;
			case ExtraBalls:
				this.lenght = 1;
				this.color = Color.blue;
				this.effects = Effects.Ball;
				break;
		}
	}

	public void update() {
		if(!running) {
			y += 2;
			hitBat();
			if(y > Window.getWindowHeight())
				upgradeManager.removeUpgrade(this);
		} else {
			elasped++;
			if(elasped <= lenght * 60) {
				if(update)
					runEffect();
			} else {
				removeEffect();
				upgradeManager.removeRunningUpgrade(this);
			}
		}
	}


	public void render(Graphics g) {
		if(!running) {
			g.setColor(color);
			g.fillRect(x, y, width, height);
		}
	}

	private void hitBat() {
		if(hasHitBat()) {
			int i = upgradeManager.contains(this);
			if(i == -1) {
				upgradeManager.makeRunning(this);
				running = true;
			} else {
				upgradeManager.getRuuningUpgarde(i).resetClock();
				upgradeManager.removeUpgrade(this);
			}
			level.setScore(level.getScore() + 5);
		}
	}

	private void runEffect() {
		switch (id) {
			case Extend:
				bat.setWidth(300);
				bat.setX(bat.getX() - 50);
				update = false;
				break;
			case Speed:
				for (int i = 0; i < objects.getSize(); i++) {
					GameObject temp = objects.getObject(i);
					if(temp.getName().equals("ball")) {
						temp.setBaseSpeed(6);
						temp.updateSpeed();
						update = false;
					}
				}
				break;
			case Sticky:
				for (int i = 0; i < objects.getSize(); i++) {
					GameObject temp = objects.getObject(i);
					if(temp.getName().equals("ball")) {
						if(bat.colision(temp) == GameObject.Side.Top) {
							Ball ball = (Ball) temp;
							ball.dock();
						}
					}
				}
				break;
			case ExtraBalls:
				level.setBalls(level.getBalls() + 2);
				Ball ball = new Ball(bat.getX() + bat.getWidth() / 2 - 16, bat.getY() - 32, level);
				ball.unDock();
				objects.addObject(ball);
				ball = new Ball(bat.getX() + bat.getWidth() / 2 - 16, bat.getY() - 32, level);
				ball.unDock();
				objects.addObject(ball);
				update = false;
				break;
		}
	}

	private void removeEffect() {
		switch (id) {
			case Extend:
				bat.setWidth(bat.getLenght());
				bat.setX(bat.getX() + 50);
				break;
			case Speed:
				for (int i = 0; i < level.getObjects().getSize(); i++) {
					GameObject temp = level.getObjects().getObject(i);
					if(temp.getName().equals("ball")) {
						Ball ball = (Ball) temp;
						ball.setBaseSpeed(ball.getIntiSpeed());
						ball.updateSpeed();
					}
				}
				break;
		}
	}

	private boolean hasHitBat() {
		return new Rectangle(x,y,width,height).intersects(new Rectangle(bat.getX(), bat.getY(), bat.getWidth(), bat.getHeight()));
	}

	public UpgradeID getId() {
		return id;
	}

	public void resetClock() {
		this.elasped = 0;
	}

	public Effects getEffects() {
		return effects;
	}

	public enum UpgradeID {
		Extend,
		Speed,
		Sticky,
		ExtraBalls,
	}

	public enum Effects {
		Bat,
		Ball,
	}
}
