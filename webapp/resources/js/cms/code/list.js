/**
 * filename : list.js
 * developer : kyoungjin jung.
 * date : 2020-03-20
 * description : 엑셀파일 업로드 페이지
 */
$(document).ready(function(){
	$("#btnUpload").bind("click", CodeList.btnExcelUpload);
});


/*
* LOGIN 작업 클래스 
*/

class CodeList {
	/*
	* e : 이벤트 객체
	*/ 
	static btnExcelUpload ( e ) {
		e.preventDefault();
		var url = "/excelUpload";
		var data = $("#uploadForm").serialize();
		  
		$.post( url, data )
			.done(function( data ) {
				console.log('--->', data);
		});
	}
}