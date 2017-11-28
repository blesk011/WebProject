package animal.bean;

import java.sql.*;;

public class UserDBBean {
	private Connection conn = null;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static UserDBBean instance = new UserDBBean();
	
	public static UserDBBean getinstance() {
		return instance;
	}
	
	private UserDBBean() {
		try {
			String dbURL = "jdbc:mysql://203.249.22.34:3306/web?autoReconnect=true&useSSL=false";
			String dbID = "jy";
			String dbPW = "1365";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//���� �ð��� ���ϴ� �޼ҵ�
	public String getDate() {
		String SQL="SELECT NOW()";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next())
				return rs.getString(1);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	//�α����� �ϴ� �޼ҵ�
	public int login(String user_id,String user_pw) {
		String SQL = "SELECT user_pw FROM USER WHERE user_id = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(user_pw)) 
					return 1; //�α���
				else
					return 0; //����� Ʋ��
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //���̵� ����
	}
	
	//���̵� �ߺ�Ȯ��
	public int registerCheck(String user_id) {
		String SQL="SELECT * FROM USER WHERE user_id = ?";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			//no
			if(rs.next() || user_id.equals("")) {
				return 0;
			}
			//ok
			else{
				return 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return -1;//error
	}
		
	//ȸ������
	public int register(UserDataBean member) {
		String SQL="INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, member.getUser_name());
			pstmt.setString(2, member.getUser_id());
			pstmt.setString(3, member.getUser_pw());
			pstmt.setString(4, member.getUser_phone());
			pstmt.setString(5, getDate());
			//int result = new likeDAO().create(member.getUser_id());
				return pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs !=null) rs.close();
					if(pstmt !=null) pstmt.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			return -1;//error
		}
		
	//ȸ�� ����
	public UserDataBean getUser(String user_id) {
		String sql = "select * from user where user_id=?";
		UserDataBean user = new UserDataBean();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				user.setUser_name(rs.getString("user_name"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_pw(rs.getString("user_pw"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_date(rs.getString("user_date"));
			}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs !=null) rs.close();
					if(pstmt !=null) pstmt.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			return user;
		}
	
	//ȸ�� Ż��
	public boolean deleteUser(String user_id) {
		String sql = "delete from user where user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}
