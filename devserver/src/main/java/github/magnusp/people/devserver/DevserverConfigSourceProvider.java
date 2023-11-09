package github.magnusp.people.devserver;

import github.magnusp.people.infrastructure.TestcontainersConfigSource;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.ConfigSourceProvider;

import java.util.List;

public class DevserverConfigSourceProvider implements ConfigSourceProvider {
	@Override
	public Iterable<ConfigSource> getConfigSources(ClassLoader forClassLoader) {
		return List.of(new TestcontainersConfigSource(true));
	}
}
