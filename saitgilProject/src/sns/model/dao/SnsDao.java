package sns.model.dao;
import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import sns.model.vo.Board;
import sns.model.vo.Comment;
import sns.model.vo.Photo;

public class SnsDao {
	private Properties prop = new Properties();

	public SnsDao() {
		String fileName = SnsDao.class.getResource("/sql/sns/sns-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertBoard(Connection conn, Board b) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getUserId());
			pstmt.setString(2, b.getcCode());
			pstmt.setString(3, b.getbContent());
			pstmt.setString(4, b.getIsPublic());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public int insertPhoto(Connection conn, ArrayList<Photo> fileList) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertPhoto");
		
		try {
			
			for(int i=0; i<fileList.size(); i++) {
				Photo p = fileList.get(i);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, p.getPhotoPath());
				pstmt.setString(2, p.getChangeName());
				
				result = pstmt.executeUpdate();
				
				if(result <=0 ) {
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<Board> selectShareList(Connection conn, int st, int ed){
		ArrayList<Board> bList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectShareList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,st);
			pstmt.setInt(2, ed);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board(rset.getInt("b_no"),
									rset.getString("user_id"),
									rset.getString("partner_name"),
									rset.getString("c_code"),
									rset.getString("b_content"),
									rset.getDate("b_date"),
									rset.getString("is_public"),
									rset.getString("status"),
									rset.getInt("report"),
									rset.getInt("like_count"));
				bList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bList;
		
	}
	
	public ArrayList<Photo> selectSharePhList(Connection conn){
		ArrayList<Photo> phList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectSharePhList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Photo p = new Photo(rset.getInt("p_no"),
									rset.getString("photo_path"),
									rset.getInt("b_no"),
									rset.getString("change_name"),
									rset.getString("status")
									);
				phList.add(p);
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return phList;
	}
	
	public ArrayList<Comment> selectShareComment(Connection conn){
		ArrayList<Comment> comList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectShareComList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Comment c = new Comment(rset.getInt("comment_no"),
										rset.getString("user_name"),
										rset.getString("content").replace("\n", "<br>"),
										rset.getDate("comment_date"),
										rset.getInt("report"),
										rset.getInt("b_no"),
										rset.getString("status"),
										rset.getString("profile_pic"),
										rset.getString("partner_name")
										);
				comList.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return comList;
		
	}
	
	public int getListCount(Connection conn) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getShareListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
		
	}
	
	public int insertComment(Connection conn, Comment c) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getUserId());
			pstmt.setString(2, c.getContent());
			pstmt.setInt(3, c.getbNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Comment> selectComment(Connection conn, int bNo){
		
		ArrayList<Comment> comList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectComment");
		System.out.println(bNo);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Comment c = new Comment(rset.getInt("comment_no"),
										rset.getString("user_name"),
										rset.getString("content"),
										rset.getDate("comment_date"),
										rset.getInt("report"),
										rset.getInt("b_no"),
										rset.getString("status"),
										rset.getString("profile_pic"),
										rset.getString("partner_name")
										);
				comList.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return comList;
	}
	
	public int insertLike(Connection conn, int bNo, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
		
	}
	
	public int deleteLike(Connection conn, int bNo, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int addLikeCount(Connection conn, int bNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("addLikeCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int minusLikeCount(Connection conn, int bNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("minusLikeCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int selectLikeCount(Connection conn, int bNo) {
		int likeCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLikeCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				likeCount = rset.getInt("like_count");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return likeCount;
		
	}

	public ArrayList<Board> selectLike(Connection conn) {
		
		ArrayList<Board> likeList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				b.setbNo(rset.getInt("b_no"));
				b.setUserId(rset.getString("user_id"));
				
				likeList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return likeList;
		
	}

	public int insertScrap(Connection conn, int bNo, String cCode) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertScrap");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cCode);
			pstmt.setInt(2, bNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteScrap(Connection conn, int bNo, String cCode) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteScrap");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cCode);
			pstmt.setInt(2, bNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Board> selectScrap(Connection conn) {
		ArrayList<Board> scrapList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectScrap");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				b.setbNo(rset.getInt("b_no"));
				b.setcCode(rset.getString("c_code"));
				scrapList.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return scrapList;
		
	}

	public ArrayList<Board> selectSecretList(Connection conn, String cCode, int st, int ed) {
		ArrayList<Board> bList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectSecretList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cCode);
			pstmt.setInt(2,st);
			pstmt.setInt(3, ed);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board(rset.getInt("b_no"),
									rset.getString("user_id"),
									rset.getString("partner_name"),
									rset.getString("c_code"),
									rset.getString("b_content"),
									rset.getDate("b_date"),
									rset.getString("is_public"),
									rset.getString("status"),
									rset.getInt("report"),
									rset.getInt("like_count"));
				bList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bList;
	}

	public ArrayList<Photo> selectSecretPhList(Connection conn) {
		ArrayList<Photo> phList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectSecretPhList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Photo p = new Photo(rset.getInt("p_no"),
									rset.getString("photo_path"),
									rset.getInt("b_no"),
									rset.getString("change_name"),
									rset.getString("status")
									);
				phList.add(p);
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return phList;
	}

	public ArrayList<Comment> selectSecretComment(Connection conn) {
		ArrayList<Comment> comList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectSecretComList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Comment c = new Comment(rset.getInt("comment_no"),
										rset.getString("user_name"),
										rset.getString("content").replace("\n", "<br>"),
										rset.getDate("comment_date"),
										rset.getInt("report"),
										rset.getInt("b_no"),
										rset.getString("status"),
										rset.getString("profile_pic"),
										rset.getString("partner_name")
										);
				comList.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return comList;
	}

	public int getSecretListCount(Connection conn) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getShareListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}

	public ArrayList<Board> selectLikeList(Connection conn, int st, int ed, String userId) {
		ArrayList<Board> bList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLikeList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2,st);
			pstmt.setInt(3, ed);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board(rset.getInt("b_no"),
									rset.getString("user_id"),
									rset.getString("partner_name"),
									rset.getString("login_user"),
									rset.getString("b_content"),
									rset.getDate("b_date"),
									rset.getString("is_public"),
									rset.getString("status"),
									rset.getInt("report"),
									rset.getInt("like_count"));
				bList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bList;
	}

	public ArrayList<Board> selectScrapList(Connection conn, int st, int ed, String cCode) {
		ArrayList<Board> bList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectScrapList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cCode);
			pstmt.setInt(2,st);
			pstmt.setInt(3, ed);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board(rset.getInt("b_no"),
									rset.getString("user_id"),
									rset.getString("partner_name"),
									rset.getString("c_code"),
									rset.getString("b_content"),
									rset.getDate("b_date"),
									rset.getString("is_public"),
									rset.getString("status"),
									rset.getInt("report"),
									rset.getInt("like_count"));
				bList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bList;
	}

	public ArrayList<Photo> selectPhList(Connection conn) {
		ArrayList<Photo> phList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPhList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Photo p = new Photo(rset.getInt("p_no"),
									rset.getString("photo_path"),
									rset.getInt("b_no"),
									rset.getString("change_name"),
									rset.getString("status")
									);
				phList.add(p);
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return phList;
	}

	public ArrayList<Comment> selectComList(Connection conn) {
		ArrayList<Comment> comList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectComList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Comment c = new Comment(rset.getInt("comment_no"),
										rset.getString("user_name"),
										rset.getString("content").replace("\n", "<br>"),
										rset.getDate("comment_date"),
										rset.getInt("report"),
										rset.getInt("b_no"),
										rset.getString("status"),
										rset.getString("profile_pic"),
										rset.getString("partner_name")
										);
				comList.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return comList;
	}

	public int getLikeListCount(Connection conn, String userId) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getLikeListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}

	public int getScrapListCount(Connection conn, String cCode) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getScrapListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cCode);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}

	public int deleteBoard(Connection conn, int bNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Board> selectMyList(Connection conn, int st, int ed, String userName) {
		
		ArrayList<Board> bList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userName);
			pstmt.setInt(2,st);
			pstmt.setInt(3, ed);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board(rset.getInt("b_no"),
									rset.getString("user_id"),
									rset.getString("partner_name"),
									rset.getString("c_code"),
									rset.getString("b_content"),
									rset.getDate("b_date"),
									rset.getString("is_public"),
									rset.getString("status"),
									rset.getInt("report"),
									rset.getInt("like_count"));
				bList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bList;
	}

	public int getMyListCount(Connection conn, String userName) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getMyListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	
}
