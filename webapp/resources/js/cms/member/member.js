/**
 * filename : list.js
 * developer : kyoungjin jung.
 * date : 2020-03-20
 * description : 엑셀파일 업로드 페이지
 */
$(document).ready(function(){

	MemberList.init();
	MemberList.gridInit();
	
	$("#chkIdConfirm").bind("click", MemberList.btnIdCheck);	
	$("#btnSearch").bind("click", MemberList.btnSearch);
	$("#btnModalSave").bind("click", MemberList.btnSave);
	$("#btnUserAdd").bind("click", MemberList.openDialog);
	$("#btnCloseHeader").bind("click", MemberList.closeDialog);
	$("#btnClose").bind("click", MemberList.closeDialog);
	
	$("#modalMember").draggable({
	      handle: ".modal-header"
	});

	
});


/*
* 멤버 관리 작업 클래스 
*/

var MemberList  = {
	
		
		
	init : function() {
		this.mode = 'REG';
		this.idCheckPressed = false;
	},
	
	//grid init
	gridInit : function () {
		
		$("#memberList").jsGrid({
			 height: "300px",
		     width: "100%",
		     paging: true,
		     sorting: false,
		     loadIndication: true,
		     loadIndicationDelay: 500,
		     pageIndex: 1,
		     pageSize: 10,
		     pageButtonCount: 10,
		     pagePrevText: "P",
		     pageNextText: "N",
		     pageFirstText: "F",
		     pageLastText: "L",
		     data: [],
		     fields: [
		    		{
		                headerTemplate: function() {
		                    return $("<input>").attr("type", "checkbox").on("change", this.checkAllItem);
		                },
		                itemTemplate: function(_, item) {
		                    return $("<input>").attr("type", "checkbox").attr('name', 'excelData').attr('value', item.dataSeq);
		                },
		                align: "center",
		                width: 50
		            },
		            { title : '순번', 	name: 'rownum', 		type: 'text',  align: 'center', width: 50  },
			        { title : '계정', 	name: 'memberId',		type: 'text',  align: 'left', 	width: 200 },
			        { title : '회원명', 	name: 'memberNm', 		type: 'text',  align: 'left', 	width: 150 },
			        { title : '회원타입명', name: 'memberTypeNm', 	type: 'text',  align: 'left', 	width: 150 },
			        { title : '동록일자', 	name: 'regDate',		type: 'text',  align: 'center', width: 150 },
			        { title : '사용유무', 	name: 'useYnNm',		type: 'text',  align: 'center', width: 150 },
		        ],
		      loadIndicator: function(config) {
		          return {
		        	  show: function() {
		        		  $('#cover-spin').show();;
		              },
		              hide: function() {
		            	  $('#cover-spin').hide();;
		              }
		          };
		      },
		      rowDoubleClick : MemberList.gridRowDblClick,
		 });
	},
	
	/*
	* e : 이벤트 객체
	*/ 
	btnSearch : function( e ) {
		var url = "/admin/memberList";
		var formData = $("#searchForm").serialize();
		$('#cover-spin').show();
		
		$.ajax({
            url: url,
            dataType: 'json',
            type: 'POST',
            data: formData,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",  
            success: MemberList.cbMemberRetrieveResult,
            error  : MemberList.cbMemberRetrieveError,
        });

	},
	
	cbMemberRetrieveResult : function(data) {
		$("#memberList").jsGrid("option", "data", data.LIST);
		$("#memberList").jsGrid("option", "itemsCount", data.COUNT);
		
		$('#cover-spin').hide();
		
	},
	
	cbMemberRetrieveError : function (data) {
		$('#cover-spin').hide();
		
	},
	//전체선택
	checkAllItem : function() {
		
	},
	//row double click
	gridRowDblClick : function(item, itemIndex, event) {
		console.log(item.item.memberId);
		
		if ( item.item.memberId == null ) return false;
		MemberList.mode = 'UPT';
		var url = "/admin/memberView";
		var data = {
			"memberId" : item.item.memberId
		}
		
		$.ajax({
            url: url,
            dataType: 'json',
            type: 'POST',
            data: data,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",  
            success: function(data) {
            	console.log(data);
            	
            	
            	var memberVal = data.OBJECT;
            	if ( memberVal != null) {
	            	$("#memberNm").val(memberVal.memberNm);
	        		$("#memberId").val(memberVal.memberId);
	        		$("#memberId").attr('readonly', true);
	        		$("#memberType").val(memberVal.memberType);
	
	        		$("#chkIdConfirm").unbind("click");
	        		
	            	$("#btnUserAdd").trigger('click');
	            	
	            	$("#divPassWd").hide();
	        		$("#divPassWdCf").hide();
            	}
            	
            },
            error  : MemberList.cbMemberRetrieveError,
        });
	},
	
	
	//사용자 버튼 추가 완료처리
	btnSave : function() {
		console.log('save button clicked');
		if ( MemberList.mode == "REG") {
			if( ! MemberList.idCheckPressed ) {
				msgBox.alert('ID 가능 체크 버튼을 클릭하세요');
				return false;
			}
		}
		
		$('#cover-spin').show();
		var url = '/admin/memberSave';
		var formData = {
			'memberNm'   : $("#memberNm").val(),
			'memberId'   : $("#memberId").val(),
			'memberType' : $("#memberType").val(),
			'memberPw'   : $("#memberPw").val(),
			'mode'   : MemberList.mode,
		}
		
		$.ajax({
            url: url,
            dataType: 'json',
            type: 'POST',
            data: formData,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",  
            success: function(data) {
            	if ( data.RESULT == 'OK') {
            		msgBox.alert('저장이 완료되었습니다');
            		$("#btnClose").trigger("click");
            		MemberList.idCheckPressed = false;
            		MemberList.formClear();
            		MemberList.btnSearch();
            		
            	} else {
            		msgBox.alert('저장중 오류가 발생하였습니다<br>관리자에게 문의하세요');
            	}
            	$('#cover-spin').hide();
            },
            error  : MemberList.cbMemberRetrieveError,
        });
		
		
		
	},
	
	//폼 클리어
	formClear : function() {
		$("#memberNm").val("");
		$("#memberId").val("");
		$("#memberId").attr('readonly', false);
		$("#memberType").val("");
		$("#memberPw").val("");
		$("#memberPwConfirm").val("");
		
		$("#divPassWd").show();
		$("#divPassWdCf").show();
	}, 
	
	//id확인 체크
	btnIdCheck : function() {
		var memberNm = $("#memberNm").val();
		var memberId = $("#memberId").val();
		
		
		if ( memberNm == '' ) {
			msgBox.alert('사용자명이 입력되지 않았습니다');
			$("#memberNm").focus();
			return false;
		}
		
		if ( memberId == '' ) {
			msgBox.alert('사용자ID가 입력되지 않았습니다');
			$("#memberId").focus();
			return false;
		}
		
		var url = '/admin/checkIdExists';
		var formData = {
			'memberId' : memberId,
		} ;
		
		$.ajax({
            url: url,
            dataType: 'json',
            type: 'POST',
            data: formData,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",  
            success: function(data) {
            	console.log(data);
            	var count = data.COUNT;
            	if (count == 0 ) {
            		msgBox.alert('사용하실수 있는 아이디입니다');
            		$("#memberId").attr('readonly', true);
            		this.idCheckPressed = true;
            	} else {
            		msgBox.alert('사용할 수 없는 아이디 입니다');
            		return false;
            	}
            },
            error  : MemberList.cbMemberRetrieveError,
        });
	},
	
	openDialog : function() {
		
	},
	closeDialog : function() {
		
		MemberList.formClear();
		MemberList.mode = 'REG';
		$("#chkIdConfirm").bind("click", MemberList.btnIdCheck);
		$("#btnCloseHeader").attr("data-dismiss", "modal");
		$("#btnClose").attr("data-dismiss", "modal");
		$("btnClose").trigger("click");
			
	}, 
	//이메일 검증확인
	isEmail : function(asValue) {
		// 형식에 맞는 경우 true 리턴
		var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		return regExp.test(asValue); 	
	}



	
	
}