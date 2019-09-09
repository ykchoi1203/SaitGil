package shareFile.model.vo;

import java.sql.Date;

public class ShareFile {
	
	private String cCode;
	private String cPicturePath; 
	private String originName;
	private String changeName;
	private Date meetDate;
	
	public ShareFile() {}
	
	public ShareFile(String cCode, String cPicturePath, String originName, String changeName, Date meetDate) {
		super();
		this.cCode = cCode;
		this.cPicturePath = cPicturePath;
		this.originName = originName;
		this.changeName = changeName;
		this.meetDate = meetDate;
	}


	public String getcCode() {
		return cCode;
	}


	public void setcCode(String cCode) {
		this.cCode = cCode;
	}


	public String getcPicturePath() {
		return cPicturePath;
	}


	public void setcPicturePath(String cPicturePath) {
		this.cPicturePath = cPicturePath;
	}


	public String getOriginName() {
		return originName;
	}


	public void setOriginName(String originName) {
		this.originName = originName;
	}


	public String getChangeName() {
		return changeName;
	}


	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}


	public Date getMeetDate() {
		return meetDate;
	}


	public void setMeetDate(Date meetDate) {
		this.meetDate = meetDate;
	}


	@Override
	public String toString() {
		return "ShareFile [cCode=" + cCode + ", cPicturePath=" + cPicturePath + ", originName=" + originName
				+ ", changeName=" + changeName + ", meetDate=" + meetDate + "]";
	}
	
	
}
