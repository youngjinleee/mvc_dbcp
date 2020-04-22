package ex0416.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ex0416.dao.MemberDAO;
import ex0416.dao.MemberDAOImpl;
import ex0416.domain.Member;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���۵Ǵ� keyField keyWord�ޱ�
		String keyField = request.getParameter("keyField");
		String keyWord = request.getParameter("keyWord");
	    String url="error.jsp";
	   try { 
			if(keyField==null || keyField.equals("") || 
					keyWord==null || keyWord.equals("")){
				request.setAttribute("errMsg", "�˻��ʵ�� �˻��ܾ� ���� ������� �ʽ��ϴ�.");
				
			}else {
				MemberDAO dao = new MemberDAOImpl();
				List<Member> list = dao.searchByKeyword(keyField, keyWord);
				request.setAttribute("list", list);
				url="memberSelect.jsp";
			}
			
	    }catch (SQLException e) {
		    request.setAttribute("errMsg",  e.getMessage());
	    }
	   
	   request.getRequestDispatcher(url).forward(request, response);
		
	}

	

}





