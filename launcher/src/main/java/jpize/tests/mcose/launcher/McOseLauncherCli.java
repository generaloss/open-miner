package jpize.tests.mcose.launcher;

import jpize.util.io.FastReader;

public class McOseLauncherCli{

    public McOseLauncherCli(){
        final McOseClient client = new McOseClient();

        final FastReader reader = new FastReader();
        while(!Thread.interrupted()){
            Thread.yield();

            if(reader.hasNext()){
                final String line = reader.nextLine();
                switch(line){
                    case "run" -> client.launch();
                    case "download" -> client.downloadJar();
                }
            }
        }
    }

}
