<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">엑셀파일 업로드</h1>
		</div>
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<i class="fa fa-bell fa-fw"></i> FILE UPLOAD
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<form id="uploadForm" method="post" enctype="multipart/form-data">
						<table>
							<colgroup>
								<col width="5%" />
								<col width="20%" />
								<col width="10%" />
								<col width="15%" />
								<col width="10%" />
								<col width="20%" />
								<col width="*" />
							</colgroup>
							<tbody>
								<tr>
									<th><label for="title">제목</label></th>
									<td><input type="text" name="title" id="title" size="30" maxlength="30" class="form-control form-control-sm" placeholder="제목을 입력해주세요" /></td>
									<th style="text-align:center !important;"><label for="title">지역코드</label></th>
									<td>
										<select name="locationCode" id="locationCode" class="form-control form-control-sm">
											<option value="">---선택---</option>
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
									<th  style="text-align:center !important;"><label for="uploadfile">파일</label></th>
									<td style="padding-top:5px;">
										<span class="control-fileupload">
          									<input type="file" id="uploadfile" name="uploadfile">
        								</span>
									</td>
									<td>
										<input type="button" id="btnUpload" class="btn btn-warning btn-sm" value="업로드">			
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<!-- /.panel-body -->
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="/resources/js/cms/excel/excelUpload.js"></script>