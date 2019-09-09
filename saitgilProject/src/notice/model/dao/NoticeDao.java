package notice.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import notice.model.vo.Notice;

import static common.JDBCTemplate.*;

public class NoticeDao {
	
	private Properties prop = new Properties();	
	
	public NoticeDao() {
		String fileName = NoticeDao.class.getResource("/sql/notice/notice-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/** 1. 공지사항 목록 불러오기 
	 * @param conn
	 * @return
	 */
	public ArrayList<Notice> selectList(Connection conn, int currentPage, int boardLimit) {
		
		ArrayList<Notice> list = new ArrayList<Notice>(); 
		PreparedStatement pstmt = null;
		ResultSet rset = null; 
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (currentPage - 1) * boardLimit + 1; 
			int endRow = startRow + boardLimit - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Notice(rset.getInt("notice_no"),
							 	 	rset.getString("notice_title"),
							 	 	rset.getString("notice_content"),
							 	 	rset.getString("notice_writer"),
							 	 	rset.getDate("notice_date"),
							 	 	rset.getInt("notice_count")));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}
	
	
	/**2. 공지사항 작성하기 
	 * @param conn
	 * @param n - title, writer, content 
	 * @return
	 */
	public int insertNotice(Connection conn, Notice n) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNotice"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setString(3, n.getWriter());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
		
	}
	
	
	/** 3. 공지사항 불러오기 
	 * @param conn
	 * @param n
	 * @return
	 */
	public Notice selectNotice(Connection conn, int noticeNo) {
		Notice n = null; 
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectNotice"); 

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);

			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n = new Notice(rset.getInt("notice_no"),
							   rset.getString("notice_title"),
							   rset.getString("notice_content"),
							   rset.getString("notice_writer"),
							   rset.getDate("notice_date"),
							   rset.getInt("notice_count"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return n;

	}
	
	
	/** 조회수 증가시키기 
	 * @param conn
	 * @param noticeNo
	 * @return
	 */
	public int increaseCount(Connection conn, int noticeNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCount"); 
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	
	
	
	/** 공지사항 수정하기
	 * @param conn
	 * @param n
	 * @return
	 */
	public int updateNotice(Connection conn, Notice n) {
		int result = 0; 
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateNotice"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, n.getNoticeNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	
	
	/** 공지사항 삭제하기 
	 * @param conn
	 * @param noticeNo
	 * @return
	 */
	public int deleteNotice(Connection conn, int noticeNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteNotice"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		 
		return result;
	}
	
	
	
	/** 공지사항 페이징처리를 위한 게시글 수 구하는 서비스 
	 * @param conn
	 * @return
	 */
	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getListCount"); 
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql); 
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
		
	}
	
	   /** 메뉴바에 최근 공지사항 띄우는거 가져오기 
	    * @param conn
	    * @return
	    */
   public Notice selectRecent(Connection conn) {
      Notice recent = null; 
      PreparedStatement pstmt = null;
      ResultSet rset = null; 
      String sql = prop.getProperty("selectRecent");
      
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         rset = pstmt.executeQuery();
         
         if(rset.next()) {
            recent = new Notice();
            recent.setNoticeNo(rset.getInt("notice_no"));
            recent.setNoticeTitle(rset.getString("notice_title"));

         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      
      return recent;
      
   }
	   

}
