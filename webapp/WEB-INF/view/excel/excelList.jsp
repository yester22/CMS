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
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"><small>엑셀 데이터 조회</small></h1>
		</div>
		<div class="col-lg-12">
			<div class="panel panel-primary">
				<!-- /.panel-heading -->
				<div class="panel-body">
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
									<th><label for="title">제목</label></th>
									<td><input type="text" name="searchTitle" id="title" size="30" maxlength="30" class="form-control form-control-sm" placeholder="제목을 입력해주세요" /></td>
									<th style="text-align:center !important;"><label for="title">지역코드</label></th>
									<td>
										<select name="searchLocationCode" id="locationCode" class="form-control form-control-sm">
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
									</td>
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
				<!-- /.panel-body -->
			</div>
		</div>
		<div class="col-lg-12">
			<div class="panel panel-default">
				<!-- /.panel-heading -->
				<div class="panel-body">
				 	<div id="excelUploadGrid"></div>
				</div>
			</div>
		</div>
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					Excel Data
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div id="excelDataGrid"></div>
				</div>
			</div>
		</div>
	</div>
	
</div>
<script type="text/javascript" src="/resources/js/cms/excel/excelList.js"></script>
