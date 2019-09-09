package sns.model.vo;

import java.sql.Date;

public class Board {
	
	/*
	 * board_table, like_table, scrap 3媛쒖쓽 �뀒�씠釉� 紐⑤몢 �궗�슜 �븷 �닔 �엳�뒗 vo�엯�땲�떎.
	 */
	
	private int bNo;			// 湲� 踰덊샇
	private String userId;		// 湲� �옉�꽦�옄 or 醫뗭븘�슂 �븳 �쑀�� or �뒪�겕�옪 �븳 而ㅽ뵆肄붾뱶
	private String partnerName;	// �뙆�듃�꼫 �씠由�
	private String cCode;		// �옉�꽦�옄 而ㅽ뵆肄붾뱶
	private String bContent;	// 湲� �궡�슜
	private Date bDate;			// �옉�꽦 �궇吏�
	private String isPublic;	// 怨듭쑀�씤吏�, 媛쒖씤�씤吏�
	private String status;		// �궘�젣 �뿬遺�
	private int report;			// �떊怨� �슏�닔
	private int likeCount;		// 醫뗭븘�슂 �슏�닔
	


	public Board() {}



	public Board(int bNo, String userId, String partnerName, String cCode, String bContent, Date bDate, String isPublic, String status,
			int report, int likeCount) {
		super();
		this.bNo = bNo;
		this.userId = userId;
		this.partnerName = partnerName;
		this.cCode = cCode;
		this.bContent = bContent;
		this.bDate = bDate;
		this.isPublic = isPublic;
		this.status = status;
		this.report = report;
		this.likeCount = likeCount;
	}



	public int getbNo() {
		return bNo;
	}



	public void setbNo(int bNo) {
		this.bNo = bNo;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getcCode() {
		return cCode;
	}



	public void setcCode(String cCode) {
		this.cCode = cCode;
	}



	public String getbContent() {
		return bContent;
	}



	public void setbContent(String bContent) {
		this.bContent = bContent;
	}



	public Date getbDate() {
		return bDate;
	}



	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}



	public String getIsPublic() {
		return isPublic;
	}



	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public int getReport() {
		return report;
	}



	public void setReport(int report) {
		this.report = report;
	}



	public int getLikeCount() {
		return likeCount;
	}



	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}



	public String getPartnerName() {
		return partnerName;
	}



	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}



	@Override
	public String toString() {
		return "Board [bNo=" + bNo + ", userId=" + userId + ", cCode=" + cCode + ", bContent=" + bContent + ", bDate="
				+ bDate + ", isPublic=" + isPublic + ", status=" + status + ", report=" + report + ", likeCount="
				+ likeCount + ", partnerName=" + partnerName + "]";
	}





	
}
