package openminer.core;

public class Chunks{

    public static int SIZE_SHIFT   = 4;
    public static int SIZE_SHIFT_2 = (SIZE_SHIFT * 2);
    public static int SIZE         = (1 << SIZE_SHIFT);
    public static int SIZE_IDX     = (SIZE - 1);

    public static int SECTIONS_NUM     = 16;
    public static int SECTIONS_NUM_IDX = (SECTIONS_NUM - 1);
    public static int HEIGHT           = (SECTIONS_NUM * SIZE);
    public static int HEIGHT_IDX       = (HEIGHT - 1);
    
    public static int AREA   = (SIZE * SIZE);
    public static int VOLUME = (SIZE * SIZE * SIZE);

    /** Use to calculate chunk/section position */
    public static int idxByPos(int position){
        return position >> SIZE_SHIFT;
    }

    /** 3-D array index */
    public static int arrIdx(int x, int y, int z){
        return x | z << SIZE_SHIFT | y << SIZE_SHIFT_2;
    }

    /** 2-D array index */
    public static int arrIdx(int x, int z){
        return x | z << SIZE_SHIFT;
    }

}
