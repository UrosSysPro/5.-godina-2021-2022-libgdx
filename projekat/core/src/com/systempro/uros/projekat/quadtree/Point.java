package com.systempro.uros.projekat.quadtree;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Point {
    public int x,y;
    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }
    public void draw(ShapeRenderer renderer){
//        renderer.point(x,y,0);
        renderer.circle(x,y,3);
    }
}
