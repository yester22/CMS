/**
 * filename : list.js
 * developer : kyoungjin jung.
 * date : 2020-03-20
 * description : 엑셀파일 업로드 페이지
 */
$(document).ready(function(){

	ExcelList.init();
	
	$("#btnSearch").bind("click", ExcelList.btnExcelRetrieve);
	
	$('#searchStatDate').datepicker({
		dateFormat: 'yy-mm-dd' //Input Display Format 변경
         ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
         ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
         ,changeYear: true //콤보박스에서 년 선택 가능
         ,changeMonth: true //콤보박스에서 월 선택 가능                
         ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
         ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
         ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
         ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
         ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
         ,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
         ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
	});
	
	$('#searchEndDate').datepicker({
		dateFormat: 'yy-mm-dd' //Input Display Format 변경
	    ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
	    ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
	    ,changeYear: true //콤보박스에서 년 선택 가능
	    ,changeMonth: true //콤보박스에서 월 선택 가능                
	    ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
	    ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
	    ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
	    ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
	    ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
	    ,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
	    ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
	});
	
	$("#searchStatDate").val( CommonUtil.getPreviousDate(7,'-'));
	$("#searchEndDate").val( CommonUtil.getToday('-'));
	
	 $("#jgUploadList").jsGrid({
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
	            { title : '순번', 			name: 'rownum', 			type: 'text',  align: 'center', width: 50  },
		        { title : '제목', 			name: 'title',			 	type: 'text',  align: 'left', 	width: 200 },
		        { title : '지역명(시/도)', 	name: 'locationCodeName', 	type: 'text',  align: 'left', 	width: 100 },
		        { title : '상태', 			name: 'statusCodeNm', 		type: 'text',  align: 'left', 	width: 150 },
		        { title : '총평수', 			name: 'totalLandWidth',		type: 'text',  align: 'right', 	width: 150 },
		        { title : '등록자명', 			name: 'uploaderNm',		 	type: 'text',  align: 'left', 	width: 100 },
		        { title : '등록일자', 			name: 'uploadDt',		 	type: 'text',  align: 'center', width: 150 },
	        ],
	     rowClick: function(args) { ExcelList.excelUploadRowClick(args); }
	  });
	 
	 $("#jgDataList").jsGrid({
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
	            { title : '순번', 			name: 'rownum', 		type: 'text',  align: 'center', width: 50  },
		        { title : '지역명(시/도)', 	name: 'sido',			type: 'text',  align: 'left', 	width: 200 },
		        { title : '지역(시/군/구)', 	name: 'sigungu', 		type: 'text',  align: 'left', 	width: 150 },
		        { title : '읍면동', 			name: 'upmyundong',		type: 'text',  align: 'left', 	width: 150 },
		        { title : '리', 				name: 'ri',		 		type: 'text',  align: 'center', width: 150 },
		        { title : '번지', 			name: 'bunji', 			type: 'text',  align: 'center', width: 150 }, 
		        { title : '부번지', 			name: 'bubunji',		type: 'text',  align: 'center', width: 150 }, 
		        { title : '대지(m2)', 		name: 'landWidth', 		type: 'text',  align: 'right', width: 120 },
	        ]
	    });
	
	
});


/*
* LOGIN 작업 클래스 
*/

class ExcelList {
	
	static init() {
		this.excelKey = '';
	}
	
	/*
	* e : 이벤트 객체
	*/ 
	static btnExcelRetrieve ( e ) {
		e.preventDefault();
		var url = "/admin/excelRetrieve";
		
		$("#pageSize").val(ExcelList.pageSize);
		$("#currentPage").val(ExcelList.currentPage);
		$("#startNum").val(ExcelList.startNum);
		
		var formData = $("#searchForm").serialize();

		ExcelList.excelKey  = '';
		
        $.ajax({
            url: url,
            dataType: 'json', 
            data: formData,
            type: 'POST',
            success: ExcelList.cbExcelRetrieveResult,
            error  : ExcelList.cbExcelRetrieveError,
        });
	}
	//
	static cbExcelRetrieveResult ( data ) {
		$("#jgUploadList").jsGrid("option", "data", data.LIST);
		$("#jgUploadList").jsGrid("option", "itemsCount", data.COUNT);
		
		$("#jgDataList").jsGrid("option", "data", []);
		$("#jgDataList").jsGrid("option", "itemsCount", 0);
	}
	
	static cbExcelRetrieveError( error ) {
		console.log("result error");
		console.log( error );
	}
	
	static excelUploadRowClick( args ) {
		ExcelList.excelKey = args.item.excelKey;
		
		var url = "/admin/excelDataRetrieve";
		var sendData = {
			excelKey : args.item.excelKey ,
			pageSize : ExcelList.dataPageSize,
			startNum : ExcelList.dataStartNum, 
		};
		
		 $.ajax({
	            url: url,
	            dataType: 'json', 
	            data: sendData,
	            type: 'POST',
	            success: ExcelList.cbExcelDataRetrieveResult,
	            error  : ExcelList.cbExcelRetrieveError,
	     });
	}
	
	static cbExcelDataRetrieveResult( data ) {
		$("#jgDataList").jsGrid("option", "data", data.LIST);
		$("#jgDataList").jsGrid("option", "itemsCount", data.COUNT);
	}
	
}