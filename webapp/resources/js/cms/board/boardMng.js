/**
 * filename : list.js
 * developer : kyoungjin jung.
 * date : 2020-03-20
 * description : 엑셀파일 업로드 페이지
 */
$(document).ready(function(){

	BoardMngList.init();
	
	$("#btnSearch").bind("click", BoardMngList.btnBoardMng);
	$("#btnExcelDownload").bind("click", BoardMngList.btnExcelDownload);
	$("#btnDelete").bind("click", BoardMngList.btnDelete);
	$("#btnCreat").bind("click", BoardMngList.btnBoardMngView);
	$("#btnUpload").bind("click", BoardMngList.btnBoardMngReg);
	$("#btnCancle").bind("click", BoardMngList.btnBoardMngCancle);
	 
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
	                    return $("<input>").attr("type", "checkbox").on("change", BoardMngList.checkAllItem);
	                },
	                itemTemplate: function(_, item) {
	                    return $("<input>").attr("type", "checkbox").attr('name', 'boardData').attr('value', item.boardCd);
	                },
	                align: "center",
	                width: 50
	            },
	            { title : '순번', 			name: 'rownum', 		type: 'text',  align: 'center', width: 50  },
	            { title : '게시판코드', 		name: 'boardCd',		type: 'text',  align: 'center', width: 100  },
		        { title : '게시판이름', 		name: 'boardNm',		type: 'text',  align: 'left', 	width: 200 },
		        { title : '상태', 			name: 'useYn', 			type: 'text',  align: 'center', width: 100 },
		        { title : '등록일', 			name: 'regDate',		type: 'text',  align: 'center', width: 150 },
		        { title : '등록자', 			name: 'regId',			type: 'text',  align: 'center', width: 150 },
		        { title : '수정일', 			name: 'uptDate',		type: 'text',  align: 'center', width: 150 },
		        { title : '수정자', 			name: 'uptId', 			type: 'text',  align: 'center', width: 150 }
		  ],
		  rowClick: function(args) { BoardMngList.btnBoardMngRead(args); }
	});
});


/*
* LOGIN 작업 클래스 
*/

class BoardMngList {
	
	static init() {
		this.boardCd = '';
	}
	
	/*
	* e : 이벤트 객체
	*/ 
	static btnBoardMng ( e ) {
		e.preventDefault();
		var url = "/admin/boardMngList";
		
		$("#pageSize").val(BoardMngList.pageSize);
		$("#currentPage").val(BoardMngList.currentPage);
		$("#startNum").val(BoardMngList.startNum);
		
		var formData = $("#searchForm").serialize();

		BoardMngList.boardCd  = '';
		
        $.ajax({
            url: url,
            dataType: 'json', 
            data: formData,
            type: 'POST',
            success: BoardMngList.cbBoardMngResult,
            error  : BoardMngList.cbBoardMngError,
        });
	}
	//
	static cbBoardMngResult ( data ) {
		$("#jgDataList").jsGrid("option", "data", data.LIST);
		$("#jgDataList").jsGrid("option", "itemsCount", data.COUNT);
	}
	
	static cbBoardMngError( error ) {
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
        hiddenField.setAttribute("name", "boardCd");
        hiddenField.setAttribute("value", BoardMngList.boardCd);

        form.appendChild(hiddenField);
        document.body.appendChild(form);

        form.submit();
	}

	static btnDelete(e) {
		if ( BoardMngList.boardCd == '') {
			msgBox.alert("게시판을 선택 후 삭제하세요");
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
		
		var title = "게시판 삭제 확인";
		var msg = '게시판을 삭제하시겠습니까?';
		msgBox.confirm (title, msg, function() {
			var url = "/admin/boardMngDelete";
			var sendData = {
				deleteData : checkVal ,
				boardCd : BoardMngList.boardCd
			}			

			 $.ajax({
		            url: url,
		            dataType: 'json', 
		            data: sendData,
		            type: 'POST',
		            success: BoardMngList.cbBoardMngDeleteResult,
		            error  : BoardMngList.cbBoardMngError,
		     });
		});
	}

	//삭제후 실행되는 콜백함수 
	static cbBoardMngDeleteResult(data) {
		if ( data.RESULT == "OK") {
			msgBox.alert('데이터가 삭제되었습니다');
			
			var args = {
				item : {
					boardCd : BoardMngList.boardCd	
				}		
			};
			
			BoardMngList.btnBoardMng(args);
		}
	}

	static btnBoardMngView() {
		$(".titleTextCg").text("게시판 등록");
		$("#boardSaveKey").val("C");
		$("#boardCode").val("");
		$("#boardName").val("");
		$("#listSize").val("");
		$("#pageBlockSize").val("");
		$("#titleLength").val("");
		$("#contentLength").val("");
		$("#useStates").val("Y");
		$(".saveTextCg").text("등록");
		$(".boardRegBox").show();

		var offset = $("#saveArea").offset();
		$("html body").animate({scrollTop:offset.top},2000);
	}

	static btnBoardMngReg ( e ) {
		if($("#boardName").val() == "") {
			alert("게시판이름을 등록하세요.");
			$("#boardName").focus();
			return false;
		} else if($("#listSize").val() == "") {
			alert("리스트 목록 사이즈를 등록하세요.");
			$("#listSize").focus();
			return false;
		} else if($("#pageBlockSize").val() == "") {
			alert("페이징 블락 사이즈를 등록하세요.");
			$("#pageBlockSize").focus();
			return false;
		} else if($("#titleLength").val() == "") {
			alert("제목길이를 등록하세요.");
			$("#titleLength").focus();
			return false;
		} else if($("#contentLength").val() == "") {
			alert("내용길이를 등록하세요.");
			$("#contentLength").focus();
			return false;
		}
		e.preventDefault();
		var url = "/admin/boardMngReg";
		var form = $("#uploadForm")[0];
        var formData = new FormData(  form );
        formData.append("boardSaveKey", $("#boardSaveKey").val());
        formData.append("boardCode", $("#boardCode").val());
        formData.append("boardName", $("#boardName").val());
        formData.append("listSize", $("#listSize").val());
        formData.append("pageBlockSize", $("#pageBlockSize").val());
        formData.append("titleLength", $("#titleLength").val());
        formData.append("contentLength", $("#contentLength").val());
        formData.append("useStates", $("#useStates").val());
                
        $.ajax({
            url: url,
            processData: false,
            contentType: false,
            data: formData,
            type: 'POST',
            success: BoardMngList.cbBoardMngRegResult,
            error  : BoardMngList.cbBoardMngError,
        });
	}

	static cbBoardMngRegResult ( data ) {
		if ( data != null ) {
			if ( data.RESULT == "OK" ) {
				var title = "게시판 등록";
				alert(title+"을 완료 하였습니다.");
				location.reload();
			}
		}
	}

	static btnBoardMngCancle() {
		$("#boardSaveKey").val("C");
		$("#boardCode").val("");
		$("#boardName").val("");
		$("#listSize").val("");
		$("#pageBlockSize").val("");
		$("#titleLength").val("");
		$("#contentLength").val("");
		$("#useStates").val("Y");
		$(".boardRegBox").hide();
	}

	static btnBoardMngRead( args ) {
		BoardMngList.boardCd = args.item.boardCd;
		
		var url = "/admin/boardMngRead";
		var sendData = {
			boardCd : args.item.boardCd
		};
		
		$.ajax({
	            url: url,
	            dataType: 'json', 
	            data: sendData,
	            type: 'POST',
	            success: BoardMngList.cbBoardMngReadResult,
	            error  : BoardMngList.cbBoardMngError,
	    });
	}

	static cbBoardMngReadResult( data ) {
		$(".titleTextCg").text("게시판 수정");
		$("#boardSaveKey").val("E");
		$("#boardCode").val(data.boardCd);
		$("#boardName").val(data.boardNm);
		$("#listSize").val(data.listRowCnt);
		$("#pageBlockSize").val(data.listBlockCnt);
		$("#titleLength").val(data.titleSplitLen);
		$("#contentLength").val(data.contentsSplitLen);
		$("#useStates").val(data.useYn);
		$(".saveTextCg").text("수정");
		$(".boardRegBox").show();

		var offset = $("#saveArea").offset();
		setTimeout($("html body").animate({scrollTop:offset.top},2000), 500);
	}
}