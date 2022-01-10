package com.systempro.uros.projekat.shaderPaint;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Main extends ApplicationAdapter {

    public WritableImage paint;
    public int w,h,mx=0,my=0;
    public OrthographicCamera camera;
    @Override
    public void create() {
        ShaderProgram.pedantic=false;
        WritableImage.init();
        w= Gdx.graphics.getWidth();
        h= Gdx.graphics.getHeight();
        paint=new WritableImage(w,h);
        camera=new OrthographicCamera();
        camera.position.set(w/2,h/2,0);
        camera.viewportWidth=w;
        camera.viewportHeight=h;
        camera.update();
    }

    @Override
    public void render() {
        ScreenUtils.clear(1,1,1,1);
        int x=Gdx.input.getX();
        int y=Gdx.input.getY();
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
//            paint.drawCircle(x,y,10, Color.CYAN);
            paint.drawLine(mx,my,x,y,5,Color.CORAL);
        }
        mx=x;
        my=y;

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            paint.applyInvert();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
            paint.applyInvert(mx-50,my-50,100,100);
        }

        paint.draw(camera);
    }

}
