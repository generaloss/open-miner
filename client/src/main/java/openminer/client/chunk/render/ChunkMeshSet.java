package openminer.client.chunk.render;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ChunkMeshSet{

    private final Map<Long, SectionMesh> map;

    public ChunkMeshSet(){
        this.map = new HashMap<>();
    }

    public void put(long sectionPosPacked, SectionMesh mesh){
        map.put(sectionPosPacked, mesh);
    }

    public SectionMesh remove(long sectionPosPacked){
        return map.remove(sectionPosPacked);
    }

    public Collection<SectionMesh> collection(){
        return map.values();
    }

}
