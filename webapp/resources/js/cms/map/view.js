/**
 * filename : map.js
 * developer : kyoungjin jung.
 * date : 2020-03-20
 * description : 엑셀파일 업로드 페이지
 */
$(document).ready(function(){
	MapView.init();
	MapView.mapInit();
	MapView.gridInit();
	MapView.excelRetrieve ( );	
	MapView.move( 38.44874761259062, 128.391105775592, 16);
});


/*
* MapView 작업 클래스 
*/
var MapView = {
	init : function() {
		this.rdata = '';
		this.geojsonObject = '';
		this.vmap = null;
		this.stylep = null;
		this.vectorSource = null;
		this.dataPageSize = 10;
		this.dataStartNum = 1;
		this.mapColor = null;
		this.pageSize = 20;
		this.startNum = 1;
		this.featureData = new Array();
		this.markerArray = new Array();
	},
	
	mapInit : function() {
		
		//create the map
		var vmap = new google.maps.Map(document.getElementById('map-canvas'), {
		  zoom: 17,
		  center: {lat: 38.4487476125906, lng:128.391105775592},
		  mapTypeId : 'hybrid'
		});
		
		this.vmap = vmap;
	},
	
	move : function( y, x, zoom) {
		const center = new google.maps.LatLng(y, x);
		this.fnMoveZoom(zoom);
		this.vmap.panTo(center);
	},


	fnMoveZoom : function( level ) {
		 google.maps.event.addListener(this.vmap, 'zoom_changed', function () {
	            var zoomChangeBoundsListener = google.maps.event.addListener(this.vmap, 'bounds_changed', function (event) {
	                if (this.getZoom() > level && this.initialZoom == true) {
	                    this.setZoom(level);
	                    this.initialZoom = false;
	                }
	                google.maps.event.removeListener(zoomChangeBoundsListener);
	            });
	        });
	},
	
	//grid init
	gridInit : function() {
		$("#jgUploadList").jsGrid({
			 height: "350px",
		     width: "100%",
		     paging: true,
		     sorting: false,
		     loadIndication: true,
		     loadIndicationDelay: 500,
		     pageIndex: 1,
		     pageSize: 10,
		     pageButtonCount: 10,
		     pagePrevText: "P",
		     pageNextText: "N",
		     pageFirstText: "F",
		     pageLastText: "L",
		     data: [],
		     fields: [
		            { title : '순번', 			name: 'rownum', 			type: 'text',  align: 'center', width: 50  },
			        { title : '제목', 			name: 'title',			 	type: 'text',  align: 'left', 	width: 200 },
			        { title : '지역명(시/도)', 	name: 'locationCodeName', 	type: 'text',  align: 'left', 	width: 100 },
			        { title : '상태', 			name: 'statusCodeNm', 		type: 'text',  align: 'left', 	width: 150 },
			        { title : '총평수', 			name: 'totalLandWidth',		type: 'text',  align: 'right', 	width: 150 },
			        { title : '등록자명', 			name: 'uploaderNm',		 	type: 'text',  align: 'left', 	width: 100 },
			        { title : '등록일자', 			name: 'uploadDt',		 	type: 'text',  align: 'center', width: 150 },
		        ],
		     rowClick: function(args) { this.excelUploadRowClick(args); },
		     onRefreshed : function(args) { this.refreshedGrid(args); }
		  });
	},
	
	/*
	* e : 이벤트 객체
	*/ 
	excelRetrieve : function ( ) {
		var url = "/admin/excelRetrieve";

		this.excelKey  = '';
		var formData = {};
		
        $.ajax({
            url: url,
            dataType: 'json', 
            data: formData,
            type: 'POST',
            success: this.cbExcelRetrieveResult,
            error  : this.cbExcelRetrieveError,
        });
	}, 

	cbExcelRetrieveResult : function( data ) {
		$("#jgUploadList").jsGrid("option", "data", data.LIST);
		$("#jgUploadList").jsGrid("option", "itemsCount", data.COUNT);
	},
	
	cbExcelRetrieveError : function( error ) {
		console.log("result error");
		console.log( error );
	},
	
	//그리드 아이템 선택시 web service 호출
	excelUploadRowClick : function ( args ) {
		this.excelKey = args.item.excelKey;
		this.mapColor = args.item.mapColor;
		
		this.markerArray = [];
		this.featureData = [];		
		
		this.excelDataRetrieve(this.excelKey);
	},

	//excelData retrieve
	excelDataRetrieve : function( excelKey ) {
		var url = "/admin/excelDataRetrieveCount";
		var sendData = {
			excelKey : excelKey ,
		};
		
		 $.ajax({
	            url: url,
	            dataType: 'json', 
	            data: sendData,
	            type: 'POST',
	            async : false,
	            success: this.cbExcelDataRetrieveResult,
	            error  : this.cbExcelRetrieveError,
	     });
		 
		 this.startNum = 1;
		 this.pageSize = 1;
	
		 
	},

	//그리드 아이템 선택시 web service 콜백함수
	cbExcelDataRetrieveResult : function( data ) {
		
		/*if ( this.vmap.data ) {
			this.vmap.data.forEach(function(feature) {
				this.vmap.data.remove(feature);
			});
			this.featureArray = null;
		}
		*/

		this.featureArray = new Array();
			
			//폴리곤 데이터 그리기
		var list = null;
		var polygonData = '';
		var shortAddr = '';
		var nStartNum = 0;
		
		var dataTotalCnt = data.COUNT;
		
		for ( var idx = 0; idx < dataTotalCnt; idx+=20 ) {

			nStartNum = idx + 1;
			
			var url = "/admin/excelDataRetrieveByPaging";
			var sendData = {
				excelKey : this.excelKey ,
				startNum : nStartNum,
				pageSize : 20,
				onlyData : "Y",
			};
			
			 $.ajax({
		            url: url,
		            dataType: 'json', 
		            data: sendData,
		            type: 'POST',
		            async : false,
		            success: function(data) {
		            	list = data.LIST;
		            	
		            	for ( var nIdx = 0; nIdx < list.length; nIdx++ ) {
		            		polygonData = list[nIdx].poligonData;
			    						    			
			    			//marker 생성
			    			shortAddr = ( ( list[nIdx].ri == null || list[nIdx].ri == '') ? list[nIdx].upmyundong : list[nIdx].ri ); 
			    			shortAddr = shortAddr + ' ' + ( ( list[nIdx].bubunji == null || list[nIdx].bubunji == '') ? list[nIdx].bunji : list[nIdx].bunji + '-' + list[nIdx].bubunji );

			    			if ( polygonData != '') {
				    			var markerData = new Object();
				    			markerData.labelContent = shortAddr;
				    			markerData.lat = ( list[nIdx].yPos != null && list[nIdx].yPos != '' ) ? Number(list[nIdx].yPos) : 0;
				    			markerData.lng = ( list[nIdx].xPos != null && list[nIdx].xPos != '' ) ? Number(list[nIdx].xPos) : 0;
				    			markerData.polygonData = polygonData;
				    			
				    			if ( this.markerArray.length <= dataTotalCnt ) this.markerArray.push(markerData);
			    			}
		            	}
		            	list = null;
		            }
		     });
			 
			 if ( dataTotalCnt == this.markerArray.length || this.markerArray.length > dataTotalCnt) break;
		}
		
		this.drawMapInfo();
	},
	
	
	drawMapInfo : function () {
		console.log('drawMapInfo');
		//console.log( this.markerArray);

		if (this.markerArray.length <= 0 ) return;
		var list = this.markerArray;
		
		var polygonData = null;
		var feature  = null;
		for ( var idx=0; idx < list.length; idx++ ) {
			try {
				polygonData = JSON.parse(list[idx].polygonData);
				feature = this.vmap.data.addGeoJson(polygonData.featureCollection,{idPropertyName:"id_" + (idx + 1) });
				this.featureData.push( feature );
			} catch ( e ) { }
			
			/*var marker = new MarkerWithLabel({
				position: {lat: Number(list[idx].yPos), lng: Number(list[idx].xPos)},
				map: this.vmap,
				icon: ' ',  
				labelContent: list[idx].shortAddr,
				labelClass: "label",
				labelStyle: {opacity: 0.5},
			});
			
			this.markerArray.push(marker);*/
		}	
		
		this.vmap.data.setStyle({
			strokeColor: '#000000',
			strokeWeight: 1,
			strokeOpacity: 0.8,
			fillColor: this.mapColor,
			fillOpacity: 0.5
		});
	}, 
	
	//화면 로드시 그리드 첫번째 아이템 자동 선택 
	refreshedGrid : function ( args ) {
		/**
		if (args.grid.data.length) {
	        var gridBody = $("#jgUploadList").find('.jsgrid-grid-body');
	        gridBody.find('.jsgrid-table tr:first-child').trigger('click');
	        gridBody.animate({
	            scrollTop: 0,
	            scrollLeft: 0
	        }, 250);
	    }
	    **/
	}
	
};