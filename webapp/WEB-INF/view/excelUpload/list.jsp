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
											<option value="02">서울특별시</option>
											<option value="031">경기도</option>
											<option value="032">인천광역시</option>
											<option value="033">강원도</option>
											<option value="041">충청남도</option>
											<option value="042">대전광역시</option>
											<option value="043">충청북도</option>
											<option value="044">세종특별자치시</option>
											<option value="051">부산광역시</option>
											<option value="052">울산광역시</option>
											<option value="053">대구광역시</option>
											<option value="054">경상북도</option>
											<option value="055">경상남도</option>
											<option value="061">전라남도</option>
											<option value="062">광주광역시</option>
											<option value="063">전라북도</option>
											<option value="064">제주특별자치도</option>
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
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					업로드된 엑셀 파일 목록
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					 <table width="100%" class="table table-striped table-bordered table-hover" id="excelUploadGrid">
                     	<thead>
                        	<tr>
	                            <th>Rendering engine</th>
	                            <th>Browser</th>
	                            <th>Platform(s)</th>
	                            <th>Engine version</th>
	                            <th>CSS grade</th>
                            </tr>
                         </thead>
                         <tbody>
                           <tr class="odd gradeX">
                                <td>Trident</td>
                                <td>Internet Explorer 4.0</td>
                                <td>Win 95+</td>
                                <td class="center">4</td>
                           		<td class="center">X</td>
                           </tr>
                           <tr class="even gradeC">
                                <td>Trident</td>
                                <td>Internet Explorer 5.0</td>
                                <td>Win 95+</td>
                                <td class="center">5</td>
                            	<td class="center">C</td>
                            </tr>
                            <tr class="odd gradeA">
                                <td>Trident</td>
                                <td>Internet Explorer 5.5</td>
                                <td>Win 95+</td>
                                <td class="center">5.5</td>
                            	<td class="center">A</td>
                        	</tr>
                    	</tbody>
					</table>
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
					 <table width="100%" class="table table-striped table-bordered table-hover" id="excelDataGrid">
                     	<thead>
                        	<tr>
                        		<th><input type="checkbox" name="dataChkAll" id="dataChkAll" /></th>
	                            <th>Seq</th>
	                            <th>시/도</th>
	                            <th>시/군/구</th>
	                            <th>면/동</th>
	                            <th>리</th>
	                            <th>번지</th>
	                            <th>확인유무</th>
	                            <th>수정대상</th>
                            </tr>
                         </thead>
                         <tbody>
                           <tr class="odd gradeX">
                                <td><input type="checkbox" name="selectedData" /></td>
                                <td>1</td>
                                <td>서울 특별시</td>
                                <td>관악구</td>
                                <td>봉천동</td>
                                <td></td>
                                <td>196-49</td>
                                <td class="center">Y</td>
                           		<td class="center">N</td>
                           </tr>
                           <tr class="even gradeX">
                                <td><input type="checkbox" name="selectedData" /></td>
                                <td>2</td>
                                <td>서울 특별시</td>
                                <td>관악구</td>
                                <td>봉천동</td>
                                <td></td>
                                <td>196-48</td>
                                <td class="center">Y</td>
                           		<td class="center">N</td>
                           </tr>
                            <tr class="odd gradeX">
                                <td><input type="checkbox" name="selectedData" /></td>
                                <td>3</td>
                                <td>서울 특별시</td>
                                <td>관악구</td>
                                <td>봉천동</td>
                                <td></td>
                                <td>196-47</td>
                                <td class="center">Y</td>
                           		<td class="center">N</td>
                           </tr>
                    	</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
</div>
<script type="text/javascript" src="/resources/js/cms/code/list.js"></script>