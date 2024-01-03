package openminer.client.entity.player;

import openminer.client.run.Openminer;

public class LocalPlayer extends AbstractClientPlayer{

    private final PlayerInput input;

    public LocalPlayer(Openminer openminer, PlayerInput input){
        super(openminer);
        this.input = input;
    }

}
