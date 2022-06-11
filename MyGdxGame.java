package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {

	public ShapeRenderer renderer;
	public Simulation simulation;
	int px=0;
	int py=0;

	@Override
	public void create () {
		renderer=new ShapeRenderer();

		int cellSize=10;
		int width= Gdx.graphics.getWidth()/cellSize;
		int height= Gdx.graphics.getHeight()/cellSize;

		simulation=new Simulation(width,height,cellSize);
	}

	@Override
	public void render () {
		ScreenUtils.clear(Color.CORAL);

		int x=Gdx.input.getX()/simulation.cellSize;
		int y=Gdx.input.getY()/simulation.cellSize;
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			int n=3;
			for(int i=-n;i<n;i++)
				for(int j=-n;j<n;j++)
					if(x+i>=0&&x+i<simulation.width)
						if(y+j>=0&&y+j<simulation.height){
							simulation.dens[x+i][y+j]=1;
							simulation.u[x+i][y+j]=x-px;
							simulation.v[x+i][y+j]=y-py;
						}
		}
		px=x;
		py=y;
		simulation.update(Gdx.graphics.getDeltaTime());
		simulation.draw(renderer);
	}
	
	@Override
	public void dispose () {
		renderer.dispose();
	}
}
