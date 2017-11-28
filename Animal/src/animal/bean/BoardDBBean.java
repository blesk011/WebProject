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
			String dbURL = "jdbc:mysql://localhost:3306/web?autoReconnect=true&useSSL=false";
			String dbID = "jy";
			String dbPW = "1365";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//작성될 글 번호 구하기
	public int getNext_board() {
		String SQL="SELECT board_num FROM board ORDER BY board_num DESC";
		try {
			pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)+1;
			}
			return 1;//현재가 첫번째 게시글인 경우
		}catch(Exception e) {
			e.printStackTrace();	
		}
		return -1;
	}
	
	//작성될 글 번호 구하기
	public int getNext_news() {
		String SQL="SELECT news_num FROM board ORDER BY board_num DESC";
		try {
			pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)+1;
			}
			return 1;//현재가 첫번째 게시글인 경우
		}catch(Exception e) {
			e.printStackTrace();	
		}
		return -1;
	}
		
	//현재 시간을 구하는 메소드
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
	
	//뉴스피드 게시글 등록
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
	
	//뉴스피드 게시글 보기
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
				board.setUser_id(rs.getString("user_id"));
				board.setBoard_title(rs.getString("board_title"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_image(rs.getString("board_image"));
				board.setBoard_path(rs.getString("board_path"));
				board.setNews_visible(rs.getInt("news_visible"));
				board.setBoard_date(rs.getString("board_date"));
				board.setBoard_like(rs.getInt("board_like"));
				list.add(board);
			}
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//뉴스피드 게시글 불러오기
	public BoardDataBean news_getboard(int news_num){
		String SQL="SELECT * FROM board WHERE news_num = ?";
		BoardDataBean board = new BoardDataBean();
		try {
				PreparedStatement pstmt=conn.prepareStatement(SQL);
				pstmt.setInt(1, news_num);
				rs=pstmt.executeQuery();
			
			while(rs.next()) {
				board.setBoard_num(rs.getInt("board_num"));
				board.setNews_num(rs.getInt("news_num"));
				board.setUser_id(rs.getString("user_id"));
				board.setBoard_title(rs.getString("board_title"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_image(rs.getString("board_image"));
				board.setBoard_path(rs.getString("board_path"));
				board.setNews_visible(rs.getInt("news_visible"));
				board.setBoard_date(rs.getString("board_date"));
			}
			return board;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
