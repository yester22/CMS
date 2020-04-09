<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://openlayers.org/en/v4.6.5/css/ol.css" type="text/css">
<!-- <script type="text/javascript" src="http://map.vworld.kr/js/vworldMapInit.js.do?version=2.0&apiKey=3C0D8395-23FD-3870-B012-50F4659E1CC1"></script>  -->
<script src="https://openlayers.org/en/v5.3.0/build/ol.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/proj4js/2.3.12/proj4.js" type="text/javascript"></script>
<script src="https://cdn.polyfill.io/v2/polyfill.min.js?features=requestAnimationFrame,Element.prototype.classList,URL"></script>

<div class="content-wrapper">
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>Map 조회</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item"><a href="#">Map Data 조회</a></li>
						<li class="breadcrumb-item active">Map Data 조회</li>
					</ol>
				</div>
			</div>
		</div>
	</section>
	</section>
	<!-- card content start -->
	<section class="content">
		<div class="container-fluid">
			<div class="card card-default">
				<div class="row"">
					<div class="loding" style="display: none;"></div>
					<div id="vmap" style="width:100%;height:450px;left:0px;top:0px"></div>
				</div>
			</div>
			<div class="card card-default">
				<div class="card-header">
	            	<h3 class="card-title">업로드 엑셀정보 목록</h3>
	            	<div class="card-tools" style="margin-top:15px;">
	            		<button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i></button>
	        		</div>
	        	</div>
	        	<div class="card-body">
		          	<div id="jgUploadList"></div>
		        </div>
			</div>
		</div>
	</section>
</div>	
<script type="text/javascript" src="/resources/js/cms/map/view.js"></script>
				