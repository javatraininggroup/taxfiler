/*
 * package com.company.taxfiler;
 * 
 * import java.sql.SQLException; import java.util.HashMap;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.apache.tomcat.jdbc.pool.PoolProperties; import
 * org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
 * 
 * public class CustomRoutingDataSource extends AbstractRoutingDataSource {
 * 
 * private static String dataSourceURL = "spring.datasource.default.url";
 * private static String dataSourceUsername =
 * "spring.datasource.default.username"; private static String
 * dataSourcePassword = "spring.datasource.default.password"; private static
 * String dataSourceDriverClassName =
 * "spring.datasource.default.driverClassName";
 * 
 * private static String dataSourceInitialSize =
 * "spring.datasource.initialSize"; private static String dataSourceMaxActive =
 * "spring.datasource.maxActive"; private static String dataSourceTestOnBorrow =
 * "spring.datasource.testOnBorrow"; private static String
 * dataSourceTestWhileIdle = "spring.datasource.testWhileIdle"; private static
 * String dataSourceTestOnReturn = "spring.datasource.testOnReturn"; private
 * static String dataSourceValidationQuery =
 * "spring.datasource.validationQuery";
 * 
 * @Override protected Object determineCurrentLookupKey() { return "DEFAULT"; }
 * 
 * public static DataSource dataSource() throws SQLException {
 * CustomRoutingDataSource customRoutingDataSource = new
 * CustomRoutingDataSource(); HashMap<Object, Object> targetDataSources = new
 * HashMap<>();
 * 
 * DataSource tenantDataSource = createPooledDataSourceForTenant();
 * targetDataSources.put("DEFAULT", tenantDataSource);
 * 
 * customRoutingDataSource.setTargetDataSources(targetDataSources); return
 * customRoutingDataSource; }
 * 
 * private static DataSource createPooledDataSourceForTenant() {
 * 
 * String url = "jdbc:mysql://localhost:3306/tax_filer"; String driverClass =
 * "com.mysql.jdbc.Driver"; String username = "u4ia"; String password =
 * "mySQLu4ia";
 * 
 * PoolProperties p = new PoolProperties(); // Set the connection and login
 * properties. p.setUrl(url); p.setDriverClassName(driverClass);
 * p.setUsername(username); p.setPassword(password);
 * 
 * // Set the connection testing p.setTestWhileIdle(true);
 * p.setTestOnBorrow(false); p.setTestOnReturn(false);
 * p.setValidationQuery("SELECT 1"); p.setValidationInterval(30000);
 * p.setTimeBetweenEvictionRunsMillis(30000); p.setMaxActive(100);
 * p.setInitialSize(1); p.setMaxWait(10000); p.setRemoveAbandonedTimeout(60);
 * p.setMinEvictableIdleTimeMillis(30000); p.setMinIdle(10);
 * p.setLogAbandoned(true); p.setRemoveAbandoned(true); p.setJdbcInterceptors(
 * "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
 * "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;" +
 * "org.apache.tomcat.jdbc.pool.interceptor.ResetAbandonedTimer"); DataSource
 * dataSource = new org.apache.tomcat.jdbc.pool.DataSource(p); return
 * dataSource; }
 * 
 * }
 */