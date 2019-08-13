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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//회원 로그인
	public Member loginMember(Connection conn, String userId, String userPwd) {
		System.out.println("dao까지는 옸다.");
		
		Member loginUser = null; 
		
		PreparedStatement pstmt = null; 
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		System.out.println(userId + userPwd);
		
		try {

			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery(); 
			
			if(rset.next()) {
				System.out.println("있다");
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
			} else {
				System.out.println("reset이 없다");
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
	
	
	/** 회원 객체 가져오기 (회원정보 변경 시 덮어씌울 용도)
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
			close(pstmt);
		}
		
		
		
		return mem;
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
				loginUserAll.setPartnerName(rset.getString("user_name"));
				loginUserAll.setPartnerBirth(rset.getString("birth"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		System.out.println("파트너 있을까?" + loginUserAll.getPartnerName());
		
		return loginUserAll;
		
	}
	
	

}
