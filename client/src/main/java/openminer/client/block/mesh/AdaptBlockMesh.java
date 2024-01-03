package openminer.client.block.mesh;

public class AdaptBlockMesh{

    private final float[] vertices;

    public AdaptBlockMesh(float[] vertices){
        this.vertices = vertices;
    }

    public float[] getVertices(){
        return vertices;
    }

}
