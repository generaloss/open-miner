package openminer.client.run;

import jpize.gl.Gl;
import jpize.io.context.JpizeApplication;
import jpize.sdl.Sdl;
import jpize.util.time.Sync;
import openminer.client.camera.Camera;
import openminer.client.entity.player.LocalPlayer;
import openminer.client.entity.player.PlayerInput;
import openminer.client.options.Options;
import openminer.client.gui.screens.ScreenManager;
import openminer.client.render.LevelRenderer;

public class Openminer extends JpizeApplication{

    private final Options options;
    private final Sync fpsLimiter;
    private final LocalPlayer player;
    private final Camera camera;
    private final LevelRenderer levelRenderer;
    private final ScreenManager screenManager;

    public Openminer(ArgsMap args) throws Exception{
        this.options = new Options("options.cfg");
        this.fpsLimiter = new Sync(options.display.fps_limit);
        this.fpsLimiter.enable(!options.display.vsync && options.display.fps_limit != -1);
        Sdl.enableVsync(options.display.vsync);
        this.player = new LocalPlayer(this, new PlayerInput(){ });
        this.camera = new Camera(this);
        this.levelRenderer = new LevelRenderer(this);
        this.screenManager = new ScreenManager(this);
    }

    @Override
    public void update(){
        fpsLimiter.sync();
    }

    @Override
    public void render(){
        Gl.clearColorDepthBuffers();
    }

    @Override
    public void resize(int width, int height){
        camera.resize(width, height);
    }

    @Override
    public void dispose(){
        options.write();
        levelRenderer.dispose();
        screenManager.dispose();
    }


    public Options getOptions(){
        return options;
    }

    public Camera getCamera(){
        return camera;
    }

    public LevelRenderer getLevelRenderer(){
        return levelRenderer;
    }

    public ScreenManager getScreenManager(){
        return screenManager;
    }

}
