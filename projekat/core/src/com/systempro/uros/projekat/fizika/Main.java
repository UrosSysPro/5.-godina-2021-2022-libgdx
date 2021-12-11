package com.systempro.uros.projekat.fizika;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {

    public World world;
    ShapeRenderer renderer;

    public ArrayList<PhysicsBody> list;
    public Player player;
    public OrthographicCamera camera;

    @Override
    public void create() {
        PhysicsBody.scale=40;
        renderer=new ShapeRenderer();

        world=new World(new Vector2(0,-10),false);
        list=new ArrayList<PhysicsBody>();
        float w=Gdx.graphics.getWidth();
        float h=Gdx.graphics.getHeight();

        camera=new OrthographicCamera();
        camera.viewportWidth=Gdx.graphics.getWidth();
        camera.viewportHeight=Gdx.graphics.getHeight();
        camera.position.x=0;
        camera.position.y=0;

        list.add(new Platform(world,w/2,10,w/2,10,0));
        list.add(new Platform(world,100,100,200,10,0));
        list.add(new Platform(world,0,200,200,10,0));
        float a=(float)( 5f/180f*Math.PI);
        list.add(new Platform(world,500,300,300,10,a));

        player=new Player(world,w/2,30,20,20, Color.CORAL);
        list.add(player);
    }

    @Override
    public void render() {
        ScreenUtils.clear(1,1,1,1);
        input();
        update();
        draw();
    }


    public void input(){
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            float x=Gdx.input.getX();
            float y=Gdx.graphics.getHeight()-Gdx.input.getY();
            list.add(new PhysicsBody(world,x,y,5,5));
        }
        player.keyRight=Gdx.input.isKeyPressed(Input.Keys.D);
        player.keyLeft=Gdx.input.isKeyPressed(Input.Keys.A);
        player.keyUp=Gdx.input.isKeyPressed(Input.Keys.W);
        player.keyDown=Gdx.input.isKeyPressed(Input.Keys.S);
    }

    public void update(){
        player.update();
        world.step(1f/60f,10,10);
        camera.position.x=player.getX();
        camera.position.y=player.getY();
        camera.update();
    }

    public void draw(){
        renderer.setProjectionMatrix(camera.combined);
        for(int i=0;i<list.size();i++){
            list.get(i).draw(renderer);
        }
    }
}
