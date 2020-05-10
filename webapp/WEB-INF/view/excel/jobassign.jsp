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
      	<div class="col-lg-3">
			<div class="card">
              <div class="card-header border-0">
                <h3 class="card-title">Products</h3>
                <div class="card-tools">
                  <a href="#" class="btn btn-tool btn-sm">
                    <i class="fas fa-download"></i>
                  </a>
                  <a href="#" class="btn btn-tool btn-sm">
                    <i class="fas fa-bars"></i>
                  </a>
                </div>
              </div>
              <div class="card-body table-responsive p-0">
                <table class="table table-striped table-valign-middle">
                  <thead>
                  <tr>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Sales</th>
                    <th>More</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                    <td>
                      <img src="/resources/bootstrap/adminite/dist/img/default-150x150.png" alt="Product 1" class="img-circle img-size-32 mr-2">
                      Some Product
                    </td>
                    <td>$13 USD</td>
                    <td>
                      <small class="text-success mr-1">
                        <i class="fas fa-arrow-up"></i>
                        12%
                      </small>
                      12,000 Sold
                    </td>
                    <td>
                      <a href="#" class="text-muted">
                        <i class="fas fa-search"></i>
                      </a>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <img src="/resources/bootstrap/adminite/dist/img/default-150x150.png" alt="Product 1" class="img-circle img-size-32 mr-2">
                      Another Product
                    </td>
                    <td>$29 USD</td>
                    <td>
                      <small class="text-warning mr-1">
                        <i class="fas fa-arrow-down"></i>
                        0.5%
                      </small>
                      123,234 Sold
                    </td>
                    <td>
                      <a href="#" class="text-muted">
                        <i class="fas fa-search"></i>
                      </a>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <img src="/resources/bootstrap/adminite/dist/img/default-150x150.png" alt="Product 1" class="img-circle img-size-32 mr-2">
                      Amazing Product
                    </td>
                    <td>$1,230 USD</td>
                    <td>
                      <small class="text-danger mr-1">
                        <i class="fas fa-arrow-down"></i>
                        3%
                      </small>
                      198 Sold
                    </td>
                    <td>
                      <a href="#" class="text-muted">
                        <i class="fas fa-search"></i>
                      </a>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <img src="/resources/bootstrap/adminite/dist/img/default-150x150.png" alt="Product 1" class="img-circle img-size-32 mr-2">
                      Perfect Item
                      <span class="badge bg-danger">NEW</span>
                    </td>
                    <td>$199 USD</td>
                    <td>
                      <small class="text-success mr-1">
                        <i class="fas fa-arrow-up"></i>
                        63%
                      </small>
                      87 Sold
                    </td>
                    <td>
                      <a href="#" class="text-muted">
                        <i class="fas fa-search"></i>
                      </a>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>      		
      	</div>
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
		     <div class="form-group row">
		      	<label for="memberType" class="col-sm-2 col-form-label">사용</label>
		        <div class="col-sm-10">
		        	<select id="useYn" name="useYn" class="form-control">
		        		<option value="Y">사용</option>
		        		<option value="N">사용불가</option>
		        	</select>
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

  
<script type="text/javascript" src="/resources/js/cms/jobassign/jobassign.js"></script>
