/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.43
 * Generated at: 2019-08-12 06:17:30 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import member.model.vo.Member;
import member.model.vo.Member;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class indexLogin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/views/common/../common/menubar.jsp", Long.valueOf(1565590646682L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.Calendar");
    _jspx_imports_classes.add("member.model.vo.Member");
    _jspx_imports_classes.add("java.util.Date");
    _jspx_imports_classes.add("java.text.SimpleDateFormat");
    _jspx_imports_classes.add("java.text.ParseException");
    _jspx_imports_classes.add("java.text.DateFormat");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML>\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<title>우리 둘만의 사잇길</title>\r\n");
      out.write("\t\t<meta charset=\"utf-8\" />\r\n");
      out.write("\t\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"resources/css/mainLogin.css\" />\r\n");
      out.write("\t\t<style>\r\n");
      out.write("\t\t    /* 커플 메인사진  */\r\n");
      out.write("\t\t\t#banner {  \r\n");
      out.write("\t\t\t\tdisplay: -ms-flexbox;\r\n");
      out.write("\t\t\t\t-ms-flex-pack: center;\r\n");
      out.write("\t\t\t\t-ms-flex-align: center;\r\n");
      out.write("\t\t\t\tpadding: 0em 0em 0em 0em;\r\n");
      out.write("\t\t\t\t-moz-align-items: center;\r\n");
      out.write("\t\t\t\t-webkit-align-items: center;\r\n");
      out.write("\t\t\t\t-ms-align-items: center;\r\n");
      out.write("\t\t\t\talign-items: center;\r\n");
      out.write("\t\t\t\tdisplay: -moz-flex;\r\n");
      out.write("\t\t\t\tdisplay: -webkit-flex;\r\n");
      out.write("\t\t\t\tdisplay: -ms-flex;\r\n");
      out.write("\t\t\t\tdisplay: flex;\r\n");
      out.write("\t\t\t\t-moz-justify-content: center;\r\n");
      out.write("\t\t\t\t-webkit-justify-content: center;\r\n");
      out.write("\t\t\t\t-ms-justify-content: center;\r\n");
      out.write("\t\t\t\tjustify-content: center;\t\t\t\r\n");
      out.write("\t\t\t\tbackground-image: url(\"resources/images/mainImage.jpg\");\r\n");
      out.write("\t\t\t\tbackground-position: center;\r\n");
      out.write("\t\t\t\tbackground-size: cover;\r\n");
      out.write("\t\t\t\tbackground-repeat: no-repeat;\r\n");
      out.write("\t\t\t\tborder-top: 0;\r\n");
      out.write("\t\t\t\tmin-height: 100vh;\r\n");
      out.write("\t\t\t\theight: 100vh !important;\r\n");
      out.write("\t\t\t\tposition: relative;\r\n");
      out.write("\t\t\t\ttext-align: center;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t#notice {\r\n");
      out.write("\t\t\t\twidth:80%;\r\n");
      out.write("\t\t\t\tmargin:auto;\r\n");
      out.write("\t\t\t\tcolor:red;\r\n");
      out.write("\t\t\t\ttext-align:left;\r\n");
      out.write("\t\t\t\tmargin-left:15% ;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t</style>\r\n");
      out.write("\t</head>\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\t<div id=\"notice\">\r\n");
      out.write("\t\t<img src=\"resources/images/icons/notice.png\" width=\"30px\" height=\"30px\"> <b>[공지사항]</b> 사잇길 점검시간 공지안내\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\r\n");

	Member loginUser = (Member)session.getAttribute("loginUser");
	

      out.write("    \r\n");

	String birthday = loginUser.getBirth();
	String pBirthday = loginUser.getPartnerBirth();
	String result ="";
	String pResult=""; 

	if(birthday == null || birthday == "") {
	    result = "입력된 값이 없습니다.";
	 } else {
	
		 if(birthday.charAt(5) == '0') {
		    if(birthday.charAt(8) == 0) {
		       result = birthday.charAt(6) + "월 " + birthday.charAt(9) + "일";
		    }else {
		       result = birthday.charAt(6) + "월 " + birthday.substring(8,10) + "일";
		    }
		 }else {
		    if(birthday.charAt(8) == 0) {
		       result = birthday.substring(5,7) + "월 " + birthday.charAt(9) + "일";
		    }else {
		       result = birthday.substring(5,7) + "월 " + birthday.substring(8,10) + "일";
		    }
		 }
	}
	if(pBirthday == null || pBirthday == "") {
	    pResult = "입력된 값이 없습니다.";
	 } else {
	
		 if(pBirthday.charAt(5) == '0') {
		    if(pBirthday.charAt(8) == 0) {
		       pResult = pBirthday.charAt(6) + "월 " + pBirthday.charAt(9) + "일";
		    }else {
		       pResult = pBirthday.charAt(6) + "월 " + pBirthday.substring(8,10) + "일";
		    }
		 }else {
		    if(pBirthday.charAt(8) == 0) {
		       pResult = pBirthday.substring(5,7) + "월 " + pBirthday.charAt(9) + "일";
		    }else {
		       pResult = pBirthday.substring(5,7) + "월 " + pBirthday.substring(8,10) + "일";
		    }
		 }
	}
	
    Calendar cal = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();
    Calendar cal3 = Calendar.getInstance();
    Calendar today = Calendar.getInstance();
	
    String start = "2017-01-01";
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = null;

    try {         
       startDate = sdf.parse(start);

    } catch (ParseException e) {
       e.printStackTrace();

    }
    
    cal.setTime(startDate);
    cal2.setTime(startDate);
    cal3.setTime(startDate);
    
    int day = 100;
    int dday = (int)((today.getTimeInMillis()-cal.getTimeInMillis())/(1000*60*60*24)+1);
    while(dday > day) {
       day +=100;
    }
    
    cal2.add(Calendar.DATE, day-1);
    cal3.add(Calendar.DATE, day+99);
    int year = (int)(((today.getTimeInMillis()-cal.getTimeInMillis())/(1000*60*60*24)+1)/365)+1;
    
    cal.add(Calendar.YEAR, year);
    
    String anivDate = sdf.format(cal.getTime());
    String dDate1 = sdf.format(cal2.getTime());
    String dDate2 = sdf.format(cal3.getTime());
    
    long anivDay = ((cal.getTimeInMillis()-today.getTimeInMillis())/(1000*60*60*24)+1);
    long dDay1 = ((cal2.getTimeInMillis()-today.getTimeInMillis())/(1000*60*60*24)+1);
    long dDay2 = ((cal3.getTimeInMillis()-today.getTimeInMillis())/(1000*60*60*24)+1);
    


      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta charset=\"utf-8\" />\r\n");
      out.write("\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\r\n");
      out.write("\t<title>우리 둘만의 사잇길</title>\r\n");
      out.write("\t<link href=\"resources/css/menubar.css\" rel=\"stylesheet\" type=\"text/css\">\t\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    \t <aside id=\"profile-1\" class=\"profileSide\"> \r\n");
      out.write("            <a href=\"#\"><img src=\"resources/images/profile_boy.png\" id=\"photo1\" class=\"profile\"></a>\r\n");
      out.write("            <span id=\"myName\">");
      out.print( loginUser.getUserName() );
      out.write("</span> ♥\r\n");
      out.write("\t\t\t<p id=\"myBirthDay\">");
      out.print( result );
      out.write("</p>\r\n");
      out.write("\t\t\t<table id=\"menuTB\"\t>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th><img src=\"");
      out.print( request.getContextPath() );
      out.write("/resources/images/icons/myPage2.png\" class=\"icon\" onclick=\"goMyPage();\"></th>\r\n");
      out.write("\t\t\t\t\t<th><img src=\"resources/images/icons/calendar2.png\" class=\"icon\"></th>\r\n");
      out.write("\t\t\t\t\t<th><img src=\"resources/images/icons/store2.png\" class=\"icon\"></th>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>마이페이지</td>\r\n");
      out.write("\t\t\t\t\t<td>캘린더</td>\r\n");
      out.write("\t\t\t\t\t<td>스토어</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th><img src=\"resources/images/icons/gallery2.png\" class=\"icon\"></th>\r\n");
      out.write("\t\t\t\t\t<th><img src=\"resources/images/icons/saving3.png\" class=\"icon\"></th>\r\n");
      out.write("\t\t\t\t\t<th><img src=\"resources/images/icons/sns2.png\" class=\"icon\"></th>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>사진첩</td>\r\n");
      out.write("\t\t\t\t\t<td>가계부</td>\r\n");
      out.write("\t\t\t\t\t<td>SNS</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div id=\"help\">\r\n");
      out.write("\t\t\t\t<img src=\"resources/images/icons/help3.png\" alt=\"\">\r\n");
      out.write("\t\t\t\t문의하기\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</aside>\r\n");
      out.write("        <aside id=\"profile-2\" class=\"profileSide\">\r\n");
      out.write("\t\t\t<div id=\"logout\" onclick=\"logout();\">\r\n");
      out.write("\t\t\t\tLOGOUT\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("            <a href=\"#\"><img src=\"resources/images/profile_girl.png\" id=\"photo2\" class=\"profile\"></a>\t\r\n");
      out.write("            ♥ <span id=\"partnerName\">");
      out.print( loginUser.getPartnerName() );
      out.write("</span>\r\n");
      out.write("            <p id=\"partnerBirthDay\">");
      out.print( pResult );
      out.write("</p>\r\n");
      out.write("\t\t\t<p id=\"firstDate\"></p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<table id=\"anniversary\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>");
      out.print( day );
      out.write("일</th>\r\n");
      out.write("\t\t\t\t\t<th id=\"date1\" class=\"date\">");
      out.print( dDay1 );
      out.write("일 남음</th>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>");
      out.print( day+100 );
      out.write("일</th>\r\n");
      out.write("\t\t\t\t\t<th id=\"date2\" class=\"date\">");
      out.print( dDay2 );
      out.write("일 남음</th>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>");
      out.print( year );
      out.write("주년</th>\r\n");
      out.write("\t\t\t\t\t<th id=\"date3\" class=\"date\">");
      out.print( anivDay );
      out.write("일 남음</th>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("        </aside>\r\n");
      out.write("        \r\n");
      out.write("        <script>\r\n");
      out.write("        \tfunction goMyPage() {\r\n");
      out.write("        \t\tlocation.href = \"");
      out.print( request.getContextPath() );
      out.write("/myPage.me\";\r\n");
      out.write("        \t}\r\n");
      out.write("        \t\r\n");
      out.write("        \tfunction logout() {\r\n");
      out.write("        \t\tlocation.href = '");
      out.print( request.getContextPath() );
      out.write("/logout.me';\r\n");
      out.write("        \t}\r\n");
      out.write("        </script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\t\t<!-- Banner -->\r\n");
      out.write("\t\t<section id=\"banner\">\r\n");
      out.write("\t\t\t<div class=\"inner\" style=\"width:100%; font-family:'Nanum Myeongjo'; margin: 0em !important; padding: 0em !important; border: 0em !important;\">\r\n");
      out.write("\t\t\t\t\t<header>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"#selectPhoto\" style=\"float:right;\" onclick=\"changePhoto();\"> <img src=\"resources/images/photoIcon.png\" width=\"40px\" class=\"icon\"></a>\r\n");
      out.write("\t\t\t\t\t\t<h3 calss=\"mainDay\" >  &nbsp;&nbsp;&nbsp;&nbsp; 처음 만난 날</h3>\r\n");
      out.write("\t\t\t\t\t\t<h2 class=\"mainDay\" style=\"font-family:'Nanum Myeongjo'; color: #fa96b5\" ><a href=\"#none\" onclick=\"changeDate();\">&nbsp;&nbsp;&nbsp;106일째</a></h2>\r\n");
      out.write("\t\t\t\t\t\t<h3 class=\"mainDay\">상순 ♥ 효리</h3>\r\n");
      out.write("\t\t\t\t\t</header>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</section>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<script>\r\n");
      out.write("\t\t\t\tfunction changePhoto() {\r\n");
      out.write("\t\t\t\t\talert('배경 사진바꿔라');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t\tfunction changeDate() {\r\n");
      out.write("\t\t\t\t\tvar firstDate = document.getElementById('firstDate');\r\n");
      out.write("\t\t\t\t\tvar date = prompt('처음 만날 날짜를 입력하세요');\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\tfirstDate.innerHTML = '사귄 날 : ' + date;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- Footer -->\r\n");
      out.write("\t\t\t<footer id=\"footer\">\r\n");
      out.write("\t\t\t\t<div class=\"copyright\">\r\n");
      out.write("\t\t\t\t\t<ul class=\"icons\">\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"#\" class=\"icon fa-twitter\"><span class=\"label\">Twitter</span></a></li>\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"#\" class=\"icon fa-facebook\"><span class=\"label\">Facebook</span></a></li>\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"#\" class=\"icon fa-instagram\"><span class=\"label\">Instagram</span></a></li>\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"#\" class=\"icon fa-snapchat\"><span class=\"label\">Snapchat</span></a></li>\r\n");
      out.write("\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t<p>&copy; Untitled. All rights reserved. Design: <a href=\"https://templated.co\">TEMPLATED</a>. Images: <a href=\"https://unsplash.com\">Unsplash</a>.</p>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</footer>\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- Scripts -->\r\n");
      out.write("\t\t\t<script src=\"resources/js/jquery.min.js\"></script>\r\n");
      out.write("\t\t\t<script src=\"resources/js/jquery.scrolly.min.js\"></script>\r\n");
      out.write("\t\t\t<script src=\"resources/js/jquery.scrollex.min.js\"></script>\r\n");
      out.write("\t\t\t<script src=\"resources/js/skel.min.js\"></script>\r\n");
      out.write("\t\t\t<script src=\"resources/js/util.js\"></script>\r\n");
      out.write("\t\t\t<script src=\"resources/js/main.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\t</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
