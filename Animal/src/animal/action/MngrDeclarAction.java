package animal.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import animal.bean.BoardDBBean;
import animal.bean.UserDBBean;
import animal.bean.UserDataBean;

/**
 * Servlet implementation class MngrDeclarListAction
 */
@WebServlet("/MngrDeclarAction")
public class MngrDeclarAction extends HttpServlet {
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

		String action = request.getParameter("action");

		if(action != null) {
			if(action.equals("boardDelete")){
				BoardDBBean.getinstance().news_delete(Integer.parseInt(request.getParameter("board_num")));
			}
			else if(action.equals("banUser")) {
				String user_id = request.getParameter("user_id");
				UserDBBean.getinstance().banUser(user_id);
			}
		}

		ArrayList<UserDataBean> declarList = null; 
		declarList = UserDBBean.getinstance().getAllStaff();
		//수정해야함
		request.setAttribute("declarList", declarList);
		request.setAttribute("count", new Integer(declarList.size()));

		RequestDispatcher dispatcher = request.getRequestDispatcher("mngr/declar/declarManage.jsp");
		dispatcher.forward(request, response);
	}
}
