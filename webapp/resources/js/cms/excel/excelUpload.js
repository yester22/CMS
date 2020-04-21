/**
 * filename : list.js
 * developer : kyoungjin jung.
 * date : 2020-03-20
 * description : 엑셀파일 업로드 페이지
 */
$(document).ready(function(){
	$("#btnUpload").bind("click", ExcelUpload.btnExcelUpload);
	$('.my-colorpicker2').colorpicker()

	$('.my-colorpicker2').on('colorpickerChange', function(event) {
		$('.my-colorpicker2 .fa-square').css('color', event.color.toString());
	});
	
	
});


/*
* LOGIN 작업 클래스 
*/

var ExcelUpload  = {
	/*
	* e : 이벤트 객체
	*/ 
	btnExcelUpload : function ( e ) {
		e.preventDefault();
		var url = "/admin/excelUpload";
		var form = $("#uploadForm")[0];
        var formData = new FormData(  form );
        formData.append("fileObj", $("#uploadfile")[0].files[0]);
        formData.append("title", $("#title").val());
        formData.append("locationCode", $("#locationCode").val());
        formData.append("mapColor", $("#mapColor").val());
                
        $.ajax({
            url: url,
            processData: false,
            contentType: false,
            data: formData,
            type: 'POST',
            success: CodeList.cbExcelUploadResult,
            error  : CodeList.cbExcelUploadError,
        });
	},
	
	inputcheck : function() {

	},
	cbExcelUploadResult : function ( data ) {
		if ( data != null ) {
			if ( data.RESULT == "OK" ) {
				var title = "데이터업로드 성공";
				var msg = '엑셀 데이터 업로드에 성공 하였습니다<br/>데이터 조회페이지로 이동하시겠습니까?';
				msgBox.confirm (title, msg, function() {
					pageMove.goPost('excel/excelList', '', 'post');
				});
			}
		}
	},
	
	cbExcelUploadError : function( error ) {
		console.log("result error");
		console.log( error );
	}
	
}