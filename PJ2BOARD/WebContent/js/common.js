/**
 * common files
 */
var context = "/HakSa";

function fn_back(){
	history.back(); // 페이지 뒤로 이동
}

function fn_isLogin(sessionValue, loginUrl, toUrl){
	if(sessionValue == null || sessionValue == ''){ // 값이 없을 경우 다시 로그인 하라고 보냄
		alert('로그인 후 사용 가능합니다.'); // 알림 창
		location.href = loginUrl; // 새로운 페이지로 이동 -> loginUrl
	}else{
		location.href = toUrl; // 새로운 페이지로 이동 -> toUrl // 로그인 완료. 다음으로 넘어감
	}
}

function fn_validation(){
	var obj = $("input[required=required],select[required=required],textarea[required=required]");
	var objSize = obj.size();
	var selectedObj;
	var alertMsg;

	for(var i = 0; i < objSize; i++){
		if(obj.eq(i).val() == "" || $.trim(obj.eq(i).val()).length == 0){ // 값이 없으면
			alertMsg = obj.eq(i).parent().parent().find("label").eq(0).children().next().text();
			alertMsg += "을(를) 입력해주세요.";

			alert(alertMsg);

			selectedObj = obj.eq(i);
			$(selectedObj).animate({
				backgroundColor : "red",
			}, 200, function(){
				$(selectedObj).animate({
					backgroundColor : "white",
				}, 200);
			});

			selectedObj.focus();
			return false;
		}

	}
	return true;

}

$(document).keydown(function(e){
	if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA" && e.target.nodeName != "SELECT"){
		if(e.keyCode === 8){
			return false;
		}
		window.history.forward(0);
	}
});

function fn_init(){
	var obj = $("input[required=required],select[required=required],textarea[required=required]");
	var objSize = obj.size();

	var selectObj = $("select");
	var selectObjSize = selectObj.size();
	var divSize = 0;

	for(var i = 0; i < objSize; i++){
		obj.eq(i).parent().parent().find("label").eq(0).prepend("<font color='red'>*&nbsp;</font>");

		if(obj.eq(i).prop("tagName") != "TEXTAREA"){
			divSize = obj.eq(i).parent().parent().children("div").size();
			if(divSize > 1){
				i += divSize - 1;
				continue;
			}
		}
	}

	for(var i = 0; i < selectObjSize; i++){
		selectObj.eq(i).prepend("<option value=''>--선택--</option>");

		selectObj.eq(i).find("option").eq(1).prop("selected", false);
		selectObj.eq(i).find("option").eq(0).prop("selected", true);
	}

}

function fn_showKeyCode(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
		            // 0~9						// 키패드 0~9              // pageup, pagedown, end, home, 방향키
	if( ( keyID >=48 && keyID <= 57 ) || ( keyID >=96 && keyID <= 105 ) || ( keyID >=33 && keyID <= 40 )
				|| keyID === 45 || keyID === 12 || keyID === 8) // insert, clear, backspace
	{
		return;
	}
	else
	{
		return false;
	}
	/* 48~57:일반 숫자키 코드, 96~105:숫자키패드 숫자키 코드 */
	// https://keycode.info/
}

//String.prototype.replaceAll = function(target, replacement) {
//    var reg = new RegExp(target, 'g');
//    return this.replace(reg, replacement); // 정규식 사용
//};
