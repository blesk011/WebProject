
package animal.control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import animal.bean.BoardDBBean;
import animal.bean.BoardDataBean;
import animal.bean.CateDBBean;
import animal.bean.CateDataBean;
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
		String board_path = "C:\\Users\\UNS\\Documents\\WS\\Animal\\image";
		String enType = "utf-8";
		int maxSize = 1024 * 1024 * 1024; 
		
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
				String k = request.getParameter("cate_num");
				int cate_num = Integer.parseInt(k);
				CateDBBean cate = CateDBBean.getinstance();
				BoardDBBean b = BoardDBBean.getinstance();
				ArrayList<BoardDataBean> blist = b.getCateBoardList(cate_num);	//카테고리의 게시글 리스트
				CateDataBean c = cate.getBoard(cate_num); 						//카테고리의 정보 (이름, 번호)
				request.setAttribute("cate_num", cate_num);
				request.setAttribute("cate_name", c.getCate_name());
				request.setAttribute("boardlist", blist);
				address = "board.jsp";
			}
			//글쓰기 확인을 누를 경우
			else if(action.equals("writeAction")) {
				System.out.println(1);
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				System.out.println(1);
				BoardDataBean boarddt = new BoardDataBean();
				Enumeration oldFileNames = null;
				File oldFile = null;
				File newFile = null;
		    	String board_image = "";
		    	String newFileName = "";
		    	int count = 1;
		    	System.out.println(1);
		    	//<시작>업로드 된 파일 저장---------------------------------------------------------------------------------------------------------------
				MultipartRequest multipartrequest = new MultipartRequest(request, board_path, maxSize, enType ,new DefaultFileRenamePolicy());
				
				System.out.println(1);
				//파라미터값 받아오기
				boarddt.setBoard_title(multipartrequest.getParameter("board_title"));
				boarddt.setCate_num(Integer.parseInt(multipartrequest.getParameter("cate_num")));
				boarddt.setUser_id((String)request.getSession().getAttribute("user_id"));
				boarddt.setBoard_content(multipartrequest.getParameter("board_content"));

				System.out.println(1);
				//저장할 이름 생성
				newFileName = boarddt.getCate_num() +""+ board.getNext_board() +""+ boarddt.getUser_id();
				oldFileNames = multipartrequest.getFileNames();
				
				System.out.println(1);
				//입력받은 사진들의 이름을 모두 수정
				while(oldFileNames.hasMoreElements()) {
					String parameter = (String)oldFileNames.nextElement();
					if(multipartrequest.getOriginalFileName(parameter) == null)
						continue;
					oldFile = new File(board_path + "/" + multipartrequest.getOriginalFileName(parameter));
					newFile = new File(board_path + "/" + newFileName+count);
					oldFile.renameTo(newFile);
					board_image += newFileName + count + "/";
					count++;
				}
				System.out.println(1);
				
				boarddt.setBoard_image(board_image);
				boarddt.setBoard_path(board_path);
				
			   //<끝>업로드 된 파일 저장---------------------------------------------------------------------------------------------------------------
				System.out.println(1);
				if(boarddt.getBoard_title() == null || boarddt.getBoard_title().equals("") || boarddt.getBoard_content() == null || boarddt.getBoard_content().equals("")) {
					request.getSession().setAttribute("messageType", "오류 메시지");
					request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
					request.setAttribute("test", count);
					address="write.jsp";
				}
				
				else {
					System.out.println(1);
					int result = board.write(boarddt);
					
					if(result == -1) {
						System.out.println(1);
						request.getSession().setAttribute("messageType", "오류 메시지");
						request.getSession().setAttribute("messageContent", "글쓰기에 실패했습니다.");
						request.setAttribute("test", count);
						address="write.jsp";
					}
					
					else {
						System.out.println(1);
						request.setAttribute("cate_num", boarddt.getCate_num());
						address="board.jsp";
					}
				}

			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request,response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
