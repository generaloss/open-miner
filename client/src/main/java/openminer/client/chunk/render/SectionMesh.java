package openminer.client.chunk.render;

import jpize.graphics.mesh.QuadMesh;
import jpize.math.vecmath.matrix.Matrix4f;
import openminer.client.core.Meshes;
import openminer.core.pos.SectionPos;

public class SectionMesh extends QuadMesh{

    private final SectionPos position;
    private final Matrix4f translateMatrix;

    public SectionMesh(SectionPos position, int quads){
        super(quads, Meshes.POSITION, Meshes.TINT, Meshes.UV);
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
