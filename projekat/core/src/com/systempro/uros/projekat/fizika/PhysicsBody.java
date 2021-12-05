package com.systempro.uros.projekat.fizika;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.*;

public class PhysicsBody {
    public Body body;
    public Fixture fixture;
    public float w,h;
    public static float scale=20;

    public PhysicsBody(World world, float x,float y,float w,float h){
        this.w=w;
        this.h=h;

        BodyDef bodyDef=new BodyDef();
        bodyDef.position.x= x/scale;
        bodyDef.position.y= y/scale;
        bodyDef.type= BodyDef.BodyType.DynamicBody;

        FixtureDef fixtureDef=new FixtureDef();
        fixtureDef.density=1;
        fixtureDef.friction=0.7f;
        fixtureDef.restitution=0f;

        PolygonShape shape=new PolygonShape();
        shape.setAsBox(w/scale,h/scale);
        fixtureDef.shape=shape;

        body=world.createBody(bodyDef);
        fixture=body.createFixture(fixtureDef);
    }
    public void draw(ShapeRenderer renderer){
        float x=body.getPosition().x*scale;
        float y=body.getPosition().y*scale;
        float a=body.getAngle()/(float)Math.PI*180;

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(0,0,0,1);
        renderer.rect(x-w,y-h,w,h,2*w,2*h,1,1,a);
        renderer.end();
    }
}
