package coupleLink.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import static common.JDBCTemplate.*;

import member.model.dao.MemberDao;
import coupleLink.model.vo.CoupleAuth;

public class CoupleLinkDao {
	
	
	private Properties prop = new Properties();
	
	public CoupleLinkDao() {
		String fileName = MemberDao.class.getResource("/sql/coupleLink/coupleLink.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 인증번호 보내기 위해 파트너 아이디 찾기 
	 * @param conn
	 * @param partnerName
	 * @param partnerPhone
	 * @return
	 */
	public String selectId(Connection conn, String partnerName, String partnerPhone) {
		String partnerId = null; 
		
		PreparedStatement pstmt = null;
		ResultSet rset = null; 
		String sql = prop.getProperty("selectId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, partnerName);
			pstmt.setString(2, partnerPhone);
			
			rset = pstmt.executeQuery(); 
			
			if(rset.next()) {
				partnerId = rset.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
			
		return partnerId;
		
		
	}
	
	
	/** 인증번호 데이터 입력 
	 * @param conn
	 * @param ca
	 * @return
	 */
	public int insertCoupleAuth(Connection conn, CoupleAuth ca) {
		
		int result = 0; 
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCoupleAuth");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ca.getUserId());
			pstmt.setString(2, ca.getUserName());
			pstmt.setInt(3, ca.getAuthNo());
			pstmt.setString(4, ca.getFromId());
			pstmt.setString(5, ca.getFromName());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println(result + "인증번호 테이블 성공..?");

		return result;
		
				
	}
	
	
	
	/** 유효한 인증번호가 있는지 확인하기 
	 * @param conn
	 * @param userId
	 * @return  양수 : 이미 인증번호 존재. 0: 없음 (즉, 인증번호 보낼 수 있음)
	 */
	public int checkExist(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("checkExist");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			
			rset = pstmt.executeQuery(); 
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	/** 입력받은 인증번호 일치한지 확인하는 서비스 
	 * @param conn
	 * @param ca
	 * @return
	 */
	public int isMatch(Connection conn, CoupleAuth ca) {
		int result = 0; 
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("isMatch"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ca.getUserId());
			pstmt.setString(2, ca.getUserName());
			pstmt.setString(3, ca.getFromName());
			pstmt.setInt(4, ca.getAuthNo());
			
			rset = pstmt.executeQuery(); 
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(conn);
		}
		
		return result;
		
	}
	
	 
	/** 인증번호가 일치하는 커플 아이디 가져오기 
	 * @param conn
	 * @param authNo
	 * @return
	 */
	public CoupleAuth selectCouple(Connection conn, int authNo) {
		CoupleAuth ca = new CoupleAuth(); 
		PreparedStatement pstmt = null;
		ResultSet rset = null; 
		String sql = prop.getProperty("selectCouple");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, authNo);
			
			rset = pstmt.executeQuery(); 
			
			if(rset.next()) {
				ca.setUserId(rset.getString(1));
				ca.setFromId(rset.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt); 
		}
		
		return ca;

	}
	
	
	
	/** 커플코드 중복 체크 
	 * @param conn
	 * @param randomCode
	 * @return
	 */
	public int isCodeExist(Connection conn, String randomCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("isCodeExist"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, randomCode);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
	/** 커플코드 추가하기 
	 * @param conn
	 * @param coupleId
	 * @param coupleId2
	 * @param coupleCode
	 * @return
	 */
	public int insertCcode(Connection conn, String coupleId, String coupleId2, String coupleCode) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCcode");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, coupleCode);
			pstmt.setString(2, coupleId);
			pstmt.setString(3, coupleId2);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	
	
	/** 링크 후 인증번호 삭제하기 
	 * @param conn
	 * @param authNo
	 * @return
	 */
	public int deleteCoupleAuth(Connection conn, int authNo) {
		int result = 0;
		PreparedStatement pstmt = null; 
		String sql = prop.getProperty("deleteCoupleAuth");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, authNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	
	}
	
	
	
	public boolean checkCouple(Connection conn, String userId) {
		boolean isCouple = false;
		ResultSet rset = null;
		PreparedStatement pstmt = null; 
		String sql = prop.getProperty("isCouple");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				int result = rset.getInt(1);
				
				if(result > 0) {
					isCouple = true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return isCouple;
		
	}
	

}
