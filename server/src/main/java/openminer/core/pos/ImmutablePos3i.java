package openminer.core.pos;

public class ImmutablePos3i{

    public final int x, y, z;

    public ImmutablePos3i(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public ImmutablePos3i(long packed){
        this.x = unpackX(packed);
        this.y = unpackY(packed);
        this.z = unpackZ(packed);
    }

    public long pack(){
        return pack(x, y, z);
    }

    @Override
    public String toString(){
        return "[" + x + ", " + y + ", " + z + "]";
    }


    /** Bits for X & Z packing = 30 = (32 - 2) = (64 - 4) / 2
     *  64 - long bits (8 bytes * 8 bits)
     *  4  - bits for Y (2 ^ 4 = 16 sections)
     *  2  - 2 ints (X, Z)
     */
    private static final int PACK_BITS_XZ = 30;
    private static final int PACK_MASK_XZ = 0x3FFFFFFF; // 30 bits max value
    private static final int PACK_MIN_VALUE_XZ = PACK_MASK_XZ / 2 + 1; // for negative X, Z values support
    private static final int PACK_SHIFT_Y = PACK_BITS_XZ * 2;
    private static final int PACK_MASK_Y = 0xF; // 4 bits max value


    public static long pack(int x, int y, int z){
        final long xl =  (long) (x + PACK_MIN_VALUE_XZ) & PACK_MASK_XZ;
        final long zl = ((long) (z + PACK_MIN_VALUE_XZ) & PACK_MASK_XZ) << PACK_BITS_XZ;
        final long yl = ((long) y) << PACK_SHIFT_Y;
        return xl | zl | yl;
    }

    public static int unpackX(long packed){
        return (int) (packed & PACK_MASK_XZ) - PACK_MIN_VALUE_XZ;
    }

    public static int unpackZ(long packed){
        return (int) ((packed >> PACK_BITS_XZ) & PACK_MASK_XZ) - PACK_MIN_VALUE_XZ;
    }

    public static int unpackY(long packed){
        return (int) (packed >> PACK_SHIFT_Y) & PACK_MASK_Y;
    }

    public static long packedStep(long packed, int stepX, int stepY, int stepZ){
        return pack(
            unpackX(packed) + stepX,
            unpackY(packed) + stepY,
            unpackZ(packed) + stepZ
        );
    }

}
