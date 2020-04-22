package ex0416.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * DB������ ���õ� 
 * �ε�, ����, �ݱ� ��� ��� Ŭ���� 
 * */
public class DbUtil {

	private static DataSource dataSource;
	/**
	 * �ε�
	 * */
	static {
		try {
		  Context context = new InitialContext();
		  dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/myoracle");
		}catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����
	 * */
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	/**
	 * �ݱ� (DML���� : insert, update, delete)
	 * */
	public static void dbClose(Connection con, Statement st){
		try {
			if(st!=null) st.close();
			if(con!=null)con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * �ݱ� (select����)
	 * */
	public static void dbClose(Connection con, Statement st, ResultSet rs) {
		try {
			if(rs!=null)rs.close();
			dbClose(con, st);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}