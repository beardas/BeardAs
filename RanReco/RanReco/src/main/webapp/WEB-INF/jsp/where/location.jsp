<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
#game_board{
   width: 400px; 
}
#dice {
   position:relative; z-index: 1; top: -120px; padding-left: 10px;
}
#dice1 {
   width: 30px; left: 10px;
}
#dice2 {
   width: 30px; left: 10px;
}
#button {
   position:relative; z-index: 3; top: -40px; padding-left: 10px;
}
#dicediv {
   position:relative; z-index: 2; top: -110px; padding-left: 10px;
}

@media ( min-width: 768px ) {
   #game_board{
      width: 750px;
   }
   #dice {
      position:relative; z-index: 1; top: -220px; padding-left: 10px;
   }
   #dice1 {
      width: 50px;
   }
   #dice2 {
      width: 50px; 
   }
   #button {
      position:relative; z-index: 3; top: -50px; padding-left: 10px;
   }
   #dicediv {
      position:relative; z-index: 2; top: -205px; padding-left: 10px;
   }
   #diceValue {
      font-size: 20px;
   }
@media ( min-width: 1200px ) {
   #game_board{
      width: 1150px;
   }
   #dice {
      position:relative; z-index: 1; top: -350px; padding-left: 10px;
   }
   #dice1 {
      width: 70px;
   }
   #dice2 {
      width: 70px;
   }
   #button {
      position:relative; z-index: 3; top: -390px; padding-left: 10px;
   }
   #dicediv {
      position:relative; z-index: 2; top: -295px; padding-left: 10px;
   }
   #diceValue {
      font-size: 30px;
   }

</style>


   <div align="center">
         <form name="form">
            <div align="center">
               <IMG id="game_board" src="/images/오늘뭐하지.png" style="position: relative; z-index: 0; display: block;" >
            </div>
            <div id="dice">
               <IMG id="dice1" src="/images/dice/주사위1.png" name="first"  >
               <IMG id="dice2" src="/images/dice/주사위1.png" name="second" >
            </div>
            <div id="dicediv">
                <p id="diceValue"></p>
            </div>
               <div id="button">
               <input type="button" value="  돌리기  " onClick="javascript:startGame();">   
               <input type="button" value="  멈추기  " onClick="javascript:end(4);">
               </div>
               <input type="hidden" id="memberLocationFile" name="memberLocationFile" value="">
               <input type="hidden" id="memberLocation" name="memberLocation" value="">
               <input type="hidden" id="memberIdx" name="memberIdx" value="${userIdx}">
      </form>
   </div>
<SCRIPT language="JavaScript1.1">


game = false;   // 현재 게임 진행 상태
box1 = true;
box2 = true;
num1 = Math.floor(Math.random() * 6);
num2 = Math.floor(Math.random() * 6);
dice_double = 0;
var tmp = new Array();


IMG = new Array();

IMG[0] = new Image(); IMG[0].src = "/images/dice/주사위1.png";
IMG[1] = new Image(); IMG[1].src = "/images/dice/주사위2.png";
IMG[2] = new Image(); IMG[2].src = "/images/dice/주사위3.png";
IMG[3] = new Image(); IMG[3].src = "/images/dice/주사위4.png";
IMG[4] = new Image(); IMG[4].src = "/images/dice/주사위5.png";
IMG[5] = new Image(); IMG[5].src = "/images/dice/주사위6.png";





function end(variable) { // 멈추기 버튼
        if (game == true) {
                if (variable == 1) box1 = false;
                if (variable == 2) box2 = false;

            if (variable == 4) {
               box1 = box2 = false;
            }
        } else {
         alert ('게임이 시작되지 않았습니다.');
            }

        if ((box1 == false) && (box2 == false)) {
              clearTimeout (tid);
              reset();
        }
}

function hyouji() {   // 주사위 돌아가는거 표현    
       if (num1 == 6) num1 = 0;
       if (num2 == 6) num2 = 0;
       if (box1) document.images["first"].src = IMG[num1 % 6].src;
       if (box2) document.images["second"].src = IMG[num2 % 6].src;
       num1 += 1;
       num2 += 1;
      tid = setTimeout("hyouji()",60);
}

function reset() {
   switch (document.images["first"].src) {
   case IMG[0].src: dice1=1; break; 
   case IMG[1].src: dice1=2; break;
   case IMG[2].src: dice1=3; break;
   case IMG[3].src: dice1=4; break;
   case IMG[4].src: dice1=5; break;
   case IMG[5].src: dice1=6; break;
   default:
      break;
   }
   switch (document.images["second"].src) {
   case IMG[0].src: dice2=1; break; 
   case IMG[1].src: dice2=2; break;
   case IMG[2].src: dice2=3; break;
   case IMG[3].src: dice2=4; break;
   case IMG[4].src: dice2=5; break;
   case IMG[5].src: dice2=6; break;
   default:
      break;
   }
      
   
     for(var i = 0; i<tmp.length; i++){
        dice_double += tmp[i];
     }
     for(var i = 0; i<tmp.length-1; i++){
        dice_double -= tmp[i];
     }
   
   dice=dice1+dice2+dice_double;
         
   
   
   switch(dice%20){
   case 1: loc="노량진"; break; 
   case 2: loc="문래";break; 
   case 3: loc="강남";break; 
   case 4: loc="가로수길";break; 
   case 5: loc="익선동";break; 
   case 6: loc="여의도";break; 
   case 7: loc="홍대입구";break; 
   case 8: loc="DDP";break; 
   case 9: loc="COEX";break; 
   case 10: loc="서울숲";break; 
   case 11: loc="샤로수길";break; 
   case 12: loc="이태원";break; 
   case 13: loc="대학로";break; 
   case 14: loc="센트럴시티";break; 
   case 15: loc="경복궁";break; 
   case 16: loc="성수";break; 
   case 17: loc="왕십리";break; 
   case 18: loc="상수";break; 
   case 19: loc="압구정로데오";break; 
   
   default:
      break;
   }

   document.getElementById('diceValue').innerHTML= dice+"칸 이동!"+loc+"로 go!"
   document.getElementById('memberLocation').innerHTML= loc
   
   if(dice1 != dice2){
	if(document.getElementById('memberIdx').value != 0){
	   setTimeout(function() {
		var returnValue = confirm(loc+"로 결정하시겠습니까?");
		if(returnValue){
			alert("오늘 갈곳은 "+loc+"으로 결정!");
				$("#memberLocation").val(loc);
				document.getElementById('memberLocationFile').value = "/images/locationImage/"+loc+".jpg";
				var f = document.form;
				f.action = "/locationPopup.do";
				f.submit();
		}
		else 
			alert("다시 선택해주세요!")
		}, 1000);
  }else{
	  if(dice%20==0){	
		  setTimeout(function(){
	   	  alert("오늘은 집콕?! ");
		  },1000);
	  }else{
		  setTimeout(function(){
		  alert("오늘 갈곳은 "+loc+"으로 결정! ");
		    },1000);
	  }
	  }
  }
   
   
  if(dice1 == dice2) {
      document.getElementById('diceValue').innerHTML="더블!! 한번더!!"
      
      tmp.push(dice1+dice2);
      
      
   } else {
      tmp.length = 0;
      dice_double = 0;
   }
   
   
   game = false
   box1 = true
   box2 = true
   
   
   
}

function startGame() { // 돌리기 버튼
   if(game == false) {
        game = true;
        num1 = Math.floor(Math.random() * 6);
        num2 = Math.floor(Math.random() * 6);
        hyouji();
     }
}



   document.getElementById('diceValue').innerHTML="돌리기 버튼 꾹!";

</SCRIPT>
