package github.magnusp.people.devserver;

import java.util.ServiceLoader;

import io.helidon.common.HelidonServiceLoader;
import io.helidon.common.SerializationConfig;
import io.helidon.logging.common.LogConfig;
import io.helidon.spi.HelidonStartupProvider;

/**
 * Main entry point for any Helidon application.
 * {@link java.util.ServiceLoader} is used to discover the correct {@link io.helidon.spi.HelidonStartupProvider}
 * to start the application (probably either Helidon Injection based application, or a CDI based application).
 * <p>
 * The default option is to start Helidon injection based application.
 */
public class Main {
	static {
		LogConfig.initClass();
	}

	private Main() {
	}

	/**
	 * Start Helidon.
	 * This method is required to start directly from a command line.
	 *
	 * @param args arguments of the application
	 */
	public static void main(String[] args) {
		// we always initialize logging
		LogConfig.configureRuntime();
		// and make sure JEP-290 is enforced (deserialization)
		SerializationConfig.configureRuntime();

		// this should only be called once in a lifetime of the server, so no need to optimize
		var services = HelidonServiceLoader.create(ServiceLoader.load(HelidonStartupProvider.class))
			.asList();

		if (services.isEmpty()) {
			throw new IllegalStateException("Helidon Main class can only be called if a startup provider is available. "
				+ "Please use either Helidon Injection, or Helidon MicroProfile "
				+ "(or a custom extension). If neither is available, you should use "
				+ "your own Main class.");
		}
		services.get(0).start(args);
	}
}
