package openminer.chunk.section;

import openminer.chunk.section.storage.ByteSectionData;
import openminer.core.pos.SectionPos;

public class ChunkSection{

    private final SectionPos position;
    private final ByteSectionData blocks;

    public ChunkSection(int x, int y, int z){
        this.position = SectionPos.of(x, y, z);
        this.blocks = new ByteSectionData();
    }

    public SectionPos getPosition(){
        return position;
    }

    public ByteSectionData getBlocks(){
        return blocks;
    }

}
