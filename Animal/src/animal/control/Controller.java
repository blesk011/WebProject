
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
import animal.bean.DeclarationDBBean;
import animal.bean.DeclarationDataBean;
import animal.bean.LikeDBBean;
import animal.bean.UserDBBean;
import animal.bean.UserDataBean;

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
		LikeDBBean like = LikeDBBean.getinstance();
		DeclarationDBBean declaration = DeclarationDBBean.getinstance();

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
				request.setAttribute("action", request.getParameter("goal"));
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

			//타임라인 폼을 매칭시켜주는 부분
			else if(action.equals("mypage")) {
				request.setAttribute("click_id", request.getParameter("click_id"));
				address = "mypage.jsp";
			}

			//타임라인 글 수정 폼을 매칭시켜주는 부분
			else if(action.equals("news_update")) {
				request.setAttribute("board_num",request.getParameter("board_num"));
				address = "news_update.jsp";
			}

			//타임라인 글 삭제
			else if(action.equals("news_delete")) {
				board.news_delete(Integer.parseInt(request.getParameter("board_num")));
				request.setAttribute("click_id", request.getSession().getAttribute("user_id"));
				address = "mypage.jsp";
			}

			//신고 폼으로 매칭시켜주는 부분
			else if(action.equals("declaration")) {
				request.setAttribute("board_num", request.getParameter("board_num"));
				request.setAttribute("click_id", request.getSession().getAttribute("user_id"));
				request.setAttribute("news_num", request.getParameter("news_num"));
				address = "declaration.jsp";
			}

			//신고 DB에 저장시켜주는 부분
			else if(action.equals("declaration_comp")){
				DeclarationDataBean declarationdt = new DeclarationDataBean();
				declarationdt.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
				declarationdt.setUser_id((String)request.getSession().getAttribute("user_id"));
				declarationdt.setDeclaration_content(request.getParameter("declaration_content"));

				if(declaration.check_id(declarationdt) == 1) {
					System.out.println("잉");
					request.getSession().setAttribute("messageType", "오류 메시지");
					request.getSession().setAttribute("messageContent", "이미 신고 하셨습니다.");
					request.setAttribute("board_num", declarationdt.getBoard_num());
					address = "index.jsp";
				}

				else {
					declaration.declaration(declarationdt);
					request.getSession().setAttribute("messageType", "확인 메시지");
					request.getSession().setAttribute("messageContent", "신고 완료했습니다.");
					if(request.getParameter("news_num") == null) {
						request.setAttribute("board_num", declarationdt.getBoard_num());
						address = "index.jsp"; //board만들어지면 경로 수정
					}
					else {
						request.setAttribute("click_id", request.getSession().getAttribute("user_id"));
						address = "mypage.jsp";
					}
				}
			}

			//회원정보 수정 폼으로 매칭
			else if(action.equals("update_user")) {
				UserDataBean userdt = user.getUser((String)request.getSession().getAttribute("user_id"));
				if(request.getParameter("confirm_pw").equals(userdt.getUser_pw())) {
					request.setAttribute("user_name", userdt.getUser_name());
					request.setAttribute("user_pw", userdt.getUser_pw());
					request.setAttribute("user_phone", userdt.getUser_phone());
					address = "user_update.jsp";
				}

				else {
					request.getSession().setAttribute("messageType", "오류 메시지");
					request.getSession().setAttribute("messageContent", "비밀번호가 틀렸습니다.");
					address = "Confirmpassword.jsp";
				}
			}

			else if(action.equals("user_update_comp")) {
				UserDataBean userdt = new UserDataBean();
				userdt.setUser_pw(request.getParameter("user_pw"));
				userdt.setUser_phone(request.getParameter("user_phone"));
				userdt.setUser_id((String)request.getSession().getAttribute("user_id"));
				String check_passwd = request.getParameter("check_passwd");

				if(!userdt.getUser_pw().equals(check_passwd) || userdt.getUser_pw() == null || userdt.getUser_pw().equals("") || userdt.getUser_phone() == null || userdt.getUser_phone().equals("") || check_passwd == null || check_passwd.equals("")) {
					request.setAttribute("user_name", userdt.getUser_name());
					request.setAttribute("user_pw", userdt.getUser_pw());
					request.setAttribute("user_phone", userdt.getUser_phone());
					request.getSession().setAttribute("messageType", "오류 메시지");
					request.getSession().setAttribute("messageContent", "다시 확인해 주세요.");
					address = "user_update.jsp";
				}

				else {
					user.update_user(userdt);
					request.setAttribute("click_id", userdt.getUser_id());
					address = "mypage.jsp";
				}
			}
			//탈퇴 버튼 누를 경우
			else if(action.equals("delete_user")) {
				user.deleteUser((String)request.getSession().getAttribute("user_id"));
				session.invalidate();
				address = "index.jsp";
			}
			//게시판을 누를 경우
			else if(action.equals("boardAction")) {
				request.setAttribute("cate_num", request.getParameter("cate_num"));
				address = "board.jsp";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request,response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
