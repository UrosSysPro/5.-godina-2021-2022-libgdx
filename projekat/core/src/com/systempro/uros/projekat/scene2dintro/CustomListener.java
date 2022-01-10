package com.systempro.uros.projekat.scene2dintro;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class CustomListener extends ClickListener {
    public static CustomButton[][] buttons;
    public static CustomButton selected;
    public static int selectedi,selectedj;
    public int i,j;

    public CustomListener(int i,int j){
        this.i=i;
        this.j=j;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
//        System.out.println("enter");
        if(selected!=null)selected.selected=false;
        selected=buttons[i][j];
        selectedi=i;
        selectedj=j;
        selected.selected=true;
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        buttons[i][j].selected=false;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        System.out.println("click");
    }

}
