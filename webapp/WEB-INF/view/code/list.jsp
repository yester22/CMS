<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">코드 목록</h1>
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
								<col width="15%" />
								<col width="10%" />
								<col width="20%" />
								<col width="*" />
							</colgroup>
							<tbody>
								<tr>
									<th><label for="title">제목</label></th>
									<td style="text-align:center;"><input type="text" name="title" id="title" size="30" maxlength="30" placeholder="제목을 입력해주세요" /></td>
									<th><label for="uploadfile">파일</label></th>
									<td>
										<input type="file" id="uploadfile" name="uploadfile" placeholder="파일 선택" />
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
<script type="text/javascript" src="/resources/js/cms/code/list.js"></script>