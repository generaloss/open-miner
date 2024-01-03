package openminer.core.pos;

import jpize.math.Maths;
import jpize.math.vecmath.matrix.Matrix4f;
import openminer.core.Chunks;

public class SectionPos extends ImmutablePos3i{

    private SectionPos(int x, int y, int z){
        super(x, Maths.clamp(y, 0, Chunks.SECTIONS_NUM_IDX), z);
    }

    private SectionPos(long packed){
        super(packed);
    }

    public Matrix4f calcTranslateMatrix(){
        return new Matrix4f().setTranslate(
            x * Chunks.SIZE,
            y * Chunks.SIZE,
            z * Chunks.SIZE
        );
    }


    public static SectionPos of(int x, int y, int z){
        return new SectionPos(x, y, z);
    }

    public static SectionPos ofEntity(int x, int y, int z){
        return new SectionPos(
            Chunks.idxByPos(x),
            Chunks.idxByPos(y),
            Chunks.idxByPos(z)
        );
    }

}
