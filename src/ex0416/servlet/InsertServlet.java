package ex0416.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ex0416.dao.MemberDAO;
import ex0416.dao.MemberDAOImpl;
import ex0416.domain.Member;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url="error.jsp";
		String message=null;
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String age = request.getParameter("age");
		String addr = request.getParameter("addr");
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		
		if(id==null || id.equals("") || pwd==null || pwd.equals("") ||
		 age ==null || age.equals("") || addr ==null || addr.equals("") || 
		phone ==null || phone.equals("") || name==null || name.equals("") ) {
			
			request.setAttribute("errMsg", "�Է°��� ������� �ʽ��ϴ�^^");
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		
		try {
			Member member =
			  new Member(id, pwd, name, Integer.parseInt(age), phone, addr);
			
			MemberDAO dao = new MemberDAOImpl();
			//���̵� �ߺ�üũ����
			if(dao.idCheck(id)) {//�ߺ�
				message=id+"�� �ߺ��Դϴ�.";
			}else {
				if(dao.insert(member) > 0) {
					//�����̴�.. select�� �̵��Ѵ�. - 
					response.sendRedirect("selectAll");
					return;
				}else {
					message="��ϵ��� �ʾҽ��ϴ�.";
				}
			}
			
		}catch (NumberFormatException e) {
			message ="���̴� ���ڸ� �Է��ϼ���.";
		}catch (SQLException e) {
			message = e.getMessage();
		}
		
		request.setAttribute("errMsg", message);
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}








