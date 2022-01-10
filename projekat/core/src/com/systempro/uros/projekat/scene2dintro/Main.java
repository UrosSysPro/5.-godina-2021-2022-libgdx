package com.systempro.uros.projekat.scene2dintro;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
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


        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                CustomButton t= new CustomButton("eeee jel radi");
                t.pad(10);

                table.add(t);
            }
            table.row();
        }
        CustomButton t=new CustomButton("key event listener");
        t.pad(10);
        table.add(t).colspan(4);
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

        stage.draw();
    }
}
