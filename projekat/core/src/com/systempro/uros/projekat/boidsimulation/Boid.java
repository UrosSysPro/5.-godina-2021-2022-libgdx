package com.systempro.uros.projekat.boidsimulation;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Boid {
    public float x,y,vx,vy;
    public static int visibility=50;
    public static float maxSpeed=3;

    public Boid(float x,float y,float vx,float vy){
        this.x=x;
        this.y=y;
        this.vx=vx;
        this.vy=vy;
    }
    public void update(ArrayList<Boid> list){
        Vector2 a=alignment(list);
        Vector2 c=cohesion(list);
        Vector2 s=separation(list);
        a.x=a.x*0.5f;
        a.y=a.y*0.5f;
        c.x=c.x*0.1f;
        c.y=c.y*0.1f;
        s.x*=-0.1f;
        s.y*=-0.1f;
        vx+=a.x+c.x+s.x;
        vy+=a.y+c.y+s.y;

        float i=(float)Math.sqrt(vx*vx+vy*vy);
        if(i>maxSpeed){
            vx/=i;
            vy/=i;
            vx*=maxSpeed;
            vy*=maxSpeed;
        }

        x+=vx;
        y+=vy;
        if(x>Main.w)x=0;
        if(y>Main.h)y=0;
        if(x<0)x=Main.w-1;
        if(y<0)y=Main.h-1;
    }
    public Vector2 separation(ArrayList<Boid> list){
        float sumX=0;
        float sumY=0;
        float n=0;
        for(int i=0;i<list.size();i++){
            if(distance(list.get(i),this)>visibility&&list.get(i)!=this){
                continue;
            }
            sumX+=list.get(i).x-x;
            sumY+=list.get(i).y-y;
            n++;
        }
        if(n!=0){
            return new Vector2(sumX/n-vx,sumY/n-vy);
        }
        return new Vector2(0,0);
    }
    public Vector2 alignment(ArrayList<Boid> list){
        float sumX=0;
        float sumY=0;
        float n=0;
        for(int i=0;i<list.size();i++){
            if(distance(list.get(i),this)>visibility&&list.get(i)!=this){
                continue;
            }
            sumX+=list.get(i).vx;
            sumY+=list.get(i).vy;
            n++;
        }
        if(n!=0){
            return new Vector2(sumX/n-vx,sumY/n-vy);
        }
        return new Vector2(0,0);
    }
    public Vector2 cohesion(ArrayList<Boid> list){
        float sumX=0;
        float sumY=0;
        float n=0;
        for(int i=0;i<list.size();i++){
            if(distance(list.get(i),this)>visibility&&list.get(i)!=this){
                continue;
            }
            sumX+=list.get(i).x;
            sumY+=list.get(i).y;
            n++;
        }
        if(n!=0){
            return new Vector2(sumX/n-x,sumY/n-y);
        }
        return new Vector2(0,0);
    }
    public void draw(ShapeRenderer renderer){
        renderer.circle(x,y,3);
    }

    public static float distance(Boid a,Boid b){
        return (float)Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
    }
}
