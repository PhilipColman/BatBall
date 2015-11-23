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

package BatBall.Main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Toolkit;

public class Window extends JFrame {

    private static Toolkit toolkit = Toolkit.getDefaultToolkit();

    public Window(Game game) {
        super("Bat and Ball");
        //this.setUndecorated(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.add(game);
        this.setVisible(true);
    }

    public static int getWindowWidth() {
        return (int) toolkit.getScreenSize().getWidth();
    }

    public static int getWindowHeight() {
        return (int) toolkit.getScreenSize().getHeight();
    }
}
