package com.systempro.uros.projekat.fizika;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {

    public World world;
    ShapeRenderer renderer;

    public ArrayList<PhysicsBody> list;

    @Override
    public void create() {
        PhysicsBody.scale=40;
        renderer=new ShapeRenderer();

        world=new World(new Vector2(0,-10),false);
        list=new ArrayList<PhysicsBody>();
        float w=Gdx.graphics.getWidth();
        float h=Gdx.graphics.getHeight();

        list.add(new PhysicsBody(world,w/2,10,w/2,10));
        list.get(0).body.setType(BodyDef.BodyType.StaticBody);
    }

    @Override
    public void render() {
        ScreenUtils.clear(1,1,1,1);
        world.step(1f/60f,10,10);
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            float x=Gdx.input.getX();
            float y=Gdx.graphics.getHeight()-Gdx.input.getY();
            list.add(new PhysicsBody(world,x,y,5,5));
        }

        for(int i=0;i<list.size();i++){
            list.get(i).draw(renderer);
        }
    }
}
