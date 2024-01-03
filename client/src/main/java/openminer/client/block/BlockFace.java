package openminer.client.block;

import jpize.util.array.list.FloatList;
import openminer.core.Dir;

public class BlockFace{

    /** The face will be hidden if there is an opposite face of the neighboring block located in this Direction */
    private final Dir occlude;

    /** Color map for face */
    private final FaceColormap colormap;

    /** True if all vertices have integer coordinates */
    private final boolean packed;

    /** Data */
    private final FloatList vertices;

    public BlockFace(Dir occlude, FaceColormap colormap, boolean packed, FloatList vertices){
        this.occlude = occlude;
        this.colormap = colormap;
        this.packed = packed;
        this.vertices = vertices;
    }

    public Dir getOcclude(){
        return occlude;
    }

    public boolean isPacked(){
        return packed;
    }

    public FaceColormap getColormap(){
        return colormap;
    }

    public FloatList getVertices(){
        return vertices;
    }

}
