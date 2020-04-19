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

class MemberList {
	
	static init() {
		
	}
	
	/*
	* e : 이벤트 객체
	*/ 
	static btnSearch ( e ) {
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
	}
	
	static cbMemberRetrieveResult(data) {
		
	}
	
	static cbMemberRetrieveError(data) {
		
	}
	
}