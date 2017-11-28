package animal.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import animal.bean.BoardDBBean;
import animal.bean.BoardDataBean;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String src = "";
		String address = null;
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		BoardDBBean board = BoardDBBean.getinstance();
		
		if(action != null) {
			
		if(action.equals("login")) {
			address = "login.jsp";
		}
		
		else if(action.equals("register")) {
			address = "register.jsp";
		}
		
		else if(action.equals("logout")) {
			session.invalidate();
			address = "index.jsp";
		}
		
		else if(action.equals("login_comp")) {
			//request.setAttribute("user_id", request.getParameter("user_id"));
			//request.setAttribute("user_pw", request.getParameter("user_pw"));
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
			address = "LoginAction?user_id="+user_id+"&user_pw="+user_pw;
		}
		
		else if (action.equals("write")) {
			address = "write.jsp";
		}
		
		else if (action.equals("confirm")) {
			address = "Confirmpassword.jsp";
		}
		
		else if (action.equals("forget_id")) {
			address = "forget_id.jsp";
		}
		
		else if (action.equals("forget_pw")) {
			address = "forget_pw.jsp";
		}
		
		else if(action.equals("news_write")) {
			address = "news_write.jsp";
		}
		
		else if(action.equals("mypage")) {
			request.setAttribute("click_id", request.getParameter("click_id"));
			address = "mypage.jsp";
		}
		
		else if(action.equals("news_update")) {
			BoardDataBean boarddt = board.news_getboard(Integer.parseInt(request.getParameter("")));
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request,response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
