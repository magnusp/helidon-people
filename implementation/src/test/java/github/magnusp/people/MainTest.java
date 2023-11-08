
package github.magnusp.people;

import github.magnusp.people.infrastructure.TestcontainersConfigSource;
import io.helidon.config.Config;
import io.helidon.config.mp.MpConfig;
import io.helidon.microprofile.config.ConfigCdiExtension;
import io.helidon.microprofile.server.JaxRsCdiExtension;
import io.helidon.microprofile.server.ServerCdiExtension;
import io.helidon.microprofile.testing.junit5.AddBean;
import io.helidon.microprofile.testing.junit5.AddExtension;
import io.helidon.microprofile.testing.junit5.DisableDiscovery;
import io.helidon.microprofile.testing.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@HelidonTest
@DisableDiscovery
@AddExtension(ServerCdiExtension.class)
@AddExtension(JaxRsCdiExtension.class)
@AddExtension(CdiComponentProvider.class)
@AddBean(MockPersonRepository.class)
@AddBean(MockPersonQueries.class)
@AddBean(ConfigCdiExtension.class)
@AddBean(PersonResource.class)
class MainTest {

    @Inject
    private WebTarget target;

    @Inject
    MockPersonRepository mockPersonRepository;

    @Inject
    MockPersonQueries mockPersonQueries;


    @Test
    void testGreet() {
        GenericType<ListPersonResponse> messageListType = new GenericType<>() {
        };

        when(mockPersonQueries.getProxy().all()).thenReturn(Collections.emptyList());
        ListPersonResponse listPersonResponse = target
                .path("person")
                .request()
                .get(messageListType);
        assertThat(listPersonResponse).isNotNull();
        assertThat(listPersonResponse.values()).isNotNull();
    }
}
