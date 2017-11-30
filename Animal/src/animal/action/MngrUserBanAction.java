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
 * Servlet implementation class MngrUserBanAction
 */
@WebServlet("/MngrUserBanAction")
public class MngrUserBanAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String user_id = request.getParameter("user_id");
		UserDBBean.getinstance().banUser(user_id);
		
		ArrayList<UserDataBean> userList = null; //삭제 후 보여줄 리스트
		userList = UserDBBean.getinstance().getAllUser();
		request.setAttribute("userList", userList);
		request.setAttribute("count", new Integer(userList.size()));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("mngr/member/fullMemberManage.jsp");
		dispatcher.forward(request, response);
	}
}
