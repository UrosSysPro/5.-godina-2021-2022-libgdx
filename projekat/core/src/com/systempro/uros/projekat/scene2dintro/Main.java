package com.systempro.uros.projekat.scene2dintro;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends ApplicationAdapter {
    public Stage stage;
    public Table table;
    public int w,h;
    @Override
    public void create() {
        CustomButton.init();
        w= Gdx.graphics.getWidth();
        h= Gdx.graphics.getHeight();
        stage=new Stage(new FitViewport(w,h));
        Gdx.input.setInputProcessor(stage);
        table=new Table();
        stage.addActor(table);
        table.setFillParent(true);

        CustomListener.buttons=new CustomButton[4][4];
        CustomListener.selected=null;

        for(int j=0;j<CustomListener.buttons[0].length;j++){
            for(int i=0;i<CustomListener.buttons.length;i++){
                CustomButton t= new CustomButton("eeee jel radi");
                t.set(10,5,10);
                t.setColor(Color.CLEAR,Color.GOLD,Color.CORAL,Color.CORAL);
                Cell<CustomButton> cell=table.add(t);

                CustomListener.buttons[i][j]=t;
                t.addListener(new CustomListener(i,j));
            }
            table.row();
        }

        CustomButton t=new CustomButton("wide btn");
        t.set(10,5,10);
        t.setColor(Color.CLEAR,Color.GOLD,Color.CORAL,Color.CORAL);

        table.add(t).colspan(4).fill();

        t.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("click na "+x+" "+y);
            }
        });

    }

    @Override
    public void render() {
        ScreenUtils.clear(0,0,0,1);
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)&&CustomListener.selected!=null){
            CustomListener.selectedi--;
            if(CustomListener.selectedi<0)CustomListener.selectedi=CustomListener.buttons.length-1;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)&&CustomListener.selected!=null){
            CustomListener.selectedi++;
            if(CustomListener.selectedi>=CustomListener.buttons.length)CustomListener.selectedi=0;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)&&CustomListener.selected!=null){
            CustomListener.selectedj--;
            if(CustomListener.selectedj<0)CustomListener.selectedj=CustomListener.buttons[0].length-1;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)&&CustomListener.selected!=null){
            CustomListener.selectedj++;
            if(CustomListener.selectedj>=CustomListener.buttons[0].length)CustomListener.selectedj=0;
        }
        if(CustomListener.selected!=null)
        {
            CustomListener.selected.selected = false;
            CustomListener.selected = CustomListener.buttons[CustomListener.selectedi][CustomListener.selectedj];
            CustomListener.selected.selected = true;
        }
        float delta=Gdx.graphics.getDeltaTime();
        stage.act(delta);
        stage.draw();
    }
}
