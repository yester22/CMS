/**
 * filename : leftMenu.js
 * developer : kyoungjin jung.
 * date : 2020-03-21
 * description : currented login
 */
$(document).ready(function(){
	
});


/*
* LOGIN 작업 클래스 
*/

class MenuMove {
	/*
	* e : 이벤트 객체
	*/ 
	static go ( pageUrl ) {
		$("#hidGo").val( pageUrl );
		$("#formGo").submit();
	}
};	