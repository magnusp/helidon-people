package github.magnusp.people;

import github.magnusp.people.infrastructure.VetoDuringTest;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.BeforeBeanDiscovery;
import jakarta.enterprise.inject.spi.Extension;
import jakarta.enterprise.inject.spi.ProcessAnnotatedType;
import jakarta.enterprise.inject.spi.WithAnnotations;

public class VetoDuringTestExtension implements Extension {
	<T> void processAnnotatedType(@Observes @WithAnnotations({VetoDuringTest.class}) ProcessAnnotatedType<T> pat) {
		/* tell the container to ignore the type if it is annotated @Ignore */
		pat.veto();
	}
}
