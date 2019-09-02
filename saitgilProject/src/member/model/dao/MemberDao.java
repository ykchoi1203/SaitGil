package member.model.dao;

import member.model.vo.Member;

import static common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() {
		String fileName = MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//회원 로그인
	public Member loginMember(Connection conn, String userId, String userPwd) {
		
		Member loginUser = null; 
		
		PreparedStatement pstmt = null; 
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			System.out.println(userId);
			System.out.println(userPwd);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery(); 
			
			if(rset.next()) {
				loginUser = new Member(rset.getString("user_id"),
									   rset.getString("user_pwd"),
									   rset.getString("user_name"),
									   rset.getString("birth"),
									   rset.getString("phone"),
									   rset.getString("email"),
									   rset.getString("gender"),
									   rset.getString("address"),
									   rset.getDate("join_date"),
									   rset.getInt("reports"),
									   rset.getString("profile_pic"),
									   rset.getInt("invitation_code"),
									   rset.getString("status"),
									   rset.getString("c_code")
									   );
				System.out.println(loginUser);
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return loginUser;
	}
	
	
	/** 회원 가입 
	 * @param conn
	 * @param mem 
	 * @return 
	 */
	public int insertMember(Connection conn, Member mem) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getUserId());
			pstmt.setString(2, mem.getUserPwd());
			pstmt.setString(3, mem.getUserName());
			pstmt.setString(4, mem.getBirth());
			pstmt.setString(5,  mem.getPhone());
			pstmt.setString(6, mem.getEmail());
			pstmt.setString(7, mem.getGender());
			pstmt.setString(8, mem.getAddress());
			System.out.println(mem.getGender());
			result = pstmt.executeUpdate(); 
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result; 
		
	}
	
	

	
	/** 회원정보 수정하기 
	 * @param conn
	 * @param mem
	 * @return 
	 */
	public int updateMember(Connection conn, Member mem) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		System.out.println("dao" + mem.getUserId());
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getBirth());
			pstmt.setString(2, mem.getPhone());
			pstmt.setString(3, mem.getEmail());
			pstmt.setString(4, mem.getGender());
			pstmt.setString(5, mem.getAddress());
			pstmt.setString(6, mem.getUserId());
			
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	
	/** 회원 객체 불러오기 
	 * @param conn
	 * @param userId
	 * @return
	 */
	public Member selectMember(Connection conn, String userId) {
		Member mem = null; 
		PreparedStatement pstmt = null;
		ResultSet rset = null; 
		
		String sql = prop.getProperty("selectMember"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery(); 
			
			if(rset.next()) {
				mem = new Member(rset.getString("user_id"),
						   rset.getString("user_pwd"),
						   rset.getString("user_name"),
						   rset.getString("birth"),
						   rset.getString("phone"),
						   rset.getString("email"),
						   rset.getString("gender"),
						   rset.getString("address"),
						   rset.getDate("join_date"),
						   rset.getInt("reports"),
						   rset.getString("profile_pic"),
						   rset.getInt("invitation_code"),
						   rset.getString("status"),
						   rset.getString("c_code")
						);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return mem;

	}
	
	
	 
	/** 회원 탈퇴하기 
	 * @param conn
	 * @param userId
	 * @return
	 */
	public int deleteMember(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;

	}
	
	
	public int deleteCCode(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null; 
		String sql = prop.getProperty("deleteCCode"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("커플 코드 2명 삭제완료" + result);
		return result;
		
	}
	
	
	
	
	/** 아이디 찾기 
	 * @param conn
	 * @param userName 입력된 회원 이름
	 * @param phone 입력된 회원 전화번호 
	 * @return 아이디 
	 */
	public String searchId(Connection conn, String userName, String phone) {
		
		String userId = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchId"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, phone);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				userId = rset.getString("user_id");
			}
						
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return userId;

	}
	
	
	
	/** 비밀번호 찾기 
	 * @param conn
	 * @param userName
	 * @param userId
	 * @param email
	 * @return 비밀번호 
	 */
	public String searchPwd(Connection conn, String userName, String userId, String email) {
		String userPwd = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchPwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, userId);
			pstmt.setString(3, email);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				userPwd = rset.getString("user_pwd");
			}
						
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return userPwd;
		
	}
	
	/** 파트너 정보 가져오기 
	 * @param conn
	 * @param loginUser
	 * @return
	 */
	public Member getPartner(Connection conn, Member loginUserAll) {
		
		String sql = prop.getProperty("getPartner");
		PreparedStatement pstmt = null; 
		ResultSet rset = null; 
		
		System.out.println("ccode :  " + loginUserAll.getcCode());
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginUserAll.getcCode());
			pstmt.setString(2, loginUserAll.getUserId());
			
			rset = pstmt.executeQuery(); 
			
			if(rset.next()) {
				loginUserAll.setPartnerId(rset.getString("user_id"));
				loginUserAll.setPartnerBirth(rset.getString("birth"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return loginUserAll;
		
	}
	
	
	/** 아이디 중복체크 
	 * @param conn
	 * @param userId
	 * @return
	 */
	public int idCheck(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null; 
		ResultSet rset = null; 
		
		String sql = prop.getProperty("idCheck"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
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
	
	
	public int updateProfile(Connection conn, String path, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProfile");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, path);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
		
	}
	
	

}
