package member.model.vo;

import java.sql.Date;

public class Member implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2012656010484232559L;
	private String userId;
	private String userPwd;
	private String userName;
	private String birth;
	private String phone;
	private String email;
	private String gender;
	private String address;
	private Date joinDate;
	private int reports;
	private String profilePic;
	private int invitationCode;
	private String status;
	private String cCode;
	private String partnerId;
	private String partnerBirth;
	
	public Member() {}
	
	public Member(String userId, String userPwd, String userName, String birth, String phone, String email, String gender,
			String address) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.birth = birth;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.address = address;
	}
	
	
	
	public Member(String userId, String userName, String birth, String phone, String email, String gender, String address,
			Date joinDate, int reports, String status, String cCode) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.birth = birth;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.address = address;
		this.joinDate = joinDate;
		this.reports = reports;
		this.status = status;
		this.cCode = cCode;
	}

	public Member(String userId, String userPwd, String userName, String birth, String phone, String email, String gender,
			String address, Date joinDate, int reports, String profilePic, int invitationCode, String status,
			String cCode) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.birth = birth;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.address = address;
		this.joinDate = joinDate;
		this.reports = reports;
		this.profilePic = profilePic;
		this.invitationCode = invitationCode;
		this.status = status;
		this.cCode = cCode;
	}
	

	

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserPwd() {
		return userPwd;
	}


	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Date getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}


	public int getReports() {
		return reports;
	}


	public void setReports(int reports) {
		this.reports = reports;
	}


	public String getProfilePic() {
		return profilePic;
	}


	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}


	public int getInvitationCode() {
		return invitationCode;
	}


	public void setInvitationCode(int invitationCode) {
		this.invitationCode = invitationCode;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getcCode() {
		return cCode;
	}


	public void setcCode(String cCode) {
		this.cCode = cCode;
	}


	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}


	public String getPartnerBirth() {
		return partnerBirth;
	}

	public void setPartnerBirth(String partnerBirth) {
		this.partnerBirth = partnerBirth;
	}





	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", birth=" + birth
				+ ", phone=" + phone + ", email=" + email + ", gender=" + gender + ", address=" + address
				+ ", joinDate=" + joinDate + ", reports=" + reports + ", profilePic=" + profilePic + ", invitationCode="
				+ invitationCode + ", status=" + status + ", cCode=" + cCode + "]";
	}
	


}
