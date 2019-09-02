package notice.model.vo;

import java.sql.Date;

/**
 * @author Meseon May Han
 *
 */
public class Notice {
	
	private int noticeNo; 
	private String noticeTitle;
	private String noticeContent;
	private String writer;
	private Date noticeDate;
	private int noticeCount;
	
	public Notice() {}
	
	
	public Notice(int noticeNo, String noticeTitle, String noticeContent, String writer, Date noticeDate,
			int noticeCount) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.writer = writer;
		this.noticeDate = noticeDate;
		this.noticeCount = noticeCount;
	}
	
	


	public Notice(String noticeTitle, String noticeContent, String writer) {
		super();
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.writer = writer;
	}




	public int getNoticeNo() {
		return noticeNo;
	}


	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}


	public String getNoticeTitle() {
		return noticeTitle;
	}


	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}


	public String getNoticeContent() {
		return noticeContent;
	}


	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public Date getNoticeDate() {
		return noticeDate;
	}


	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}


	public int getNoticeCount() {
		return noticeCount;
	}


	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}


	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", writer=" + writer + ", noticeDate=" + noticeDate + ", noticeCount=" + noticeCount + "]";
	}
	
}
