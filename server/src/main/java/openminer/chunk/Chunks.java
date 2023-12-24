package openminer.chunk;

public class Chunks{

    public static int SIZE_SHIFT = 4;
    public static int SIZE = 1 << SIZE_SHIFT;
    public static int SIZE_IDX = SIZE - 1;

    public static int SECTIONS_NUM = 16;
    public static int HEIGHT = SECTIONS_NUM * SIZE;
    public static int HEIGHT_IDX = HEIGHT - 1;

}
