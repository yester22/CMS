/**
 * filename : login.js
 * developer : kyoungjin jung.
 * date : 2018-04-05
 * description : currented login
 */
$(document).ready(function(){
	var loginHadderText = Message.getMsg ('LGN0000001');
	$("#btnLogin").bind("click", Login.btnLoginEvent);
	$("#pwd").keyup(Login.keyUpPwd);
	
});

/*
* LOGIN 작업 클래스 
*/
var Login = {
	btnLoginEvent : function(thing){
		var url  = "/login/loginCheck"
	    var data = $("#loginForm").serialize();
	    var rtnObj = ajaxUtil.ajaxExecute ( url, data );
	    var result = rtnObj.ajaxResult;
	    	
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
	},
	
	keyUpPwd : function( e ) {
		if( e.keyCode == 13 ) {
			console.log( 'enterkey' );
			Login.btnLoginEvent(e);
		}
	}
	
}

/**
( function () {
	
    function btnLoginEvent () {
    	var url  = "/login/loginCheck"
    	var data = $("#loginForm").serialize();
    	var rtnObj = ajaxUtil.ajaxExecute ( url, data );
    	var result = rtnObj.ajaxResult;
    		
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
    };
    
    function keyUpPwd ( e ) {
		if( e.keyCode == 13 ) {
			console.log( 'enterkey' );
			Login.btnLoginEvent(e);
		}
	}
    
}())
**/
/*
class Login {
	
	* e : 이벤트 객체
	 
	static btnLoginEvent ( e ) {
		var url  = "/login/loginCheck"
		var data = $("#loginForm").serialize();
		var rtnObj = ajaxUtil.ajaxExecute ( url, data );
		var result = rtnObj.ajaxResult;
		
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
	
	
	* e : 이벤트 객체
	 
	static keyUpPwd ( e ) {
		if( e.keyCode == 13 ) {
			console.log( 'enterkey' );
			Login.btnLoginEvent(e);
		}
	}
	
}*/