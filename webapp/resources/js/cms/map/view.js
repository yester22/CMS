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

class MapView {
	static init() {
		this.rdata = '';
		this.geojsonObject = '';
		this.vmap = null;
		this.stylep = null;
		this.vectorSource = null;
		this.dataPageSize = 10;
		this.dataStartNum = 1;
		this.featureData = new Array();
		this.mapColor = null;
		this.markerArray = new Array();
		this.pageSize = 20;
		this.startNum = 1;
	};
	
	static mapInit() {
		
		//create the map
		var vmap = new google.maps.Map(document.getElementById('map-canvas'), {
		  zoom: 17,
		  center: {lat: 38.4487476125906, lng:128.391105775592},
		  mapTypeId : 'hybrid'
		});
		
		MapView.vmap = vmap;
	}
	
	static move( y, x, zoom) {
		const center = new google.maps.LatLng(y, x);
		MapView.fnMoveZoom(zoom);
		MapView.vmap.panTo(center);
	}


	static fnMoveZoom( level ) {
		 google.maps.event.addListener(MapView.vmap, 'zoom_changed', function () {
	            var zoomChangeBoundsListener = google.maps.event.addListener(MapView.vmap, 'bounds_changed', function (event) {
	                if (this.getZoom() > level && this.initialZoom == true) {
	                    this.setZoom(level);
	                    this.initialZoom = false;
	                }
	                google.maps.event.removeListener(zoomChangeBoundsListener);
	            });
	        });
	};
	
	//grid init
	static gridInit() {
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
		     rowClick: function(args) { MapView.excelUploadRowClick(args); },
		     onRefreshed : function(args) { MapView.refreshedGrid(args); }
		  });
	}
	
	/*
	* e : 이벤트 객체
	*/ 
	static excelRetrieve ( ) {
		var url = "/admin/excelRetrieve";

		MapView.excelKey  = '';
		var formData = {};
		
        $.ajax({
            url: url,
            dataType: 'json', 
            data: formData,
            type: 'POST',
            success: MapView.cbExcelRetrieveResult,
            error  : MapView.cbExcelRetrieveError,
        });
	}
	//
	static cbExcelRetrieveResult ( data ) {
		$("#jgUploadList").jsGrid("option", "data", data.LIST);
		$("#jgUploadList").jsGrid("option", "itemsCount", data.COUNT);
	}
	
	static cbExcelRetrieveError( error ) {
		console.log("result error");
		console.log( error );
	}
	
	//그리드 아이템 선택시 web service 호출
	static excelUploadRowClick( args ) {
		MapView.excelKey = args.item.excelKey;
		MapView.mapColor = args.item.mapColor;
		
		var url = "/admin/excelDataRetrieveByPaging";
		var sendData = {
			excelKey : args.item.excelKey ,
			startNum : 1,
			pageSize : 20,
		};
		
		 $.ajax({
	            url: url,
	            dataType: 'json', 
	            data: sendData,
	            type: 'POST',
	            success: MapView.cbExcelDataRetrieveResult,
	            error  : MapView.cbExcelRetrieveError,
	     });
		 
		 MapView.startNum = 1;
		 MapView.pageSize = 1;
		 
	}

	//그리드 아이템 선택시 web service 콜백함수
	static cbExcelDataRetrieveResult( data ) {
		
		/*if ( MapView.vmap.data ) {
			MapView.vmap.data.forEach(function(feature) {
				MapView.vmap.data.remove(feature);
			});
			MapView.featureArray = null;
		}
		*/
		
		var moveData = new Object();
		var mapColorRgbData = null;
		if( MapView.mapColor ) {
			mapColorRgbData = MapView.mapColor;
		}
		
		if ( data.LIST.length <= 0 ) {
			MsgBox.alert('데이터가 존재하지 않습니다');
			return false;
		}
			
		MapView.featureArray = new Array();
			
			//폴리곤 데이터 그리기
		var list = data.LIST;
		var polygonData = '';
		var marker = null;
		var shortAddr = '';
		
		for ( var idx = 0; idx < list.length; idx++ ) {
			polygonData = list[idx].poligonData;
			
			if ( polygonData != null && polygonData != '' ) {
				if ( idx == 0 ) { //move data
					moveData.xPos = list[idx].xPos;
					moveData.yPos = list[idx].yPos;
				}
				//polygonData = JSON.parse(polygonData);
				//var featrue = MapView.vmap.data.addGeoJson(polygonData.featureCollection,{idPropertyName:"id_" + (idx + 1) }); 

			}
			
			//marker 생성
			shortAddr = ( ( list[idx].ri == null || list[idx].ri == '') ? list[idx].upmyundong : list[idx].ri ); 
			shortAddr = shortAddr + ' ' + ( ( list[idx].bubunji == null || list[idx].bubunji == '') ? list[idx].bunji : list[idx].bunji + '-' + list[idx].bubunji );
			
			var markerData = new Object();
			markerData.labelContent = shortAddr;
			markerData.lat = ( list[idx].yPos != null && list[idx].yPos != '' ) ? 0 : Number(list[idx].yPos);
			markerData.lng = ( list[idx].xPos != null && list[idx].xPos != '' ) ? 0 : Number(list[idx].xPos);
			markerData.polygonData = polygonData;
			
		/*	if ( list[idx].xPos != null && list[idx].xPos != '' && list[idx].yPos != null && list[idx].yPos != '' ) { 
				var marker = new MarkerWithLabel({
					position: {lat: Number(list[idx].yPos), lng: Number(list[idx].xPos)},
					map: MapView.vmap,
					icon: ' ',  
					labelContent: shortAddr,
					labelClass: "label",
					labelStyle: {opacity: 0.5},
				});
				MapView.markerArray.push(marker);
			}*/
		}
		
		/*MapView.vmap.data.setStyle({
			strokeColor: mapColorRgbData,
			strokeWeight: 1,
			strokeOpacity: 0.8,
			fillColor: mapColorRgbData,
			fillOpacity: 0.5
		});*/
		
		MapView.move( moveData.yPos, moveData.xPos, 15);
	
	}
	
	
	static drawMapInfo() {
		
	}
	
	//화면 로드시 그리드 첫번째 아이템 자동 선택 
	static refreshedGrid( args ) {
		if (args.grid.data.length) {
	        var gridBody = $("#jgUploadList").find('.jsgrid-grid-body');
	        gridBody.find('.jsgrid-table tr:first-child').trigger('click');
	        gridBody.animate({
	            scrollTop: 0,
	            scrollLeft: 0
	        }, 250);
	    }
	}
	
};