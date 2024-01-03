package openminer.client.core;

import jpize.gl.type.GlType;
import jpize.gl.vertex.GlVertexAttr;

public class Meshes{
    
    public static final GlVertexAttr POSITION = new GlVertexAttr(3, GlType.FLOAT);
    public static final GlVertexAttr TINT = new GlVertexAttr(4, GlType.FLOAT);
    public static final GlVertexAttr UV = new GlVertexAttr(2, GlType.FLOAT);
    
    public static final int VERTEX_STRIDE = POSITION.getCount() + TINT.getCount() + UV.getCount();
    
}
