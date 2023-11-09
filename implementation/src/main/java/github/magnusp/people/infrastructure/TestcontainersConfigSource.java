package github.magnusp.people.infrastructure;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.OutputFrame;
import org.testcontainers.containers.output.ToStringConsumer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestcontainersConfigSource implements ConfigSource {
	private static final String NAME = "TestcontainersConfigSource";
	private static final int ORDINAL = 200; // Default for MP is 100
	private static final Map<String, String> PROPERTIES = new HashMap<>();
	private final boolean withReuse;
	private PostgreSQLContainer postgresContainer;

	public TestcontainersConfigSource(boolean withReuse) {
		this.withReuse = withReuse;
	}

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
		if(!key.startsWith("javax.")) {
			return null;
		}
		if (this.postgresContainer == null) {
			var container = new PostgreSQLContainer("postgres:16")
				.withExposedPorts(5432)
				.waitingFor(
					Wait.forLogMessage(".*database system is ready to accept connections\\n", 1)
				)
				.withReuse(withReuse);
			this.postgresContainer = (PostgreSQLContainer)container;

			container.start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		if (key.endsWith(".dataSourceClassName")) {
			PROPERTIES.computeIfAbsent(key, s -> {
				return "org.postgresql.ds.PGSimpleDataSource";
			});
		} else if (key.endsWith(".dataSource.url")) {
			PROPERTIES.computeIfAbsent(key, s -> {
				var containerUrl = postgresContainer.getJdbcUrl();
				var formatted = "%s&TC_REUSABLE=true".formatted(containerUrl);
				return formatted;
			});
		} else if (key.endsWith(".dataSource.user")) {
			PROPERTIES.computeIfAbsent(key, s -> {
				return postgresContainer.getUsername();
			});
		} else if (key.endsWith(".dataSource.password")) {
			PROPERTIES.computeIfAbsent(key, s -> {
				return postgresContainer.getPassword();
			});
		}
		return PROPERTIES.get(key);
	}

	@Override
	public int getOrdinal() {
		return ORDINAL;
	}
}
