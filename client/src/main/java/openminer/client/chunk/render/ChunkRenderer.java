package openminer.client.chunk.render;

import jpize.graphics.texture.Texture;
import jpize.graphics.util.Shader;
import jpize.util.file.Resource;
import openminer.client.block.mesh.BlockMeshManager;
import openminer.client.camera.Camera;
import openminer.client.core.Renderer;
import openminer.client.run.Openminer;
import openminer.core.pos.SectionPos;

import java.util.Collection;

public class ChunkRenderer implements Renderer{

    private final Openminer openminer;
    private final LevelChunkMeshManager levelMeshes;
    private final BlockMeshManager blockMeshes;
    private final ChunkTessellator tessellator;
    private final Shader chunkShader;
    Texture atlas = new Texture("texture/block/grass_block_side.png");

    public ChunkRenderer(Openminer openminer){
        this.openminer = openminer;
        this.levelMeshes = new LevelChunkMeshManager();
        this.blockMeshes = new BlockMeshManager();
        this.tessellator = new ChunkTessellator(this);
        this.chunkShader = new Shader(Resource.internal("shader/level/chunk/chunk.vsh"), Resource.internal("shader/level/chunk/chunk.fsh"));
    }

    public Openminer getOpenminer(){
        return openminer;
    }

    public LevelChunkMeshManager getLevelMeshes(){
        return levelMeshes;
    }

    public BlockMeshManager getBlockMeshes(){
        return blockMeshes;
    }

    public ChunkTessellator getTessellator(){
        return tessellator;
    }

    @Override
    public void render(Camera camera){
        // Gl.enable(GlTarget.DEPTH_TEST);

        chunkShader.bind();
        chunkShader.uniform("u_projection", camera.getProjection());
        chunkShader.uniform("u_view", camera.getView());
        chunkShader.uniform("u_atlas", atlas);

        final Collection<SectionMesh> meshesBase = levelMeshes.collection(ChunkMeshType.BASE);

        for(SectionMesh mesh: meshesBase){
            final SectionPos position = mesh.getPosition();
            if(camera.isNotVisible(position))
                continue;

            chunkShader.uniform("u_model", mesh.getTranslateMatrix());
            mesh.render();
        }

        // Gl.disable(GlTarget.DEPTH_TEST);
    }

    @Override
    public void dispose(){
        chunkShader.dispose();
        atlas.dispose();
    }

}
