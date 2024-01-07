package openminer.client.chunk.render;

import jpize.gl.tesselation.GlPrimitive;
import jpize.graphics.mesh.Mesh;
import jpize.math.vecmath.matrix.Matrix4f;
import openminer.client.core.Meshes;
import openminer.core.pos.SectionPos;

public class SectionMesh extends Mesh{

    private final SectionPos position;
    private final Matrix4f translateMatrix;

    public SectionMesh(SectionPos position){
        super(Meshes.POSITION, Meshes.TINT, Meshes.UV);
        super.setMode(GlPrimitive.QUADS);
        this.position = position;
        this.translateMatrix = position.calcTranslateMatrix();
    }

    public SectionPos getPosition(){
        return position;
    }

    public Matrix4f getTranslateMatrix(){
        return translateMatrix;
    }

}
