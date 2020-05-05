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
						<input type="hidden" id="csrfToken" 	value="${_csrf.token}"/>
						<input type="hidden" id="csrfHeader" 	value="${_csrf.headerName}"/>
						
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
											<option value="Y" >사용</option>
											<option value="N" >사용불가</option>
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
				<button type="button" id="btnDelete"  class="btn btn-info">삭제</button>
        		<button type="button" id="btnUserAdd" class="btn btn-primary float-right" data-toggle="modal" data-target="#modalMember">등록 </button>
        	</div><!-- card footer end -->
		</div>
        <!-- /.card -->
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <!-- Modal -->
<div class="modal modal-center fade" id="modalMember" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-center modal-80size" role="document">
    <div class="modal-content modal-80size">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">사용자 상세정보</h4>
        <button type="button" id="btnCloseHeader" class="close"  aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
        <form id="modalInputHome">
        	 <div class="form-group row">
		      	<label for="memberNm" class="col-sm-2 col-form-label">사용자명</label>
		        <div class="col-sm-10">
		        	<input type="text" name="memberNm" id="memberNm" size="30" maxlength="30" class="form-control" placeholder="사용자명" />
		        </div>
		     </div>
		     <div class="form-group row">
		      	<label for="memberId" class="col-sm-2 col-form-label">사용자 계정</label>
		        <div class="col-sm-10">
  					<input type="text" class="form-control" id="memberId" name="memberId" placeholder="사용자명" aria-label="사용자명" aria-describedby="basic-addon2" style="float:left;width:86%;">
  					<div class="input-group-append">
    					<button id="chkIdConfirm" class="btn btn-outline-secondary" type="button">ID확인</button>
  					</div>
				</div>
		     </div>
		     <div class="form-group row">
		      	<label for="memberType" class="col-sm-2 col-form-label">사용자 타입</label>
		        <div class="col-sm-10">
		        	<select id="memberType" name="memberType" class="form-control">
		        		<option value="">선 택</option>
		        		<option value="ADMIN">관리자</option>
		        		<option value="CHARGER">방제 담당자</option>
		        	</select>
		        </div>
		     </div>
		     <div id="divPassWd" class="form-group row">
		      	<label for="pwd" class="col-sm-2 col-form-label">암호</label>
		        <div class="col-sm-10">
		        	<input type="password" class="form-control" id="memberPw" name="pwd" placeholder="암호" aria-label="암호" aria-describedby="basic-addon2" />
		        </div>
		     </div>
		     <div id="divPassWdCf" class="form-group row">
		      	<label for="memberPwConfirm" class="col-sm-2 col-form-label">암호 재확인</label>
		        <div class="col-sm-10">
		        	<input type="password" class="form-control" id="memberPwConfirm" name="memberPwConfirm" placeholder="암호 재입력" aria-label="암호 재입력" aria-describedby="basic-addon2" />
		        </div>
		     </div>
        </form>
      </div>
      <div class="modal-footer">
      	<button type="button" id="btnModalSave" class="btn btn-primary">저장</button>
        <button type="button" id="btnClose" class="btn btn-default">닫기</button>
      </div>
    </div>
  </div>
</div>

  
<script type="text/javascript" src="/resources/js/cms/member/member.js"></script>
