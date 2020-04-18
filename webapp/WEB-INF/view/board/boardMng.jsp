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
            <h1>게시판 조회</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
            	<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item"><a href="#">게시판 관리</a></li>
				<li class="breadcrumb-item active">게시판 조회</li>
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
								<col width="15%" />
								<col width="10%" />
								<col width="15%" />
								<col width="10%" />
								<col width="30%" />
								<col width="*" />
							</colgroup>
							<tbody>
								<tr>
									<th style="text-align:center !important;"><label for="title">게시판 명</label></th>
									<td>
										<select name="searchBoardCode" id="searchBoardCode" class="form-control form-control-sm">
											<option value="">선택</option>
											<option value="1">게시판1</option>
											<option value="2">게시판2</option>
											<option value="3">게시판3</option>
											<option value="4">게시판4</option>
											<option value="5">게시판5</option>
										</select>
									</td>
									<th><label for="title">제목</label></th>
									<td><input type="text" name="searchTitle" id="title" size="30" maxlength="30" class="form-control form-control-sm" placeholder="제목을 입력해주세요" /></td>
									<th  style="text-align:center !important;"><label for="uploadfile">검색일자</label></th>
									<td>
					                    <input type='text' id="searchStatDate" name="searchStartDate" class="cmsDatePicker" size="10" maxlength="10" />
						                &nbsp;~&nbsp;
					                    <input type='text' id="searchEndDate"  name="searchEndDate" class="cmsDatePicker"  size="10" maxlength="10" />
									</td>
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
        <div class="card card-default">
        	<div class="card-header">
            	<h3 class="card-title">업로드 엑셀정보 목록</h3>
            	<div class="card-tools" style="margin-top:15px;">
            		<button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i></button>
        		</div>
        	</div>
        	<div class="card-body">
	          	<div id="jgUploadList"></div>
	        </div>
		</div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<script type="text/javascript" src="/resources/js/cms/board/boardMng.js"></script>
