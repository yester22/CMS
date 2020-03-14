/**
 * hello.js
 * 
 */
$(document).ready(function(){
	var msg = Message.getMsg ('LGN0000001');
	
	console.log ( msg );
	
	$("#helloDiv").html( msg );
});


var onBtClicked = function (e) {
	//alert('test');
	
	var buttonMsg = {
			"OK"     : Message.getMsg('CFM0000001'),
			"CANCEL" : Message.getMsg('CFM0000002')
	};
	
	var rtn = msgBox.confirm ('저장확인','저장하시겠습니까?', buttonMsg );
	if (rtn ) {
		var pData = {
			"hello" : "world",
			"java"  : "web"
		};
			
		pageMove.goPost ("/hello", pData );
	} else {
		alert ('false 선택');
	}
	
	
}