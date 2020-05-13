/**
 * filename : jobAssign.js
 * developer : kyoungjin jung.
 * date : 2020-05-10
 * description : job Assign
 */
$(document).ready(function(){

	JobAssign.init();
	
	$("#searchInput").keydown(function(key) {
		if (key.keyCode == 13) {
			JobAssign.searchExcelOpen();
		}
	});
	
	$("#modalExcelSelect").draggable({
	      handle: ".modal-header"
	});

});


/*
* LOGIN 작업 클래스 
*/

var JobAssign = {
	
	init : function() {
		this.excelKey = '';
	},
	
	searchExcelListInit : function() {
		$("#excelSelectList").jsGrid({
			 height: "200px",
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
		            { title : '순번', 	name: 'rownum', 			type: 'text',  align: 'center', width: 50  },
			        { title : '엑셀키', 	name: 'excelKey',			type: 'text',  align: 'left', 	width: 200 },
			        { title : '엑셀명', 	name: 'title', 				type: 'text',  align: 'left', 	width: 150 },
			        { title : '지역명', 	name: 'locationCodeName', 	type: 'text',  align: 'left', 	width: 150 },
		        ],
		     rowDoubleClick : JobAssign.excelSelect,
		 });
	},
	
	//search form open
	searchExcelOpen : function() {
		JobAssign.searchExcelListInit();
		JobAssign.searchExcelData();
		$("#btnOpenDialog").trigger("click");
		
	},
	
	//excel file select
	excelSelect : function ( data ) {
		
	},
	
	//dialog close
	closeDialog : function() {
		$("#chkIdConfirm").bind("click", MemberList.btnIdCheck);
		$("btnClose").trigger("click");
			
	},
	//search
	searchExcelData : function() {
		var url = "/admin/notAssignExcelList";
		var sendData = { };

		 $.ajax({
	            url: url,
	            dataType: 'json', 
	            data: sendData,
	            type: 'POST',
	            success: JobAssign.cbExcelDataRetrieve,
	            error  : JobAssign.cbExcelDataError,
	     });
	},
	//data retrieve ended!!
	cbExcelDataRetrieve : function(data) {
		$("#excelSelectList").jsGrid("option", "data", data.LIST);
	},
	//error
	cbExcelDataError  : function(error) {
		
	},
	
	
}