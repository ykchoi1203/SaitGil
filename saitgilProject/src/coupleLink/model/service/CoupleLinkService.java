package coupleLink.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.Random;

import coupleLink.model.dao.CoupleLinkDao;
import member.model.dao.MemberDao;
import shareFile.model.dao.ShareFileDao;
import coupleLink.model.vo.CoupleAuth;

public class CoupleLinkService {
	
	/** 인증번호 보내기 위해 파트너 아이디 찾기 
	 * @param partnerName   입력받은 파트너 이름 
	 * @param partnerPhone  입력받은 파트너 전화번호 
	 * @return  검색된 파트너 아이디 
	 */
	public String selectId(String partnerName, String partnerPhone) {
		Connection conn = getConnection(); 
		
		String partnerId = new CoupleLinkDao().selectId(conn, partnerName, partnerPhone);
		
		close(conn);
		
		return partnerId;
	}
	
	
	/** 인증번호 생성 
	 * @return
	 */
	public int getAuthNo() {
		int authNo = 0;
		
		while(authNo < 1000) {
			authNo = (int)(Math.random() * 9999) + 1;
		}
				
		return authNo; 
	}
	
	
	
	
	/** 인증번호 데이터에 추가하는 서비스 
	 * @param ca
	 * @return
	 */
	public int insertCoupleAuth(CoupleAuth ca) {
		Connection conn = getConnection(); 

		
		int result = new CoupleLinkDao().checkExist(conn, ca.getUserId()); 
		int result2 = 0;
		if(result > 0) { //이미 전송된 인증번호가 존재한다. 
			result2 = -1; 
		} else { //전송된 인증번호 X,  인증번호 보낼 수 있음. 
			result2 = new CoupleLinkDao().insertCoupleAuth(conn, ca); 
		}
		
		if(result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn); 
		
		return result2;
		
	}
	
	
	
	/** 입력받은 인증번호 일치한지 확인하는 서비스 
	 * @param ca
	 * @return
	 */
	public int isMatch(CoupleAuth ca) {
		Connection conn = getConnection(); 
		
		int result = new CoupleLinkDao().isMatch(conn, ca); 
		
		close(conn);
		
		return result;

	}
	
	
	
	
	/** 커플 코드 생성하기 
	 * @param authNo
	 * @return
	 */
	public String insertCcode(int authNo) {
		Connection conn = getConnection(); 
		CoupleAuth ca = new CoupleLinkDao().selectCouple(conn, authNo);
		String coupleId = ca.getUserId();
		String coupleId2 = ca.getFromId(); 
		
		String coupleCode = createCcode(); 
		
		int result = new CoupleLinkDao().insertCcode(conn, coupleId, coupleId2, coupleCode);
		int result1 = new ShareFileDao().insertCcode(conn, coupleCode);
		
		if(result > 0 && result1 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
	
		return coupleCode;
	}
	
	
	
	/**
	 * @return
	 */
	public String createCcode() {
		Connection conn = getConnection(); 
		Random rnd = new Random();
		int result = 1; 
		String ran ="";
		while(result > 0) {
			ran ="";
			
			for(int i=0; i<=4; i++ ) {
				ran += String.valueOf((char) ((int) (rnd.nextInt(26)) + 97));
			}
			
			for(int i=0; i<=4; i++) {
				ran += String.valueOf(rnd.nextInt(10));
			}
			
			result = new CoupleLinkDao().isCodeExist(conn, ran);
		
		}
		return ran;
	}
	
	/** 인증번호 확인 후 삭제 
	 * @param auth
	 */
	public void deleteCoupleAuth(int authNo) {
		Connection conn = getConnection(); 
		
		int result = new CoupleLinkDao().deleteCoupleAuth(conn, authNo);
		
		if(result > 0) {
			System.out.println("인증번호를 삭제하였습니다.");
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
	}
	
	
	/** 커플인 회원인지 조회하기 
	 * @param userId
	 * @return
	 */
	public boolean checkCouple(String userId) {
		boolean isCouple = false; 
		
		Connection conn = getConnection(); 
		
		isCouple = new CoupleLinkDao().checkCouple(conn, userId); 
		
		close(conn);
		
		return isCouple;
			
	}
	
	

}
