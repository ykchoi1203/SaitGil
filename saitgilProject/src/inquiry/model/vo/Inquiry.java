package inquiry.model.vo;

import java.sql.Date;

public class Inquiry {
   
    private int inquiryNo; 
    private String userId;
    private String inquiryTitle;
    private String inquiryContent; 
    private String writer; 
    private Date date;
    private String rStatus;
    private Date rDate;
    private String rContent;
    private String delStatus;
    
    
    public Inquiry() {}
    
    
    
    

   public Inquiry(String userId, String inquiryTitle, String inquiryContent, String writer) {
      super();
      this.userId = userId;
      this.inquiryTitle = inquiryTitle;
      this.inquiryContent = inquiryContent;
      this.writer = writer;
   }


   public Inquiry(int inquiryNo, String userId, String inquiryTitle, String inquiryContent, String writer, Date date,
         String rStatus, Date rDate, String rContent, String delStatus) {
      super();
      this.inquiryNo = inquiryNo;
      this.userId = userId;
      this.inquiryTitle = inquiryTitle;
      this.inquiryContent = inquiryContent;
      this.writer = writer;
      this.date = date;
      this.rStatus = rStatus;
      this.rDate = rDate;
      this.rContent = rContent;
      this.delStatus = delStatus;
   }



   public String getUserId() {
      return userId;
   }


   public void setUserId(String userId) {
      this.userId = userId;
   }


   public int getInquiryNo() {
      return inquiryNo;
   }



   public void setInquiryNo(int inquiryNo) {
      this.inquiryNo = inquiryNo;
   }



   public String getInquiryTitle() {
      return inquiryTitle;
   }



   public void setInquiryTitle(String inquiryTitle) {
      this.inquiryTitle = inquiryTitle;
   }



   public String getInquiryContent() {
      return inquiryContent;
   }



   public void setInquiryContent(String inquiryContent) {
      this.inquiryContent = inquiryContent;
   }



   public String getWriter() {
      return writer;
   }



   public void setWriter(String writer) {
      this.writer = writer;
   }



   public Date getDate() {
      return date;
   }



   public void setDate(Date date) {
      this.date = date;
   }



   public String getrStatus() {
      return rStatus;
   }



   public void setrStatus(String rStatus) {
      this.rStatus = rStatus;
   }



   public Date getrDate() {
      return rDate;
   }



   public void setrDate(Date rDate) {
      this.rDate = rDate;
   }



   public String getrContent() {
      return rContent;
   }



   public void setrContent(String rContent) {
      this.rContent = rContent;
   }



   public String getDelStatus() {
      return delStatus;
   }



   public void setDelStatus(String delStatus) {
      this.delStatus = delStatus;
   }



   @Override
   public String toString() {
      return "Inquiry [inquiryNo=" + inquiryNo + ", inquiryTitle=" + inquiryTitle + ", inquiryContent="
            + inquiryContent + ", writer=" + writer + ", date=" + date + ", rStatus=" + rStatus + ", rDate=" + rDate
            + ", rContent=" + rContent + ", delStatus=" + delStatus + "]";
   }
   
}