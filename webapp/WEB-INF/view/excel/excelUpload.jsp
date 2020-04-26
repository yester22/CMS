<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div class="content-wrapper">
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>Data Upload</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item"><a href="#">Data 관리</a></li>
						<li class="breadcrumb-item active">데이터 업로드</li>
					</ol>
				</div>
			</div>
		</div>
	</section>
	<!-- card content start -->
	<section class="content">
		<div class="container-fluid">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">엑셀양식 업로드</h3>
				</div>
				<form id="uploadForm" method="post" enctype="multipart/form-data" class="form-horizontal">
					<div class="card-body">
						 <div class="form-group row">
		                 	<label for="title" class="col-sm-2 col-form-label">제목</label>
		                    <div class="col-sm-10">
		                    	<input type="text" name="title" id="title" size="30" maxlength="30" class="form-control" placeholder="제목을 입력해주세요" />
		                    </div>
		                 </div>
						<div class="form-group row">		                    
		                    <label for="uploadfile" class="col-sm-2 col-form-label">파일선택</label>
                    		<div class="col-sm-10">
                      			<div class="custom-file">
				            		<input type="file" class="custom-file-input" id="uploadfile" name="uploadfile" placeHolder="파일을 선택하세요">
				                	<label class="custom-file-label" for="exampleInputFile">파일선택</label>
                      			</div>
                     		</div>
						</div>
						<div class="form-group row">
		                 	<label for="locationCode" class="col-sm-2 col-form-label">지역코드</label>
		                    <div class="col-sm-10">
		                    	<select class="form-control select2"  name="locationCode" id="locationCode" style="width: 100%;">
                    				<option value="">선택</option>
									<option value="11">서울특별시</option>
									<option value="26">부산광역시</option>
									<option value="27">대구광역시</option>
									<option value="28">인천광역시</option>
									<option value="29">광주광역시</option>
									<option value="30">대전광역시</option>
									<option value="31">울산광역시</option>
									<option value="36">세종특별자치시</option>
									<option value="41">경기도</option>
									<option value="42">강원도</option>
									<option value="43">충청북도</option>
									<option value="44">충청남도</option>
									<option value="45">전라북도</option>
									<option value="46">전라남도</option>
									<option value="47">경상북도</option>
									<option value="48">경상남도</option>
									<option value="50">제주특별자치도</option>
                  				 </select>
		                    </div>
		                 </div>
		                 <div class="form-group row">
		                    <label for="viewColor" class="col-sm-2 col-form-label">색상 선택</label>
                  			<div class="input-group col-sm-10 my-colorpicker2">
                    			<input type="text" class="form-control my-colorpicker2" id="viewColor" name="mapColor" placeHolder="색상을 선택하세요">
			                    <div class="input-group-append">
                      				<span class="input-group-text"><i class="fas fa-square"></i></span>
                    			</div>
                  			</div>
		                 </div>
					</div><!-- card body end -->
					<div class="card-footer">
                  		<button type="button" id="btnTemplateDownload" class="btn btn-info">양식 다운로드</button>
                  		<button type="button" id="btnUpload" class="btn btn-primary float-right" click="ExcelUpload.btnExcelUploade()">업로드</button>
                	</div><!-- card footer end -->
				</form>
			</div>
			
			<div class="card">
              <div class="card-header">
                <h3 class="card-title">
                  <i class="fas fa-text-width"></i>&nbsp;&nbsp;업로드시 주의 사항</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <p class="text-info">* 업로드 이후, 데이터 생성 및 국토부 서비스를 이용한 정보 업데이트로 인해 바로 사용할 수 없습니다</p>
                <p class="text-primary">&nbsp;-&nbsp;국토부 토지대장 정보 확인 및 공간정보 플래폼에서 업로드된 데이터의 공간정보 획득을 위한 시간이 소요 됩니다</p>
                <p class="text-primary">&nbsp;-&nbsp;해당 데이터들의 각 상태 완료시 공지로 표기 되오니 공지 확인 부탁드립니다</p>
              </div>
              <!-- /.card-body -->
            </div>
			
		</div><!-- container-fluid -->
	</section>
	<!-- card content end -->
</div>
<script type="text/javascript" src="/resources/js/cms/excel/excelUpload.js"></script>