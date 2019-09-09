package coupleLink.model.vo;

import java.sql.Date;

public class CoupleAuth {
	
    String userId; 
    String userName; 
    int authNo;
    String fromId;
    String fromName;
    Date startTime;
    Date endTime;
    
    public CoupleAuth() {}

    



	public CoupleAuth(String userId, String userName, int authNo, String fromId, String fromName, Date startTime,
			Date endTime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.authNo = authNo;
		this.fromId = fromId;
		this.fromName = fromName;
		this.startTime = startTime;
		this.endTime = endTime;
	}



	public String getFromName() {
		return fromName;
	}


	public void setFromName(String fromName) {
		this.fromName = fromName;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



	public int getAuthNo() {
		return authNo;
	}



	public void setAuthNo(int authNo) {
		this.authNo = authNo;
	}



	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "CoupleCode [userId=" + userId + ", userName=" + userName + ", authNo=" + authNo + ", fromId="
				+ fromId + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
    
}
