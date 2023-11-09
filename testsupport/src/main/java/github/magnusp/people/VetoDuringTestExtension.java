package github.magnusp.people;

import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.Extension;
import jakarta.enterprise.inject.spi.ProcessAnnotatedType;
import jakarta.enterprise.inject.spi.WithAnnotations;

public class VetoDuringTestExtension implements Extension {
	<T> void processAnnotatedType(@Observes @WithAnnotations({VetoDuringTest.class}) ProcessAnnotatedType<T> pat) {
		/* tell the container to ignore the type if it is annotated @VetoDuringTest */
		pat.veto();
	}
}
