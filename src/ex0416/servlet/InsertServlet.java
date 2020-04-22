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
			
			request.setAttribute("errMsg", "입력값이 충분하지 않습니다^^");
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		
		try {
			Member member =
			  new Member(id, pwd, name, Integer.parseInt(age), phone, addr);
			
			MemberDAO dao = new MemberDAOImpl();
			//아이디 중복체크하자
			if(dao.idCheck(id)) {//중복
				message=id+"는 중복입니다.";
			}else {
				if(dao.insert(member) > 0) {
					//성공이다.. select로 이동한다. - 
					response.sendRedirect("selectAll");
					return;
				}else {
					message="등록되지 않았습니다.";
				}
			}
			
		}catch (NumberFormatException e) {
			message ="나이는 숫자만 입력하세요.";
		}catch (SQLException e) {
			message = e.getMessage();
		}
		
		request.setAttribute("errMsg", message);
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}








