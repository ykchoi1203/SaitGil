package shareFile.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
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
	
	
	

}
