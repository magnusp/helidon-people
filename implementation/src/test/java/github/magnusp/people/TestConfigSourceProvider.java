package github.magnusp.people;

import github.magnusp.people.infrastructure.TestcontainersConfigSource;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.ConfigSourceProvider;

import java.util.List;

import static java.util.Collections.emptyList;

public class TestConfigSourceProvider  implements ConfigSourceProvider {
	@Override
	public Iterable<ConfigSource> getConfigSources(ClassLoader forClassLoader) {
		//return List.of(new TestcontainersConfigSource(false));
		return emptyList();
	}
}
