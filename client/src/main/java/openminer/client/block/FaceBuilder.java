package openminer.client.block;

import jpize.util.array.list.FloatList;
import openminer.core.Dir;

public class FaceBuilder{

    public static FaceBuilder create(){
        return new FaceBuilder();
    }


    private Dir occlude;
    private FaceColormap colormap;
    private boolean packed;
    private final FloatList vertices;

    private FaceBuilder(){
        this.vertices = new FloatList();
    }


    public FaceBuilder occlude(Dir occlude){
        this.occlude = occlude;
        return this;
    }

    public FaceBuilder packed(boolean packed){
        this.packed = packed;
        return this;
    }

    public FaceBuilder colormap(FaceColormap colormap){
        this.colormap = colormap;
        return this;
    }


    public BlockFace face(){
        return new BlockFace(occlude, colormap, packed, vertices);
    }

}
