/**
 * filename : map.js
 * developer : kyoungjin jung.
 * date : 2020-03-20
 * description : 엑셀파일 업로드 페이지
 */
$(document).ready(function(){
	MapView.init();
	MapView.mapInit();
});


/*
* MapView 작업 클래스 
*/

class MapView {
	static init() {
		this.rdata = '';
		this.geojsonObject = '';
		this.map = null;
		this.stylep = null;
		this.vectorSource = null;
	};
	
	static mapInit() {
		
		$.ajax({
		     type: "get",
		     url: "http://api.vworld.kr/req/data",
		     data : $('#dataForm').serialize(),
		     dataType: 'jsonp',
		     async: false,
		     success: function(data) {
		     	var geojsonObject = data.response.result.featureCollection.features[0].geometry;
		     	MapView.geojsonObject = geojsonObject;
		     	
		     	var vectorSource = new ol.source.Vector({
		              features: (new ol.format.GeoJSON()).readFeatures(geojsonObject)
		        });
		        vectorSource.set("ctp_kor_nm",data.response.result.featureCollection.features[0].properties.ctp_kor_nm); 
		        vectorSource.set("ctp_eng_nm",data.response.result.featureCollection.features[0].properties.ctp_eng_nm); 
		        MapView.vectorSource = vectorSource;
		          
		          
		        var vector_layer = new ol.layer.Vector({
		        	source: vectorSource
		        })
		        vector_layer.set("ctp_kor_nm_layer",data.response.result.featureCollection.features[0].properties.ctp_kor_nm+"_layer");
		        
		           
		        MapView.map.addLayer(vector_layer);
		     }
		});
		
		var map = new ol.Map({
			target: 'map',
			layers: [
				new ol.layer.Tile({
			    	source: new ol.source.OSM()
			    })
			],
			view: new ol.View({
				center: ol.proj.transform([126.9380517322744,37.16792263658907], 'EPSG:4326', 'EPSG:900913'),
			    zoom: 5
			})
		});
		
		     
		/* 선택시 스타일 설정*/
		 var stylep = new ol.style.Style({
		        stroke: new ol.style.Stroke({
		            color: [51, 51, 51, .0],
		            width: 3
		        }),
		        fill: new ol.style.Fill({
		            color: [51, 51, 51, .7]
		        })
		  });
		 
		var selectInteraction = new ol.interaction.Select({
		    features: function (feature) {
		        return true;
		    },
		        style: [stylep]
		    });
		map.getInteractions().extend([selectInteraction]);
		
		/* 이벤트 추가 */
	    map.on("click", function(evt) {
	        var coordinate = evt.coordinate //좌표정보
	        var pixel = evt.pixel
	        var features = [];
	         
	        //선택한 픽셀정보로  feature 체크 
	        map.forEachFeatureAtPixel(pixel, function(feature, layer) {
	         
	            title = layer.getSource().get("ctp_kor_nm");
	            olId = layer.getSource().get("ctp_eng_nm");
	             
	            if(title.length > 0){
	                var overlayElement= document.createElement("div"); // 오버레이 팝업설정 
	                overlayElement.setAttribute("class", "overlayElement");
	                overlayElement.setAttribute("style", "background-color: #3399CC; border: 2px solid white; color:white");
	                overlayElement.setAttribute("onclick", "MapView.deleteOverlay('"+olId+"')");
	                //overlayElement.innerHTML="<p>"+title+"</p>";
	                 
	                var overlayInfo = new ol.Overlay({
	                    id : olId,
	                    element : overlayElement,
	                    offset: [0, -50],
	                    position: coordinate,
	                });
	                if(map.getOverlayById(olId) != null ){ MapView.deleteOverlay(olId); }
	                map.addOverlay(overlayInfo)
	            }
	        });
	    });
	    
	    MapView.map = map;
	    MapView.move( "14283776.791077042", "4163507.838217992" );
	}
	/**
    	오버레이 삭제
	 */
	static deleteOverlay (id) {
		MapView.map.removeOverlay(MapView.map.getOverlayById(id));
	}
	
	static move( x, y, zoom) {
		MapView.map.getView().setCenter(ol.proj.transform([ x, y ],'EPSG:4326', "EPSG:900913")); // 지도 이동
		MapView.map.getView().setZoom(zoom);
	}
	
};