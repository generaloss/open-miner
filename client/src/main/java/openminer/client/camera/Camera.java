package openminer.client.camera;

import jpize.graphics.camera.PerspectiveCamera;
import jpize.graphics.camera.controller.Rotation3DController;
import openminer.client.run.Openminer;
import openminer.core.Chunks;
import openminer.core.pos.SectionPos;

public class Camera extends PerspectiveCamera{

    private final Rotation3DController controller;

    public Camera(Openminer openminer){
        super(0.1, 1000, openminer.getOptions().graphics.fov);

        this.controller = new Rotation3DController(false);
        this.controller.setSpeed(openminer.getOptions().mouse.sensitivity);
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

    @Override
    public void update(){
        controller.update();
        rotation.set(controller.getRotation());
        super.update();
    }

    public void unlockRotation(){
        controller.setEnabled(true);
    }

    public void lockRotation(){
        controller.setEnabled(false);
    }

}
