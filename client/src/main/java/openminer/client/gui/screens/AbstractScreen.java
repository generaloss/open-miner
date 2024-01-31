package openminer.client.gui.screens;

import jpize.app.Disposable;
import jpize.app.Screen;
import jpize.graphics.font.Font;
import jpize.graphics.font.FontLoader;

public abstract class AbstractScreen implements Screen, Disposable{

    protected final ScreenManager screenManager;
    protected final Font font;

    public AbstractScreen(ScreenManager screenManager){
        this.screenManager = screenManager;

        this.font = FontLoader.getDefault();
        this.font.setScale(0.75F);
    }

    public void setScreen(String name){
        screenManager.setScreen(name);
    }

    @Override
    public void dispose(){ }

}
