package com.systempro.uros.projekat.fizika;

import com.badlogic.gdx.physics.box2d.*;

import java.awt.event.ContainerListener;

public class CustomContactListener implements ContactListener {
    public Main app;

    public CustomContactListener(Main app){
        this.app=app;
    }

    @Override
    public void beginContact(Contact contact) {
        PhysicsBody a=(PhysicsBody) contact.getFixtureA().getUserData();
        PhysicsBody b=(PhysicsBody) contact.getFixtureB().getUserData();
//        if(!(a instanceof Player || b instanceof Player))return;
        if(!(
                (a instanceof Player && b instanceof Platform)||
                (a instanceof Platform && b instanceof Player)
        ))return;

        Player player=(Player) (a instanceof Player?a:b);
        Platform platform=(Platform)(a instanceof Platform?a:b);

        if(player.getY()-player.h>=platform.getY()+platform.h){
            player.isStanding=true;
        }

    }

    @Override
    public void endContact(Contact contact) {
        PhysicsBody a=(PhysicsBody) contact.getFixtureA().getUserData();
        PhysicsBody b=(PhysicsBody) contact.getFixtureB().getUserData();
        if(!(
            (a instanceof Player && b instanceof Platform)||
            (a instanceof Platform && b instanceof Player)
        ))return;

        Player player=(Player) (a instanceof Player?a:b);
        Platform platform=(Platform)(a instanceof Platform?a:b);

//        if(player.getY()-player.h>=platform.getY()+platform.h){
            player.isStanding=false;
//        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
