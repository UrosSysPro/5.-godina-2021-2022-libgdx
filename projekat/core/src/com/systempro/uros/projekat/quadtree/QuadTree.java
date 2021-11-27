package com.systempro.uros.projekat.quadtree;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.LinkedList;

public class QuadTree {

    private Quad root;

    public QuadTree(int x,int y,int w,int h){
        root=new Quad(x,y,w,h);
    }
    public void add(int x,int y){
        add(new Point(x,y));
    }
    public void add(Point p){
        addR(root,p);
    }
    public void addR(Quad root,Point p){
        if(root.subdevided){
            for(int i=0;i<root.quads.length;i++){
                if(root.quads[i].contains(p)){
                    addR(root.quads[i],p);
                    return;
                }
            }
        }else{
            if(root.n>=Quad.maxPoints){
                int x=root.x,y=root.y,w=root.w,h=root.h;
                root.quads[0]=new Quad(x,y,w/2,h/2);
                root.quads[1]=new Quad(x+w/2,y,w/2,h/2);
                root.quads[2]=new Quad(x,y+h/2,w/2,h/2);
                root.quads[3]=new Quad(x+w/2,y+h/2,w/2,h/2);
                root.subdevided=true;
                for(int i=0;i<root.n;i++){
                    for(int j=0;j<root.quads.length;j++){
                        if(root.quads[j].contains(root.points[i])){
                            addR(root.quads[j],root.points[i]);
                            break;
                        }
                    }
                }
                for(int j=0;j<root.quads.length;j++){
                    if(root.quads[j].contains(p)){
                        addR(root.quads[j],p);
                        break;
                    }
                }

            }else{
                root.points[root.n]=p;
                root.n++;
            }
        }
    }

    public void draw(ShapeRenderer renderer){
        drawR(root,renderer);
    }
    public void drawR(Quad root,ShapeRenderer renderer){
        if(root.subdevided){
            for(int i=0;i<root.quads.length;i++){
                drawR(root.quads[i],renderer);
            }
        }else {
            renderer.begin(ShapeRenderer.ShapeType.Line);

            renderer.rect(root.x,root.y,root.w,root.h);
            renderer.end();

            renderer.begin(ShapeRenderer.ShapeType.Filled);
            for(int i=0;i<root.n;i++){
                root.points[i].draw(renderer);
            }
            renderer.end();
        }
    }
//    public LinkedList<Point> query(int x,int y,int w,int h){
//        return queryR(root,new Quad(x,y,w,h));
//    }
//    public LinkedList<Point> queryR(Quad root,Quad q){
//        if(root.overlaps(q)){
//            if(root.subdevided){
//                for(int i=0;i<root.quads.length;i++){
//                    queryR()
//                }
//            }else{
//
//            }
//        }
//    }

}
