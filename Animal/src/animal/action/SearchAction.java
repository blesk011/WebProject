package animal.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import animal.bean.BoardDBBean;
import animal.bean.BoardDataBean;

/**
 * Servlet implementation class SearchAction
 */
@WebServlet("/SearchAction")
public class SearchAction extends HttpServlet {
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

		String searchName = null;  //검색하려는 키워드
		BoardDBBean searchService = BoardDBBean.getinstance();
		ArrayList<BoardDataBean> list = null;  //검색 결과를 가져올 list
		try {  //해당 parameter가 없을 경우
			searchName = request.getParameter("keyword");
		} catch(NullPointerException e) {  //키워드의 내용이 없을 경우
			e.printStackTrace();
			request.setAttribute("error_message", e.getMessage() +"값을 입력해주세요");  //에러 메시지 저장
		}
		try {
			searchService.searchByName(searchName);
			request.setAttribute("searchResultList", list);  //검색 결과 리스트 attribute에 저장
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/searchBoardList.jsp");
			dispatcher.forward(request, response);
		} catch(SQLException e) {
			e.printStackTrace();
			request.setAttribute("error_message",  e.getMessage());
		}
	}
}
