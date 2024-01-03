package openminer.core.pos;

import openminer.core.Chunks;

public class ChunkPos extends ImmutablePos2i{

    private ChunkPos(int x, int z){
        super(x, z);
    }

    private ChunkPos(long packed){
        super(packed);
    }


    public static ChunkPos of(int x, int z){
        return new ChunkPos(x, z);
    }

    public static ChunkPos ofEntity(int x, int z){
        return new ChunkPos(
            Chunks.idxByPos(x),
            Chunks.idxByPos(z)
        );
    }

}
