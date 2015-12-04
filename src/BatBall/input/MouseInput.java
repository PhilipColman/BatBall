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

package BatBall.input;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseInput implements MouseListener, MouseMotionListener, MouseWheelListener {

	private static int mX = 0, mY = 0;
	private static int x = 0, y = 0;

	@Override
	public void mouseClicked(MouseEvent e) {
		mX = e.getX();
		mY = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	public static boolean buttonClicked(int x, int y, int width, int height) {
		if (new Rectangle(x, y, width, height).intersects(new Rectangle(mX, mY, 1, 1))) {
			mX = 0;
			mY = 0;
			return true;
		}
		return false;
	}

	public static boolean mouseOver(int x, int y, int width, int height) {
		return new Rectangle(x, y, width, height).intersects(new Rectangle(MouseInput.x, MouseInput.y, 1, 1));
	}

	public static int getmX() {
		return mX;
	}

	public static int getmY() {
		return mY;
	}
}
