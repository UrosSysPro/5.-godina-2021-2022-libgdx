package com.systempro.uros.projekat.fizika;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Platform extends PhysicsBody {
    public Platform(World world, float x, float y, float w, float h,float a){
        super(world, x, y, w, h,a);

        body.setType(BodyDef.BodyType.StaticBody);
    }
}
