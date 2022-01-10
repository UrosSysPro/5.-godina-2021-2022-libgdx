package com.systempro.uros.projekat.scene2dintro;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class CustomButton extends TextButton {

    public static TextButtonStyle style;
    public static BitmapFont font;
    public static ShapeRenderer renderer;

    public int margin,padding,border;
    public Color background,marginColor,paddingColor,borderColor;
    public boolean selected;
//    public static

    public static void init(){
        font=new BitmapFont();
        style=new TextButtonStyle();
        style.font=font;
        renderer=new ShapeRenderer();
    }
    public CustomButton(String text){

        super(text,style);
        margin=0;
        padding=0;
        border=0;
        background=new Color(0,0,0,0);
        marginColor=new Color(0,0,0,0);
        paddingColor=new Color(0,0,0,0);
        borderColor=new Color(0,0,0,0);
        selected=false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();

        float x=getX(),y=getY(),width=getWidth(),height=getHeight();
        float inset=0;
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(marginColor);
        renderer.rect(x+inset,y+inset,width-2*inset,height-2*inset);
        inset+=margin;

        renderer.setColor(borderColor);
        renderer.rect(x+inset,y+inset,width-2*inset,height-2*inset);
        inset+=border;

        renderer.setColor(paddingColor);
        renderer.rect(x+inset,y+inset,width-2*inset,height-2*inset);
        inset+=padding;

        renderer.setColor(background);
        renderer.rect(x+inset,y+inset,width-2*inset,height-2*inset);

        if(selected) {
            renderer.setColor(Color.valueOf("00ef7f"));
            renderer.rect(x, y, width, height);
        }

        renderer.end();

        batch.begin();
        super.draw(batch, parentAlpha);
    }
    public void set(int margin,int border,int padding){
        this.margin=margin;
        this.border=border;
        this.padding=padding;
        pad(margin+border+padding);
    }
    public void setColor(Color marginColor,Color borderColor, Color paddingColor, Color background){
        this.marginColor=marginColor;
        this.paddingColor=paddingColor;
        this.borderColor=borderColor;
        this.background=background;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
