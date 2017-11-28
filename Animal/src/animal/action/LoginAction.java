package animal.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import animal.bean.UserDBBean;


@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String user_id = request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		UserDBBean userdb = UserDBBean.getinstance();
		//������� ���
		if( user_id == null || user_id.equals("") || user_pw == null || user_pw.equals("")) {
			request.getSession().setAttribute("messageType", "���� �޽���");
			request.getSession().setAttribute("messageContent", "��� ������ �Է��ϼ���.");
			response.sendRedirect("login.jsp");
			return;
		}	
		
		int result=userdb.login(user_id, user_pw);
		
		if(result == 1) {
			//�α��� ������ id ���� �ο�
			request.getSession().setAttribute("user_id",user_id);
			response.sendRedirect("index.jsp"); //complete page�� redirect�ؾ���
			
			return;
		}
		
		else if(result == 0) {
			request.getSession().setAttribute("messageType", "���� �޼���");
			request.getSession().setAttribute("messageContent", "���̵� Ȥ�� ��й�ȣ�� ���� �ʽ��ϴ�.");
			response.sendRedirect("login.jsp");
		}
		
		else {
			request.getSession().setAttribute("messageType", "���� �޽���");
			request.getSession().setAttribute("messageContent", "�������� �����Դϴ�. �ٽ� �õ��� �ּ���.");
			response.sendRedirect("login.jsp");
		}
		
	}

}
