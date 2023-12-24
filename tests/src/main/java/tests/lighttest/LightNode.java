package tests.lighttest;

public class LightNode{

    public int x, y, level;

    public LightNode(int x, int y, int level){
        this.x = x;
        this.y = y;
        this.level = level;
    }

    /** Во избежание дубликатов в очередях*/
    @Override
    public boolean equals(Object object){
        if(object != null && object.getClass() == getClass()){
            LightNode lightNode = (LightNode) object;
            return x == lightNode.x && y == lightNode.y && level == lightNode.level;
        }else
            return false;
    }

}
