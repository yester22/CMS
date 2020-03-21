/**
 * filename : list.js
 * developer : kyoungjin jung.
 * date : 2020-03-20
 * description : 엑셀파일 업로드 페이지
 */
$(document).ready(function(){
	$("#btnUpload").bind("click", CodeList.btnExcelUpload);
	$('#excelUploadGrid').DataTable({
        responsive: true
    });
	$('#excelDataGrid').DataTable({
		pagingType : "full_numbers",
		responsive: true
	});
});


/*
* LOGIN 작업 클래스 
*/

class CodeList {
	/*
	* e : 이벤트 객체
	*/ 
	static btnExcelUpload ( e ) {
		e.preventDefault();
		var url = "/admin/excelUpload";
		var form = $("#uploadForm")[0];
        var formData = new FormData(  form );
        formData.append("fileObj", $("#uploadfile")[0].files[0]);
        formData.append("title", $("#title").val());
        formData.append("locationCode", $("#locationCode").val());
        
        $.ajax({
            url: url,
            processData: false,
            contentType: false,
            data: formData,
            type: 'POST',
            success: CodeList.cbExcelUploadResult,
            error  : CodeList.cbExcelUploadError,
        });
	}
	
	static cbExcelUploadResult ( data ) {
		console.log("result success");
		console.log(data);
	}
	
	static cbExcelUploadError( error ) {
		console.log("result error");
		console.log( error );
	}
	
}