/***
 * 페이지 이동 유틸
**/

class pageMove {
	
	/*
	* path : 전송 URL
	* params : 전송 데이터 {‘q’:’a’,’s’:’b’,’c’: ‘d’…}으로 묶어서 배열 입력
	* method : 전송 방식(생략가능)
	*/
	static goPost ( path,  params, method ) {
		var $form = $('<form></form>');
		/*$form.attr("enctype", enctype);*/
		
		if ( method == null ) method = "post";
		
		$form.attr("method", method);
		$form.attr("action", "/go");
		$form.attr("target", "_self");
		$form.appendTo('body');
		
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "go"    );
		hiddenField.setAttribute("value", path   );
		$form.append(hiddenField);
		
		for(var key in params) {
			hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", key);
			hiddenField.setAttribute("value", params[key]);
			$form.append(hiddenField);
		}
		$form.submit();
	} ;
};
