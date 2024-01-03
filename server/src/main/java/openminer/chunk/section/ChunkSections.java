package openminer.chunk.section;

import openminer.core.Chunks;
import openminer.core.pos.ChunkPos;

public class ChunkSections{

    private final ChunkSection[] array;

    public ChunkSections(ChunkPos position){
        this.array = new ChunkSection[Chunks.SECTIONS_NUM];
        for(int i = 0; i < array.length; i++)
            array[i] = new ChunkSection(position.x, i, position.z);
    }


    public ChunkSection getByIndex(int index){
        return array[index];
    }

    public ChunkSection getByY(int y){
        return array[Chunks.idxByPos(y)];
    }


    public void set(ChunkSections sections){
        System.arraycopy(array, 0, sections.array, 0, array.length);
    }

    public ChunkSection[] array(){
        return array;
    }

}
