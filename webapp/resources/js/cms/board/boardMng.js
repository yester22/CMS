/**
 * filename : list.js
 * developer : kyoungjin jung.
 * date : 2020-03-20
 * description : 엑셀파일 업로드 페이지
 */
$(document).ready(function(){

	ExcelList.init();
	
	$("#btnSearch").bind("click", ExcelList.btnExcelRetrieve);
	$("#btnExcelDownload").bind("click", ExcelList.btnExcelDownload);
	$("#btnDelete").bind("click", ExcelList.btnDelete);
	 
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
	    		{
	                headerTemplate: function() {
	                    return $("<input>").attr("type", "checkbox").on("change", ExcelList.checkAllItem);
	                },
	                itemTemplate: function(_, item) {
	                    return $("<input>").attr("type", "checkbox").attr('name', 'boardData').attr('value', item.dataSeq);
	                },
	                align: "center",
	                width: 50
	            },
	            { title : '순번', 			name: 'rownum', 		type: 'text',  align: 'center', width: 50  },
	            { title : '게시판코드', 		name: 'boardCd',		type: 'text',  align: 'center', width: 50  },
		        { title : '게시판명', 			name: 'boardNm',		type: 'text',  align: 'left', 	width: 200 },
		        { title : '상태', 			name: 'useYn', 			type: 'text',  align: 'center', width: 150 },
		        { title : '등록일', 			name: 'regDate',		type: 'text',  align: 'center', width: 150 },
		        { title : '등록자', 			name: 'regId',			type: 'text',  align: 'center', width: 150 },
		        { title : '수정일', 			name: 'uptDate',		type: 'text',  align: 'center', width: 150 },
		        { title : '수정자', 			name: 'uptId', 			type: 'text',  align: 'center', width: 150 }
		  ],
		  rowClick: function(args) { ExcelList.boardUpdateRowClick(args); }
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
		var url = "/admin/boardMngList";
		
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
		$("#jgDataList").jsGrid("option", "data", data.LIST);
		$("#jgDataList").jsGrid("option", "itemsCount", data.COUNT);
	}
	
	static cbExcelRetrieveError( error ) {
		console.log("result error");
		console.log( error );
	}
	
	//그리드 전체 선택시
	static checkAllItem() {
		console.log( $(this).val() );
	}
	
	
	//엑셀 다운로드
	static btnExcelDownload( e ) {
		var url = "/admin/excelDown";
		var form = document.createElement("form");
        form.setAttribute("charset", "UTF-8");
        form.setAttribute("method", "Post");
        form.setAttribute("action", url);

        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "excelKey");
        hiddenField.setAttribute("value", ExcelList.excelKey);

        form.appendChild(hiddenField);
        document.body.appendChild(form);

        form.submit();
	}
	
	
	static btnDelete(e) {
		if ( ExcelList.excelKey == '') {
			msgBox.alert("엑셀정보를 선택 후 선택하세요");
			return false;
		};
		
		var checkVal = '';
		$('input:checkbox[name="boardData"]').each(function(index, item) {
			if(this.checked){
				checkVal = checkVal + ',' + this.value;
			}
		});
		if ( checkVal.length > 1 ) {
			checkVal = checkVal.substring(1,checkVal.length);	
		}
		
		console.log ( checkVal );
		
		
		if ( checkVal.length <= 1) {
			msgBox.alert("체크된 데이터 건수가 존재하지 않습니다");
			return false;
		}
		
		var title = "데이터 삭제 확인";
		var msg = '데이터를 삭제하시겠습니까?';
		msgBox.confirm (title, msg, function() {
			var url = "/admin/excelDataDelete";
			var sendData = {
				deleteData : checkVal ,
				excelKey : ExcelList.excelKey
			}			

			 $.ajax({
		            url: url,
		            dataType: 'json', 
		            data: sendData,
		            type: 'POST',
		            success: ExcelList.cbExcelDataDeleteResult,
		            error  : ExcelList.cbExcelRetrieveError,
		     });
		});
	}

	//삭제후 실행되는 콜백함수 
	static cbExcelDataDeleteResult(data) {
		if ( data.RESULT == "OK") {
			msgBox.alert('데이터가 삭제되었습니다');
			
			var args = {
				item : {
					excelKey : ExcelList.excelKey	
				}		
			};
			
			ExelList.excelUploadRowClick(args);
		}
	}
}