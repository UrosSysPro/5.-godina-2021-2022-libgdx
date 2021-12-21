package com.systempro.uros.projekat.shader2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;


public class WritableImage {
    public static ShaderProgram grayScaleProgram;
    public static ShaderProgram blurProgram;
    public static ShaderProgram swapRedGreenProgram;

    public static void init(){
        String grayScale=    Gdx.files.internal("WritableImageShaders/grayScale.fsh").readString();
        String blur=         Gdx.files.internal("WritableImageShaders/blur.fsh").readString();
        String swapRedGreen= Gdx.files.internal("WritableImageShaders/swapRedGreen.fsh").readString();
        String vertex=       Gdx.files.internal("vertex.vsh").readString();

        grayScaleProgram=new ShaderProgram(vertex,grayScale);
        blurProgram=new ShaderProgram(vertex,blur);
        swapRedGreenProgram=new ShaderProgram(vertex,swapRedGreen);
        System.out.println(grayScaleProgram.getLog());
        System.out.println(blurProgram.getLog());
        System.out.println(swapRedGreenProgram.getLog());
    }

    public FrameBuffer mainBuffer,helperBuffer;
    public float x,y,a;
    public int w,h;
    public OrthographicCamera camera;

    public WritableImage(float x,float y,int w,int h,float a){
        mainBuffer=new FrameBuffer(Pixmap.Format.RGBA8888,w,h,false);
        helperBuffer=new FrameBuffer(Pixmap.Format.RGBA8888,w,h,false);
        camera=new OrthographicCamera();
        camera.position.x=w/2;
        camera.position.y=h/2;
        camera.viewportWidth=w;
        camera.viewportHeight=h;
//        camera.rotate(180);

        camera.update();
        this.x=x;
        this.y=y;
        this.a=a;
        this.w=w;
        this.h=h;
    }

    public WritableImage(int w,int h){
        this(0,0,w,h,0);
    }

    public void draw(SpriteBatch batch){
        batch.begin();
        Texture t=mainBuffer.getColorBufferTexture();
        batch.draw(t,x,y,w/2,h/2,w,h,1,1,a,0,0,w,h,false,false);
        batch.end();
    }

    public void applyTexutre(Texture t,SpriteBatch batch,boolean resize){
        mainBuffer.begin();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.setShader(null);
        if(resize){
            batch.draw(t,0,0,0,0,w,h,1,1,0,0,0,t.getWidth(),t.getHeight(),false,true);
        }else{
            batch.draw(t,0,0,0,0,w,h,1,1,0,0,0,w,h,false,true);

        }
        batch.end();
        mainBuffer.end();
        System.out.println("texture applied");
    }
    public void applyGrayScale(SpriteBatch batch){
        helperBuffer.begin();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.setShader(grayScaleProgram);
        Texture t=mainBuffer.getColorBufferTexture();
        batch.draw(t,0,0,0,0,w,h,1,1,0,0,0,w,h,false,true);
        batch.end();
        helperBuffer.end();
        FrameBuffer f=mainBuffer;
        mainBuffer=helperBuffer;
        helperBuffer=f;
    }
    public void applySwapRedGreen(SpriteBatch batch){
        helperBuffer.begin();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.setShader(swapRedGreenProgram);
        Texture t=mainBuffer.getColorBufferTexture();
        batch.draw(t,0,0,0,0,w,h,1,1,0,0,0,w,h,false,true);
        batch.end();
        helperBuffer.end();
        FrameBuffer f=mainBuffer;
        mainBuffer=helperBuffer;
        helperBuffer=f;
    }
    public void applyBlur(SpriteBatch batch,int size){
        helperBuffer.begin();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.setShader(blurProgram);
        blurProgram.setUniformi("size",size);
        blurProgram.setUniform2fv("imgSize",new float[]{w,h},0,2);
        Texture t=mainBuffer.getColorBufferTexture();
        batch.draw(t,0,0,0,0,w,h,1,1,0,0,0,w,h,false,true);
        batch.end();
        helperBuffer.end();
        FrameBuffer f=mainBuffer;
        mainBuffer=helperBuffer;
        helperBuffer=f;
    }

}
