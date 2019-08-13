package member.model.service;

import member.model.dao.MemberDao;
import member.model.vo.Member;

import static common.JDBCTemplate.*;

import java.sql.Connection;

public class MemberService {
	
	//회원 로그인
	public Member loginMember(String userId, String userPwd) {
		System.out.println("서비스단엔 왔다");
		
		Connection conn = getConnection(); 
		
		Member loginUser = new MemberDao().loginMember(conn, userId, userPwd);
		
		if(loginUser.getcCode() != null) { 
			Member loginUserAll = new MemberDao().getPartner(conn, loginUser); 
			
			return loginUserAll;
		}
		
		close(conn);
		
		return loginUser;

	}
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
	public Member updateMember(Member mem) {
		Connection conn = getConnection(); 
		
		int result = new MemberDao().updateMember(conn, mem);
		
		Member updateMem = null;
		
		if(result > 0) {
			commit(conn);
			
			updateMem = new MemberDao().selectMember(conn, mem.getUserId());
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		System.out.println("updateMem" + updateMem.getUserId());
		return updateMem;
		
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
	public String searchPwd(String userName, String userId, String email) {
		Connection conn = getConnection(); 
		
		String userPwd = new MemberDao().searchPwd(conn, userName, userId, email);
		
		close(conn);
		
		return userPwd;
		
	}

}
