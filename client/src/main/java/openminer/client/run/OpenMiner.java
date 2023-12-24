package openminer.client.run;

import jpize.Jpize;
import jpize.gl.Gl;
import jpize.io.context.JpizeApplication;
import jpize.sdl.input.Key;
import jpize.util.time.Sync;
import openminer.client.options.Options;
import openminer.client.gui.screens.ScreenManager;

public class OpenMiner extends JpizeApplication{

    private final Options options;
    private final Sync fpsLimiter;
    private final ScreenManager screenManager;

    public OpenMiner(ArgsMap args) throws Exception{
        this.options = new Options("options.cfg");
        this.fpsLimiter = new Sync(options.display.fps_limit);
        this.fpsLimiter.enable(!options.display.vsync);
        this.screenManager = new ScreenManager(this);
    }


    public void update(){
        fpsLimiter.sync();

        if(Key.ESCAPE.isDown())
            Jpize.exit();
    }

    public void render(){
        Gl.clearColorBuffer();
    }

    public void dispose(){
        options.write();
    }


    public Options getOptions(){
        return options;
    }

    public ScreenManager getScreenManager(){
        return screenManager;
    }

}
