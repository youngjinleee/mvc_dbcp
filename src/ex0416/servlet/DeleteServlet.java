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

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			if(id==null || id.equals("")) {
				request.setAttribute("errMsg", "�Է°��� �����մϴ�...");
			}else {
				MemberDAO dao  =new MemberDAOImpl();
				if(dao.delete(id)>0) {
					response.sendRedirect("selectAll");
					//request.getRequestDispatcher("selectAll").forward(request, response);
					return;
				}else {
					request.setAttribute("errMsg", "�������� �ʾҽ��ϴ�");
				}
			}
		}catch (SQLException e) {
			request.setAttribute("errMsg", e.getMessage());
		}
		
		request.getRequestDispatcher("error.jsp").forward(request, response);
		
	}

}
