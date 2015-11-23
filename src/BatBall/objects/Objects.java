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

package BatBall.objects;

import java.awt.Graphics;
import java.util.LinkedList;

public class Objects {

    private LinkedList<GameObject> objects = new LinkedList<GameObject>();

    public Objects() {

    }

    public void update() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i);
            temp.update();
        }
    }

    public void render(Graphics g) {
        for (GameObject temp : objects) {
            temp.render(g);
        }
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public int getSize() {
        return objects.size();
    }

    public void removeObject(GameObject object) {
        objects.remove(object);
    }

    public GameObject getObject(int i) {
        return objects.get(i);
    }

}
