package sns.model.vo;

import java.sql.Date;

public class Comment {
	private int commentNo;
	private String userId;
	private String content;
	private Date commentDate;
	private int report;
	private int bNo;
	private String status;
	private String profile_pic;
	private String partnerName;
	
	
	public Comment() {}
	
	






	public Comment(int commentNo, String userId, String content, Date commentDate, int report, int bNo, String status,
			String profile_pic, String partnerName) {
		super();
		this.commentNo = commentNo;
		this.userId = userId;
		this.content = content;
		this.commentDate = commentDate;
		this.report = report;
		this.bNo = bNo;
		this.status = status;
		this.profile_pic = profile_pic;
		this.partnerName = partnerName;
	}








	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public int getReport() {
		return report;
	}

	public void setReport(int report) {
		this.report = report;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getProfile_pic() {
		return profile_pic;
	}




	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}




	public String getPartnerName() {
		return partnerName;
	}








	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}








	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", userId=" + userId + ", content=" + content + ", commentDate="
				+ commentDate + ", report=" + report + ", bNo=" + bNo + ", status=" + status + ", profile_pic="
				+ profile_pic + ", partnerName=" + partnerName + "]";
	}








	





	
}
