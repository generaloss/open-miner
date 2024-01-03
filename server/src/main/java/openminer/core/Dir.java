package openminer.core;

import jpize.math.vecmath.vector.Vec3i;

public enum Dir{

    NONE ( 0,  0,  0),

    NEG_X(-1,  0,  0),
    POS_X(+1,  0,  0),
    NEG_Y( 0, -1,  0),
    POS_Y( 0, +1,  0),
    NEG_Z( 0,  0, -1),
    POS_Z( 0,  0, +1);

    public final Vec3i normal;

    Dir(int normalX, int normalY, int normalZ){
        this.normal = new Vec3i(normalX, normalY, normalZ);
    }

}
