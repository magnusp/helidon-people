package github.magnusp.people.devserver;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.ConfigSourceProvider;

import java.util.List;

public class MyConfigSourceProvider implements ConfigSourceProvider {
	@Override
	public Iterable<ConfigSource> getConfigSources(ClassLoader forClassLoader) {
		return List.of(new CustomConfigSource());
	}
}
