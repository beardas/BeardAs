<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
      <link rel="stylesheet" href="/css/datepicker.min.css">
    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/datepicker.min.js"></script>
    <script src="/js/i18n/datepicker.ko.js"></script>
<style>

@media (min-width: 768px) {
.font{
	font-family: sans-serif;
	font-size: x-large;
	color: navy;
	font-weight: bold;
}
 .table .today{margin:0;padding:0;list-style:none} 
  .table .today{width:20%;float:left;box-sizing:border-box;padding:0 20px;margin-left: 5%; margin-right: 4%;} 

.today{
	width: 200px;
	height: 300px;
	border-radius: 70px;
	-moz-border-radius: 70px;
	-khtml-border-radius: 70px;
	-webkit-border-radius: 70px;
	overflow: hidden;
	object-fit: cover;
}

.location1{
	width: 80%;
	height: 80%;
	object-fit: contain;
}
.food2{
	width: 80%;
	height: 80%;
	object-fit: contain;
}
.fashion3{
	width: 80%;
	height: 80%;
	object-fit: contain;
	
}
}
@media (max-width: 767px) {

.font{
	font-family: sans-serif;
	font-size: x-large;
	color: navy;
	font-weight: bold;
}

.today{margin: 0 0 100px 0;}
.table .today{margin:0 0 0 -10px;padding:0;list-style:none;box-sizing:border-box;} 
.location1{
	width: 300px;
	height: 300px;
	object-fit: contain;
}

.food2{
	width: 400px;
	object-fit: contain;
}

.fashion3{
	width: 300px;
	height: 300px;
	object-fit: contain;
	
}
}

</style>
<form name="dateResult" method="post">
</form>
    <input type="hidden" id="memberIdx" value="${userIdx}">
	<div style="margin-left: 10%;">
		<span>오늘 뭐하지?</span><br>
		<h2 >오늘의 일정표</h2>
	<div class="single" style="text-align: center; margin:0 0 0 30%;">
        <h3>날짜 지정</h3>
        <input id="datepicker" type="text" >
    <br>
    <p align="center" onclick="javascript:fn_datepick();" >해당 날짜 결과 보기</p>
    </div>
	</div>
	<hr style="border-bottom-style: solid;border-bottom-width: 1px; margin-left: 10%; margin-right: 10%;"><br><br>
	<div align="center" style="margin-bottom: 30%;">
	<ul class="table">
		<li class="today">
			<img class="location1" src="${resultVO.memberLocationFile}" onerror="this.src='/images/지역골라.png'">
			<p>오늘은 </p> <p class="font">${resultVO.memberLocation}</p> <p> GO!</p>
		</li>
		<li class="today">
			<img class="food2" src="${resultVO.memberFoodFile}" onerror="this.src='/images/음식골라.png'">
			<p>오늘은</p>  <p class="font">${resultVO.memberFood}</p> <p>먹으러 GO!</p>
		</li>
		<li class="today">
			<img class="fashion3" src="${resultVO.memberFashionFile}" width="150px;" onerror="this.src='/images/옷골라.png'">
			<p>오늘은</p> <p class="font">${resultVO.look}</p> <p>입고 GO!</p>
		</li>
	</ul>
   </div>
   


   
    <script>
        //한개만 단순하게 만들때
        $(function(){
        $("#datepicker").datepicker({
            language: 'ko'
        });
        	
        });

           
        function datePickerSet(sDate, eDate, flag) {
                if (!isValidStr(sDate)) {
                var sDay = sDate.val();
                if (flag && !isValidStr(sDay)) { //처음 입력 날짜 설정, update...			
                    var sdp = sDate.datepicker().data("datepicker");
                    sdp.selectDate(new Date(sDay.replace(/-/g, "/"))); //익스에서는 그냥 new Date하면 -을 인식못함 replace필요
                }

                
                sDate.datepicker({
                    language: 'ko',
                    autoClose: true
                });
            }


            function isValidStr(str) {
                if (str == null || str == undefined || str == "")
                    return true;
                else
                    return false;
            }
        }
        
        
       function fn_datepick(){
        var f = document.dateResult;
		f.action = "/selectMyResult.do?idx="+document.getElementById('memberIdx').value+"/"+(document.getElementById('datepicker').value).replaceAll("-",""); 
		f.submit(); 
        }
		
		</script>
   
   