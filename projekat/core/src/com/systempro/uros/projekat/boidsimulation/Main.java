package com.systempro.uros.projekat.boidsimulation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class Main extends ApplicationAdapter {

    public ShapeRenderer renderer;
    public BoidSimulation sim;
    public static int w,h;
    @Override
    public void create() {
        renderer=new ShapeRenderer();
        sim=new BoidSimulation();
        w=Gdx.graphics.getWidth();
        h=Gdx.graphics.getHeight();
        Random random=new Random();
        for(int i=0;i<100;i++){
            float x=random.nextInt(w);
            float y=random.nextInt(h);
            double r=random.nextFloat()*Math.PI*2;
            float vx=(float) Math.cos(r)*2;
            float vy=(float)Math.sin(r)*2;
            sim.add(x,y,vx,vy);
        }
    }

    @Override
    public void render() {
        ScreenUtils.clear(1,1,1,1);
        sim.update();
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(Color.BLACK);
        sim.draw(renderer);

        renderer.end();
    }
}
