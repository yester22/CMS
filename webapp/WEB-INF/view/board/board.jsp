<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
	.cmsDatePicker {
		padding:4px 2px 5px 25px; 
		width:150px;
		height:35px; 
		border:1px solid #CACACA;
		font-size:15px; 
		color:#666;
		background:url('/resources/images/btn_date.png') no-repeat 2px 2px; 
		background-size:28px ;
		background-position: right center;
	}
</style>

	<!-- summernote -->
  <link rel="stylesheet" href="/resources/bootstrap/adminite//plugins/summernote/summernote-bs4.css">

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>게시물 관리</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
            	<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item"><a href="#">공통코드</a></li>
				<li class="breadcrumb-item active">게시물 관리</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        
        <div class="card card-default">
          	<div class="card-header">
            	<h3 class="card-title">검색</h3>
            	<div class="card-tools" style="margin-top:15px;">
            		<button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i></button>
        		</div>
        	</div>
        
	        <div class="card-body">
				<form id="searchForm" method="post">
						<input type="hidden" name="pageSize" 	id="pageSize" 	value="" />
						<input type="hidden" name="currentPage" id="currentPage" value="" />
						<input type="hidden" name="startNum" 	id="startNum" 	value="" />
						
						<table class="col-lg-12">
							<colgroup>
								<col width="5%" />
								<col width="20%" />
								<col width="10%" />
								<col width="20%" />
								<col width="10%" />
								<col width="20%" />
								<col width="*" />
							</colgroup>
							<tbody>
								<tr>
									<th><label for="searchBoardCode">게시판 명</label></th>
									<td>
										<select name="searchBoardCode" id="searchBoardCode" class="form-control form-control-sm">
											<option value="">선택하세요</option>
										</select>
									</td>
									<th><label for="searchTitle">게시물 제목</label></th>
									<td><input type="text" name="searchTitle" id="searchTitle" size="30" maxlength="30" class="form-control form-control-sm" placeholder="게시물 제목을 입력해주세요" /></td>
									<th><label for="searchTitle">게시물 내용</label></th>
									<td><input type="text" name="searchContent" id="searchContent" size="30" maxlength="30" class="form-control form-control-sm" placeholder="게시물 내용을 입력해주세요" /></td>
									<td style="text-align:right;">
										<input type="button" id="btnSearch" class="btn btn-primary" value="검색">			
									</td>
								</tr>
							</tbody>
						</table>
					</form>
	        </div>
        </div>
        <!-- /.card -->
		 <div class="card card-default">
        	<div class="card-header">
            	<h3 class="card-title">게시물 List</h3>
            	<div class="card-tools" style="margin-top:15px;">
            		<button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i></button>
        		</div>
        	</div>
        	<div class="card-body">
	          <div id="jgDataList"></div>
	        </div>
	        <div class="card-footer">
				<button type="button" id="btnCreat" class="btn btn-info">등록</button>
				<button type="button" id="btnDelete" class="btn btn-info">삭제</button>
        	</div><!-- card footer end -->
		</div>
        <!-- /.card -->
        <div class="card card-default boardRegBox" id="saveArea" style="display:none;">
        	<div class="card-header">
				<h3 class="card-title titleTextCg"></h3>
			</div>
			<form id="uploadForm" method="post" enctype="multipart/form-data" class="form-horizontal">
				<input type="hidden" name="boardSaveKey" id="boardSaveKey" value="C" />
				<input type="hidden" name="boardSeq" id="boardSeq" value="" />
				<div class="card-body">
					<div class="form-group row">
	                 	<label for="boardCd" class="col-sm-2 col-form-label">게시판 명</label>
	                    <div class="col-sm-10">
	                    	<select class="form-control select2"  name="boardCord" id="boardCord" style="width: 100%;">
                 				<option value="">선택하세요</option>
                 			</select>
	                    </div>
					</div>
					<div class="form-group row">
	                	<label for="boardTitle" class="col-sm-2 col-form-label">제목</label>
	                    <div class="col-sm-10">
	                    	<input type="text" name="boardTitle" id="boardTitle" value="" size="30" maxlength="30" class="form-control" placeholder="게시판이름을 입력해주세요" />
	                    </div>
	                </div>
	                <div class="form-group row">
	                	<label for="boardContent" class="col-sm-2 col-form-label">내용</label>
	                    <div class="col-sm-10">
	                		<textarea class="textarea" name="boardContent" id="boardContent" value="" placeholder="Place some text here" style="width: 100%; height: 400px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
	              		</div>
	                </div>
	                <div class="form-group row">
	                	<label for="pageBlockSize" class="col-sm-2 col-form-label">태그</label>
	                    <div class="col-sm-10">
	                    	<input type="text" name="boardTag" id="boardTag" value="" size="30" maxlength="11" class="form-control" placeholder="태그를 입력해주세요.태그는 쉼표로 구분합니다." />
	                    </div>
	                </div>
	                <div class="form-group row">
	                 	<label for="boardCd" class="col-sm-2 col-form-label">HTML 존재유무</label>
	                    <div class="col-sm-10">
	                    	<select class="form-control select2"  name="boardHtmlYn" id="boardHtmlYn" style="width: 100%;">
                 				<option value="Y">존재함</option>
                 				<option value="N">존재안함</option>
                 			</select>
	                    </div>
					</div>
				</div><!-- card body end -->
				<div class="card-footer">
					<button type="button" id="btnCancle" class="btn btn-primary float-right" style="margin-left: 5px;">취소</button>
                	<button type="button" id="btnUpload" class="btn btn-primary float-right saveTextCg"></button>
               	</div><!-- card footer end -->
			</form>
		</div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
<!-- Summernote -->
<script src="/resources/bootstrap/adminite/plugins/summernote/summernote-bs4.min.js"></script>
<script>
  $(function () {
    // Summernote
    $('.textarea').summernote()
  })
</script>
<script type="text/javascript" src="/resources/js/cms/board/board.js"></script>