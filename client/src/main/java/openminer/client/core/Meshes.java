package openminer.client.core;

import jpize.gl.type.GlType;
import jpize.gl.vertex.GlVertAttr;

public class Meshes{
    
    public static final GlVertAttr POSITION = new GlVertAttr(3, GlType.FLOAT);
    public static final GlVertAttr TINT     = new GlVertAttr(4, GlType.FLOAT);
    public static final GlVertAttr UV       = new GlVertAttr(2, GlType.FLOAT);
    
    public static final int VERTEX_STRIDE = POSITION.getCount() + TINT.getCount() + UV.getCount();
    
}
