package jdbc;


import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		String poolConfig = sce.getServletContext().getInitParameter("poolConfig");
		
		Properties prop = new Properties();
		try {
			prop.load(new StringReader(poolConfig));
		}catch (IOException e) {
			throw new RuntimeException("config load fail", e);
		}
		loadJDBCDriver(prop);
		initConnectionPool(prop);
	}

	private void loadJDBCDriver(Properties prop) {
		String driverClass = prop.getProperty("jdbcdriver");
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}
	
	private void initConnectionPool(Properties prop) {
		try {
			String jdbcUrl = prop.getProperty("jdbcUrl");
			// String jdbcDriver = "jdbc:mysql://localhost:3306/chap14?" +
			//					"useUnicode=true&characterEncoding=utf8";
			// String jdbcDriver = "jdbc:mysql://192.168.1.5:3306/chap14?" + 
			//					"serverTimezone=UTC&allowPublicKeyRetrieval=true";
			String username = prop.getProperty("dbUser");
			String pw = prop.getProperty("dbPass");
			
			// 커넥션 풀이 새로운 커넥션을 생성할 때 사용할 커넥션 팩토리를 생성한다.
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, username, pw);
											// MySQL 연결에 사용할 jdbcUrl, username, pw를 생성자로 지정한다.
			
			// PoolableConnection을 생성하는 팩토리를 생성한다.
			// DBCP는 커넥션 풀에 커넥션을 보관할 때 PoolableConnection을 사용한다.
			// 이 클래스는 내부적으로 실제 커넥션을 담고 있으며,
			// 커넥션 풀을 관리하는데 필요한 기능을 추가로 제공한다.
			// 예를 들면, close() 메서드를 실행하면 실제 커넥션을 종료하지 않고 풀에 커넥션을 반환한다.
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			
			String validationQuery = prop.getProperty("validationQuery");
			if(validationQuery != null && !validationQuery.isEmpty()) {
				poolableConnFactory.setValidationQuery("select 1");
			}
			// DB 커넥션이 유효한지 여부를 검사할 때 사용할 쿼리를 지정합니다.
			// 이것은 특정 시간마다 트랜잭션DB 세션 재접속을 검증하는 validationQuery를 실행하게 됩니다.
			// 오라클의 경우 : validationQuery="select 1 from dual" 환경을 셋팅합니다.
			
			
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig(); // 커넥션 풀의 설정 정보를 생성한다.
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L); // 유휴 커넥션 검사 주기(1000L*60L*5L) 설정
			poolConfig.setTestWhileIdle(true); // 풀에 보관중인 커넥션 유효 검사 설정(true)
			int minIdle = getIntProperty(prop, "minIdle", 5);
			poolConfig.setMinIdle(minIdle); // 커넥션 최소 개수 (4) 설정
			int maxTotal = getIntProperty(prop, "maxTotal", 50);
			poolConfig.setMaxTotal(maxTotal); // 커넥션 최대 개수 (50) 설정
			
			// 커넥션 풀을 생성한다. 생성자는 PoolableConnection을 생성할 때 사용할 팩토리(PoolableConnectionFactory)와
			// 커넥션 풀 설정(GenericObjectPoolConfig)을 파라미터로 전달 받는다.
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);
			// PoolableConnectionFactory에서도 GenericObjectPool에서 생성한 커넥션 풀을 연결한다.
			poolableConnFactory.setPool(connectionPool);
			
			// 커넥션 풀을 제공하는 JDBC 드라이버를 등록한다.
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			String poolName = prop.getProperty("poolName");
//			if(poolName == null) poolName = "pool";
			
			// 커넥션 풀 드라이버에 GenericObjectPool에서 생성한 커넥션 풀을 등록한다.
			// 이때, "chap14"를 커넥션 풀 이름으로 주었는데,
			// 이 경우 프로그램에서 사용하는 JDBC URL은 "jdbc:apache:commons:dbcp:chap14"가 된다.
			driver.registerPool(poolName, connectionPool);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private int getIntProperty(Properties prop, String propName, int defaultValue) {
		String value = prop.getProperty(propName);
		if(value == null) return defaultValue;
		return Integer.parseInt(value);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {}
}
