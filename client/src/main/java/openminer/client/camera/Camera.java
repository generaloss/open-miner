package openminer.client.camera;

import jpize.graphics.camera.PerspectiveCamera;
import openminer.client.run.Openminer;
import openminer.core.Chunks;
import openminer.core.pos.SectionPos;
import openminer.entity.Entity;
import openminer.entity.LivingEntity;

public class Camera extends PerspectiveCamera{

    private final LivingEntity entity;

    public Camera(Openminer openminer, LivingEntity entity){
        super(0.1, 10000, openminer.getOptions().graphics.fov);
        this.entity = entity;
    }

    public Entity getEntity(){
        return entity;
    }

    @Override
    public void update(){
        position.set(entity.position);
        rotation.set(entity.yaw, entity.pitch);
        super.update();
    }

    public boolean isNotVisible(SectionPos position){
        final int x = position.x * Chunks.SIZE;
        final int y = position.y * Chunks.SIZE;
        final int z = position.z * Chunks.SIZE;
        return !super.getFrustum().isBoxInFrustum(
            x, y, z,
            x + Chunks.SIZE, y + Chunks.SIZE, z + Chunks.SIZE
        );
    }

}
