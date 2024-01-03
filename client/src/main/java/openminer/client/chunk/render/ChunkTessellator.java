package openminer.client.chunk.render;

import jpize.util.array.list.FloatList;
import openminer.chunk.Chunk;
import openminer.chunk.section.ChunkSection;
import openminer.chunk.section.storage.ByteSectionData;
import openminer.client.block.mesh.AdaptBlockMesh;
import openminer.client.block.mesh.BlockMeshManager;
import openminer.client.core.Meshes;
import openminer.core.Chunks;
import openminer.core.pos.SectionPos;

public class ChunkTessellator{

    private static final int INITIAL_VERTEX_BUFFER_SIZE = 24576; // Random value: 4bytes * 3floats * 4vertices * (16 * 16)faces * 2planes

    private final ChunkRenderer renderer;
    private final FloatList vertexBuffer;


    public ChunkTessellator(ChunkRenderer renderer){
        this.renderer = renderer;
        this.vertexBuffer = new FloatList();
    }


    public void tesselate(Chunk chunk){
        for(ChunkSection section: chunk.getSections().array())
            tesselate(section);
    }

    public void tesselate(ChunkSection section){
        if(section.getBlocks().isEmpty())
            return;
        
        final ByteSectionData blocks = section.getBlocks();
        final BlockMeshManager blockMeshes = renderer.getBlockMeshes();
        final SectionPos position = section.getPosition();

        for(int y = 0; y < Chunks.SIZE; y++){
            for(int x = 0; x < Chunks.SIZE; x++){
                for(int z = 0; z < Chunks.SIZE; z++){

                    final byte blockID = blocks.get(x, y, z);
                    if(blockID == 0) // Skip air
                        continue;
                    
                    final AdaptBlockMesh mesh = blockMeshes.getMesh(blockID);
                    if(mesh == null) // Skip empty mesh
                        continue;

                    // Put arrays
                    final int beginPosition = vertexBuffer.size();
                    final float[] vertices = mesh.getVertices();
                    vertexBuffer.add(vertices);
                    final int endPosition = vertexBuffer.size();

                    // Add vertex pos
                    for(int i = beginPosition; i < endPosition; i += Meshes.VERTEX_STRIDE){
                        vertexBuffer.valAdd(i    , x);
                        vertexBuffer.valAdd(i + 1, y);
                        vertexBuffer.valAdd(i + 2, z);
                    }

                }
            }
        }

        final int quads = vertexBuffer.size() / Meshes.VERTEX_STRIDE / 4;
        final SectionMesh mesh = new SectionMesh(position, quads);
        mesh.getBuffer().setData(vertexBuffer.array());
        renderer.getLevelMeshes().put(ChunkMeshType.BASE, position.pack(), mesh);

        vertexBuffer.clear();
        vertexBuffer.trim();
    }

}
