
package github.magnusp.people;

import io.helidon.microprofile.testing.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@HelidonTest
class MainTest {

    @Inject
    private WebTarget target;


    @Test
    void testGreet() {
        GenericType<ListPersonResponse> messageListType = new GenericType<>() {
        };

        ListPersonResponse listPersonResponse = target
                .path("person")
                .request()
                .get(messageListType);
        assertThat(listPersonResponse).isNotNull();
    }

}
