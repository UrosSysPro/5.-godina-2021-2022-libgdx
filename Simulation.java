package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Simulation {

    float[][] u,v,dens,u_prev,v_prev,dens_prev;
    float[][] dens_r,dens_g,dens_b;
    float dens_diff=0f;
    float vel_diff=0.01f;

    public int width,height,cellSize;

    public Simulation(int width,int height,int cellSize) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        u=new float[width][height];
        v=new float[width][height];
        dens=new float[width][height];
        dens_r=new float[width][height];
        dens_g=new float[width][height];
        dens_b=new float[width][height];
        u_prev=new float[width][height];
        v_prev=new float[width][height];
        dens_prev=new float[width][height];
        for(int i=0;i<width;i++)
            for(int j=0;j<height;j++){
//                u[i][j]=((float) i-width/2f)/width;
//                v[i][j]=((float) j-height/2f)/height;
                u[i][j]=0;
                v[i][j]=0;
                dens[i][j]=0;
                dens_r[i][j]=0;
                dens_g[i][j]=0;
                dens_b[i][j]=0;
                u_prev[i][j]=0;
                v_prev[i][j]=0;
                dens_prev[i][j]=0;
            }
    }
    public void update(float delta){

        update_velocity(delta);
        update_dens(delta);
    }
    public void update_dens(float delta){
        float[][] niz=dens_r;
        dens_r=dens_prev;
        dens_prev=niz;
        difuse(dens_r,dens_prev,dens_diff,delta);
        niz=dens_r;
        dens_r=dens_prev;
        dens_prev=niz;
        advect(dens_r,dens_prev,u,v,delta);

        niz=dens_g;
        dens_g=dens_prev;
        dens_prev=niz;
        difuse(dens_g,dens_prev,dens_diff,delta);
        niz=dens_g;
        dens_g=dens_prev;
        dens_prev=niz;
        advect(dens_g,dens_prev,u,v,delta);

        niz=dens_b;
        dens_b=dens_prev;
        dens_prev=niz;
        difuse(dens_b,dens_prev,dens_diff,delta);
        niz=dens_b;
        dens_b=dens_prev;
        dens_prev=niz;
        advect(dens_b,dens_prev,u,v,delta);
    }

    public void update_velocity(float delta){
        float[][] p;
        p=u;
        u=u_prev;
        u_prev=p;
        difuse (  u, u_prev,vel_diff,delta);
        p=v;
        v=v_prev;
        v_prev=p;
        difuse (  v, v_prev, vel_diff, delta);
        project (  u, v, u_prev,v_prev );
        p=u;
        u=u_prev;
        u_prev=p;
        p=v;
        v=v_prev;
        v_prev=p;
        advect (  u, u_prev, u_prev,v_prev,delta );
        advect (  v, v_prev, u_prev,v_prev,delta);
        project (  u, v, u_prev,v_prev );

    }

    public void difuse(float[][] x,float[][] x0,float diff,float delta){
        float a=delta*diff*(width-2)*(height-2);
        for(int k=0;k<20;k++){
            for(int i=1;i<width-1;i++){
                for(int j=1;j<height-1;j++){
                    float zbir=x[i-1][j]+
                            x[i+1][j]+
                            x[i][j-1]+
                            x[i][j+1];
                    x[i][j]=(x0[i][j]+a*zbir)/(1+4*a);
                }
            }
        }
    }

    public void advect(float[][] d,float[][] d0,float[][] u,float[][] v,float delta){
        float dt0=delta*(width-2);
        for(int i=1;i<width-1;i++){
            for(int j=1;j<height-1;j++){
                float x=i-dt0*u[i][j];
                float y=j-dt0*v[i][j];
                if(x<0.5f)x=0.5f;
                if(x>width-2+0.5)x=width-2+0.5f;
                if(y<0.5f)y=0.5f;
                if(y>height-2+0.5)y=height-2+0.5f;
                int i0=(int)x;
                int i1=i0+1;
                int j0=(int)y;
                int j1=j0+1;
                float s1=x-i0;
                float s0=1-s1;
                float t1=y-j0;
                float t0=1-t1;
                d[i][j]=s0*(t0*d0[i0][j0]+t1*d0[i0][j1])+
                        s1*(t0*d0[i1][j0]+t1*d0[i1][j1]);

            }
        }
    }

    void project (float[][] u, float[][] v, float[][] p, float[][] div )
    {
        int i, j, k;
        float h;
        h = 1f/(width-2f);
        for ( i=1 ; i<=width-2 ; i++ ) {
            for ( j=1 ; j<=height-2 ; j++ ) {
                div[i][j] = -0.5f*h*(u[i+1][j]-u[i-1][j]+
                        v[i][j+1]-v[i][j-1]);
                p[i][j] = 0;
            }
        }
        set_bnd ( 0, div ); set_bnd ( 0, p );
        for ( k=0 ; k<20 ; k++ ) {
            for ( i=1 ; i<=width-2 ; i++ ) {
                for ( j=1 ; j<=height-2 ; j++ ) {
                    p[i][j] = (div[i][j]+p[i-1][j]+p[i+1][j]+
                            p[i][j-1]+p[i][j+1])/4f;
                }
            }
            set_bnd ( 0, p );
        }
        for ( i=1 ; i<=width-2 ; i++ ) {
            for ( j=1 ; j<=height-2 ; j++ ) {
                u[i][j] -= 0.5f*(p[i+1][j]-p[i-1][j])/h;
                v[i][j] -= 0.5f*(p[i][j+1]-p[i][j-1])/h;
            }
        }
        set_bnd ( 1, u ); set_bnd (  2, v );
    }

    void set_bnd ( int b, float [][] x )
    {

        for (int i=1 ; i<=width-2 ; i++ ) {

            x[i][0]=b==2?(-x[i][1]):x[i][1];

            x[i][height-1]=b==2?(- x[i][height-2]):x[i][height-2];
        }
        for(int j=1;j<=height-2;j++){
            x[0 ][j] = b==1 ? -x[1][j] : x[1][j];
            x[width-1][j] = b==1 ? -x[width-2][j] : x[width-2][j];
        }
//        x[0][0] = 0.5*(x[IX(1,0 )]+x[IX(0 ,1)]);
//        x[0][height-1] = 0.5*(x[IX(1,N+1)]+x[IX(0 ,N )]);
//        x[IX(N+1,0 )] = 0.5*(x[IX(N,0 )]+x[IX(N+1,1)]);
//        x[IX(N+1,N+1)] = 0.5*(x[IX(N,N+1)]+x[IX(N+1,N )]);
    }

    public void draw(ShapeRenderer renderer){
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for(int i=0;i<width;i++)
            for(int j=0;j<height;j++){
                float r=dens_r[i][j];
                float g=dens_g[i][j];
                float b=dens_b[i][j];
                renderer.setColor(r,g,b,1);
                float x=i*cellSize;
                float y=(height-1-j)*cellSize;
                renderer.rect(x,y,cellSize,cellSize);
            }
        renderer.end();
    }
}
