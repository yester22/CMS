/**
 * filename : list.js
 * developer : kyoungjin jung.
 * date : 2020-03-20
 * description : 엑셀파일 업로드 페이지
 */
$(document).ready(function(){

	CodeList.init();
	
	$("#btnSearch").bind("click", CodeList.btnCode);
	$("#btnDelete").bind("click", CodeList.btnDelete);
	$("#btnCreat").bind("click", CodeList.btnCodeView);
	$("#btnUpload").bind("click", CodeList.btnCodeReg);
	$("#btnCancle").bind("click", CodeList.btnCodeCancle);
	$("#codeKey").bind("keypress", CodeList.inputBoardTag);
	 
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
	                    return $("<input>").attr("type", "checkbox").on("change", CodeList.checkAllItem);
	                },
	                itemTemplate: function(_, item) {
	                    return $("<input>").attr("type", "checkbox").attr('name', 'boardData').attr('value', item.codeSeq);
	                },
	                align: "center",
	                width: 50
	            },
	            { title : '순번', 			name: 'rownum', 		type: 'text',  align: 'center', width: 50  },
		        { title : '코드', 			name: 'code',			type: 'text',  align: 'center', width: 100 },
		        { title : '코드명', 			name: 'codeNm',			type: 'text',  align: 'left',   width: 200 },
		        { title : '상위코드', 			name: 'upperCdKey',		type: 'text',  align: 'center', width: 100 },
		        { title : '정렬순번', 			name: 'orderSeq',		type: 'text',  align: 'center', width: 100 },
		        { title : '사용유무', 			name: 'useYn',			type: 'text',  align: 'center', width: 100 },
		        { title : '등록자', 			name: 'regId', 			type: 'text',  align: 'center', width: 100 },
		        { title : '등록일시', 			name: 'regDate', 		type: 'text',  align: 'center', width: 150 }
		  ],
		  rowClick: function(args) { CodeList.btnCodeRead(args); }
	});
});


/*
* LOGIN 작업 클래스 
*/

var CodeList = {
	
	init : function() {
		this.codeSeq = '';

		var url = "/admin/CodeKeyRead";
		var formData = {};
		$.ajax({
	            url: url,
	            dataType: 'json', 
	            data: formData,
	            type: 'POST',
	            success: CodeList.cbBoardCodeReadResult,
	            error  : CodeList.cbBoardError,
	    });
	},

	cbBoardCodeReadResult : function ( data ) {
		if(data != null) {
			var list = data.LIST;
			for(var i = 0;i < list.length;i++) {
				$("#searchBoardCode").append("<option value='"+list[i].boardCd+"'>"+list[i].boardNm+"</option>");
			}
		}
	},

	/*
	* e : 이벤트 객체
	*/ 
	btnCode : function ( e ) {
		if($("#searchBoardCode").val() == "") {
			alert("게시판을 선택해 주세요.");
			$("#searchBoardCode").focus();
			return false;
		}

		$('#cover-spin').show(0);

		e.preventDefault();
		var url = "/admin/CodeList";
		
		$("#pageSize").val(CodeList.pageSize);
		$("#currentPage").val(CodeList.currentPage);
		$("#startNum").val(CodeList.startNum);
		
		var formData = $("#searchForm").serialize();

		CodeList.codeSeq  = '';
		
        $.ajax({
            url: url,
            dataType: 'json', 
            data: formData,
            type: 'POST',
            success: CodeList.cbBoardResult,
            error  : CodeList.cbBoardError,
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
		if ( CodeList.codeSeq == '') {
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
				codeSeq : CodeList.codeSeq
			}			

			 $.ajax({
		            url: url,
		            dataType: 'json', 
		            data: sendData,
		            type: 'POST',
		            success: CodeList.cbBoardDeleteResult,
		            error  : CodeList.cbBoardError,
		     });
		});
	},

	//삭제후 실행되는 콜백함수 
	cbBoardDeleteResult : function ( data ) {
		if ( data.RESULT == "OK") {
			msgBox.alert('데이터가 삭제되었습니다');
			
			var args = {
				item : {
					codeSeq : CodeList.codeSeq	
				}		
			};
			
			CodeList.btnCode(args);
		}
		$('#cover-spin').hide();
	},

	btnCodeView : function () {
		$(".titleTextCg").text("게시물 등록");
		$("#boardSaveKey").val("C");
		$("#codeSeq").val("");
		$("#boardCode").val("");
		$("#boardTitle").val("");
		$("#boardContent").val("");
		$("#boardTag").val("");
		$("#boardHtmlYn").val("Y");
		$(".saveTextCg").text("등록");
		$(".boardRegBox").show();

		setTimeout(CodeList.cbBoardTimeSet, 500);
	},

	btnCodeReg : function ( e ) {
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
        formData.append("codeSeq", $("#codeSeq").val());
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
            success: CodeList.cbBoardRegResult,
            error  : CodeList.cbBoardError,
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

	btnCodeCancle : function () {
		$("#boardSaveKey").val("C");
		$("#codeSeq").val("");
		$("#boardCode").val("");
		$("#boardTitle").val("");
		$("#boardContent").val("");
		$("#boardTag").val("");
		$("#boardHtmlYn").val("Y");
		$(".boardRegBox").hide();
	},

	btnCodeRead : function ( args ) {
		$('#cover-spin').show(0);

		CodeList.codeSeq = args.item.codeSeq;
		
		var url = "/admin/boardRead";
		var sendData = {
			codeSeq : args.item.codeSeq
		};
		
		$.ajax({
	            url: url,
	            dataType: 'json', 
	            data: sendData,
	            type: 'POST',
	            success: CodeList.cbBoardReadResult,
	            error  : CodeList.cbBoardError,
	    });
	},

	cbBoardReadResult : function ( data ) {
		$(".titleTextCg").text("게시물 수정");
		$("#boardSaveKey").val("E");
		$("#codeSeq").val(data.codeSeq);
		$("#boardCode").val(data.boardCd);
		$("#boardTitle").val(data.title);
		$("#boardContent").html(data.body);
		$("#boardTag").val(data.tag);
		$("#boardHtmlYn").val(data.htmlYn);
		$(".saveTextCg").text("수정");
		$(".boardRegBox").show();

		setTimeout(CodeList.cbBoardTimeSet, 500);

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