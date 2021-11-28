package com.systempro.uros.projekat.boidsimulation;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class BoidSimulation {

    ArrayList<Boid> list,pomocnaLista;


    public BoidSimulation(){
        list=new ArrayList<Boid>();
        pomocnaLista=new ArrayList<Boid>();
    }

    public void draw(ShapeRenderer renderer){
        for(int i=0;i<list.size();i++){
            list.get(i).draw(renderer);
        }
    }
    public void update(){
        for(int i=0;i<list.size();i++){
            pomocnaLista.get(i).update(list);
        }
        ArrayList<Boid> p = pomocnaLista;
        pomocnaLista = list;
        list = p;
    }
    public void add(float x,float y,float vx,float vy){
        list.add(new Boid(x,y,vx,vy));
        pomocnaLista.add(new Boid(x,y,vx,vy));
    }
}
