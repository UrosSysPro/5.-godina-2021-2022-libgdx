package com.systempro.uros.projekat.shader2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;

public class MandelbrotSet {
    public static ShaderProgram program;

    public float[] start,end;
    public float w,h,a,x,y;
    public Texture t;
    public float iterations;

    public static String init(){
        ShaderProgram.pedantic=false;
        String vertex= Gdx.files.internal("vertex.vsh").readString();
        String fragment= Gdx.files.internal("mandelbrotSet.fsh").readString();
        program=new ShaderProgram(vertex,fragment);

        if(!program.isCompiled()){
            return program.getLog();
        }
        return "";
    }
    public MandelbrotSet(Texture t, float x, float y, float w, float h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.a=0;
        this.t=t;
        iterations=100;
        start=new float[]{-1,-1};
        end=new float[]{1,1};
    }

    public void setStart(float x,float y){
        start=new float[]{x,y};
    }
    public void setEnd(float x,float y){
        end=new float[]{x,y};
    }
    public void setVisibility(float vx,float vy,float vw,float vh){
        start[0]=vx-vw;
        end[0]=vx+vw;
        start[1]=vy-vh;
        end[1]=vy+vh;
    }

    public void draw(SpriteBatch batch){
        batch.begin();
        batch.setShader(program);
        program.setUniform2fv("start",start,0,2);
        program.setUniform2fv("end",end,0,2);
        program.setUniformf("iterations",iterations);

        batch.draw(t,x-w/2,y-h/2,w/2,h/2,w,h,1,1,a,0,0,t.getWidth(),t.getHeight(),false,false);

        batch.end();

        batch.setShader(null);
    }

}
