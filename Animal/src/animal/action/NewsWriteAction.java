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
    	//<����>���ε� �� ���� ����---------------------------------------------------------------------------------------------------------------
		MultipartRequest multipartrequest = new MultipartRequest(request, board_path, maxSize, enType ,new DefaultFileRenamePolicy());
		
		//�Ķ���Ͱ� �޾ƿ���
		boarddt.setBoard_title(multipartrequest.getParameter("board_title"));
		boarddt.setUser_id((String)request.getSession().getAttribute("user_id"));
		boarddt.setBoard_content(multipartrequest.getParameter("board_content"));
		boarddt.setNews_visible(Integer.parseInt(multipartrequest.getParameter("news_visible")));

		//������ �̸� ����
		newFileName = board.getNext_board() + boarddt.getUser_id();
		oldFileNames = multipartrequest.getFileNames();
		
		//�Է¹��� �������� �̸��� ��� ����
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
		
	   //<��>���ε� �� ���� ����---------------------------------------------------------------------------------------------------------------
		System.out.println(boarddt.getUser_id());
		System.out.println(boarddt.getBoard_title());
		System.out.println(boarddt.getBoard_content());
		System.out.println(boarddt.getBoard_image());
		System.out.println(boarddt.getBoard_path());
		System.out.println(boarddt.getNews_visible());
		
		if(boarddt.getBoard_title() == null || boarddt.getBoard_title().equals("") || boarddt.getBoard_content() == null || boarddt.getBoard_content().equals("")) {
			request.getSession().setAttribute("messageType", "���� �޽���");
			request.getSession().setAttribute("messageContent", "��� ������ �Է��ϼ���.");
			response.sendRedirect("news_write.jsp");
			return;
		}
		
		else {
			int result = board.news_write(boarddt);
			
			if(result == -1) {
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "�۾��⿡ �����߽��ϴ�.");
				response.sendRedirect("news_write.jsp");
				return;
			}
			
			else {
				response.sendRedirect("index.jsp");
			}
		}

	}

}
