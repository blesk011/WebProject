package animal.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDBBean {
	private Connection conn = null;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static BoardDBBean instance = new BoardDBBean();
	
	public static BoardDBBean getinstance() {
		return instance;
	}
	
	private BoardDBBean() {
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
	
	
	//�ۼ��� �� ��ȣ ���ϱ�
	public int getNext_board() {
		String SQL="SELECT board_num FROM board ORDER BY board_num DESC";
		try {
			pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)+1;
			}
			return 1;//���簡 ù��° �Խñ��� ���
		}catch(Exception e) {
			e.printStackTrace();	
		}
		return -1;
	}
	
	//�ۼ��� �� ��ȣ ���ϱ�
	public int getNext_news() {
		String SQL="SELECT news_num FROM board ORDER BY board_num DESC";
		try {
			pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)+1;
			}
			return 1;//���簡 ù��° �Խñ��� ���
		}catch(Exception e) {
			e.printStackTrace();	
		}
		return -1;
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
	
	//�����ǵ� �Խñ� ���
	public int news_write(BoardDataBean board) {
		String SQL = "INSERT INTO board (board_num,news_num,user_id,board_title,board_content,board_image,board_path,news_visible,board_date) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext_board());
			pstmt.setInt(2, getNext_news());
			pstmt.setString(3, board.getUser_id());
			pstmt.setString(4, board.getBoard_title());
			pstmt.setString(5, board.getBoard_content());
			pstmt.setString(6, board.getBoard_image());
			pstmt.setString(7, board.getBoard_path());
			pstmt.setInt(8, board.getNews_visible());
			pstmt.setString(9, getDate());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//�����ǵ� �Խñ� ����
	public ArrayList<BoardDataBean> news_getlist(String user_id){
		String SQL="SELECT * FROM board WHERE news_num > 0 AND user_id = ?";
		ArrayList<BoardDataBean> list = new ArrayList<BoardDataBean>();
		try {
				PreparedStatement pstmt=conn.prepareStatement(SQL);
				pstmt.setString(1, user_id);
				rs=pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDataBean board = new BoardDataBean();
				board.setBoard_num(rs.getInt("board_num"));
				board.setNews_num(rs.getInt("news_num"));
				board.setCate_num(rs.getInt("cate_num"));
				board.setUser_id(rs.getString("user_id"));
				board.setBoard_title(rs.getString("board_title"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_image(rs.getString("board_image"));
				board.setBoard_path(rs.getString("board_path"));
				board.setBoard_date(rs.getString("board_date"));
				board.setBoard_like(rs.getInt("board_like"));
				board.setBoard_scrap(rs.getInt("board_scrab"));
				board.setBoard_declaration(rs.getInt("board_declaration"));
				board.setNews_visible(rs.getInt("news_visible"));
				list.add(board);
			}
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//�����ǵ� �Խñ� �ҷ�����
	public BoardDataBean news_getboard(int board_num){
		String SQL="SELECT * FROM board WHERE board_num = ?";
		BoardDataBean board = new BoardDataBean();
		try {
				PreparedStatement pstmt=conn.prepareStatement(SQL);
				pstmt.setInt(1, board_num);
				rs=pstmt.executeQuery();
			
			while(rs.next()) {
				board.setBoard_num(rs.getInt("board_num"));
				board.setNews_num(rs.getInt("news_num"));
				board.setCate_num(rs.getInt("cate_num"));
				board.setUser_id(rs.getString("user_id"));
				board.setBoard_title(rs.getString("board_title"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_image(rs.getString("board_image"));
				board.setBoard_path(rs.getString("board_path"));
				board.setBoard_date(rs.getString("board_date"));
				board.setBoard_like(rs.getInt("board_like"));
				board.setBoard_scrap(rs.getInt("board_scrab"));
				board.setBoard_declaration(rs.getInt("board_declaration"));
				board.setNews_visible(rs.getInt("news_visible"));
			}
			return board;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int news_update(BoardDataBean board) {
		 String SQL="UPDATE board SET board_title = ?, board_content = ?, board_image = ?, board_path = ?, news_visible = ? WHERE board_num = ?";

	      try {
	         PreparedStatement pstmt=conn.prepareStatement(SQL);
	         pstmt.setString(1, board.getBoard_title());
	         pstmt.setString(2, board.getBoard_content());
	         pstmt.setString(3, board.getBoard_image());
	         pstmt.setString(4, board.getBoard_path());
	         pstmt.setInt(5, board.getNews_visible());
	         pstmt.setInt(6, board.getBoard_num());
	         return pstmt.executeUpdate();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      return -1;
	}
	
	//뉴스피드 글 삭제
	public int news_delete(int board_num) {
		String SQL="DELETE FROM board WHERE board_num = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, board_num);
			pstmt.executeUpdate();
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int like_board(int board_num,int board_like) {
		String SQL="UPDATE board SET board_like = ? WHERE board_num = ?";

	      try {
	         PreparedStatement pstmt=conn.prepareStatement(SQL);
	         pstmt.setInt(1, board_like);
	         pstmt.setInt(2, board_num);
	         return pstmt.executeUpdate();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      return -1;
	}
	public ArrayList<BoardDataBean> getList() {
		ArrayList<BoardDataBean> list = new ArrayList<>();
		try {
			String sql = "select * from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDataBean board = new BoardDataBean();
				board.setBoard_num(rs.getInt("board_num"));
				board.setNews_num(rs.getInt("news_num"));
				board.setCate_num(rs.getInt("cate_num"));
				board.setUser_id(rs.getString("user_id"));
				board.setBoard_title(rs.getString("board_title"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_image(rs.getString("board_image"));
				board.setBoard_path(rs.getString("board_path"));
				board.setBoard_date(rs.getString("board_date"));
				board.setBoard_like(rs.getInt("board_like"));
				board.setBoard_scrap(rs.getInt("board_scrap"));
				board.setBoard_declaration(rs.getInt("board_declaration"));
				board.setNews_visible(rs.getInt("news_visible"));
				list.add(board);
			}
		} catch (Exception e) {
			System.out.println("getAllUser err : " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
			}
		}
		return list;
	}
}
