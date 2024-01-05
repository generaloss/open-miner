package openminer.client.gui.screens.ingame;

import jpize.Jpize;
import jpize.graphics.camera.controller.Motion3DController;
import jpize.math.Maths;
import jpize.sdl.input.Key;
import openminer.chunk.Chunk;
import openminer.chunk.section.storage.ByteSectionData;
import openminer.client.block.mesh.AdaptBlockMesh;
import openminer.client.camera.Camera;
import openminer.client.gui.screens.AbstractScreen;
import openminer.client.gui.screens.ScreenManager;
import openminer.client.render.LevelRenderer;
import openminer.client.run.Openminer;
import openminer.core.Chunks;

public class IngameScreen extends AbstractScreen{

    private final Camera camera;
    private final LevelRenderer levelRenderer;
    private final Motion3DController controller;

    public IngameScreen(ScreenManager screenManager){
        super(screenManager);

        final Openminer openminer = screenManager.getOpenminer();
        this.camera = openminer.getCamera();
        this.levelRenderer = openminer.getLevelRenderer();

        this.controller = new Motion3DController();

        camera.getPosition().set(8, 25, 8);
        AdaptBlockMesh blockMesh = new AdaptBlockMesh(
            new float[]{
                0, 1, 1,  1, 1, 1, 1,  0, 1,
                0, 0, 1,  1, 1, 1, 1,  1, 1,
                0, 0, 0,  1, 1, 1, 1,  1, 0,
                0, 1, 0,  1, 1, 1, 1,  0, 0,

                1, 1, 0,  1, 1, 1, 1,  0, 1,
                1, 0, 0,  1, 1, 1, 1,  1, 1,
                1, 0, 1,  1, 1, 1, 1,  1, 0,
                1, 1, 1,  1, 1, 1, 1,  0, 0,

                1, 0, 1,  1, 1, 1, 1,  0, 1,
                1, 0, 0,  1, 1, 1, 1,  1, 1,
                0, 0, 0,  1, 1, 1, 1,  1, 0,
                0, 0, 1,  1, 1, 1, 1,  0, 0,

                1, 1, 0,  1, 1, 1, 1,  0, 1,
                1, 1, 1,  1, 1, 1, 1,  1, 1,
                0, 1, 1,  1, 1, 1, 1,  1, 0,
                0, 1, 0,  1, 1, 1, 1,  0, 0,

                0, 1, 0,  1, 1, 1, 1,  0, 1,
                0, 0, 0,  1, 1, 1, 1,  1, 1,
                1, 0, 0,  1, 1, 1, 1,  1, 0,
                1, 1, 0,  1, 1, 1, 1,  0, 0,

                1, 1, 1,  1, 1, 1, 1,  0, 1,
                1, 0, 1,  1, 1, 1, 1,  1, 1,
                0, 0, 1,  1, 1, 1, 1,  1, 0,
                0, 1, 1,  1, 1, 1, 1,  0, 0,
            }
        );
        levelRenderer.getChunkRenderer().getBlockMeshes().putMesh((byte) 1, blockMesh);

        Chunk chunk = new Chunk(0, 0);
        ByteSectionData blocks = chunk.getSections().getByIndex(1).getBlocks();
        for(int x = 0; x < Chunks.SIZE; x++){
            for(int z = 0; z < Chunks.SIZE; z++){
                if(Maths.randomBoolean())
                    blocks.set(x, 5, z, (byte) 1);
            }
        }
        levelRenderer.getChunkRenderer().getTessellator().tesselate(chunk);
    }

    public void update(){
        controller.update(camera.getRotation().yaw);
        camera.getPosition().add(controller.getDirectedMotion().mul(0.1));

        if(Key.ESCAPE.isDown())
            screenManager.setScreen("main_menu");
    }

    @Override
    public void render(){
        update();
        camera.update();
        levelRenderer.render(camera);
    }

    @Override
    public void show(){
        camera.unlockRotation();
    }

    @Override
    public void hide(){
        camera.lockRotation();
        Jpize.input().toCenter();
    }

}
