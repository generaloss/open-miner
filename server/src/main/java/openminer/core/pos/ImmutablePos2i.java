package openminer.core.pos;

public class ImmutablePos2i{

    public final int x, z;

    public ImmutablePos2i(int x, int z){
        this.x = x;
        this.z = z;
    }

    public ImmutablePos2i(long packed){
        this.x = unpackX(packed);
        this.z = unpackZ(packed);
    }

    public long pack(){
        return pack(x, z);
    }

    @Override
    public String toString(){
        return "[" + x + ", " + z + "]";
    }


    private static final int PACK_BITS = 32;
    private static final long PACK_MASK = 0xFFFFFFFFL; // 32 bits max value & cast long(L)


    public static long pack(int x, int z){
        return (x & PACK_MASK) | (((long) z) << PACK_BITS);
    }

    public static int unpackX(long packed){
        return (int) (packed);
    }

    public static int unpackZ(long packed){
        return (int) (packed >> PACK_BITS);
    }

    public static long packedStep(long packed, int stepX, int stepZ){
        return pack(
            unpackX(packed) + stepX,
            unpackZ(packed) + stepZ
        );
    }

}
