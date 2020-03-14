/**
 * filename : login.js
 * developer : kyoungjin jung.
 * date : 2018-04-05
 * description : currented login
 */
$(document).ready(function(){
	var loginHadderText = Message.getMsg ('LGN0000001');
	$("#btnLogin").bind("click", Login.btnLoginEvent);	
	
});

/*
* LOGIN 작업 클래스 
*/

class Login {
	/*
	* e : 이벤트 객체
	*/ 
	static btnLoginEvent ( e ) {
		var url  = "/login/loginCheck"
		var data = $("#loginForm").serialize();
		var rtnObj = ajaxUtil.ajaxExecute ( url, data );
		var result = rtnObj.ajaxResult;
		
		
		console.log ( rtnObj );
		
		if ( result == 'ERROR' ) {
			msgBox.alert ( rtnObj.data.MSG );
			return;
		} else {
			if ( rtnObj.data.RESULT == "OK") {
				var url = '/main';
				location.href = url;
			} else {
				msgBox.alert (rtnObj.data.MSG );
			}

		}
		
	} 
}