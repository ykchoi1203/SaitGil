package inquiry.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.Date;

import inquiry.model.vo.Inquiry;
import member.model.dao.MemberDao;
import notice.model.vo.Notice;


public class InquiryDao {
	
	private Properties prop = new Properties();
	
	public InquiryDao() {
		String fileName = MemberDao.class.getResource("/sql/inquiry/inquiry-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/** 1. 문의게시판 목록 불러오기 
	 * @param conn
	 * @return
	 */
	public ArrayList<Inquiry> selectList(Connection conn, int currentPage, int boardLimit, String userId) {
		
		ArrayList<Inquiry> list = new ArrayList<Inquiry>(); 
		PreparedStatement pstmt = null;
		ResultSet rset = null; 
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (currentPage - 1) * boardLimit + 1; 
			int endRow = startRow + boardLimit - 1;
			
			pstmt.setString(1, userId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Inquiry(rset.getInt(2),
							 	 	rset.getString(3),
							 	 	rset.getString(4),
							 	 	rset.getString(5),
							 	 	rset.getString(6),
							 	 	rset.getDate(7),
							 	 	rset.getString(8),
							 	 	rset.getDate(9),
							 	 	rset.getString(10),
							 	 	rset.getString(11)
						));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}
	
	
	/** 2. 문의사항 상세보기 불러오기 
	 * @param conn
	 * @param n
	 * @return
	 */
	public Inquiry selectInquiry(Connection conn, int inquiryNo) {
		Inquiry i = null; 
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectInquiry"); 

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inquiryNo);

			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				i = new Inquiry(rset.getInt(1),
				 	 	rset.getString(2),
				 	 	rset.getString(3),
				 	 	rset.getString(4),
				 	 	rset.getString(5),
				 	 	rset.getDate(6),
				 	 	rset.getString(7),
				 	 	rset.getDate(8),
				 	 	rset.getString(9),
				 	 	rset.getString(10)
			);	
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return i;

	}
	
	
	
	/** 문의사항 작성하기 
	 * @param conn
	 * @param n - title, writer, content 
	 * @return
	 */
	public int insertInquiry(Connection conn, Inquiry i) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertInquiry"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, i.getUserId());
			pstmt.setString(2, i.getInquiryTitle());
			pstmt.setString(3, i.getInquiryContent());
			pstmt.setString(4, i.getWriter());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
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
	public int updateInquiry(Connection conn, Inquiry i) {
		int result = 0; 
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateInquiry"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, i.getInquiryTitle());
			pstmt.setString(2, i.getInquiryContent());
			pstmt.setInt(3, i.getInquiryNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	
	
	
	/** 문의사항 삭제하기 
	 * @param conn
	 * @param noticeNo
	 * @return
	 */
	public int deleteInquiry(Connection conn, int inquiryNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteInquiry"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inquiryNo);
			
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		 
		return result;
	}


	public int getListCount(Connection conn, String userId) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getListCount"); 
		
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
	
	
	
	
	
	

}
