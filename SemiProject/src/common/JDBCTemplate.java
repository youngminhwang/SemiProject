package common;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
	//JDBC_Driver를 Oracle_RDBMS_Client로 Set
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	//Oracle_DB 계정 정보 Set
	private static final String USERNAME = "semiproject";
	private static final String PASSWORD = "1234";
	
	//private 생성자(= Singleton_Pattern 적용)
	private JDBCTemplate() {}
	
	//DB_Connetion_객체_Handler
	private static Connection conn;
	
	//DB_Connetion_객체를 생성하여 반환해줄 static_Method
	public static Connection getConnection() {
		
		if(conn == null) {	//conn_객체_Handler로 현재 handling되고 있는 특정 DB_Connetion_객체가 존재하지 않을 경우,   
		
			try {
				
				//Oracle_RDBMS_Client로 Setting된 JDBC_Driver를 메모리에 Load
				//= Oracle_RDBMS_Client 기능이 구현돼있는 static_클래스를 메모리에 Load 
				Class.forName(DRIVER);
			
				//DB_Connetion_객체(= 특정_DB_접속에 필요한 data) 생성 후, 객체_Handler에 참조시킴
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				
				//AutoCommit_OFF
				conn.setAutoCommit(false);
			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//conn_객체_Handler로 현재 handling되고 있는 특정 DB_Connetion_객체가 존재할 경우,   
		return conn;
	}
	
	//DB 접속 해제
	public static void close(Connection conn) {
		
		try {
			
			if( (conn != null) && !conn.isClosed() ) {	
				conn.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	//Statement_객체 닫기
	public static void close(Statement st) {
		
		try {
			
			if( (st != null) && !st.isClosed() ) {
				st.close();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	//PreparedStatement_객체 닫기
	public static void close(PreparedStatement ps) {
		
		try {
			
			if( (ps != null) && !ps.isClosed() ) {
				ps.close();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//ResultSet_객체 닫기
	public static void close(ResultSet rs) {
		
		try {
			
			if( (rs != null) && !rs.isClosed() ) {
				rs.close();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Commit 수행
	public static void commit(Connection conn) {
		
		try {
			
			if( (conn != null) && !conn.isClosed() ) {
				conn.commit();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
			
	//Rollback 수행
	public static void rollback(Connection conn) {
		
		try {
			
			if( (conn != null) && !conn.isClosed() ) {
				conn.rollback();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
			
}
