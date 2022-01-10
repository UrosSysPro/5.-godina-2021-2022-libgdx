package com.systempro.uros.projekat.shaderPaint;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class WritableImage {
    public FrameBuffer mainBuffer,helperBuffer;
    public OrthographicCamera camera;
    public int x,y,w,h,a;

    public static SpriteBatch batch;
    public static ShapeRenderer renderer;
    public static ShaderProgram invertShader;

    public static void init(){
        batch=new SpriteBatch();
        renderer=new ShapeRenderer();
        String vertex= Gdx.files.internal("vertex.vsh").readString();
        String invert= Gdx.files.internal("shaderPaint/invert.fsh").readString();

        invertShader=new ShaderProgram(vertex,invert);
        System.out.println(invertShader.getLog());
    }

    public  WritableImage(int w,int h){
        mainBuffer=new FrameBuffer(Pixmap.Format.RGBA8888,w,h,false);
        helperBuffer=new FrameBuffer(Pixmap.Format.RGBA8888,w,h,false);
        camera=new OrthographicCamera();
        x=0;
        y=0;
        this.w=w;
        this.h=h;
        a=0;
        camera.viewportWidth=w;
        camera.viewportHeight=h;
        camera.position.set(w/2,h/2,0);
        camera.update();
        mainBuffer.begin();
        ScreenUtils.clear(1,1,1,1);
        mainBuffer.end();
    }

    public void draw(OrthographicCamera camera){
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        Texture t=mainBuffer.getColorBufferTexture();
        batch.draw(t,x,y,w,h);
        batch.end();
    }

    public void drawCircle(int x, int y, int r, Color color){
        mainBuffer.begin();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setProjectionMatrix(camera.combined);
        renderer.setColor(color);
        renderer.circle(x,y,r);
        renderer.end();
        mainBuffer.end();
    }
    public void drawLine(int x1,int y1,int x2,int y2,float width,Color color){
        mainBuffer.begin();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setProjectionMatrix(camera.combined);
        renderer.setColor(color);
        renderer.rectLine(x1,y1,x2,y2,width);
        renderer.end();
        mainBuffer.end();
    }
    public void applyInvert(){
        applyInvert(0,0,w,h);
    }
    public void applyInvert(int x,int y,int w,int h){
        Texture t=mainBuffer.getColorBufferTexture();
        helperBuffer.begin();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.setShader(invertShader);
        invertShader.setUniform2fv("start",new float[]{x,y},0,2);
        invertShader.setUniform2fv("end",new float[]{x+w,y+h},0,2);
        invertShader.setUniform2fv("res",new float[]{this.w,this.h},0,2);
        batch.draw(t,0,this.h,this.w,-this.h);
//        batch.draw(t,0,0);
        batch.setShader(null);
        batch.end();
        helperBuffer.end();

        FrameBuffer fbo=mainBuffer;
        mainBuffer=helperBuffer;
        helperBuffer=fbo;
    }

}
