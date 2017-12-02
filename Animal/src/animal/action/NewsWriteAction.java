package animal.action;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.oreilly.servlet.multipart.*;

import animal.bean.BoardDBBean;
import animal.bean.BoardDataBean;

import com.oreilly.servlet.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NewsWriteAction")
public class NewsWriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String board_path = "C:\\Users\\jaeyo\\eclipse-workspace\\image";
	private static String enType = "utf-8";
	private static int maxSize = 1024 * 1024 * 1024; 
	
    public NewsWriteAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		BoardDBBean board = BoardDBBean.getinstance();
		BoardDataBean boarddt = new BoardDataBean();
		Enumeration oldFileNames = null;
		File oldFile = null;
		File newFile = null;
    	String board_image = "";
    	String newFileName = "";
    	int count = 1;
    	//<시작>업로드 된 파일 저장---------------------------------------------------------------------------------------------------------------
		MultipartRequest multipartrequest = new MultipartRequest(request, board_path, maxSize, enType ,new DefaultFileRenamePolicy());
		
		//파라미터값 받아오기
		boarddt.setBoard_title(multipartrequest.getParameter("board_title"));
		boarddt.setUser_id((String)request.getSession().getAttribute("user_id"));
		boarddt.setBoard_content(multipartrequest.getParameter("board_content"));
		boarddt.setNews_visible(Integer.parseInt(multipartrequest.getParameter("news_visible")));

		//저장할 이름 생성
		String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
		newFileName = board.getNext_board() + boarddt.getUser_id() + now;
		oldFileNames = multipartrequest.getFileNames();
		
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
		boarddt.setBoard_image(board_image);
		boarddt.setBoard_path(board_path);
		
	   //<끝>업로드 된 파일 저장---------------------------------------------------------------------------------------------------------------
		
		if(boarddt.getBoard_title() == null || boarddt.getBoard_title().equals("") || boarddt.getBoard_content() == null || boarddt.getBoard_content().equals("")) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
			response.sendRedirect("news_write.jsp");
			return;
		}
		
		else {
			int result = board.news_write(boarddt);
			
			if(result == -1) {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "글쓰기에 실패했습니다.");
				response.sendRedirect("news_write.jsp");
				return;
			}
			
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("mypage.jsp");
				request.setAttribute("click_id", request.getSession().getAttribute("user_id"));
				dispatcher.forward(request,response);
			}
		}

	}

}
