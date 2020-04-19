/***
 * 공통 메세지 유틸리티
**/

/*class Message {
	
	static getMsg ( MessageKey ) {
		var rtnObject = '';
		var pUrl = "/message/get";
		var pData = "1=1&msgKey=" + MessageKey;	
		
		$.ajax({
			type: 'post',
			url: pUrl,
			data : pData ,
			dataType: 'json',
		    async: false,
			success : function(data) {
				if ( data.result == 'OK' ) {
					rtnObject = data.MSG;
				};
			},
			error : function(data) {
				alert ( MessageKey + ' is Not Defined');
				rtnObject = null;
			}
		});
		return rtnObject;
	} ;
	
};
*/
var Message = {
	getMsg : function( MessageKey ) {
		var rtnObject = '';
		var pUrl = "/message/get";
		var pData = "1=1&msgKey=" + MessageKey;	
		
		$.ajax({
			type: 'post',
			url: pUrl,
			data : pData ,
			dataType: 'json',
		    async: false,
			success : function(data) {
				if ( data.result == 'OK' ) {
					rtnObject = data.MSG;
				};
			},
			error : function(data) {
				alert ( MessageKey + ' is Not Defined');
				rtnObject = null;
			}
		});
		return rtnObject;
	}	
}