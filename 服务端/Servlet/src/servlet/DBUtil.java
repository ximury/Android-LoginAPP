package servlet;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBUtil {
	// table����Ӧ���ݿ�ı�
	public static final String TABLE_PASSWORD = "account";
	public static final String TABLE_USERINFO = "userinfo";
 
	// connect to MySql database
	public static Connection getConnect() {
		String url = "jdbc:mysql://localhost:3306/android"; // ���ݿ��Url��android�������ݿ���
		Connection connecter = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // java���䣬�̶�д��
			//root �����ݿ���˻����ƣ�123456���˻����룬��Ϊ�Լ���
			connecter = (Connection) DriverManager.getConnection(url, "root", "123456");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		return connecter;
	}
}