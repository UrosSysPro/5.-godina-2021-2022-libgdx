package com.systempro.uros.projekat.quadtree;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class Main extends ApplicationAdapter {
    public QuadTree tree;
    public ShapeRenderer renderer;

    @Override
    public void create() {
        int w=Gdx.graphics.getWidth();
        int h=Gdx.graphics.getHeight();
        tree=new QuadTree(0,0,w,h);
        renderer=new ShapeRenderer();
        Random r=new Random();
        for(int i=0;i<100;i++){
            tree.add(r.nextInt(w),r.nextInt(h));
        }
    }

    @Override
    public void render() {
        ScreenUtils.clear(1,1,1,1);
        input();
        renderer.setColor(Color.BLACK);
        tree.draw(renderer);
    }
    public void input(){
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            int x=Gdx.input.getX();
            int y=Gdx.graphics.getHeight()-Gdx.input.getY();
            tree.add(new Point(x,y));
        }
    }

    @Override
    public void dispose() {

    }
}
