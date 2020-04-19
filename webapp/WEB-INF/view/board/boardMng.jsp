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
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>게시판 관리</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
            	<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item"><a href="#">공통코드</a></li>
				<li class="breadcrumb-item active">게시판 관리</li>
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
								<col width="10%" />
								<col width="60%" />
								<col width="*" />
							</colgroup>
							<tbody>
								<tr>
									<th><label for="searchTitle">게시판 명</label></th>
									<td><input type="text" name="searchTitle" id="searchTitle" size="30" maxlength="30" class="form-control form-control-sm" placeholder="제목을 입력해주세요" /></td>
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
            	<h3 class="card-title">게시판 List</h3>
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
        		<button type="button" id="btnExcelDownload" class="btn btn-primary float-right">엑셀다운로드</button>
        	</div><!-- card footer end -->
		</div>
        <!-- /.card -->
        <div class="card card-default boardRegBox" id="saveArea" style="display:none;">
        	<div class="card-header">
				<h3 class="card-title titleTextCg"></h3>
			</div>
			<form id="uploadForm" method="post" enctype="multipart/form-data" class="form-horizontal">
				<input type="hidden" name="boardSaveKey" id="boardSaveKey" value="C" />
				<input type="hidden" name="boardCode" id="boardCode" value="" />
				<div class="card-body">
					<div class="form-group row">
	                	<label for="title" class="col-sm-2 col-form-label">게시판이름</label>
	                    <div class="col-sm-10">
	                    	<input type="text" name="boardName" id="boardName" value="" size="30" maxlength="30" class="form-control" placeholder="게시판이름을 입력해주세요" />
	                    </div>
	                </div>
	                <div class="form-group row">
	                	<label for="title" class="col-sm-2 col-form-label">리스트 목록 사이즈</label>
	                    <div class="col-sm-10">
	                    	<input type="number" name="listSize" id="listSize" value="" size="30" maxlength="11" class="form-control" placeholder="리스트 목록 사이즈를 입력해주세요" />
	                    </div>
	                </div>
	                <div class="form-group row">
	                	<label for="title" class="col-sm-2 col-form-label">페이징 블락 사이즈</label>
	                    <div class="col-sm-10">
	                    	<input type="number" name="pageBlockSize" id="pageBlockSize" value="" size="30" maxlength="11" class="form-control" placeholder="페이징 블락 사이즈를 입력해주세요" />
	                    </div>
	                </div>
	                <div class="form-group row">
	                	<label for="title" class="col-sm-2 col-form-label">제목길이</label>
	                    <div class="col-sm-10">
	                    	<input type="number" name="titleLength" id="titleLength" value="" size="30" maxlength="11" class="form-control" placeholder="제목길이를 입력해주세요" />
	                    </div>
	                </div>
	                <div class="form-group row">
	                	<label for="title" class="col-sm-2 col-form-label">내용길이</label>
	                    <div class="col-sm-10">
	                    	<input type="number" name="contentLength" id="contentLength" value="" size="30" maxlength="11" class="form-control" placeholder="내용길이를 입력해주세요" />
	                    </div>
	                </div>
					<div class="form-group row">
	                 	<label for="locationCode" class="col-sm-2 col-form-label">사용유무</label>
	                    <div class="col-sm-10">
	                    	<select class="form-control select2"  name="useStates" id="useStates" style="width: 100%;">
								<option value="Y">사용</option>
								<option value="N">미사용</option>
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

<script type="text/javascript" src="/resources/js/cms/board/boardMng.js"></script>
