<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/menubar.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Calendar</title>
<!-- 
경로를 환경에 맞게 조정합니다. -->
<!--<meta charset='utf-8' />  -->
<link href='assets/packages/core/main.css' rel='stylesheet' />
<link href='assets/packages/daygrid/main.css' rel='stylesheet' />
<link href='assets/packages/timegrid/main.css' rel='stylesheet' />
<link href='assets/packages/list/main.css' rel='stylesheet' />
<link href='https://bootswatch.com/4/superhero/bootstrap.min.css' />
<script src='assets/packages/core/main.js'></script>
<script src='assets/packages/core/locales-all.js'></script>
<script src='assets/packages/interaction/main.js'></script>
<script src='assets/packages/daygrid/main.js'></script>
<script src='assets/packages/timegrid/main.js'></script>
<script src='assets/packages/list/main.js'></script>


</head>
<body>

<script>
var calendar ;
  document.addEventListener('DOMContentLoaded', function() {
    var initialLocaleCode = 'ko';
    var localeSelectorEl = document.getElementById('locale-selector');
    var calendarEl = document.getElementById('calendar');
    getCalendarList();
    calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'interaction', 'dayGrid', 'timeGrid', 'list' ],
      themeSystem : 'superhero',
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'addSchedule, dayGridMonth,timeGridWeek,timeGridDay,listMonth'
      },
      defaultDate: new Date(),
      locale: initialLocaleCode,
      buttonIcons: false, // show the prev/next text
      weekNumbers: true,
      navLinks: true, // can click day/week names to navigate views
      editable: false,
      eventLimit: true, // allow "more" link when too many events
      events: [],
      eventClick: function(arg) {
         if (confirm('일정을 삭제하시겠습니까?')) {
        	 //removeSchedule();
           removeSchedule(arg.event.id);
         }
       },
      customButtons: {
		addSchedule: {
		text: '일정등록',
		click: function() {
		    layer_popup("#showPopUp");
			//addSchedule();
			
		}
      }
      }
    });

    calendar.render();

    // build the locale selector's options
    calendar.getAvailableLocaleCodes().forEach(function(localeCode) {
      var optionEl = document.createElement('option');
      optionEl.value = localeCode;
      optionEl.selected = localeCode == initialLocaleCode;
      optionEl.innerText = localeCode;
      <!--localeSelectorEl.appendChild(optionEl);-->
    });
    $("#startdate").datepicker({
		dateFormat : "yymmdd"
	});
    $("#enddate").datepicker({
		dateFormat : "yymmdd"
	});
/*     // when the selected option changes, dynamically change the calendar option
    localeSelectorEl.addEventListener('change', function() {
      if (this.value) {
        calendar.setOption('locale', this.value);
      }
    }); */

  });
  function layer_popup(el){
	  

      var $el = $(el);        //레이어의 id를 $el 변수에 저장
      var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수

      isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();

      var $elWidth = ~~($el.outerWidth()),
          $elHeight = ~~($el.outerHeight()),
          docWidth = $(document).width(),
          docHeight = $(document).height();

      // 화면의 중앙에 레이어를 띄운다.
      if ($elHeight < docHeight || $elWidth < docWidth) {
          $el.css({
              marginTop: -$elHeight /2,
              marginLeft: -$elWidth/2
          })
      } else {
          $el.css({top: 0, left: 0});
      }

      $el.find('a.btn-layerClose').click(function(){
          isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
          return false;
      });

      $('.layer .dimBg').click(function(){
          $('.dim-layer').fadeOut();
          return false;
      });


  }
  function addSchedule(){
	  var starttime = $("#starttime").val();
	  var endtime = $("#endtime").val();
	  
	  if($("#startdate").val() == null || $("#startdate").val() == "" 
		  || $("#enddate").val() == null || $("#enddate").val() == ""){
		  alert("날짜를 선택해주세요");
		  return false; 
	  }
	  
	  var starthour = starttime.substr(0,2);
	  var startmin = starttime.substr(3,2);
	  var endhour = endtime.substr(0,2);
	  var endmin = endtime.substr(3,2);
	  
	  if($("#startampm option:selected").val() == "PM"){
	  	  starthour = Number(starthour) + 12;
	  	  
	  	  starttime = starthour + startmin +"";
	  	  
	  }else{
		  if(Number(starthour) == 12){
			  starthour = "00";
		  }
		  starttime = starthour + startmin +"";
	  }
	  
	  if($("#endampm option:selected").val() == "PM"){
		  endhour = Number(endhour) + 12;
	  	  
		  endtime = endhour + endmin +"";
	  	  
	  }else{
		  if(Number(endhour) == 12){
			  endhour = "00";
		  }
		  endtime = endhour + endmin +"";
	  }
	  
	  var startdatetime = $("#startdate").val() + starttime;
	  var enddatetime = $("#enddate").val() + endtime;
	  
	  if(Number(startdatetime) >= Number(enddatetime)){
		  alert("시작시간은 종료시간보다 크거나 같을 수 없습니다.");
		  return false;
	  }
	  
	  var param = {
		  startdatetime : startdatetime,
		  enddatetime : enddatetime,
		  schedule : $("#schedule").val()
	  }
	  if(confirm("일정을 추가하시겠습니까?")){
		  $.ajax({
				type : "post",
				url : "<%=request.getContextPath()%>/insert.ca",
				data : param,
				dataType : "json",
				success : function(data) {
				  if(data.result > 0){
					  alert("일정이 등록되었습니다.");
					  // 일정 입력 레이어 초기화
					  $("#startampm option:eq(0)").attr("selected","selected");
					  $("#starttime option:eq(0)").attr("selected","selected");
					  $("#endampm option:eq(0)").attr("selected","selected");
					  $("#endtime option:eq(0)").attr("selected","selected");
					  $("#startdate").val('');
					  $("#enddate").val('');
					  $("#schedule").val('');
					  
					  var isDim = $("#showPopUp").prev().hasClass('dimBg');
					  isDim ? $('.dim-layer').fadeOut() : $("#showPopUp").fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
					  calendar.removeAllEvents();
					  getCalendarList();
				  }else{
					  alert("일정 등록이 실패하였습니다.");
					  return false;
				  }
				},
				error : function(xhr, status, error) {
					console.log("에러!: " + error);
				},
			});
	  }
  }
  function removeSchedule(id){
	  $.ajax({
			type : "post",
			url : "<%=request.getContextPath()%>/delete.ca",
			data : {sno : id},
			dataType : "json",
			success : function(data) {
			  if(data.result > 0){
				  alert("일정이 삭제되었습니다.");
				  calendar.removeAllEvents();
				  getCalendarList();
			  }else{
				  alert("일정 삭제가 실패하였습니다.");
				  return false;
			  }
			},
			error : function(xhr, status, error) {
				console.log("에러!: " + error);
			},
		});
  }
  function getCalendarList(){
	
	  $.ajax({
			type : "post",
			url : "<%=request.getContextPath()%>/list.ca",
			dataType : "json",
			success : function(data) {
				if(data.calList != null){
					
					$.each(data.calList, function(index, cal){
						var calEvent = {};
						calEvent.id = cal.sno;
						calEvent.title = cal.schedule;
						calEvent.start = cal.startdate.substr(0,4) + "-" + cal.startdate.substr(4,2) + "-" + cal.startdate.substr(6,2) + "T" + cal.startdate.substr(8, 2) + ":" + cal.startdate.substr(10,2) + ":00";
						calEvent.end = cal.enddate.substr(0,4) + "-" + cal.enddate.substr(4,2) + "-" + cal.enddate.substr(6,2) + "T" + cal.enddate.substr(8, 2) + ":" + cal.enddate.substr(10,2) + ":00";
						calendar.addEvent(calEvent);
					});
				}
			},
			error : function(xhr, status, error) {
				console.log("에러!: " + error);
			},
		});
  }
</script>
<style>

  body {
    margin: 0;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #top {
    background: #eee;
    border-bottom: 1px solid #ddd;
    padding: 0 10px;
    line-height: 40px;
    font-size: 12px;
  }

  #calendar {
    max-width: 900px;
    margin: 40px auto;
    padding: 0 10px;
  }


.pop-layer .pop-container {
  padding: 20px 25px;
}

.pop-layer p.ctxt {
  color: #666;
  line-height: 25px;
}

.pop-layer .btn-r {
  width: 100%;
  margin: 10px 0 20px;
  padding-top: 10px;
  border-top: 1px solid #DDD;
  text-align: right;
}

.pop-layer {
  display: none;
  position: absolute;
  top: 50%;
  left: 50%;
  width: 410px;
  height: auto;
  background-color: #fff;
  border: 5px solid #3571B5;
  z-index: 9;
}

.dim-layer {
  display: none;
  position: fixed;
  _position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 10;
}

.dim-layer .dimBg {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: #000;
  opacity: .5;
  filter: alpha(opacity=50);
}

.dim-layer .pop-layer {
  display: block;
}

a.btn-layerClose, a.btn-addSchedule {
  display: inline-block;
  height: 25px;
  padding: 0 14px 0;
  border: 1px solid #304a8a;
  background-color: #3f5a9d;
  font-size: 13px;
  color: #fff;
  line-height: 25px;
}

a.btn-layerClose:hover {
  border: 1px solid #091940;
  background-color: #1f326a;
  color: #fff;
}

</style>
<div class="dim-layer">
    <div class="dimBg"></div>
    <div id="showPopUp" class="pop-layer">
        <div class="pop-container">
            <div class="pop-conts">
                <div>시작날짜 : <input type="text" id="startdate" /></div>
				<div>시작시간 :
					<select id="startampm">
						<option value="AM">AM</option>
						<option value="PM">PM</option>
					</select>
					<select id="starttime">
						<option>01:00</option>
						<option>02:00</option>
						<option>03:00</option>
						<option>04:00</option>
						<option>05:00</option>
						<option>06:00</option>
						<option>07:00</option>
						<option>08:00</option>
						<option>09:00</option>
						<option>10:00</option>
						<option>11:00</option>
						<option>12:00</option>
					</select>
				</div>
				<br/>
				<div>종료날짜 : <input type="text" id="enddate" /></div>
                
				<div>종료시간 :
					<select id="endampm">
						<option value="AM">AM</option>
						<option value="PM">PM</option>
					</select>
					<select id="endtime">
						<option>01:00</option>
						<option>02:00</option>
						<option>03:00</option>
						<option>04:00</option>
						<option>05:00</option>
						<option>06:00</option>
						<option>07:00</option>
						<option>08:00</option>
						<option>09:00</option>
						<option>10:00</option>
						<option>11:00</option>
						<option>12:00</option>
					</select>
				</div>
				<br/>
				<div>
					일  정 : <input type="text" id="schedule">
				</div>
                <div class="btn-r">
                	<a href="#" onclick="addSchedule()" class="btn-addSchedule">일정추가</a>
                    <a href="#" class="btn-layerClose">닫기</a>
                </div>
                <!--// content-->
            </div>
        </div>
    </div>
</div>

	<div id='calendar'></div>

</body>
</html>