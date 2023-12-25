package openminer.client.gui.screens.options;

import jpize.ui.context.PuiLoader;
import jpize.ui.context.UIContext;
import jpize.ui.palette.Button;
import openminer.client.gui.screens.AbstractScreen;
import openminer.client.gui.screens.ScreenManager;

public class OptionsScreen extends AbstractScreen{

    private final UIContext ui;

    public OptionsScreen(ScreenManager screenManager){
        super(screenManager);
        this.ui = new PuiLoader()
            .putRes("font", super.font)
            .loadRes("ui/options.pui");

        // Done
        final Button done = ui.getByID("done");
        done.input().addPressCallback((comp, btn) -> comp.style().background().color().setA(0.75));
        done.input().addReleaseCallback((comp, btn) -> comp.style().background().color().setA(0.5));
        done.input().addReleaseCallback((comp, btn) -> super.setScreen("main_menu"));
    }

    @Override
    public void render(){
        ui.render();
    }

    @Override
    public void show(){
        ui.enable();
    }

    @Override
    public void hide(){
        ui.disable();
    }

}
