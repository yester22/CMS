<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&key=AIzaSyDfdrywjy28bEKc9POtHwCju862K7ap6Ac"></script>
<script src="http://cdn.sobekrepository.org/includes/gmaps-markerwithlabel/1.9.1/gmaps-markerwithlabel-1.9.1.js" type="text/javascript"></script>
<style>
	html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
    }
	  
	.label {
		color: #000;
        background-color: white;
        border: 1px solid #000;
        font-family: "Lucida Grande", "Arial", sans-serif;
        font-size: 12px;
        text-align: center;
        white-space: nowrap;
        padding: 2px;
    }
</style>
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
					<div id="map-canvas" style="width:100%;height:450px;left:0px;top:0px"></div>
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
				