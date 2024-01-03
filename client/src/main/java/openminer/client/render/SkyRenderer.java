package openminer.client.render;

import openminer.client.camera.Camera;
import openminer.client.core.Renderer;
import openminer.client.gui.screens.mainmenu.Panorama;
import openminer.client.run.Openminer;

public class SkyRenderer implements Renderer{

    private final Openminer openminer;
    private final Panorama panorama;

    public SkyRenderer(Openminer openminer){
        this.openminer = openminer;
        this.panorama = new Panorama();
    }

    public Openminer getOpenminer(){
        return openminer;
    }


    @Override
    public void render(Camera camera){
        panorama.render(camera);
    }

    @Override
    public void dispose(){
        panorama.dispose();
    }

}
