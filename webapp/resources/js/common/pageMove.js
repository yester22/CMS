/***
 * 페이지 이동 유틸
**/
/*
 * 
 * 
 
class pageMove {
	static goPost ( path,  params, method ) {
		var $form = $('<form></form>');
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
**/

var pageMove = {
	goPost : function( path,  params, method ) {
		var $form = $('<form></form>');
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
	} 
}