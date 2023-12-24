import jpize.Jpize;
import jpize.io.context.ContextBuilder;
import tests.lighttest.LightPropagate;

public class Main{

    public static void main(String[] args){
        ContextBuilder.newContext("OpenMiner - Test", 1280, 720)
            .register()

        .setAdapter(new LightPropagate());

        Jpize.runContexts();
    }

}
