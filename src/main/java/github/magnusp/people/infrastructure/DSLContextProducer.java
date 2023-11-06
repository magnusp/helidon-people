package github.magnusp.people.infrastructure;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;

@Dependent
public class DSLContextProducer {
	@Inject
	DataSource dataSource;

	@Produces
	public DSLContext dslContext() {
		return DSL.using(dataSource, SQLDialect.H2);
	}


}
