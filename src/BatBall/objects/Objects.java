/**
 * Created by Philip on 27/11/2015.
 * Copyright (C) 2015  Philip
 */

package BatBall.objects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Objects {

    private List<GameObject> objects = new ArrayList<GameObject>();

	public void update() {
		for (int i = 0; i < objects.size(); i++) {
            objects.get(i).update();
        }
	}

	public void render(Graphics g) {
        for (GameObject object : objects) {
            object.render(g);
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
