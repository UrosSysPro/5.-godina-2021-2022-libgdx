package com.systempro.uros.projekat.shadersintro;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    ShaderProgram shader;
    OrthographicCamera camera;
    @Override
    public void create() {
        batch=new SpriteBatch();
        img=new Texture(Gdx.files.internal("badlogic.jpg"));
        shader=new ShaderProgram(
                Gdx.files.internal("vertex.vsh"),
                Gdx.files.internal("pixel.fsh"));
        System.out.println(shader.isCompiled());
        ShaderProgram.pedantic=false;
        camera=new OrthographicCamera();
        camera.viewportWidth=Gdx.graphics.getWidth();
        camera.viewportHeight=Gdx.graphics.getHeight();
        camera.position.x=0;
        camera.position.y=0;
        camera.update();
    }

    @Override
    public void render() {
        ScreenUtils.clear(1,1,1,1);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.setShader(shader);
        batch.draw(img,0,0);
        batch.end();
    }
}
