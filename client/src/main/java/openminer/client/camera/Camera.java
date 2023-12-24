package openminer.client.camera;

import jpize.graphics.camera.PerspectiveCamera;
import openminer.client.run.OpenMiner;

public class Camera extends PerspectiveCamera{

    public Camera(OpenMiner openminer){
        super(0.1, 1000, openminer.getOptions().graphics.fov);
    }

}
