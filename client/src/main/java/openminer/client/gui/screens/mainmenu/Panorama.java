package openminer.client.gui.screens.mainmenu;

import jpize.Jpize;
import jpize.app.Disposable;
import jpize.graphics.camera.PerspectiveCamera;
import jpize.graphics.util.SkyBox;

public class Panorama implements Disposable{

    private final PerspectiveCamera camera;
    private final SkyBox panorama;

    public Panorama(){
        this.camera = new PerspectiveCamera(0.1, 10, 79);
        this.panorama = new SkyBox(
            "texture/skybox/3/skybox_positive_x.png", "texture/skybox/3/skybox_negative_x.png",
            "texture/skybox/3/skybox_positive_y.png", "texture/skybox/3/skybox_negative_y.png",
            "texture/skybox/3/skybox_positive_z.png", "texture/skybox/3/skybox_negative_z.png"
        );
    }

    public void render(PerspectiveCamera camera){
        panorama.render(camera);

        camera.getRotation().yaw += Jpize.getDt() * 10;
        camera.update();
    }

    public void render(){
        render(camera);
    }

    public void reset(){
        camera.getRotation().set(90, -25);
        camera.update();
    }

    @Override
    public void dispose(){
        panorama.dispose();
    }

}
