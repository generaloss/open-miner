package openminer.client.options;

import jpize.Jpize;
import jpize.sdl.Sdl;
import jpize.sdl.input.Key;
import jpize.util.file.Resource;
import jpize.util.file.ResourceExt;

public class Options{

    private final ResourceExt resource;
    private final OptionsWriter writer;

    public Options(String filepath) throws Exception{
        this.resource = Resource.external(filepath);
        this.resource.create();
        this.writer = new OptionsWriter(this);
        load();
        Sdl.enableVsync(display.vsync);
    }

    private void load() throws Exception{
        new OptionsMapper(this)
            .putTypeParser(Key.class, Key::valueOf)
            .map(resource);
    }

    public void write(){
        try{
            resource.create();
            writer.write(resource);
        }catch(IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }

    /** Options Sections */
    @OptionsSection public final Graphics graphics = new Graphics();
    @OptionsSection public final Display display = new Display();
    @OptionsSection public final Level level = new Level();
    @OptionsSection public final Player player = new Player();
    @OptionsSection public final Mouse mouse = new Mouse();
    @OptionsSection public final Keyboard keyboard = new Keyboard();

    public static class Graphics{
        public float fov = 85F;
        public float brightness = 1F;
        public boolean enable_fog = true;
    }

    public static class Display{
        public boolean vsync = true;
        public float fps_limit = Jpize.window().getDisplayMode(0).refreshRate;
        public boolean fullscreen = false;
    }

    public static class Level{
        public int load_distance = 16;
    }

    public static class Player{
        public boolean show_first_person_model = false;
    }

    public static class Mouse{
        public float sensitivity = 1F;
    }

    public static class Keyboard{
        public Key forward            = Key.W;
        public Key left               = Key.A;
        public Key backward = Key.S;
        public Key right              = Key.D;

        public Key jump               = Key.SPACE;
        public Key sprint             = Key.LCTRL;
        public Key sneak              = Key.LSHIFT;

        public Key chat               = Key.T;
        public Key command            = Key.SLASH;
        public Key zoom               = Key.C;
        public Key fullscreen         = Key.F11;
        public Key toggle_perspective = Key.F5;
    }

}
