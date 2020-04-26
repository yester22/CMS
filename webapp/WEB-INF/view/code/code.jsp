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
            <h1>코드 관리</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
            	<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item"><a href="#">공통코드</a></li>
				<li class="breadcrumb-item active">코드 관리</li>
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
							<col width="15%" />
							<col width="20%" />
							<col width="20%" />
							<col width="25%" />
							<col width="*" />
						</colgroup>
						<tbody>
							<tr>
								<th><label for="searchCodeKey">코드</label></th>
								<td><input type="text" name="searchCodeKey" id="searchCodeKey" size="30" maxlength="30" class="form-control form-control-sm" placeholder="코드를 입력해주세요" /></td>
								<th style="text-align:center !important;"><label for="searchCodeName">코드 명</label></th>
								<td><input type="text" name="searchCodeName" id="searchCodeName" size="30" maxlength="30" class="form-control form-control-sm" placeholder="코드명을 입력해주세요" /></td>
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
            	<h3 class="card-title">코드 List</h3>
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
				<input type="hidden" name="codeSaveKey" id="codeSaveKey" value="C" />
				<div class="card-body">
					<div class="form-group row">
	                 	<label for="codeKey" class="col-sm-2 col-form-label">코드</label>
	                    <div class="col-sm-10">
	                    	<input type="text" name="codeKey" id="codeKey" value="" size="30" maxlength="30" class="form-control" placeholder="코드를 입력해주세요" />
	                    </div>
					</div>
					<div class="form-group row">
	                	<label for="codeName" class="col-sm-2 col-form-label">코드 명</label>
	                    <div class="col-sm-10">
	                    	<input type="text" name="codeName" id="codeName" value="" size="30" maxlength="30" class="form-control" placeholder="코드명을 입력해주세요" />
	                    </div>
	                </div>
					<div class="form-group row">
	                 	<label for="upperCode" class="col-sm-2 col-form-label">상위코드</label>
	                    <div class="col-sm-10">
	                    	<select class="form-control select2"  name="upperCode" id="upperCode" style="width: 100%;">
                 				<option value="#">선택하세요</option>
                 			</select>
	                    </div>
					</div>
	                <div class="form-group row">
	                	<label for="sortNum" class="col-sm-2 col-form-label">순번</label>
	                    <div class="col-sm-10">
	                    	<input type="number" name="sortNum" id="sortNum" value="" size="30" maxlength="30" class="form-control" placeholder="순번 입력해주세요." />
	                    </div>
	                </div>
	                <div class="form-group row">
	                 	<label for="codeUseYn" class="col-sm-2 col-form-label">사용 유무</label>
	                    <div class="col-sm-10">
	                    	<select class="form-control select2"  name="codeUseYn" id="codeUseYn" style="width: 100%;">
                 				<option value="Y">사용함</option>
                 				<option value="N">사용안함</option>
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

<script type="text/javascript" src="/resources/js/cms/code/code.js"></script>