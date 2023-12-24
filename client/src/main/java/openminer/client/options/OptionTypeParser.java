package openminer.client.options;

@FunctionalInterface
public interface OptionTypeParser{

    Object parse(String value);

}
