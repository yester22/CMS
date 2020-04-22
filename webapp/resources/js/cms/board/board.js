/**
 * filename : list.js
 * developer : kyoungjin jung.
 * date : 2020-03-20
 * description : 엑셀파일 업로드 페이지
 */
$(document).ready(function(){

	BoardList.init();
	
	$("#btnSearch").bind("click", BoardList.btnBoard);
	$("#btnDelete").bind("click", BoardList.btnDelete);
	$("#btnCreat").bind("click", BoardList.btnBoardView);
	$("#btnUpload").bind("click", BoardList.btnBoardReg);
	$("#btnCancle").bind("click", BoardList.btnBoardCancle);
	$("#boardTag").bind("keypress", BoardList.inputBoardTag);
	 
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
	                    return $("<input>").attr("type", "checkbox").on("change", BoardList.checkAllItem);
	                },
	                itemTemplate: function(_, item) {
	                    return $("<input>").attr("type", "checkbox").attr('name', 'boardData').attr('value', item.boardSeq);
	                },
	                align: "center",
	                width: 50
	            },
	            { title : '순번', 			name: 'rownum', 		type: 'text',  align: 'center', width: 50  },
		        { title : '게시물 제목', 		name: 'title',			type: 'text',  align: 'left', 	width: 200 },
		        { title : 'HTML존재', 		name: 'htmlYn',			type: 'text',  align: 'center', width: 100 },
		        { title : '등록일', 			name: 'regDate',		type: 'text',  align: 'center', width: 150 },
		        { title : '등록자', 			name: 'regId',			type: 'text',  align: 'center', width: 100 },
		        { title : '수정일', 			name: 'uptDate',		type: 'text',  align: 'center', width: 150 },
		        { title : '수정자', 			name: 'uptId', 			type: 'text',  align: 'center', width: 100 }
		  ],
		  rowClick: function(args) { BoardList.btnBoardRead(args); }
	});
});


/*
* LOGIN 작업 클래스 
*/

var BoardList = {
	
	init : function() {
		this.boardSeq = '';

		var url = "/admin/boardCodeRead";
		var formData = {};
		$.ajax({
	            url: url,
	            dataType: 'json', 
	            data: formData,
	            type: 'POST',
	            success: BoardList.cbBoardCodeReadResult,
	            error  : BoardList.cbBoardError,
	    });
	},

	cbBoardCodeReadResult : function ( data ) {
		if(data != null) {
			var list = data.LIST;
			for(var i = 0;i < list.length;i++) {
				$("#searchBoardCode").append("<option value='"+list[i].boardCd+"'>"+list[i].boardNm+"</option>");
				$("#boardCode").append("<option value='"+list[i].boardCd+"'>"+list[i].boardNm+"</option>");
			}
		}
	},

	/*
	* e : 이벤트 객체
	*/ 
	btnBoard : function ( e ) {
		if($("#searchBoardCode").val() == "") {
			alert("게시판을 선택해 주세요.");
			$("#searchBoardCode").focus();
			return false;
		}

		$('#cover-spin').show(0);

		e.preventDefault();
		var url = "/admin/boardList";
		
		$("#pageSize").val(BoardList.pageSize);
		$("#currentPage").val(BoardList.currentPage);
		$("#startNum").val(BoardList.startNum);
		
		var formData = $("#searchForm").serialize();

		BoardList.boardSeq  = '';
		
        $.ajax({
            url: url,
            dataType: 'json', 
            data: formData,
            type: 'POST',
            success: BoardList.cbBoardResult,
            error  : BoardList.cbBoardError,
        });
	},
	//
	cbBoardResult : function ( data ) {
		$("#jgDataList").jsGrid("option", "data", data.LIST);
		$("#jgDataList").jsGrid("option", "itemsCount", data.COUNT);

		$('#cover-spin').hide();
	},
	
	cbBoardError : function ( error ) {
		console.log("result error");
		console.log( error );
		
		$('#cover-spin').hide();
	},
	
	//그리드 전체 선택시
	checkAllItem  : function () {
		console.log( $(this).val() );
	},

	btnDelete : function ( e ) {
		if ( BoardList.boardSeq == '') {
			msgBox.alert("게시물을 선택 후 삭제하세요");
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

		var title = "게시물 삭제 확인";
		var msg = '게시물을 삭제하시겠습니까?';
		msgBox.confirm (title, msg, function() {
			$('#cover-spin').show(0);

			var url = "/admin/boardDelete";
			var sendData = {
				deleteData : checkVal ,
				boardSeq : BoardList.boardSeq
			}			

			 $.ajax({
		            url: url,
		            dataType: 'json', 
		            data: sendData,
		            type: 'POST',
		            success: BoardList.cbBoardDeleteResult,
		            error  : BoardList.cbBoardError,
		     });
		});
	},

	//삭제후 실행되는 콜백함수 
	cbBoardDeleteResult : function ( data ) {
		if ( data.RESULT == "OK") {
			msgBox.alert('데이터가 삭제되었습니다');
			
			var args = {
				item : {
					boardSeq : BoardList.boardSeq	
				}		
			};
			
			BoardList.btnBoard(args);
		}
		$('#cover-spin').hide();
	},

	btnBoardView : function () {
		$(".titleTextCg").text("게시물 등록");
		$("#boardSaveKey").val("C");
		$("#boardSeq").val("");
		$("#boardCode").val("");
		$("#boardTitle").val("");
		$("#boardContent").val("");
		$("#boardTag").val("");
		$("#boardHtmlYn").val("Y");
		$(".saveTextCg").text("등록");
		$(".boardRegBox").show();

		setTimeout(BoardList.cbBoardTimeSet, 500);
	},

	btnBoardReg : function ( e ) {
		if($("#boardCode").val() == "") {
			alert("게시판 명을 선택하세요.");
			$("#boardCode").focus();
			return false;
		} else if($("#boardTitle").val() == "") {
			alert("제목을 등록하세요.");
			$("#boardTitle").focus();
			return false;
		} else if($("#boardContent").val() == "") {
			alert("내용을 등록하세요.");
			$("#boardContent").focus();
			return false;
		} else if($("#boardTag").val() == "") {
			alert("태그를 등록하세요.");
			$("#boardTag").focus();
			return false;
		}

		$('#cover-spin').show(0);

		e.preventDefault();
		var url = "/admin/boardReg";
		var form = $("#uploadForm")[0];
        var formData = new FormData(  form );
        formData.append("boardSaveKey", $("#boardSaveKey").val());
        formData.append("boardCode", $("#boardCode").val());
        formData.append("boardSeq", $("#boardSeq").val());
        formData.append("boardTitle", $("#boardTitle").val());
        formData.append("boardContent", $("#boardContent").val());
        formData.append("boardTag", $("#boardTag").val());
        formData.append("boardHtmlYn", $("#boardHtmlYn").val());
                
        $.ajax({
            url: url,
            processData: false,
            contentType: false,
            data: formData,
            type: 'POST',
            success: BoardList.cbBoardRegResult,
            error  : BoardList.cbBoardError,
        });
	},

	cbBoardRegResult : function ( data ) {
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

	btnBoardCancle : function () {
		$("#boardSaveKey").val("C");
		$("#boardSeq").val("");
		$("#boardCode").val("");
		$("#boardTitle").val("");
		$("#boardContent").val("");
		$("#boardTag").val("");
		$("#boardHtmlYn").val("Y");
		$(".boardRegBox").hide();
	},

	btnBoardRead : function ( args ) {
		$('#cover-spin').show(0);

		BoardList.boardSeq = args.item.boardSeq;
		
		var url = "/admin/boardRead";
		var sendData = {
			boardSeq : args.item.boardSeq
		};
		
		$.ajax({
	            url: url,
	            dataType: 'json', 
	            data: sendData,
	            type: 'POST',
	            success: BoardList.cbBoardReadResult,
	            error  : BoardList.cbBoardError,
	    });
	},

	cbBoardReadResult : function ( data ) {
		$(".titleTextCg").text("게시물 수정");
		$("#boardSaveKey").val("E");
		$("#boardSeq").val(data.boardSeq);
		$("#boardCode").val(data.boardCd);
		$("#boardTitle").val(data.title);
		$("#boardContent").val(data.body);
		$("#boardTag").val(data.tag);
		$("#boardHtmlYn").val(data.htmlYn);
		$(".saveTextCg").text("수정");
		$(".boardRegBox").show();

		setTimeout(BoardList.cbBoardTimeSet, 500);

		$('#cover-spin').hide();
	},

	cbBoardTimeSet : function () {
		var offset = $("#saveArea").offset();
		$("html body").animate({scrollTop:offset.top},2000);
	},

	inputBoardTag : function ( e ) {
		//e.preventDefault();
		var re = /[\{\}\[\]\/?.;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
		var temp = $(this).val();
		if(re.test(temp)) {
			$(this).val(temp.replace(re,""));
		}
	}
}