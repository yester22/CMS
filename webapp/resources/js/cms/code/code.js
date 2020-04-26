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
	$("#codeKey").bind("keypress", CodeList.inputCodeKey);
	 
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
	                    return $("<input>").attr("type", "checkbox").attr('name', 'codeData').attr('value', item.code);
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

		var url = "/admin/codeKeyRead";
		var formData = {};
		$.ajax({
	            url: url,
	            dataType: 'json', 
	            data: formData,
	            type: 'POST',
	            success: CodeList.cbCodeKeyReadResult,
	            error  : CodeList.cbCodeError,
	    });
	},

	cbCodeKeyReadResult : function ( data ) {
		if(data != null) {
			var list = data.LIST;
			for(var i = 0;i < list.length;i++) {
				$("#upperCode").append("<option value='"+list[i].code+"'>"+list[i].codeNm+"</option>");
			}
		}
	},

	/*
	* e : 이벤트 객체
	*/ 
	btnCode : function ( e ) {
		$('#cover-spin').show(0);

		e.preventDefault();
		var url = "/admin/codeList";
		
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
            success: CodeList.cbCodeResult,
            error  : CodeList.cbCodeError,
        });
	},
	//
	cbCodeResult : function ( data ) {
		$("#jgDataList").jsGrid("option", "data", data.LIST);
		$("#jgDataList").jsGrid("option", "itemsCount", data.COUNT);

		$('#cover-spin').hide();
	},
	
	cbCodeError : function ( error ) {
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
			msgBox.alert("코드를 선택 후 삭제하세요");
			return false;
		};
		
		var checkVal = '';
		$('input:checkbox[name="codeData"]').each(function(index, item) {
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

		var title = "코드 삭제 확인";
		var msg = '코드를 삭제하시겠습니까?';
		msgBox.confirm (title, msg, function() {
			$('#cover-spin').show(0);

			var url = "/admin/codeDelete";
			var sendData = {
				deleteData : checkVal ,
				codeSeq : CodeList.codeSeq
			}			

			 $.ajax({
		            url: url,
		            dataType: 'json', 
		            data: sendData,
		            type: 'POST',
		            success: CodeList.cbCodeDeleteResult,
		            error  : CodeList.cbCodeError,
		     });
		});
	},

	//삭제후 실행되는 콜백함수 
	cbCodeDeleteResult : function ( data ) {
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
		$(".titleTextCg").text("코드 등록");
		$("#codeSaveKey").val("C");
		$("#codeKey").val("");
		$("#upperCode").val("#");
		$("#codeName").val("");
		$("#sortNum").val("");
		$("#codeUseYn").val("Y");
		$(".saveTextCg").text("등록");
		$("#saveArea").show();

		setTimeout(CodeList.cbCodeTimeSet, 500);
	},

	btnCodeReg : function ( e ) {
		if($("#codeKey").val() == "") {
			alert("코드를 입력하세요.");
			$("#codeKey").focus();
			return false;
		} else if($("#codeName").val() == "") {
			alert("코드명을 입력하세요.");
			$("#codeName").focus();
			return false;
		} else if($("#sortNum").val() == "") {
			alert("순번을 입력하세요.");
			$("#sortNum").focus();
			return false;
		}

		$('#cover-spin').show(0);

		e.preventDefault();
		var url = "/admin/codeReg";
		var form = $("#uploadForm")[0];
        var formData = new FormData(  form );
        formData.append("codeSaveKey", $("#codeSaveKey").val());
        formData.append("codeKey", $("#codeKey").val());
        formData.append("upperCode", $("#upperCode").val());
        formData.append("codeName", $("#codeName").val());
        formData.append("sortNum", $("#sortNum").val());
        formData.append("codeUseYn", $("#codeUseYn").val());
                
        $.ajax({
            url: url,
            processData: false,
            contentType: false,
            data: formData,
            type: 'POST',
            success: CodeList.cbCodeRegResult,
            error  : CodeList.cbCodeError,
        });
	},

	cbCodeRegResult : function ( data ) {
		if ( data != null ) {
			if ( data.RESULT == "OK" ) {
				if(data.boardIds == "R") {
					alert("이미 등록된 코드입니다.");
					$("#codeKey").focus();
					return false;
				} else {
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
		}
		$('#cover-spin').hide();
	},

	btnCodeCancle : function () {
		$("#codeSaveKey").val("C");
		$("#codeKey").val("");
		$("#upperCode").val("#");
		$("#codeName").val("");
		$("#sortNum").val("");
		$("#codeUseYn").val("Y");
		$("#saveArea").hide();
	},

	btnCodeRead : function ( args ) {
		$('#cover-spin').show(0);

		CodeList.codeSeq = args.item.codeSeq;
		
		var url = "/admin/codeRead";
		var sendData = {
			codeSeq : args.item.codeSeq
		};
		
		$.ajax({
	            url: url,
	            dataType: 'json', 
	            data: sendData,
	            type: 'POST',
	            success: CodeList.cbCodeReadResult,
	            error  : CodeList.cbCodeError,
	    });
	},

	cbCodeReadResult : function ( data ) {
		$(".titleTextCg").text("코드 수정");
		$("#codeSaveKey").val("E");
		$("#codeKey").val(data.code);
		$("#upperCode").val(data.upperCdKey);
		$("#codeName").val(data.codeNm);
		$("#sortNum").val(data.orderSeq);
		$("#codeUseYn").val(data.useYn);
		$(".saveTextCg").text("수정");
		$("#saveArea").show();

		setTimeout(CodeList.cbCodeTimeSet, 500);

		$('#cover-spin').hide();
	},

	cbCodeTimeSet : function () {
		var offset = $("#saveArea").offset();
		$("html body").animate({scrollTop:offset.top},2000);
	},

	inputCodeKey : function ( e ) {
		//e.preventDefault();
		var re = /[\{\}\[\]\/?.,;:|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/gi;
		var re2 = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g;
		var temp = $(this).val();
		if(re.test(temp)) {
			$(this).val(temp.replace(re,""));
		} else if(re2.test(temp)) {
			$(this).val(temp.replace(re2,""));
		}
	}
}