package openminer.client.options;

import jpize.util.file.Resource;
import jpize.util.io.FastReader;

import javax.naming.directory.NoSuchAttributeException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class OptionsMapper{

    private final Object mapObject;
    private final Map<Type, OptionTypeParser> typeParsers;
    private Object section;

    public OptionsMapper(Object mapObject){
        this.mapObject = mapObject;
        this.typeParsers = new HashMap<>(){{
            put(String.class, value -> value);
            put(int.class, Integer::parseInt);
            put(long.class, Long::parseLong);
            put(float.class, Float::parseFloat);
            put(double.class, Double::parseDouble);
            put(boolean.class, Boolean::parseBoolean);
        }};
    }

    public OptionsMapper putTypeParser(Type type, OptionTypeParser parser){
        typeParsers.put(type, parser);
        return this;
    }


    public void map(Resource res) throws NoSuchAttributeException{
        final FastReader reader = res.reader();
        while(reader.hasNext()){
            final String line = reader.nextLine();
            if(line.isBlank())
                continue;
            parseLine(line);
        }
        reader.close();
    }

    private void parseLine(String line) throws NoSuchAttributeException{
        if(line.startsWith("[")){
            final String name = line.substring(1, line.length() - 1);
            parseSection(name);
        }else if(section != null){
            final String[] tokens = line.split(" +");
            if(tokens.length != 3 || !tokens[1].equals("="))
                throw new RuntimeException("Unexpected line: " + line);
            parseField(tokens[0], tokens[2]);
        }
    }

    private void parseSection(String name) throws NoSuchAttributeException{
        try{
            final Field field = mapObject.getClass().getDeclaredField(name);
            if(!field.isAnnotationPresent(OptionsSection.class))
                throw new NoSuchAttributeException(mapObject.getClass().getSimpleName() + " field " + name + " is not annotated with @OptionSection");
            section = field.get(mapObject);
        }catch(ReflectiveOperationException ignored){
            section = null;
        }
    }

    private void parseField(String key, String value){
        try{
            final Field option = section.getClass().getDeclaredField(key);
            option.setAccessible(true);
            setValue(option, value);
        }catch(ReflectiveOperationException ignored){}
    }

    private void setValue(Field option, String value) throws IllegalAccessException{
        final Type type = option.getGenericType();
        final OptionTypeParser parser = typeParsers.get(type);
        if(parser == null)
            throw new RuntimeException("Not found OptionValueParser for type: " + type);

        final Object parsedValue = parser.parse(value);
        option.set(section, parsedValue);
    }

}
