package openminer.client.run;

import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.util.time.Sync;
import openminer.client.camera.Camera;
import openminer.client.entity.player.LocalPlayer;
import openminer.client.entity.player.PlayerInputImpl;
import openminer.client.options.Options;
import openminer.client.gui.screens.ScreenManager;
import openminer.client.render.LevelRenderer;

public class Openminer extends JpizeApplication{

    private final Options options;
    private final Sync fpsLimiter;
    private final PlayerInputImpl playerInput;
    private final LocalPlayer player;
    private final Camera camera;
    private final LevelRenderer levelRenderer;
    private final ScreenManager screenManager;

    public Openminer(ArgsMap args) throws Exception{
        // options
        this.options = new Options("options.cfg");
        this.fpsLimiter = new Sync(options.display.fps_limit);
        this.fpsLimiter.enable(!options.display.vsync && options.display.fps_limit != -1);
        // player
        this.playerInput = new PlayerInputImpl(this);
        this.player = new LocalPlayer(this, playerInput);
        this.camera = new Camera(this, player);
        // renderer
        this.levelRenderer = new LevelRenderer(this);
        // ui
        this.screenManager = new ScreenManager(this);
    }

    @Override
    public void update(){
        fpsLimiter.sync();
        playerInput.update();
        player.update();
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

    public PlayerInputImpl getPlayerInput(){
        return playerInput;
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
