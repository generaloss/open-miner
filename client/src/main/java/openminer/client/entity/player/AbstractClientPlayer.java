package openminer.client.entity.player;

import openminer.client.run.Openminer;
import openminer.entity.Player;

public class AbstractClientPlayer extends Player{

    protected final Openminer openminer;

    public AbstractClientPlayer(Openminer openminer){
        this.openminer = openminer;
    }

}
