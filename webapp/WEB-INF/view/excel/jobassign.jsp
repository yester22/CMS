<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <!-- Content Wrapper. Contains page content -->
 <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>업무배정</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
            	<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item"><a href="#">Data 관리</a></li>
				<li class="breadcrumb-item active">업무배정</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
    	<div class="container-fluid">
    		<div class="card card-default">
    			<div class="card-body">
					<div class="input-group md-form form-sm form-2 pl-0 w-25">
						<input class="form-control my-0 py-1 lime-border" type="text" placeholder="지역명을 선택하세요" aria-label="Search" id="searchInput" name="searchKeyword">
						<div class="input-group-append">
							<span class="input-group-text lime lighten-2" id="basic-text1" style="cursor:pointer;"><i class="fas fa-search text-grey" aria-hidden="true" onClick="JobAssign.searchExcelOpen()"></i></span>
						</div>
						&nbsp;<input class="form-control my-0 py-1 lime-border" type="text" aria-label="Search" id="searchExcelKey" name="searchExcelKey" readonly >
						<button type="button" id="btnOpenDialog" class="btn btn-primary float-right" style="display:none;" data-toggle="modal" data-target="#modalExcelSelect" />
					</div>
				</div>
      		</div>
      	</div>
      	
    
      	<div style="height:100px;">
      		<div class="row" style="height:400px;">
	      		<div class="col-md-6">
		      		<div class="card card-default">
		    			<div class="card-body">
		    				카드 왼쪽
		    			</div>
		    		</div>
			    </div>
			    <div class="col-md-6">
		      		<div class="card card-default">
		    			<div class="card-body">
		    				카드 오른쪽
		    			</div>
		    		</div>
		    	</div>
		    </div>
      	</div>
    </section>
    
</div>	
<!-- search form -->
<!-- Modal -->
<div class="modal modal-center fade" id="modalExcelSelect" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-center modal-100size" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">데이터 선택</h4>
        <button type="button" id="btnCloseHeader" class="close"   data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
      	<div class="input-group md-form form-sm form-2 pl-0" style="margin-bottom:20px;">
			<input class="form-control my-0 py-1 lime-border" type="text" placeholder="지역명을 선택하세요" aria-label="Search" id="modalSearchInput" name="modalSearch">
			<span></span>
			<div class="input-group-append">
				<span class="input-group-text lime lighten-2" id="basic-text1" style="cursor:pointer;"><i class="fas fa-search text-grey" aria-hidden="true" onClick="JobAssign.searchExcelOpen()"></i></span>
			</div>
		</div>
		<div id="excelSelectList"></div>	        
      </div>
      <div class="modal-footer">
        <button type="button" id="btnClose" class="btn btn-default" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
<!-- seach form end -->  
<script type="text/javascript" src="/resources/js/cms/excel/jobassign.js"></script>
