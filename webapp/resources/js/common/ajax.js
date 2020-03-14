/***
 * 공통 AJAX 유틸리티
**/

class ajaxUtil {
	
	static ajaxExecute ( pUrl, pParamValue ) {
		var rtnObject = new Object();
		
		$.ajax({
			type: 'post',
			url: pUrl,
			data : pParamValue ,
			dataType: 'json',
			async : false,
			success : function(data) {
				rtnObject.ajaxResult = 'SUCESS';
				rtnObject.data = data;
			},
			error : function(data) {
				rtnObject.ajaxResult = 'ERROR';
				rtnObject.data = data;
			}
		});
		return rtnObject;
	} ;
	
	static 	ajaxSubmit ( frmObj, pUrl, pParamValue ) {
		var rtnObject = new Object();
		
		$(frmObj).ajaxSubmit({
			type: 'post',
			url: pUrl,
			data : pParamValue ,
			dataType: 'json',
			async : false,
			success : function(data) {
				rtnObject.ajaxResult = 'SUCESS';
				rtnObject.data = data;
			},
			error : function(data) {
				rtnObject.ajaxResult = 'ERROR';
				rtnObject.data = data;
			}
		});
		return rtnObject;
	};
	
};
