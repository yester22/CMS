<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <style>
   .message_layer {
    top: 0px;
    left: 0px;
    width: 250px;
    height: 180px;
    position: absolute;
    display:none;
    /** /background-color:#3399CC;/**/
    /*background-color:#660033;*/
    padding:3px;
    border: 2px solid white;
    color:white;
    -webkit-backdrop-filter: blur(3px); /* blur */
    /*opacity: 0.3;*/
    border-radius: 4px;
   }
   .close_btn {
    color:#ffffff;
    font-size:12px;
    font-weight:bold;
   }
   .content {
    color:#ffffff;
    font-size:12px;
   }
   input[type="text"] {
    font-size:12px;
    height:12px;
    border:1px gray solid;
    opacity: 0.6;
   }
   select {
    font-size:12px;
    /*height:12px;*/
    border:1px gray solid;
    opacity: 0.6;
   }
   body {margin:0;}
   div#searchLayer{
    position: absolute;
    top: 10px;
    left: 50px;
    border:2px gray dotted;
    padding:0 0 0 2px;
    border-radius: 4px;
   }
   div#searchListLayer{
    position: relative;
   }
   #address{width:98%;}
   .btn-ctl {
        top: 80px;
        left: .5em;
   }

  </style>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"><small>MAP 조회</small></h1>
		</div>
		<div class="col-lg-12">
			<div class="panel panel-primary">
				<!-- /.panel-heading -->
				<div class="panel-body">
					<form id="dataForm">
					    <input type="hidden" name="key" value="3C0D8395-23FD-3870-B012-50F4659E1CC1">
					    <input type="hidden" name="domain" value="http://localhost:8080">
					    <input type="hidden" name="service" value="data">
					    <input type="hidden" name="version" value="2.0">
					    <input type="hidden" name="request" value="getfeature">
					    <input type="hidden" name="format" value="json">
					    <input type="hidden" name="size" value="10">
					    <input type="hidden" name="page" value="1">
					    <input type="hidden" name="data" value="LT_C_ADSIDO_INFO">
					    <input type="hidden" name="attrfilter" value="emdCd:=:41173102">
					    <input type="hidden" name="geometry" value="true">
					    <input type="hidden" name="attribute" value="true">
					    <input type="hidden" name="crs" value="EPSG:900913">
					</form>
					<div class="loding" style="display: none;"></div>
					<div id="map"></div>
				</form>
				</div>
				<!-- /.panel-body -->
			</div>
		</div>
		<div class="col-lg-12">
		 	<div id="excelUploadDataGrid"></div>
		</div>
	</div>
	
</div>
<script type="text/javascript" src="/resources/js/cms/map/view.js"></script>
				