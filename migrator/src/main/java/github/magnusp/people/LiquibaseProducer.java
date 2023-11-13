package github.magnusp.people;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import liquibase.integration.jakarta.cdi.CDILiquibaseConfig;
import liquibase.integration.jakarta.cdi.annotations.LiquibaseType;
import liquibase.resource.ResourceAccessor;
import liquibase.resource.SearchPathResourceAccessor;

import javax.sql.DataSource;

@Dependent
public class LiquibaseProducer {
	@Inject
	DataSource dataSource;

	@Produces
	@LiquibaseType
	public CDILiquibaseConfig createConfig() {
		CDILiquibaseConfig config = new CDILiquibaseConfig();
		config.setChangeLog("changelog.xml");
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
		return new SearchPathResourceAccessor();
	}

}
