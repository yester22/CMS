<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>사용자 관리</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
            	<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item"><a href="#">공통관리</a></li>
				<li class="breadcrumb-item active">사용자 관리</li>
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
								<col width="7%" />
								<col width="20%" />
								<col width="7%" />
								<col width="20%" />
								<col width="*" />
							</colgroup>
							<tbody>
								<tr>
									<th>
										<select id="searchType" name="searchType" class="form-control">
											<option value="name" selected>성명</option>
										</select>
									</th>
									<td>
										<input type="text" class="form-control" name="searchKeyword" id="searchKeyword" />				
									</td>
									<th style="text-align:center;">
										사용유무
									</th>
									<td>
										<select id="searchUseYn" name="searchUseYn" class="form-control">
											<option value="" selected>선택</option>
											<option value="Y" selected>사용</option>
											<option value="N" selected>사용불가</option>
										</select>
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
        <div class="card card-default">
        	<div class="card-body">
	          	<div id="memberList"></div>
	        </div>
	         <div class="card-footer">
				<button type="button" id="btnDelete" class="btn btn-info">삭제</button>
        		<button type="button" id="btnExcelDownload" class="btn btn-primary float-right">등록</button>
        	</div><!-- card footer end -->
		</div>
        <!-- /.card -->
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<script type="text/javascript" src="/resources/js/cms/member/member.js"></script>
