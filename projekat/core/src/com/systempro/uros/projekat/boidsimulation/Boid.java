package com.systempro.uros.projekat.boidsimulation;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class Boid {
    public float x,y,vx,vy;

    public Boid(float x,float y,float vx,float vy){
        this.x=x;
        this.y=y;
        this.vx=vx;
        this.vy=vy;
    }
    public void update(ArrayList<Boid> list){
        separation(list);
        alignment(list);
        cohesion(list);
        x+=vx;
        y+=vy;
        if(x>Main.w)x=0;
        if(y>Main.h)y=0;
        if(x<0)x=Main.w-1;
        if(y<0)y=Main.h-1;
    }
    public void separation(ArrayList<Boid> list){

    }
    public void alignment(ArrayList<Boid> list){

    }
    public void cohesion(ArrayList<Boid> list){

    }
    public void draw(ShapeRenderer renderer){
        renderer.circle(x,y,3);
    }
}
