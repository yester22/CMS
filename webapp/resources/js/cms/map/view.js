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
	MapView.move( 14283776.791077042, 4163507.838217992, 16);
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
	};
	
	static mapInit() {
		
		 // 기본 주소
		var raster = new ol.layer.Tile({
			source:  new ol.source.XYZ({
		    	url : 'http://xdworld.vworld.kr:8080/2d/Base/201612/{z}/{x}/{y}.png' 
		    	,attributions : '&copy; ADDRON' + '&middot; '
		    }) 
		});

		var source = new ol.source.Vector({
		     id: "sourceId"
		});
		source.set("name", "sourceName");

		var vector = new ol.layer.Vector({
			id: "vectorId",
		    source: source,
		    style: new ol.style.Style({
		    	fill: new ol.style.Fill({                 // 타입을 Point로 하면 마커가 생성됨.
		    		color : [ 255, 0, 0, .4 ]
		    	}),
		    	stroke: new ol.style.Stroke({
		    		color: '#1DDB16',
		    		width: 3
		    	}),
		    	image: new ol.style.Icon(/** @type {olx.style.IconOptions} */({
		    		anchor: [0.5, 10],
		    		anchorXUnits: 'fraction',
		    		anchorYUnits: 'pixels',
		    		src: 'http://map.vworld.kr/images/ol3/marker_blue.png'
		    	}))
		    })
		});
		vector.set("name", "vectorName");
		$('#vmap').attr("style", "height:350px;width:100%;"); // 전체화면
		
		var element = document.createElement('div');
		element.className = 'btn-ctl ol-unselectable ol-control';
		
		var vmap = new ol.Map({
			layers: [raster, vector],
		    target: 'vmap',
		    controls: ol.control.defaults().extend([new ol.control.Control({element:element})]),
		    view: new ol.View({
		    	center: [14283776.791077042, 4163507.838217992],
		    	zoom: 17
		    })
		});
		
		// 지적도 레이어
		var wms_title = '지적도';
		var wms_val   = 'lp_pa_cbnd_bubun';

		var wms_tile = new ol.layer.Tile({
			name : "WMS_LAYER",
		    source : new ol.source.TileWMS({
		    url : "http://api.vworld.kr/req/wms?",
		    params : {
			      LAYERS : wms_val,
			      STYLES : wms_val,
			      CRS    : "EPSG:900913",
			      apikey : "B3DDE6F5-680A-3F04-AF90-6D16F15DB88E",
			      DOMAIN : "http://loacalhost:8080",
			      title  : wms_title,
			      FORMAT : "image/png",
			      domain : "http://localhost"
		    	}
		    })
		});
		vmap.addLayer(wms_tile);
		
		//// 필지 폴리곤 그리기 - 시작
		var vectorSource = new ol.source.Vector({
		    features: []
		});
		var vector_layer = new ol.layer.Vector({
			source: vectorSource
		
		})
		vector_layer.set("name","search_result");
		vmap.addLayer(vector_layer); 
		
		MapView.vmap = vmap;
	}
	
	static move( x, y, zoom) {
		MapView.vmap.getView().setCenter([ x, y ]); // 지도 이동
		MapView.vmap.getView().setZoom(zoom); // 줌레벨 설정
	}

	static fnMoveZoom( zoom ) {
		//var local_zoom = MapView.vmap.getView().getZoom();
		if (16 > zoom) {
			MapView.vmap.getView().setZoom(14);
		}
	};
	
	/**
	오버레이 삭제
	 */
	static deleteOverlay (id) {
		MapView.map.removeOverlay(MapView.map.getOverlayById(id));
	}
	
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
		
		var url = "/admin/excelDataRetrieve";
		var sendData = {
			excelKey : args.item.excelKey ,
		};
		
		 $.ajax({
	            url: url,
	            dataType: 'json', 
	            data: sendData,
	            type: 'POST',
	            success: MapView.cbExcelDataRetrieveResult,
	            error  : MapView.cbExcelRetrieveError,
	     });
	}

	//그리드 아이템 선택시 web service 콜백함수
	static cbExcelDataRetrieveResult( data ) {
		MapView.feautreData = [];
		
		if ( MapView.feautreData) { 
			var idx = 1;
			MapView.vmap.getLayers().forEach(function(layer){
				if ( layer.get("name") == "polygonData_" + idx) {
					MapView.vmap.removeLayer(layer);
				}
				idx = 1 + idx;
			});
		}
		
		var moveData = new Object();
		var mapColorRgbData = null;
		if( MapView.mapColor ) {
			mapColorRgbData = MapView.hexToRgb( MapView.mapColor );
		}
		
		if ( data.COUNT > 0 ) {
			var list = data.LIST;
			var polygonData = '';
			for ( var idx = 0; idx < data.COUNT; idx++ ) {
				polygonData = list[idx].poligonData;
				
				if ( polygonData != null && polygonData != '' ) {
					if ( idx == 0 ) { //move data
						moveData.xPos = list[idx].xPos;
						moveData.yPos = list[idx].yPos;
					}
					
					polygonData = JSON.parse(polygonData);
					
					var vectorSource = new ol.source.Vector({
			        	features: (new ol.format.GeoJSON()).readFeatures(polygonData.featureCollection)
			        });
			        var vector_layer = new ol.layer.Vector({
			             source: vectorSource,
			             style: new ol.style.Style({
			 		    	fill: new ol.style.Fill({                 // 타입을 Point로 하면 마커가 생성됨.
			 		    		color : [ parseInt(mapColorRgbData.r), parseInt(mapColorRgbData.g), parseInt(mapColorRgbData.b), .4 ]
			 		    	}),
			 		    	stroke: new ol.style.Stroke({
			 		    		color: MapView.mapColor,
			 		    		width: 1
			 		    	}),
			             }),
			        });
			        vector_layer.set("name","polygonData_" + (idx + 1));
			        MapView.vmap.addLayer(vector_layer);
			        
			        MapView.feautreData.push('polygonData_' + (idx + 1));
				}
			}
			
			MapView.move( moveData.xPos, moveData.yPos, 15);
		}
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
	
	//hex 값을 rgb값으로 변환
	static hexToRgb( hexType ){ 

        var hex = hexType.replace( "#", "" ); 
        var value = hex.match( /[a-f\d]/gi ); 

        // 헥사값이 세자리일 경우, 여섯자리로. 
        if ( value.length == 3 ) hex = value[0] + value[0] + value[1] + value[1] + value[2] + value[2]; 
        value = hex.match( /[a-f\d]{2}/gi ); 

        var r = parseInt( value[0], 16 ); 
        var g = parseInt( value[1], 16 ); 
        var b = parseInt( value[2], 16 ); 

        var rgbType = new Object;
        rgbType.r = r;
        rgbType.g = g;
        rgbType.b = b;

        return rgbType; 
} 
	
};