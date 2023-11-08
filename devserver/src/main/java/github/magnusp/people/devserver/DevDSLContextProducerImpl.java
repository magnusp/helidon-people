package github.magnusp.people.devserver;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Dependent
@Priority(10000)
public class DevDSLContextProducerImpl {
	@Inject
	DataSource dataSource;

	@Produces
	@Alternative
	public DSLContext dslContext() {
		return DSL.using(dataSource, SQLDialect.POSTGRES);
	}

}
