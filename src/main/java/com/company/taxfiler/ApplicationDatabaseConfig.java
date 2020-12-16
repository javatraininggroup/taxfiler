/*
 * package com.company.taxfiler;
 * 
 * import java.sql.SQLException;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.data.jpa.repository.config.EnableJpaRepositories; import
 * org.springframework.orm.jpa.JpaTransactionManager; import
 * org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean; import
 * org.springframework.orm.jpa.vendor.Database; import
 * org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
 * 
 * @Configuration
 * 
 * @EnableJpaRepositories public class ApplicationDatabaseConfig {
 * 
 * @Bean public DataSource dataSource() throws SQLException { return
 * CustomRoutingDataSource.dataSource(); }
 * 
 * @Bean public LocalContainerEntityManagerFactoryBean entityManagerFactory()
 * throws SQLException { HibernateJpaVendorAdapter vendorAdapter = new
 * HibernateJpaVendorAdapter(); vendorAdapter.setDatabase(Database.MYSQL);
 * LocalContainerEntityManagerFactoryBean factory = new
 * LocalContainerEntityManagerFactoryBean();
 * factory.setJpaVendorAdapter(vendorAdapter);
 * factory.setPackagesToScan(getClass().getPackage().getName());
 * factory.setDataSource(dataSource()); return factory; }
 * 
 * @Bean public JpaTransactionManager transactionManager() throws SQLException {
 * JpaTransactionManager transactionManager = new JpaTransactionManager();
 * transactionManager.setEntityManagerFactory(entityManagerFactory()
 * .getObject()); return transactionManager; }
 * 
 * }
 */