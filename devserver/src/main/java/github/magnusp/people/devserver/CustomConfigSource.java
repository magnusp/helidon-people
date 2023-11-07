package github.magnusp.people.devserver;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Map;
import java.util.Set;

public class CustomConfigSource implements ConfigSource {
	private static final String NAME = "MyConfigSource";
	private static final int ORDINAL = 101; // Default for MP is 100
	private static final Map<String, String> PROPERTIES = Map.of("greetingz", "Hi");


	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public Map<String, String> getProperties() {
		return PROPERTIES;
	}

	@Override
	public Set<String> getPropertyNames() {
		return PROPERTIES.keySet();
	}

	@Override
	public String getValue(String key) {
		return PROPERTIES.get(key);
	}

	@Override
	public int getOrdinal() {
		return ORDINAL;
	}
}
