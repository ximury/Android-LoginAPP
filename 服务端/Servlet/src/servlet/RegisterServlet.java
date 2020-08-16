package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String account = request.getParameter("account"); // �� request �л�ȡ��Ϊ account �Ĳ�����ֵ
		String password = request.getParameter("password"); // �� request �л�ȡ��Ϊ password �Ĳ�����ֵ
		System.out.println("account:" + account + "\npassword:" + password); // ��ӡ������һ��
 
		String resCode = "";
		String resMsg = "";
		String userId = "";
		
		
		/* ����������һ����򵥵�ע���߼�����Ȼ�����ʵ��ҵ������൱���� */
		try {
			Connection connect = DBUtil.getConnect();
			Statement statement = (Statement) connect.createStatement(); // Statement�������Ϊ���ݿ����ʵ���������ݿ�����в�����ͨ������ʵ��
			ResultSet result;
			
			String sqlQuery = "select * from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + account + "'";
			
			// ��ѯ���������һ��ResultSet���ϣ�û�в鵽���ʱResultSet�ĳ���Ϊ0
			result = statement.executeQuery(sqlQuery); // �Ȳ�ѯͬ�����˺ţ������ֻ��ţ��Ƿ����
			if(result.next()){ // �Ѵ���
				resCode = "201";
				resMsg = "���˺���ע�ᣬ��ʹ�ô��˺�ֱ�ӵ�¼��ʹ�������˺�ע��";
				userId = "";
			} else { // ������
				String sqlInsertPass = "insert into " + DBUtil.TABLE_PASSWORD + "(userAccount,userPassword) values('"+account+"','"+password+"')";
				// �������������һ��int���͵�ֵ������ò���Ӱ�쵽������
				int row1 = statement.executeUpdate(sqlInsertPass); // �����ʺ�����
				if(row1 == 1){
					String sqlQueryId = "select userId from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + account + "'";
					ResultSet result2 = statement.executeQuery(sqlQueryId); // ��ѯ������¼��userId
					if(result2.next()){
						userId = result2.getInt("userId") + "";
					}
					int new_userId=Integer.valueOf(userId);
					String sqlInsertInfo = "insert into " + DBUtil.TABLE_USERINFO + "(userId) values('" + new_userId + "')";
					int row2 = statement.executeUpdate(sqlInsertInfo); // ���û���Ϣ���в����ע���Id
					if(row2 == 1){ // �������ж�����ɹ�����ҵ��Ƕ��϶�Ϊע��ɹ�
						resCode = "100";
						resMsg = "ע��ɹ�";
					}
				} else {
					resCode = "202";
					resMsg = "�û���Ϣ��������";
					userId = "";
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HashMap<String, String> map = new HashMap<>();
		map.put("resCode", resCode);
		map.put("resMsg", resMsg);
		map.put("userId", userId);
		
		response.setContentType("text/html;charset=utf-8"); // ������Ӧ���ĵı����ʽ
		PrintWriter pw = response.getWriter(); // ��ȡ response �������
		pw.println(map.toString()); // ͨ���������ҵ���߼��Ľ�����
		pw.flush();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
