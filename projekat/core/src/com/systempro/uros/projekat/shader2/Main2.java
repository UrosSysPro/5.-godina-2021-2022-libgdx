package com.systempro.uros.projekat.shader2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main2 extends ApplicationAdapter {
    public MandelbrotSet set;
    public SpriteBatch batch;

    public float vx,vy,vw,vh;
    @Override
    public void create() {
        System.out.println(MandelbrotSet.init());
        Texture img=new Texture(Gdx.files.internal("badlogic.jpg"));
        int w=Gdx.graphics.getWidth();
        int h=Gdx.graphics.getHeight();
        set=new MandelbrotSet(img,w/2,h/2,w,h);
        vx=0;
        vy=0;
        vw=2;
        vh=1.5f;
        set.setVisibility(vx,vy,vw,vh);
        batch=new SpriteBatch();
    }

    @Override
    public void render() {
        ScreenUtils.clear(1,1,1,1);
        float speed=60;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){vx-=vw/speed;}
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){vx+=vw/speed;}
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){vy-=vh/speed;}
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){vy+=vh/speed;}
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            vw*=0.9;
            vh*=0.9;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)){
            vw*=1.1;
            vh*=1.1;
        }
        set.setVisibility(vx,vy,vw,vh);
        set.draw(batch);
    }
}
