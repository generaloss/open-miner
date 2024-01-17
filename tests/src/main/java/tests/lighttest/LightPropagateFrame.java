package tests.lighttest;

import jpize.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.graphics.util.batch.TextureBatch;
import jpize.sdl.input.Key;
import jpize.sdl.input.KeyAction;
import jpize.util.math.Maths;

public abstract class LightPropagateFrame extends JpizeApplication{

    int WIDTH = 32;
    int HEIGHT = 32;
    int MAX_LEVEL = 15;

    int[][] map = new int[WIDTH][HEIGHT];
    TextureBatch batch = new TextureBatch();

    public void init(){
        resize(Jpize.getWidth(), Jpize.getHeight());
        clear();
    }

    public void clear(){
        for(int i = 0; i < WIDTH; i++)
            for(int j = 0; j < HEIGHT; j++)
                set(i, j, 0);
    }

    public abstract void handleTouch(int i, int j, KeyAction action);

    public void set(int i, int j, int level){
        map[i][j] = level;
    }

    public int get(int i, int j){
        return map[i][j];
    }

    public void update(){
        if(Key.ESCAPE.isDown())
            Jpize.exit();
        if(Key.C.isDown())
            clear();

        if(Jpize.isTouched()){
            int tx = Maths.floor((Jpize.getX() - beginX) / tileSizeW);
            int ty = Maths.floor((Jpize.getY() - beginY) / tileSizeH);
            if(!(tx < 0 || ty < 0 || tx >= WIDTH || ty >= HEIGHT)){
                KeyAction action = Jpize.isTouchDown() ? KeyAction.DOWN : Jpize.isTouchReleased() ? KeyAction.UP : KeyAction.REPEAT;
                handleTouch(tx, ty, action);
            }
        }
    }

    public void render(){
        Gl.clearColorBuffer();
        Gl.clearColor(0.01, 0.01, 0.01);
        batch.begin();

        for(int i = 0; i < HEIGHT; i++){
            for(int j = 0; j < WIDTH; j++){
                float alpha = (float) get(i, j) / MAX_LEVEL;
                float x = beginX + i * tileSizeW;
                float y = beginY + j * tileSizeH;
                batch.drawRect(0.99, 0.77, 0.38, alpha, x, y, tileSizeW, tileSizeH);
            }
        }

        batch.end();
    }

    float tileSizeW;
    float tileSizeH;
    float beginX;
    float beginY;

    public void resize(int winW, int winH){
        tileSizeW = (float) winW / WIDTH;
        tileSizeH = (float) winH / HEIGHT;
        float tilesAspect = (float) WIDTH / HEIGHT;

        if(tilesAspect > Jpize.getAspect())
            tileSizeH = tileSizeW / tilesAspect;
        else
            tileSizeW = tileSizeH * tilesAspect;

        beginX = (winW - tileSizeW * WIDTH) / 2;
        beginY = (winH - tileSizeH * HEIGHT) / 2;
    }

}
