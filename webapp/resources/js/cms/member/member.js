/**
 * filename : list.js
 * developer : kyoungjin jung.
 * date : 2020-03-20
 * description : 엑셀파일 업로드 페이지
 */
$(document).ready(function(){

	MemberList.init();
	MemberList.gridInit();
	
	$("#btnSearch").bind("click", MemberList.btnSearch);
	
});


/*
* 멤버 관리 작업 클래스 
*/

var MemberList  = {
	
	init : function() {
		
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
		            { title : '순번', 	name: 'rownum', 	type: 'text',  align: 'center', width: 50  },
			        { title : '계정', 	name: 'memberId',	type: 'text',  align: 'left', 	width: 200 },
			        { title : '회원명', 	name: 'memberNm', 	type: 'text',  align: 'left', 	width: 150 },
			        { title : '동록일자', 	name: 'regDate',	type: 'text',  align: 'left', 	width: 150 },
			        { title : '사용중', 	name: 'useYnNm',	type: 'text',  align: 'center', width: 150 },
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
		      }  
		 });
	},
	
	/*
	* e : 이벤트 객체
	*/ 
	btnSearch : function( e ) {
		var url = "/admin/memberList";
		var formData = $("#searchForm").serialize();
        $.ajax({
            url: url,
            dataType: 'json', 
            data: formData,
            type: 'POST',
            //success: MemberList.cbMemberRetrieveResult,
            succcess : function(data) {
            	console.log(data);
            },
            error  : MemberList.cbMemberRetrieveError,
        });
	},
	
	cbMemberRetrieveResult : function(data) {
		
		console.log(data);
		
		$("#memberList").jsGrid("option", "data", data.LIST);
		$("#memberList").jsGrid("option", "itemsCount", data.COUNT);
	},
	
	cbMemberRetrieveError : function (data) {
		
	},
	//전체선택
	checkAllItem : function() {
		
	}
	
}