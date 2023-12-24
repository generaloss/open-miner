package openminer.client.gui.screens;

import jpize.Jpize;
import openminer.client.run.OpenMiner;
import openminer.client.gui.screens.mainmenu.MainMenuScreen;
import openminer.client.gui.screens.options.OptionsScreen;

import java.util.HashMap;
import java.util.Map;

public class ScreenManager{

    private final OpenMiner openminer;
    private final Map<String, AbstractScreen> screens;

    public ScreenManager(OpenMiner openminer){
        this.openminer = openminer;
        this.screens = new HashMap<>();

        put("main_menu", new MainMenuScreen(this));
        put("options", new OptionsScreen(this));
        setScreen("main_menu");
    }

    public OpenMiner getOpenminer(){
        return openminer;
    }


    public void put(String name, AbstractScreen screen){
        screens.put(name, screen);
    }

    public void setScreen(String name){
        Jpize.setScreen(screens.get(name));
    }

}
