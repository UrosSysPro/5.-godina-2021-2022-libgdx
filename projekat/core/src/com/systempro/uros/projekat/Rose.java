package com.systempro.uros.projekat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Rose extends ApplicationAdapter {

	ShapeRenderer renderer;
	public int w,h;
	public float n,d;
	@Override
	public void create () {
		renderer=new ShapeRenderer();
		w=Gdx.graphics.getWidth();
		h=Gdx.graphics.getHeight();
		n=5;
		d=4;
	}

	@Override
	public void render () {
		ScreenUtils.clear(1,1,1,1);
		input();
//		renderer.begin(ShapeRenderer.ShapeType.Filled);
//		renderer.setColor(Color.BLACK);
//
//		for(int n=0;n<200;n++){
//			double a=n*((Math.sqrt(5)+1)/2-1)*Math.PI*2;
//			double r=Math.sqrt(n)*a/50;
//
//			float x=(float)(Math.cos(a)*r)+w/2;
//			float y=(float)(Math.sin(a)*r)+h/2;
//
//			renderer.circle(x,y,2);
//		}
//		renderer.end();
		rose();

	}

	public void input(){
		if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
			d--;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
			d++;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
			n--;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
			n++;
		}
	}
	public void rose(){
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(Color.BLACK);
		float px=w/2,py=h/2;
		float k=n/d;
		for(float a=0;a<=Math.PI*2*d;a+=0.01){
			float r=(float)Math.cos(k*a);

			float x=(float)(Math.cos(a)*r)*100+w/2;
			float y=(float)(Math.sin(a)*r)*100+h/2;

			renderer.rectLine(px,py,x,y,3);
			px=x;
			py=y;
		}
		renderer.end();
	}
	@Override
	public void dispose () {

	}
}
