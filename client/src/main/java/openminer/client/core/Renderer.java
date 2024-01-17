package openminer.client.core;

import jpize.app.Disposable;
import openminer.client.camera.Camera;

public interface Renderer extends Disposable{

    void render(Camera camera);

}
