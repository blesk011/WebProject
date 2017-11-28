package animal.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import animal.bean.UserDBBean;
import animal.bean.UserDataBean;



@WebServlet("/RegisterAction")
public class RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UserDBBean manager = UserDBBean.getinstance();
		UserDataBean user = new UserDataBean(); 
		//ȸ������ ����
		user.setUser_name(request.getParameter("user_name"));
		user.setUser_phone(request.getParameter("user_phone"));
		user.setUser_id(request.getParameter("user_id"));
		user.setUser_pw(request.getParameter("user_pw"));
		String check_passwd=request.getParameter("check_passwd");
		
		
		if(user.getUser_name() == null || user.getUser_name().equals("") || user.getUser_phone() == null || user.getUser_phone().equals("") || user.getUser_id() == null || user.getUser_id().equals("") || user.getUser_pw() == null || user.getUser_pw().equals("") || check_passwd == null || check_passwd.equals("")) {
			request.getSession().setAttribute("messageType", "���� �޽���");
			request.getSession().setAttribute("messageContent", "��� ������ �Է��ϼ���.");
			response.sendRedirect("register.jsp");
			return;
		}
		
		if(!user.getUser_pw().equals(check_passwd)) {
			request.getSession().setAttribute("messageType", "���� �޽���");
			request.getSession().setAttribute("messageContent", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			response.sendRedirect("register.jsp");
			return;
		}
		
		if(user.getUser_id() != null) {
		int result = manager.register(user);
		
		if(result ==1) {
			request.getSession().setAttribute("messageType", "���� �޽���");
			request.getSession().setAttribute("messageContent", "ȸ�������� �Ϸ�Ǿ����ϴ�. �α��� ���ּ���!");
			response.sendRedirect("login.jsp");
		}
		
		else {
			request.getSession().setAttribute("messageType", "���� �޽���");
			request.getSession().setAttribute("messageContent", "�̹� �����ϴ� ȸ���Դϴ�.");
			response.sendRedirect("login.jsp");
		}
		}
	}
}
