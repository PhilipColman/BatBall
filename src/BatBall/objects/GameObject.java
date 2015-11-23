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

import BatBall.Main.Game;
import BatBall.Main.Window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public abstract class GameObject {

    protected final String name;
    protected int x,y,width,height, speedX, speedY;
    protected final Random random;
    protected int baseSpeed;
    protected Color color;
    protected final Game game;
    protected final Objects objects;

    public GameObject(String name, int x, int y, int height, int width, int baseSpeed, Color color, Game game) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.baseSpeed = baseSpeed;
        this.color = color;
        this.game = game;
        this.objects = game.getObjects();
        this.random = new Random();
    }

    public abstract void update();
    public abstract void render(Graphics g);

    protected Rectangle getBounds(){
        return new Rectangle(x,y,width,height);
    }
    protected void move(){
        x += speedX;
        y += speedY;
    }
    protected void walls(){
        if(y<= 0|| y >= Window.getWindowHeight()-32)
            speedY *= -1;

        if(x<= 0|| x >= Window.getWindowWidth()-32)
            speedX *= -1;
    }
    protected void drawBounds(Graphics g){
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.white);
        graphics2D.draw(getBounds());
    }
    protected void remove(){
        if(y>Window.getWindowHeight()){
            objects.removeObject(this);
        }
    }

    public String getName() {
        return name;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public void updateSpeed() {
        if(speedX !=0&&speedY!=0){
            speedX = (speedX/Math.abs(speedX))*baseSpeed;
            speedY = (speedY/Math.abs(speedY))*baseSpeed;
        }
    }

}
