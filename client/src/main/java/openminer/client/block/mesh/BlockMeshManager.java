package openminer.client.block.mesh;

import java.util.HashMap;
import java.util.Map;

public class BlockMeshManager{

    private final Map<Byte, AdaptBlockMesh> map;

    public BlockMeshManager(){
        this.map = new HashMap<>();
    }

    public AdaptBlockMesh getMesh(Byte blockID){
        return map.get(blockID);
    }

    public void putMesh(Byte blockID, AdaptBlockMesh mesh){
        map.put(blockID, mesh);
    }

}
