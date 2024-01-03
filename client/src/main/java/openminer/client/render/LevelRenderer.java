package openminer.client.render;

import openminer.client.camera.Camera;
import openminer.client.chunk.render.ChunkRenderer;
import openminer.client.core.Renderer;
import openminer.client.run.Openminer;

public class LevelRenderer implements Renderer{

    private final Openminer openminer;
    private final SkyRenderer skyRenderer;
    private final ChunkRenderer chunkRenderer;

    public LevelRenderer(Openminer openminer){
        this.openminer = openminer;
        this.skyRenderer = new SkyRenderer(openminer);
        this.chunkRenderer = new ChunkRenderer(openminer);
    }

    public Openminer getOpenminer(){
        return openminer;
    }

    public SkyRenderer getSkyRenderer(){
        return skyRenderer;
    }

    public ChunkRenderer getChunkRenderer(){
        return chunkRenderer;
    }

    @Override
    public void render(Camera camera){ //! fix camera (premultiply matrices on cpu)
        skyRenderer.render(camera);
        chunkRenderer.render(camera);
    }

    @Override
    public void dispose(){
        chunkRenderer.dispose();
    }

}
