package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String code = "";
		String message = "";
 
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		log(account + ";" + password);
//		LogUtil.log(account + ";" + password);
 
	
		Connection connect = DBUtil.getConnect();
		try {
			Statement statement = (Statement) connect.createStatement();
			String sql = "select userId from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + account
					+ "' and userPassword='" + password + "'";
//			LogUtil.log(sql);
			log(sql);
			ResultSet result = statement.executeQuery(sql);
			if (result.next()) { // 能查到该账号，说明已经注册过了
				code = "200";
				message = "登陆成功";
			} else {
 
				code = "100";
				message = "登录失败，密码不匹配或账号未注册";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
 
		response.setContentType("text/html;charset=utf-8"); // 设置响应报文的编码格式，避免浏览器中输出乱码问题
		response.getWriter().append("code:").append(code).append(";message:").append(message);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
