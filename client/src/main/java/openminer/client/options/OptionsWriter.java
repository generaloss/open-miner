package openminer.client.options;

import jpize.util.file.ResourceExt;

import java.io.PrintStream;
import java.lang.reflect.Field;

public class OptionsWriter{

    private final Object writeObject;

    public OptionsWriter(Object writeObject){
        this.writeObject = writeObject;
    }

    public void write(ResourceExt res) throws IllegalAccessException{
        final Field[] fields = writeObject.getClass().getFields();
        final PrintStream writer = res.writer();
        for(Field field: fields)
            if(field.isAnnotationPresent(OptionsSection.class))
                writeSection(writer, field);
        writer.close();
    }

    private void writeSection(PrintStream writer, Field field) throws IllegalAccessException{
        writer.printf("[%s]\n", field.getName());
        final Object section = field.get(writeObject);
        final Field[] fields = section.getClass().getDeclaredFields();
        for(Field option: fields)
            writeOption(writer, section, option);
        writer.println();
    }

    private void writeOption(PrintStream writer, Object section, Field field) throws IllegalAccessException{
        field.setAccessible(true);
        final Object option = field.get(section);
        writer.printf("%s = %s\n", field.getName(), option);
    }

}
