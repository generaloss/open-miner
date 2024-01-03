package openminer.client.chunk.render;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LevelChunkMeshManager{

    private final Map<ChunkMeshType, ChunkMeshSet> meshTypeMap;

    public LevelChunkMeshManager(){
        this.meshTypeMap = new HashMap<>();
        for(ChunkMeshType type: ChunkMeshType.values())
            meshTypeMap.put(type, new ChunkMeshSet());
    }

    public void put(ChunkMeshType type, long sectionPosPacked, SectionMesh mesh){
        meshTypeMap.get(type).put(sectionPosPacked, mesh);
    }

    public SectionMesh remove(ChunkMeshType type, long sectionPosPacked){
        return meshTypeMap.get(type).remove(sectionPosPacked);
    }

    public Collection<SectionMesh> collection(ChunkMeshType type){
        return meshTypeMap.get(type).collection();
    }

}
