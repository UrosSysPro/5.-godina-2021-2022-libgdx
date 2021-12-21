package com.systempro.uros.projekat.shader2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.plaf.TextUI;

public class Main extends ApplicationAdapter {
    public SpriteBatch batch;
    public Texture img;
    public WritableImage writableImage;
    public OrthographicCamera camera;
    @Override
    public void create() {
        ShaderProgram.pedantic=false;
        WritableImage.init();
        img=new Texture(Gdx.files.internal("badlogic.jpg"));
        batch=new SpriteBatch();
        camera=new OrthographicCamera();
        int w=Gdx.graphics.getWidth();
        int h=Gdx.graphics.getHeight();
        camera.position.set(w/2f,h/2f,0);
        camera.viewportWidth=w;
        camera.viewportHeight=h;
        camera.update();
        writableImage=new WritableImage(w,h);

        writableImage.applyTexutre(img,batch,true);
    }

    @Override
    public void render() {
        ScreenUtils.clear(1,1,1,1);
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)){
            writableImage.applyBlur(batch,30);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            writableImage.applyBlur(batch,6);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
            writableImage.applyGrayScale(batch);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
            writableImage.applySwapRedGreen(batch);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
            writableImage.applyTexutre(img,batch,true);
        }
        camera.update();
//        drawImg();
        drawWritableImg();
    }


    public void drawImg(){
        batch.begin();
        batch.setShader(null);
        batch.setProjectionMatrix(camera.combined);
        batch.draw(img,img.getWidth(),0);
        batch.end();
    }
    public void drawWritableImg(){
        batch.setShader(null);
        batch.setProjectionMatrix(camera.combined);
        writableImage.draw(batch);
    }
}
