
package github.magnusp.people;

import io.helidon.microprofile.testing.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@HelidonTest
class MainTest {

    @Inject
    private WebTarget target;

    @Inject
    MockPersonQueries mockPersonQueries;

    @BeforeEach
    void beforeEach() {
        if(mockPersonQueries==null) {
            return;
        }
        Mockito.reset(mockPersonQueries.getProxy());
    }

    @Test
    void testGreet(
    ) {
        GenericType<ListPersonResponse> messageListType = new GenericType<>() {
        };

        doReturn(List.of(new Person(1, "Foo"))).when(mockPersonQueries.getProxy()).all();

        ListPersonResponse listPersonResponse = target
                .path("person")
                .request()
                .get(messageListType);
        assertThat(listPersonResponse).isNotNull();
        assertThat(listPersonResponse.values()).hasSize(1);
    }

    @Test
    void testGreet2(
    ) {
        GenericType<ListPersonResponse> messageListType = new GenericType<>() {
        };


        ListPersonResponse listPersonResponse = target
            .path("person")
            .request()
            .get(messageListType);
        assertThat(listPersonResponse).isNotNull();
        assertThat(listPersonResponse.values()).hasSize(1);
    }
}
