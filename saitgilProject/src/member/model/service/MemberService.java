package member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import coupleLink.model.dao.CoupleLinkDao;
import member.model.dao.MemberDao;
import member.model.vo.Member;


/**
 * @author Meseon May Han
 *
 */
public class MemberService {
	
	//회원 로그인 (session에 필요한 member 객체 정보, 파트너 이름, 생일 포함)
	public Member loginMember(String userId, String userPwd) {
		Connection conn = getConnection(); 
		
		Member loginUser = new MemberDao().loginMember(conn, userId, userPwd); 
		
		Member loginUserAll = null;
		
		if(loginUser !=  null) {
			loginUserAll = new MemberDao().addPartner(conn, loginUser); 
		} 
		
		close(conn);
		
		return loginUserAll;
		
		
	}
	
	//회원 로그인 페이지 결정(실패, 커플코드 유무)
	public int loginPage(String userId, String userPwd) {
		
		Connection conn = getConnection(); 
		int result = 0;  //0:로그인실패, 1:커플코드O, 2:커플코드X
		
		Member loginUser = new MemberDao().loginMember(conn, userId, userPwd);
		
		if(loginUser != null) { 
			if(loginUser.getcCode() != null) { 
				result = 1;
			} else {
				result = 2;
			}
		} 
		
		if(loginUser != null && loginUser.getUserId().equals("admin")) {
			result = 1;
		}
		
		close(conn);
		
		return result;

	}
	

	
	/** 커플파일 오브잭트 받아오는 서비스 
	 * @param cCode
	 * @return
	 */

	
	
	// 회원가입
	public int insertMember(Member mem) {
		
		Connection conn = getConnection(); 
		
		int result = new MemberDao().insertMember(conn, mem); 
		
		if(result > 0) {
			commit(conn); 
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	
	/** 회원정보 수정하기 
	 * @param mem -> 정보 변경된걸로 새로 만든 멤버 객체 
	 * @return
	 */
	public int updateMember(Member mem) {
		Connection conn = getConnection(); 
		
		int result = new MemberDao().updateMember(conn, mem);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	
	/** 회원 수정 후 객체 가져오기 (DB접속 종료 오류때문에 분리시킴..)
	 * @param userId
	 * @return
	 */
	public Member selectMember(String userId) {
		Connection conn = getConnection(); 
	
		Member updateMem = new MemberDao().selectMember(conn, userId);
		
		close(conn);
		
		return updateMem;
		
	}
	
	
	
	/** 회원 탈퇴하기 
	 * @param userId
	 * @return
	 */
	public int deleteMember(String userId) {
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMember(conn, userId); 
		int result2 = 0;
		if(!(new CoupleLinkDao().checkCouple(conn, userId))) {
			result2 = 1;
		}else {
			result2 = new MemberDao().deleteCCode(conn, userId);
		}
		
		if(result > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	 
	/** 비밀번호 변경하기 
	 * @param userPwd
	 * @param userId
	 * @return
	 */
	public int updatePwd(String newPwd, String userId) {
		Connection conn = getConnection(); 
		
		int result = new MemberDao().updatePwd(conn, newPwd, userId);
		

		if(result > 0) {
			commit(conn); 
		} else {
			rollback(conn);
		}
		
		return result;
	}
	
	//아이디 찾기
	public String searchId(String userName, String phone) {
		
		Connection conn = getConnection(); 
		String userId = new MemberDao().searchId(conn, userName, phone);
		
		close(conn);
		
		return userId;

	}
	
	/** 비밀번호 찾기 
	 * @param userName
	 * @param userId
	 * @param email
	 * @return
	 */
	public boolean searchPwd(String userName, String userId, String email) {
		Connection conn = getConnection(); 
		
		boolean isTrue = new MemberDao().searchPwd(conn, userName, userId, email);
		
		close(conn);
		
		return isTrue;
		
	}
	
	
	/** 아이디 중복확인 
	 * @param userId
	 * @return
	 */
	public int idCheck(String userId) {
		Connection conn = getConnection();
		
		int result = new MemberDao().idCheck(conn, userId);
		
		close(conn);
		
		return result;
	}
	
	
	/** 개인 프로필사진 변경 
	 * @param path
	 * @param userId
	 * @return
	 */
	public int updateProfile(String path, String userId) {
		Connection conn = getConnection();
		
		int result = new MemberDao().updateProfile(conn, path, userId);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
		
	}
	
	
	/** 커플 연동 후 커플정보페이지 넘길 파트너 객체 받아오기 
	 * @param cCode
	 * @param partnerName
	 * @return
	 */
	public Member selectPartner(String cCode, String partnerName) {
		Connection conn = getConnection(); 
		
		Member partner = new MemberDao().selectPartner(conn, cCode, partnerName);
		
		close(conn);
		
		return partner;
		
	}
	
	public int deleteCcode(String userId) {
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteCCode(conn, userId);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	
	
	

}
