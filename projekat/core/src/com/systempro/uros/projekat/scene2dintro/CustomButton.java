package com.systempro.uros.projekat.scene2dintro;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class CustomButton extends TextButton {

    public static TextButtonStyle style;
    public static BitmapFont font;
//    public static

    public static void init(){
        font=new BitmapFont();
        style=new TextButtonStyle();
        style.font=font;
    }
    public CustomButton(String text){
        super(text,style);
    }
}
