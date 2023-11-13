package github.magnusp.people;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;

import javax.sql.DataSource;

@ApplicationScoped
@Default
public class DataSourceProducer {
	@Produces
	DataSource createDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:postgresql://127.0.0.1:5432/helidonpeople");
		config.setUsername("postgres");
		config.setPassword("postgres");
		config.addDataSourceProperty("cachePrepStmts", "false");
		return new HikariDataSource(config);
	}
}
