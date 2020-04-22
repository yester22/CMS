/**
 * filename : list.js
 * developer : kyoungjin jung.
 * date : 2020-03-20
 * description : 엑셀파일 업로드 페이지
 */
$(document).ready(function(){

	BoardMngList.init();
	
	$("#btnSearch").bind("click", BoardMngList.btnBoardMng);
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
		        { title : '등록자', 			name: 'regId',			type: 'text',  align: 'center', width: 100 },
		        { title : '수정일', 			name: 'uptDate',		type: 'text',  align: 'center', width: 150 },
		        { title : '수정자', 			name: 'uptId', 			type: 'text',  align: 'center', width: 100 }
		  ],
		  rowClick: function(args) { BoardMngList.btnBoardMngRead(args); }
	});
});


/*
* LOGIN 작업 클래스 
*/

var BoardMngList = {
	
	init : function () {
		this.boardCd = '';
	},
	
	/*
	* e : 이벤트 객체
	*/ 
	btnBoardMng : function ( e ) {
		$('#cover-spin').show(0);

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
	},
	//
	cbBoardMngResult : function ( data ) {
		$("#jgDataList").jsGrid("option", "data", data.LIST);
		$("#jgDataList").jsGrid("option", "itemsCount", data.COUNT);
		
		$('#cover-spin').hide();
	},
	
	cbBoardMngError : function ( error ) {
		console.log("result error");
		console.log( error );

		$('#cover-spin').hide();
	},
	
	//그리드 전체 선택시
	checkAllItem : function () {
		console.log( $(this).val() );
	},

	btnDelete : function ( e ) {
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
			$('#cover-spin').show(0);

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
	},

	//삭제후 실행되는 콜백함수 
	cbBoardMngDeleteResult : function ( data ) {
		if ( data.RESULT == "OK") {
			msgBox.alert('데이터가 삭제되었습니다');
			
			var args = {
				item : {
					boardCd : BoardMngList.boardCd	
				}		
			};
			
			BoardMngList.btnBoardMng(args);
		}
		$('#cover-spin').hide();
	},

	btnBoardMngView : function () {
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

		setTimeout(BoardMngList.cbBoardTimeSet, 500);
	},

	btnBoardMngReg : function ( e ) {
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

		$('#cover-spin').show(0);

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
	},

	cbBoardMngRegResult : function ( data ) {
		if ( data != null ) {
			if ( data.RESULT == "OK" ) {
				var title = "";
				if(data.boardIds == "C") {
					title = "게시물 등록";
				} else {
					title = "게시물 수정";
				}
				alert(title+"을 완료 하였습니다.");
				location.reload();
			}
		}
		$('#cover-spin').hide();
	},

	btnBoardMngCancle : function () {
		$("#boardSaveKey").val("C");
		$("#boardCode").val("");
		$("#boardName").val("");
		$("#listSize").val("");
		$("#pageBlockSize").val("");
		$("#titleLength").val("");
		$("#contentLength").val("");
		$("#useStates").val("Y");
		$(".boardRegBox").hide();
	},

	btnBoardMngRead : function ( args ) {
		$('#cover-spin').show(0);

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
	},

	cbBoardMngReadResult : function ( data ) {
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

		setTimeout(BoardMngList.cbBoardTimeSet, 500);

		$('#cover-spin').hide();
	},

	cbBoardTimeSet : function () {
		var offset = $("#saveArea").offset();
		$("html body").animate({scrollTop:offset.top},2000);
	}
}