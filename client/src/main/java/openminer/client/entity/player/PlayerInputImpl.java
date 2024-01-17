package openminer.client.entity.player;

import jpize.Jpize;
import jpize.graphics.camera.ctrl.EulerRotCtrl;
import jpize.sdl.event.callback.keyboard.KeyCallback;
import jpize.util.math.util.EulerAngles;
import openminer.client.options.Options;
import openminer.client.run.Openminer;

public class PlayerInputImpl extends PlayerInput{

    private final EulerRotCtrl rotationCtrl;
    private final KeyCallback keyCallback;
    private boolean enabled;

    public PlayerInputImpl(Openminer openminer){
        final Options options = openminer.getOptions();

        this.rotationCtrl = new EulerRotCtrl(new EulerAngles(), false);
        this.rotationCtrl.setSpeed(options.mouse.sensitivity);

        this.keyCallback = (key, action, mods) -> {
            if(key == options.keyboard.forward ) super.forward  = action.pressed;
            if(key == options.keyboard.left    ) super.left     = action.pressed;
            if(key == options.keyboard.backward) super.backward = action.pressed;
            if(key == options.keyboard.right   ) super.right    = action.pressed;
            if(key == options.keyboard.jump    ) super.jump     = action.pressed;
            if(key == options.keyboard.sneak   ) super.sneak    = action.pressed;
        };
    }

    public void enable(){
        enabled = true;
        rotationCtrl.setEnabled(enabled);
        Jpize.context().callbacks().addKeyCallback(keyCallback);
    }

    public void disable(){
        enabled = false;
        rotationCtrl.setEnabled(enabled);
        Jpize.context().callbacks().addKeyCallback(keyCallback);
    }

    public void update(){
        if(!enabled)
            return;

        rotationCtrl.update();

        final EulerAngles rotation = rotationCtrl.getTarget();
        yaw = rotation.yaw;
        pitch = rotation.pitch;
    }


    public EulerAngles getRotation(){
        return rotationCtrl.getTarget();
    }

}
