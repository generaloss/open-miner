package openminer.client.gui.screens;

import jpize.Jpize;
import jpize.util.Disposable;
import openminer.client.gui.screens.ingame.IngameScreen;
import openminer.client.run.Openminer;
import openminer.client.gui.screens.mainmenu.MainMenuScreen;
import openminer.client.gui.screens.options.OptionsScreen;

import java.util.HashMap;
import java.util.Map;

public class ScreenManager implements Disposable{

    private final Openminer openminer;
    private final Map<String, AbstractScreen> screens;

    public ScreenManager(Openminer openminer){
        this.openminer = openminer;
        this.screens = new HashMap<>();

        put("main_menu", new MainMenuScreen(this));
        put("options", new OptionsScreen(this));
        put("game", new IngameScreen(this));
        setScreen("main_menu");
    }

    public Openminer getOpenminer(){
        return openminer;
    }


    public void put(String name, AbstractScreen screen){
        screens.put(name, screen);
    }

    public void setScreen(String name){
        Jpize.setScreen(screens.get(name));
    }

    @Override
    public void dispose(){
        for(AbstractScreen screen: screens.values())
            screen.dispose();
    }

}
