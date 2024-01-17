package openminer.client.gui.screens.ingame;

import jpize.Jpize;
import jpize.sdl.Sdl;
import jpize.sdl.input.Key;
import jpize.ui.context.PuiLoader;
import jpize.ui.context.UIContext;
import jpize.ui.palette.TextView;
import jpize.util.math.Maths;
import openminer.chunk.Chunk;
import openminer.chunk.section.ChunkSection;
import openminer.chunk.section.storage.ByteSectionData;
import openminer.client.block.mesh.AdaptBlockMesh;
import openminer.client.camera.Camera;
import openminer.client.gui.screens.AbstractScreen;
import openminer.client.gui.screens.ScreenManager;
import openminer.client.render.LevelRenderer;
import openminer.client.run.Openminer;
import openminer.core.Chunks;

public class IngameScreen extends AbstractScreen{

    private final UIContext ui;

    private final Camera camera;
    private final LevelRenderer levelRenderer;

    public IngameScreen(ScreenManager screenManager){
        super(screenManager);

        this.ui = new PuiLoader()
            .putRes("font", font)
            .loadRes("ui/ingame.pui");

        ui.setBorderSoftness(0);

        final Openminer openminer = screenManager.getOpenminer();
        this.camera = openminer.getCamera();
        this.levelRenderer = openminer.getLevelRenderer();

        camera.getPosition().set(8, 25, 8);
        AdaptBlockMesh blockMesh = new AdaptBlockMesh(
            new float[]{
                0, 1, 1,  0, 0, 0, 1,  0, 1,
                0, 0, 1,  1, 1, 1, 1,  1, 1,
                0, 0, 0,  0, 0, 0, 1,  1, 0,
                0, 1, 0,  1, 1, 1, 1,  0, 0,

                1, 1, 0,  1, 1, 1, 1,  0, 1,
                1, 0, 0,  0, 0, 0, 1,  1, 1,
                1, 0, 1,  1, 1, 1, 1,  1, 0,
                1, 1, 1,  0, 0, 0, 1,  0, 0,

                1, 0, 1,  1, 1, 1, 1,  0, 1,
                1, 0, 0,  1, 1, 1, 1,  1, 1,
                0, 0, 0,  1, 1, 1, 1,  1, 0,
                0, 0, 1,  1, 1, 1, 1,  0, 0,

                1, 1, 0,  1, 1, 1, 1,  0, 1,
                1, 1, 1,  1, 1, 1, 1,  1, 1,
                0, 1, 1,  1, 1, 1, 1,  1, 0,
                0, 1, 0,  1, 1, 1, 1,  0, 0,

                0, 1, 0,  0, 0, 0, 1,  0, 1,
                0, 0, 0,  0, 0, 0, 1,  1, 1,
                1, 0, 0,  1, 1, 1, 1,  1, 0,
                1, 1, 0,  1, 1, 1, 1,  0, 0,

                1, 1, 1,  1, 1, 1, 1,  0, 1,
                1, 0, 1,  1, 1, 1, 1,  1, 1,
                0, 0, 1,  0, 0, 0, 1,  1, 0,
                0, 1, 1,  0, 0, 0, 1,  0, 0,
            }
        );
        levelRenderer.getChunkRenderer().getBlockMeshes().putMesh((byte) 1, blockMesh);

        Chunk chunk = new Chunk(0, 0);
        for(ChunkSection section: chunk.getSections().array()){
            ByteSectionData blocks = section.getBlocks();

            for(int x = 0; x < Chunks.SIZE; x++)
                for(int z = 0; z < Chunks.SIZE; z++)
                    for(int y = 0; y < Chunks.SIZE; y++)
                        if(Maths.randomBoolean(0.3F))
                            blocks.set(x, y, z, (byte) 1);
        }

        levelRenderer.getChunkRenderer().getTessellator().tesselate(chunk);

    }

    public void update(){
        if(Key.V.isDown())
            Sdl.enableVsync(!Sdl.isVsyncEnabled());

        if(Key.ESCAPE.isDown())
            screenManager.setScreen("main_menu");
    }

    @Override
    public void render(){
        update();
        camera.update();
        levelRenderer.render(camera);
        ((TextView) ui.getByID("fps")).setText("fps: " + Jpize.getFPS());
        ui.render();
    }

    @Override
    public void show(){
        screenManager.getOpenminer().getPlayerInput().enable();
        ui.enable();
    }

    @Override
    public void hide(){
        screenManager.getOpenminer().getPlayerInput().disable();
        Jpize.input().toCenter();
        ui.disable();
    }

}
