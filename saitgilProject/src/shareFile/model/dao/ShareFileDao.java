package shareFile.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.dao.MemberDao;
import shareFile.model.vo.ShareFile;

import static common.JDBCTemplate.*;

public class ShareFileDao {
	
	Properties prop = new Properties();
	
	public ShareFileDao() {
		
		String fileName = ShareFileDao.class.getResource("/sql/shareFile/shareFile-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		
	}
	
	
	/** 1. 커플 사귄 날짜 변경 서비스 
	 * @param conn
	 * @param firstDate
	 * @return
	 */
	public int changeMeetDate(Connection conn, String firstDate, String cCode) {
		int result = 0; 
		PreparedStatement pstmt = null; 
		
		String sql = prop.getProperty("changeMeetDate"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, firstDate);
			pstmt.setString(2, cCode);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/** 2. 커플파일 가져오기 
	 * @param cCode (커플코드)
	 * @return 커플파일 객체 
	 */
	public ShareFile selectShareFile(Connection conn, String cCode) {
		ShareFile sf = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		String sql = prop.getProperty("selectShareFile");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cCode);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				sf = new ShareFile(rset.getString("c_code"),
								   rset.getString("c_picture_path"),
								   rset.getString("origin_name"),
								   rset.getString("change_name"),
								   rset.getDate("meetdate")
						);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	 
		return sf;
	}
	
	
	/** 커플코드 생성하기 
	 * @param conn
	 * @param sf
	 * @param meetDate
	 * @return
	 */
	public int updateCcode(Connection conn, ShareFile sf, String meetDate) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateCcode");
		System.out.println("DaoTest : " + sf.getcPicturePath() + sf.getcCode());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sf.getcPicturePath());
			pstmt.setString(2, meetDate);
			pstmt.setString(3, sf.getcCode());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
		
	}
	
	
	
	/** 커플 연동 성공시 빈 공유파일 insert 해주는거 
	 * @param conn
	 * @param coupleCode
	 * @return
	 */
	public int insertCcode(Connection conn, String coupleCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCcode");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, coupleCode);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
		
	}
	
	
	/** 커플사진 변경 
	 * @return
	 */
	public int updateProfile(Connection conn, String name, String cCode) {
		int result = 0;
		PreparedStatement pstmt = null; 
		String sql = prop.getProperty("updateProfile");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, cCode);
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt); 
		}
		
		return result;
		
	}
	
	
	/** 커플 연동끊을 때 멤버테이블 cCode 삭제하기 
	 * @param conn
	 * @param cCode
	 * @return
	 */
	public int deleteCoupleCode(Connection conn, String cCode) {
		int result = 0; 
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteCoupleCode");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cCode);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;	
		
	}
	
	
	
	
	public int deleteShareFile(Connection conn, String cCode) {
		int result = 0;
		PreparedStatement pstmt = null; 
		String sql = prop.getProperty("deleteShareFile"); 
		System.out.println("Sql : " + sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cCode);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	

}
