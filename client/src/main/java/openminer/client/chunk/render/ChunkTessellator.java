package openminer.client.chunk.render;

import jpize.util.array.list.FloatList;
import jpize.util.time.Stopwatch;
import openminer.chunk.Chunk;
import openminer.chunk.section.ChunkSection;
import openminer.chunk.section.storage.ByteSectionData;
import openminer.client.block.mesh.AdaptBlockMesh;
import openminer.client.block.mesh.BlockMeshManager;
import openminer.client.core.Meshes;
import openminer.core.Chunks;
import openminer.core.pos.SectionPos;

public class ChunkTessellator{

    private static final int INITIAL_VERTEX_BUFFER_SIZE = 1179648; // Max value: 4bytes * 3floats * 4vertices * 6 faces * 16 * 16 * 16 blocks

    private final ChunkRenderer renderer;
    private final FloatList vertexBuffer;


    public ChunkTessellator(ChunkRenderer renderer){
        this.renderer = renderer;
        this.vertexBuffer = new FloatList();
    }


    public void tesselate(Chunk chunk){
        Stopwatch sw0 = new Stopwatch().start();
        for(ChunkSection section: chunk.getSections().array())
            tesselate(section);
        System.out.println("Итого: " + sw0.getMillis());
    }

    public void tesselate(ChunkSection section){
        Stopwatch sw0 = new Stopwatch().start();

        Stopwatch sw1 = new Stopwatch().start();
        if(section.getBlocks().isEmpty())
            return;

        final ByteSectionData blocks = section.getBlocks();
        final BlockMeshManager blockMeshes = renderer.getBlockMeshes();
        final SectionPos position = section.getPosition();
        System.out.println("    Инициализация: " + sw1.getMillis());

        sw1.reset();
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
        System.out.println("    Итерация: " + sw1.getMillis());

        sw1.reset();
        Stopwatch sw2 = new Stopwatch().start();

        final SectionMesh mesh = new SectionMesh(position);

        System.out.println("      Меш: " + sw2.getMillis());
        sw2.reset();

        mesh.getBuffer().setData(vertexBuffer.array());
        renderer.getLevelMeshes().put(ChunkMeshType.BASE, position.pack(), mesh);

        System.out.println("      Запись: " + sw2.getMillis());
        sw2.reset();

        vertexBuffer.clear();
        vertexBuffer.trim();

        System.out.println("      Ресет: " + sw2.getMillis());

        System.out.println("    Данные: " + sw1.getMillis());

        System.out.println("  Секция_" + section.getPosition().y + ": " + sw0.getMillis());
    }

}
