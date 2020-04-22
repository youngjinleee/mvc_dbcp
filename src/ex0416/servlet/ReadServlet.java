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
 * Servlet implementation class ReadServlet
 */
@WebServlet("/read")
public class ReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if(id==null || id.equals("")) {
			request.setAttribute("errMsg", "id������ ��� ��ȸ �Ҽ� �����ϴ�.");
		    request.getRequestDispatcher("error.jsp").forward(request, response);
		    return;
		}
		
		String url="error.jsp";
		try {
			MemberDAO dao = new MemberDAOImpl();
			Member member = dao.selectById(id);
			request.setAttribute("member", member);
			url="read.jsp";
		}catch (SQLException e) {
			request.setAttribute("errMsg", "��ȸ �Ҽ� �����ϴ�..");
			
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}








