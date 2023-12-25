package openminer.client.gui.screens.mainmenu;

import jpize.Jpize;
import jpize.graphics.texture.Texture;
import jpize.ui.context.PuiLoader;
import jpize.ui.context.UIContext;
import jpize.ui.palette.Button;
import openminer.client.gui.screens.AbstractScreen;
import openminer.client.gui.screens.ScreenManager;

public class MainMenuScreen extends AbstractScreen{

    private final Panorama panorama;
    private final UIContext ui;

    final Texture background = new Texture("ui/background.jpg");

    public MainMenuScreen(ScreenManager screenManager){
        super(screenManager);
        this.panorama = new Panorama();

        this.ui = new PuiLoader()
            .putRes("font", font)
            .putRes("background", background)
            .loadRes("ui/main_menu.pui");

        // Singleplayer
        final Button singleplayer_button = ui.getByID("singleplayer");
        singleplayer_button.input().addPressCallback((comp, btn) -> comp.style().background().color().setA(0.75));
        singleplayer_button.input().addReleaseCallback((comp, btn) -> comp.style().background().color().setA(0.5));

        // Options
        final Button options_button = ui.getByID("options");
        options_button.input().addPressCallback((comp, btn) -> comp.style().background().color().setA(0.75));
        options_button.input().addReleaseCallback((comp, btn) -> comp.style().background().color().setA(0.5));
        options_button.input().addReleaseCallback((comp, btn) -> super.setScreen("options"));

        // Quit
        final Button quit_button = ui.getByID("quit");
        quit_button.input().addPressCallback((comp, btn) -> comp.style().background().color().setA(0.75));
        quit_button.input().addReleaseCallback((comp, btn) -> comp.style().background().color().setA(0.5));
        quit_button.input().addReleaseCallback((comp, btn) -> Jpize.exit());
    }

    @Override
    public void render(){
        panorama.render();
        ui.render();
    }

    @Override
    public void show(){
        panorama.reset();
        ui.enable();
    }

    @Override
    public void hide(){
        ui.disable();
    }

}
