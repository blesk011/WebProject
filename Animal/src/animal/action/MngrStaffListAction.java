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
 * Servlet implementation class MngrStaffListAction
 */
@WebServlet("/MngrStaffListAction")
public class MngrStaffListAction extends HttpServlet {
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
		
		ArrayList<UserDataBean> staffList = null; 
		staffList = UserDBBean.getinstance().getAllStaff();
		request.setAttribute("staffList", staffList);
		request.setAttribute("count", new Integer(staffList.size()));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("mngr/staff/staffList.jsp");
		dispatcher.forward(request, response);
	}
}
