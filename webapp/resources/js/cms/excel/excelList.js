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
	
	const excelUploadGrid = new tui.Grid({
		el: document.getElementById("excelUploadGrid"),
	    data: [],
	    scrollX: false,
	    scrollY: false,
	    selectionUnit: 'row',
	    pagination : true,
	    columns: [
	        {  header: '순번', 	 	name: 'rownum', width: 50 },
	        {  header: '제목', 	 	name: 'title', align: 'left'},
	        {  header: '지역', 	 	name: 'locationCodeName', width: 200 , align: 'left' },
	        {  header: '등록자', 	 	name: 'uploaderNm', width: 150   },
	        {  header: '등록일자', 	name: 'uploadDt', width: 150  },
	        {  header: 'excelKey', 	name: 'excelKey', hidden : true, },
	      ],
	    pageOptions: {
		    perPage: 5,
		    totalPage : 0,
		    useClient: true,
		},
	 });
	
	excelUploadGrid.getPagination().on("beforMove", function(ev) {
		console.log ( ev.page );
	});
	
/*	
	 paganation.on('afterMove', function(evt) {
		 
		 var currentPage = evt.page;
	     console.log(currentPage);
	 });
	*/
	// fired mouse event
	excelUploadGrid.on('click', (ev) => {
		//if ( excelUploadGrid.data == null ) return; 
		
		var rowData = excelUploadGrid.getRowAt(ev.rowKey);
		ExcelList.excelKey = rowData.excelKey;
		ExcelList.excelUploadRowClick( rowData.excelKey );
	});
	
	const excelDataGrid = new tui.Grid({
		el: document.getElementById("excelDataGrid"),
	    data: [],
	    dataSource: null,
	    scrollX: false,
	    scrollY: false,
	    selectionUnit: 'row',
	    pagination : true,
	    columns: [
	        {  header: 'Seq', 	 name: 'rownum', width:50, },
	        {  header: '시/도', 	 name: 'sido' },
	        {  header: '시/군/구', name: 'sigungu' },
	        {  header: '면/동', 	 name: 'upmyondong' },
	        {  header: '리', 	 name: 'ri' },
	        {  header: '번지', 	 name: 'bunji' },
	        {  header: '부번지', 	 name: 'bubunji' },
	        {  header: '확인유무', name: 'isValidYn' },
	        {  header: '수정대상', name: 'isTransYn' },
	      ], 
	 	pageOptions: {
	        perPage: 5,
	        totalPage: 3,
	        totalItems : 30,
	        page: 1,
	        useClient: true,
	    },
	 });
	
	// fired mouse event
	excelDataGrid.on('click', function(ev) {
	  var target = ev.nativeEvent.target;
	  console.log( target );
	});
	
	//엑셀키 다시 입력했을시
	excelDataGrid.getPagination().on("afterMove", function(ev) {
		var pageNum = ev.page;
		if( pageNum == 1 ) ExcelList.dataStartNum = 0;
		else ExcelList.dataStartNum = (pageNum - 1 ) * ExcelList.dataPageSize ;
		
		ExcelList.excelUploadRowClick( ExcelList.excelKey  );
	});
	
	ExcelList.excelDataGrid = excelDataGrid;
	ExcelList.excelUploadGrid =  excelUploadGrid;
	
});


/*
* LOGIN 작업 클래스 
*/

class ExcelList {
	
	static init() {
		this.excelKey = '';
		this.pageSize = 10;
		this.currentPage = 1;
		this.startNum = 0;
		this.excelDataGrid = null;
		this.excelUploadGrid = null;
		this.dataPageSize = 5;
		this.dataCurrentPage = 1;
		this.dataStartNum = 0;
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
		ExcelList.excelUploadGrid.getPagination().setTotalItems( data.COUNT );
		ExcelList.excelUploadGrid.getPagination().reset(data.COUNT);
		ExcelList.excelUploadGrid.resetData( data.LIST );
		ExcelList.excelDataGrid.resetData( [] );
		
	}
	
	static cbExcelRetrieveError( error ) {
		console.log("result error");
		console.log( error );
	}
	
	static excelUploadRowClick( excelKey ) {
		var url = "/admin/excelDataRetrieve";
		var sendData = {
			excelKey : excelKey ,
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
		ExcelList.excelDataGrid.resetData( data.LIST );
		ExcelList.excelDataGrid.getPagination().setTotalItems( data.COUNT );
		ExcelList.excelDataGrid.getPagination().reset(data.COUNT);
	}
	
}