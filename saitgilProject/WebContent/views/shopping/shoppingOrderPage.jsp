<%@page import="org.apache.catalina.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='java.util.*, shopping.model.vo.*' %>
<%
	ArrayList<Cart> glist = (ArrayList<Cart>)request.getAttribute("sblist");
	int cnt = glist.size();
	int total = 0;
	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<style>
	.form-one > input{
		background: #F0F0E9;
	    border: 0 none;
	    margin-bottom: 10px;
	    padding: 10px;
	    width: 100%;
	    font-weight: 300;
	}
	
	.form-two > input{
		background: #F0F0E9;
	    border: 0 none;
	    margin-bottom: 10px;
	    padding: 10px;
	    width: 100%;
	    font-weight: 300;
	}
	
	.form-two > select{
		background: #F0F0E9;
	    border: 0 none;
	    margin-bottom: 10px;
	    padding: 10px;
	    width: 100%;
	    font-weight: 300;
	}
</style>
</head>
<body>
	<%@ include file="shoppingMenubar.jsp" %>
	<br>
	<br>
	<br>
	<section id="cart_items">
		<div class="container">
				<div class="review-payment">
					<h2>주문 내역</h2>
				</div>
			
				<div class="table-responsive cart_info">
					<table class="table table-condensed">
						<thead>
							<tr class="cart_menu">
								<td class="image" align="center">Item</td>
								<td class="description"></td>
								<td class="price">Price</td>
								<td class="quantity">주문 개수</td>
								<td class="total">Total</td>
							</tr>
						</thead>
						<tbody>
							<%if(!glist.isEmpty()) {%>
							<%for(int i=0; i<glist.size(); i++){ %>
							<tr>
								<td class="productinfo cart_product" width="100" height="100">
									<img src="<%= request.getContextPath()%>/resources/product_uploadFiles/<%=glist.get(i).getChangeName() %>" width="100" height="100">
								</td>
								<td class="productinfo cart_description">
									<h4><%=glist.get(i).getpName() %></h4>
									<p id="pNo<%= i%>"><%=glist.get(i).getpNo() %></p>
								</td>
								<td class="productinfo cart_price">
									<p><%=glist.get(i).getPrice()%></p>
								</td>
								<td class="cart_quantity">
									<div class="cart_quantity_button" style="display:inline;">
										<input class="cart_quantity_input" id='quantity<%= i%>' type="NUMBER" name="quantity" min='1' max ='<%= glist.get(i).getpAmount() %>' value="<%=glist.get(i).getAmount() %>" autocomplete="off" size="2">
										<p>&nbsp;&nbsp;구매가능개수:<%= glist.get(i).getpAmount()%></p>
									</div>
								</td>
								<td>
									<p class="cart_total_price" id="totalPrice"><%= glist.get(i).getPrice()*glist.get(i).getAmount()%>원</p>
									<%total = total +  glist.get(i).getPrice()*glist.get(i).getAmount();%>
								</td>
							</tr>
							<%} %>
						<%} else{%>
							<tr><td>조회된 장바구니 상품이 없습니다.</td></tr>
						<%} %>
						<%if(!glist.isEmpty()) {%>
							<tr>
								<td colspan="4">&nbsp;</td>
								<td colspan="2">
									<table class="table table-condensed total-result">
										<tr>
											<td>Total</td>
											<td><span><%= total %></span>원</td>
										</tr>
									</table>
								</td>
							</tr>
						<%} %>
						</tbody>
					</table>
					<script>
						
						$(".cart_quantity_input").change(function(){
							var amount = $(this).val();
							var t = $(this);
							var price = $(this).parent().parent().parent().children().eq(2).children().eq(0).text();
							var before = $(this).parent().parent().parent().children().eq(4).children().eq(0).text().split("원");
							var be = before[0]*1;
							var after = (amount*price)-be;
							var bef = $(t).parent().parent().parent().parent().children().eq(<%= cnt%>).children().eq(1).children().children().children().children().eq(1).children().text()*1;
							
							console.log(after);
				  			$.ajax({
				  				url:"changepPrice.sp",
				  				data:{amount:amount,
				  					  price:price,
				  					  after:after,
				  					  bef:bef},
								type:"get",	  	
								success : function(result){
									console.log("ajax 통신 성공");
									console.log(result);
									$(t).parent().parent().parent().children().eq(4).children().eq(0).text(result);
									$(t).parent().parent().parent().parent().children().eq(<%= cnt%>).children().eq(1).children().children().children().children().eq(1).children().text(bef+after);
								},
								error : function(){
									console.log("ajax 통신 실패");
								}
				  			});
						});
					</script>
				</div>
				<div><input type="hidden" id="sub1" onclick="test1();"></div>
				<div class="shopper-informations">
					<div class="row">
						<div class="col-sm-5 clearfix">
							<div class="bill-to">
								<p>Bill To</p>
								<div class="form-one">
									<p>받는 사람 이름</p>
									<input type="text" placeholder="받는사람 이름 *" value='<%= loginUser.getUserName()%>' id="getName">
									<p>이메일</p>
									<input type="text" placeholder="Email*"  value='<%= loginUser.getEmail()%>' id="getEmail">
									<p>주소</p>
									<input type="text" placeholder="Address 1 *" value="<%= loginUser.getAddress()%>" style="width:450px;" id="getAddress" readonly>
									<div class="submit"  onclick="goPopup();">&nbsp;&nbsp;주소검색 &nbsp;&nbsp;</div>
								</div>
								<div class="form-two">
									<p>핸드폰번호</p>
									<input type="text" placeholder="Mobile Phone*(-없이 입력)" value="<%= loginUser.getPhone()%>" id="getPhone">
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="order-message">
								<p>주문 및 배송 요청사항</p>
								<textarea name="message" rows="16" id="getOrdertext"></textarea>
							</div>	
						</div>					
					</div>
				</div>
				
			<div class="payment-options pull-right">
				<button onclick="pay();">결제하기</button>
			</div>
		</div>
	</section> <!--/#cart_items-->
	<script>
		var IMP = window.IMP;
		IMP.init('imp39334510');
		function pay(){
            var price = $("#totalPrice").text().split('원')[0];
            console.log(price);
            var userName = "<%= loginUser.getUserName()%>";
            var phone = <%= loginUser.getPhone()%>;
            var address = "<%= loginUser.getAddress()%>";
            var userId = "<%= loginUser.getUserId()%>";
            var email = "<%= loginUser.getEmail() %>";
            var buyCall = "<%= glist.get(0).getpName()%>외" + <%= cnt-1%>;
            
            var cnt = <%= cnt%>;
            var rst = "요청사항이 없습니다.";
            
            if($("#getOrdertext").val() != ""){
            	rst = $("#getOrdertext").val();
            }
            console.log(rst);
            var email1 =$("#getEmail").val();
            var phone1 =$("#getPhone").val();
            var name =$("#getName").val();
            var address1 = $("#getAddress").val();
            var pNo = "";
            var amount1 = "";
            console.log(name);
            for(var i=0; i<<%= cnt%>; i++){
            	if(i== 0){
            		pNo = pNo + $("#pNo"+i).text();
            		amount1 = amount1 + $("#quantity"+i).val();
            	} else{
            		pNo = pNo + "," + $("#pNo"+i).text();
            		amount1 = amount1 + "," + $("#quantity"+i).val();
            	}
            }
            
            console.log(amount1);
            
			
			IMP.request_pay({
			    pg : 'html5_inicis', // version 1.1.0부터 지원.
			    pay_method : 'vbank',
			    merchant_uid : 'merchant_' + new Date().getTime(),
			    name : buyCall,
			    amount : price,
			    buyer_email : email,
			    buyer_name : userName,
			    buyer_tel : phone,
			    buyer_addr : address
			}, function(rsp) {
				 if ( rsp.success ) {
				        var msg = '결제가 완료되었습니다.';
				       
				        $.ajax({
		                      url:"orderInsert.sp", 
		                      data : {userId: userId,
		                    	      phone1:phone1,
		                    	      address1:address1,
		                    	      amount1:amount1,
		                    	      pNo:pNo,
		                    	      name:name,
		                    	      email1:email1,
		                    	      cnt:cnt,
		                    	      rst:rst},
		                      type : "post",
		                   	  success : function(result){
		                   		alert(msg);
						        location.href="<%= request.getContextPath()%>/ocList.sp";
		                   	  },
		                   	  error : function(){
		                   		 var msg = '결제에 실패하였습니다.';
		 				        msg += '에러내용 : ' + rsp.error_msg;
		 				        alert(msg);
		                   	  }
		              	});
				        
				    } else {
				        var msg = '결제에 실패하였습니다.';
				        msg += '에러내용 : ' + rsp.error_msg;
				        alert(msg);
				    }
			});
		}
	
		function goPopup(){
			// 주소검색을 수행할 팝업 페이지를 호출합니다.
			// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
			var pop = window.open("views/juso/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
			
			// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
		    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
		}
		
		
		function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
				$('#getAddress').attr("value", roadFullAddr);
		}
		
	</script>
	
	
			
</body>
</html>