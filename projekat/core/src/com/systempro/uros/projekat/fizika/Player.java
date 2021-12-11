package com.systempro.uros.projekat.fizika;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends PhysicsBody {

    public Color color;
    public boolean keyUp,keyDown,keyLeft,keyRight;
    public static float acceleration=1f;
    public static float maxSpeed=4;


    public Player(World world, float x, float y, float w, float h, Color color){
        super(world,x,y,w,h);
        this.color=color;
        body.setFixedRotation(true);
        keyUp=false;
        keyDown=false;
        keyLeft=false;
        keyRight=false;
    }

    public void update(){
        Vector2 v=body.getLinearVelocity();
        if(keyLeft){
            v.x-=acceleration;
            if(v.x<-maxSpeed)v.x=-maxSpeed;
        }
        if(keyRight){
            v.x+=acceleration;
            if(v.x>maxSpeed)v.x=maxSpeed;
        }
        if(keyUp){
            v.y=7;
        }

        body.setLinearVelocity(v);
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        float x=body.getPosition().x*scale;
        float y=body.getPosition().y*scale;
        float a=body.getAngle()/(float)Math.PI*180;

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        renderer.rect(x-w,y-h,w,h,2*w,2*h,1,1,a);
        renderer.end();
    }
}
