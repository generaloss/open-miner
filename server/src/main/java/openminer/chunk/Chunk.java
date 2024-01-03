package openminer.chunk;

import openminer.chunk.section.ChunkSections;
import openminer.core.pos.ChunkPos;

public class Chunk{

    private final ChunkPos position;
    private final ChunkSections sections;

    public Chunk(ChunkPos position){
        this.position = position;
        this.sections = new ChunkSections(position);
    }

    public Chunk(int x, int z){
        this(ChunkPos.of(x, z));
    }

    public ChunkPos getPosition(){
        return position;
    }

    public ChunkSections getSections(){
        return sections;
    }

}
