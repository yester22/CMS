/**
 * filename : list.js
 * developer : kyoungjin jung.
 * date : 2020-03-20
 * description : 엑셀파일 업로드 페이지
 */
$(document).ready(function(){

	$("#btnUpload").click(ExcelUpload.btnExcelUpload);
	$('.my-colorpicker2').colorpicker()

	$('.my-colorpicker2').on('colorpickerChange', function(event) {
		$('.my-colorpicker2 .fa-square').css('color', event.color.toString());
	});
	
	
	$('#uploadfile').on('change',function(){
        //get the file name
        var fileName = $(this).val();
        //replace the "Choose a file" label
        $(this).next('.custom-file-label').html(fileName);
    })
	
});


/*
* LOGIN 작업 클래스 
*/

var ExcelUpload  = {
	/*
	* e : 이벤트 객체
	*/ 
	btnExcelUpload : function ( e ) {
		
		if ( ! ExcelUpload.inputcheck() ) return false;
		
		e.preventDefault();
		
		$('#cover-spin').show(0);
		
		var url = "/admin/excelUpload";
		var form = $("#uploadForm")[0];
        var formData = new FormData(  form );
        formData.append("fileObj", $("#uploadfile")[0].files[0]);
        formData.append("title", $("#title").val());
        formData.append("locationCode", $("#locationCode").val());
        formData.append("mapColor", $("#viewColor").val());
                
        $.ajax({
            url: url,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            data: formData,
            type: 'POST',
            success: ExcelUpload.cbExcelUploadResult,
            error  : ExcelUpload.cbExcelUploadError,
        });
        
	},
	
	inputcheck : function() {
		var title =  $("#title").val();
		var mapColor = $("#viewColor").val();
		var locationCode = $("#locationCode").val();
		var file = $("#uploadfile")[0].files[0];
		
		if ( title == null || title == '' ) {
			msgBox.alert('제목은 필수입력입니다');
			return false;
		}
		
		if ( locationCode == null || locationCode == '' ) {
			msgBox.alert('지역코드는 필수입니다');
			return false;
		}
		
		if ( mapColor == null || mapColor == '' ) {
			msgBox.alert('색상 선택은 필수입니다');
			return false;
		}
		
		return true;
		
		
	},
	cbExcelUploadResult : function ( data ) {
		console.log ( data );
		
		if ( data != null ) {
			if ( data.RESULT == "OK" ) {
				var title = "데이터업로드 성공";
				var msg = '엑셀 데이터 업로드에 성공 하였습니다<br/>데이터 조회페이지로 이동하시겠습니까?';
				msgBox.confirm (title, msg, function() {
					pageMove.goPost('excel/excelList', '', 'post');
				});
			}
		}
		$('#cover-spin').hide();;
	},
	
	cbExcelUploadError : function( error ) {
		console.log("result error");
		console.log( error );
		
		$('#cover-spin').hide();;
	}
	
}