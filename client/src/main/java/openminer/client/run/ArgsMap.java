package openminer.client.run;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Args format: "--key1 value --key2 "multi word value" ..." */
public class ArgsMap{

    public static final Logger LOG = LogManager.getRootLogger();
    private final Map<String, String> map;

    public ArgsMap(){
        this.map = new HashMap<>();
    }


    public ArgsMap parse(String[] args){
        final List<String> tokens = groupLiterals(args);
        for(int i = 0; i < tokens.size(); i += 2){
            final String key = tokens.get(i).substring(2);
            final String value = tokens.get(i + 1);
            map.put(key, value);
        }
        return this;
    }

    private List<String> groupLiterals(String[] args){
        final List<String> tokens = new ArrayList<>();
        boolean isLiteral = false;
        StringBuilder literal = new StringBuilder();
        for(String arg: args){
            if(!isLiteral){
                if(arg.startsWith("\"") && !arg.endsWith("\""))
                    isLiteral = true;
                else
                    tokens.add(arg);
            }else{
                literal.append(arg);
                if(arg.endsWith("\"")){
                    isLiteral = false;
                    tokens.add(literal.substring(1, literal.length() - 1));
                    literal = new StringBuilder();
                }
            }
        }
        return tokens;
    }

    public ArgsMap addDefault(String key, Object value){
        map.put(key, value.toString());
        return this;
    }


    public String getString(String key){
        return map.getOrDefault(key, "");
    }

    public int getInt(String key){
        return Integer.parseInt(getString(key));
    }

}
