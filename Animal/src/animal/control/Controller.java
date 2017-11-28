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
import animal.bean.UserDBBean;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = null;
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		BoardDBBean board = BoardDBBean.getinstance();
		UserDBBean user = UserDBBean.getinstance();
		
		//action이 null이 아닐 경우에만 수행
		if(action != null) {
		
		//로그인폼을 매칭시켜주는 부분
		if(action.equals("login")) {
			address = "login.jsp";
		}
		
		//회원가입폼을 매칭시켜주는 부분
		else if(action.equals("register")) {
			address = "register.jsp";
		}
		
		//로그아웃 시켜주는 부분
		else if(action.equals("logout")) {
			session.invalidate();
			address = "index.jsp";
		}
		
		//로그인을 처리해주는 부분
		else if(action.equals("login_comp")) {
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
			
			if(user_id == null || user_id.equals("") || user_pw == null || user_pw.equals("")) {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
				address = "login.jsp";
			}
			
			else {
				int result = user.login(user_id, user_pw);
				
				if(result == 1) {
					request.getSession().setAttribute("user_id", user_id);
					address = "index.jsp";
				}
				
				else if(result == 0) {
					request.getSession().setAttribute("messageType", "오류 메세지");
					request.getSession().setAttribute("messageContent", "아이디 혹은 비밀번호가 맞지 않습니다.");
					address = "login.jsp";
				}
				
				else {
					request.getSession().setAttribute("messageType", "오류 메시지");
					request.getSession().setAttribute("messageContent", "내부적인 오류입니다. 다시 시도해 주세요.");
					address = "login.jsp";
				}
			}
			
			address = "LoginAction?user_id="+user_id+"&user_pw="+user_pw;
		}
		
		//글쓰기폼을 매칭시켜주는 부분
		else if (action.equals("write")) {
			address = "write.jsp";
		}
		
		//비밀번호 확인을 매칭시켜주는 부분
		else if (action.equals("confirm")) {
			address = "Confirmpassword.jsp";
		}
		
		//아이디찾기폼을 매칭시켜주는 부분
		else if (action.equals("forget_id")) {
			address = "forget_id.jsp";
		}
		
		//비밀번호찾기 폼을 매칭시켜주는부분
		else if (action.equals("forget_pw")) {
			address = "forget_pw.jsp";
		}
		
		//뉴스피드 글쓰기폼을 매칭시켜주는 부분
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
