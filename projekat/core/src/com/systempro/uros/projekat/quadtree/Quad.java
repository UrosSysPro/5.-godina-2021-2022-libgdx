package com.systempro.uros.projekat.quadtree;

public class Quad {
    public static int maxPoints=5;
    public int x,y,w,h,n;
    public Point[] points;
    public boolean subdevided;
    public Quad[] quads;


    public Quad(int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        subdevided=false;
        points=new Point[maxPoints];
        quads=new Quad[4];
        n=0;
    }
    public boolean contains(Point p){
        if(p.x<x)return false;
        if(p.x>=x+w)return false;
        if(p.y<y)return false;
        if(p.y>=y+h)return false;
        return true;
    }
    public boolean overlaps(Quad q){
        if(q.x+q.w<x)return false;
        if(q.x>=x+w)return false;
        if(q.y+q.h<y)return false;
        if(q.y>=y+h)return false;
        return true;
    }
}
