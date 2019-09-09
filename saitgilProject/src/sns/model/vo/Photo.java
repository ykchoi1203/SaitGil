package sns.model.vo;

public class Photo {
	private int pNo;			// �궗吏� 踰덊샇
	private String photoPath;	// �궗吏� 寃쎈줈
	private int bNo;			// �궗吏꾩씠 寃뚯떆�맂 湲� 踰덊샇
	private String changeName;	// �궗吏� �씠由�
	private String status;		// �궘�젣�뿬遺�
	public Photo() {}

	public Photo(int pNo, String photoPath, int bNo, String changeName, String status) {
		super();
		this.pNo = pNo;
		this.photoPath = photoPath;
		this.bNo = bNo;
		this.changeName = changeName;
		this.status = status;
	}


	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Photo [pNo=" + pNo + ", photoPath=" + photoPath + ", bNo=" + bNo + ", changeName=" + changeName
				+ ", status=" + status + "]";
	}

	
	
	
}
