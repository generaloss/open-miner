package openminer.chunk.section.storage;

import openminer.core.Chunks;

import java.util.Arrays;

public class ByteSectionData{

    private byte[] data;
    private byte filled;

    public ByteSectionData(byte filled){
        this.filled = filled;
    }

    public ByteSectionData(){
        this((byte) 0);
    }


    public void fill(byte value){
        data = null;
        filled = value;
    }

    public byte get(int x, int y, int z){
        if(data == null)
            return filled;
        return data[Chunks.arrIdx(x, y, z)];
    }

    public void set(int x, int y, int z, byte value){
        if(data == null)
            data = createArray();
        data[Chunks.arrIdx(x, y, z)] = value;
    }

    public boolean isEmpty(){
        return data == null && filled == 0;
    }


    public byte[] toArray(){
        if(data == null)
            return createArray();
        return data;
    }

    private byte[] createArray(){
        final byte[] array = new byte[Chunks.VOLUME];
        if(filled != 0)
            Arrays.fill(array, filled);
        return array;
    }

}
