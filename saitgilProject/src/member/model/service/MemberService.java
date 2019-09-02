package member.model.service;

import member.model.dao.MemberDao;
import member.model.vo.Member;

import static common.JDBCTemplate.*;

import java.sql.Connection;

public class MemberService {
	
	//회원 로그인 (session에 필요한 member 객체 정보, 파트너 이름, 생일 포함)
		public Member loginMember(String userId, String userPwd) {
			Connection conn = getConnection(); 
			
			Member loginUser = new MemberDao().loginMember(conn, userId, userPwd); 
			
			Member loginUserAll = null;
			
			if(loginUser !=  null) {
				loginUserAll = new MemberDao().getPartner(conn, loginUser); 
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
			int result2 = new MemberDao().deleteCCode(conn, userId);
			
			if(result > 0 && result2 > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			
			close(conn);
			
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
		public String searchPwd(String userName, String userId, String email) {
			Connection conn = getConnection(); 
			
			String userPwd = new MemberDao().searchPwd(conn, userName, userId, email);
			
			close(conn);
			
			return userPwd;
			
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

}
