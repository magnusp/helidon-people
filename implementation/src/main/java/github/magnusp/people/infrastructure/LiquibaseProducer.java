package github.magnusp.people.infrastructure;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import liquibase.integration.jakarta.cdi.CDILiquibaseConfig;
import liquibase.integration.jakarta.cdi.annotations.LiquibaseType;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import javax.sql.DataSource;

@Dependent
public class LiquibaseProducer {
	@Inject
	DataSource dataSource;

	@Produces
	@LiquibaseType
	public CDILiquibaseConfig createConfig() {
		CDILiquibaseConfig config = new CDILiquibaseConfig();
		config.setChangeLog("liquibase/changelog.xml");
		return config;
	}

	@Produces
	@LiquibaseType
	public DataSource createDataSource() {
		return dataSource;
	}

	@Produces
	@LiquibaseType
	public ResourceAccessor createResourceAccessor() {
		return new ClassLoaderResourceAccessor(getClass().getClassLoader());
	}

}
