package com.systempro.uros.projekat.shader2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    public SpriteBatch batch;
    public Texture img;
    public ShaderProgram shader;
    public float[] imgSize;
    @Override
    public void create() {
        batch=new SpriteBatch();
        img=new Texture(Gdx.files.internal("badlogic.jpg"));
        String vertex=Gdx.files.internal("vertex.vsh").readString();
        String fragment=Gdx.files.internal("fragment.fsh").readString();
        shader=new ShaderProgram(vertex,fragment);
        ShaderProgram.pedantic=false;
        System.out.println(shader.isCompiled());

        imgSize=new float[]{
                img.getWidth(),
                img.getHeight()
        };
    }

    @Override
    public void render() {
        ScreenUtils.clear(1,1,1,1);

        int x=Gdx.input.getX();
        int y=Gdx.graphics.getHeight()-Gdx.input.getY();
        int w=img.getWidth();
        int h=img.getHeight();
        batch.begin();
        batch.setShader(shader);
        shader.setUniform2fv("imgSize",imgSize,0,2);
        batch.draw(img,x-w/2,y-h/2,w/2,h/2,w,h,1,1,45,0,0,w,h,false,false);
        batch.end();
    }
}
