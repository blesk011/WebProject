package animal.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import animal.bean.UserDBBean;
import animal.bean.UserDataBean;

/**
 * Servlet implementation class MngrUserListAction
 */
@WebServlet("/MngrUserAction")
public class MngrUserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String action = request.getParameter("action");
		
		if(action != null) {
			if(action.equals("banUser")) {
				UserDBBean.getinstance().banUser(request.getParameter("user_id"));
			}
			else if(action.equals("appointStaff")){
				UserDBBean.getinstance().appointStaff(request.getParameter("user_id"));
			}
		}

		ArrayList<UserDataBean> userList = null;
		userList = UserDBBean.getinstance().getAllUser();
		request.setAttribute("userList", userList);
		request.setAttribute("count", new Integer(userList.size()));

		RequestDispatcher dispatcher = request.getRequestDispatcher("mngr/member/fullMemberManage.jsp");
		dispatcher.forward(request, response);
	}
}
