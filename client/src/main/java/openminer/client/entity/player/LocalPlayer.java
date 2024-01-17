package openminer.client.entity.player;

import jpize.util.math.vecmath.vector.Vec3f;
import openminer.client.run.Openminer;

public class LocalPlayer extends AbstractClientPlayer{

    private final PlayerInput input;

    public LocalPlayer(Openminer openminer, PlayerInput input){
        super(openminer);
        this.input = input;
    }

    public void update(){ //: replace with tick and good code!!
        yaw = input.yaw;
        pitch = input.pitch;

        final Vec3f motion = new Vec3f(input.right ? 1 : (input.left ? -1 : 0), input.jump ? 1 : (input.sneak ? -1 : 0), input.forward ? 1 : (input.backward ? -1 : 0));
        motion.nor().mul(0.3).rotY(yaw);
        position.add(motion);
    }

}
