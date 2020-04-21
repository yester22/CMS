/**
 * filename : list.js
 * developer : kyoungjin jung.
 * date : 2020-03-20
 * description : 엑셀파일 업로드 페이지
 */
$(document).ready(function(){

	MemberList.init();
	
	$("#btnSearch").bind("click", MemberList.btnSearch);
	
});


/*
* 멤버 관리 작업 클래스 
*/

var MemberList  = {
	
	init : function() {
		
	},
	
	/*
	* e : 이벤트 객체
	*/ 
	btnSearch : function( e ) {
		var url = '';
		var formData = {};
        $.ajax({
            url: url,
            dataType: 'json', 
            data: formData,
            type: 'POST',
            success: MemberList.cbMemberRetrieveResult,
            error  : MemberList.cbMemberRetrieveError,
        });
	},
	
	cbMemberRetrieveResult : function(data) {
		
	},
	
	cbMemberRetrieveError : function (data) {
		
	}
	
}