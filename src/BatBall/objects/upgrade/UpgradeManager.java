/**
 * Created by Dijkstra on 05/12/2015 at 18:37.
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

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class UpgradeManager {

	private List<Upgrade> upgrades = new ArrayList<Upgrade>();
	private List<Upgrade> runngingUpgrades = new ArrayList<Upgrade>();

	public void update() {
		for (int i = 0; i < upgrades.size(); i++) {
			upgrades.get(i).update();
		}
		for (int i = 0; i < runngingUpgrades.size(); i++) {
			runngingUpgrades.get(i).update();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < upgrades.size(); i++) {
			upgrades.get(i).render(g);
		}
	}

	public void addUpgrade(Upgrade upgrade) {
		upgrades.add(upgrade);
	}

	public void removeUpgrade(Upgrade upgrade) {
		upgrades.remove(upgrade);
	}

	public int contains(Upgrade upgrade) {
		int i;
		int c = runngingUpgrades.size();
		if(runngingUpgrades.size() == 0)
			return -1;
		for (i = 0; i < c; i++) {
			if(runngingUpgrades.get(i).getId().equals(upgrade.getId()))
				return i;
			c = runngingUpgrades.size();
		}
		return -1;
	}

	public void makeRunning(Upgrade upgrade) {
		upgrades.remove(upgrade);
		runngingUpgrades.add(upgrade);
	}

	public void removeRunningUpgrade(Upgrade upgrade) {
		runngingUpgrades.remove(upgrade);
	}

	public Upgrade getRuuningUpgarde(int i) {
		return runngingUpgrades.get(i);
	}

	public void removeBallUpgrades() {
		for (int i = 0; i < runngingUpgrades.size(); i++) {
			if(runngingUpgrades.get(i).getEffects().equals(Upgrade.Effects.Ball))
				runngingUpgrades.remove(i);
		}
	}
}
