package openminer.client.run;

import jpize.Jpize;
import jpize.io.context.ContextBuilder;
import jpize.math.Maths;
import openminer.core.SharedConstants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main{

    public static final Logger LOG = LogManager.getRootLogger();

    public static void main(String[] args) throws Throwable{
        // Configure Log4j
        Thread.currentThread().setName("Render-Thread");
        PropertyConfigurator.configure(Main.class.getResourceAsStream("/log4j.properties"));
        LOG.info("Run OpenMiner " + SharedConstants.VERSION);

        // Parse args
        final ArgsMap args_map = new ArgsMap()
            .addDefault("title", "OpenMiner")
            .addDefault("width", 1280)
            .addDefault("height", 720)
            .addDefault("name", "Player" + Maths.random(10, 99))
            .parse(args);

        // Create Jpize context
        final String title = args_map.getString("title");
        final int width = args_map.getInt("width");
        final int height = args_map.getInt("height");

        ContextBuilder.newContext(title, width, height)
            .icon("icon.png")
            .register().setAdapter(new Openminer(args_map));

        // Run
        Jpize.runContexts();
    }

}
