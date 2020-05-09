/***
 * 메세지 박스 유틸
**/
var msgBox = {

		/*
		* path : 전송 URL
		* params : 전송 데이터 {‘q’:’a’,’s’:’b’,’c’: ‘d’…}으로 묶어서 배열 입력
		* method : 전송 방식(생략가능)
		*/
		confirm : function( title,  Message, callback  ) {
			
			var rtnValue = false;
			var confirmMsg = "OK";
			var cancelMsg  = "CANCEL";
			
			var $div = $('<div></div>');
			$div.attr("id", 'MSG_CONFIRM');
			$div.attr("title", title);
			$div.appendTo('body');
			
			var msg = '<p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>';
			msg += Message + '</p>';
			$( "#MSG_CONFIRM" ).html ( msg );
			
			$( "#MSG_CONFIRM" ).dialog({
				resizable: false,
				//autoOpen : false,
			    height: "auto",
			    width: 400,
			    modal: true,
			    buttons: [{
			    	text : confirmMsg ,
			    	"id" : "btnOk" ,
			    	click : function() {
			    		 $( this ).dialog( "close" );
			    		callback();
			       }
			    }, 
			    {
			    	text : cancelMsg ,
				    "id" : "btnCancel" ,   
			       
				    click : function() {
			          $( this ).dialog( "close" );
			        }
			    }]
			});
			return rtnValue;
		},
		
		
		/**
		 * 
		 * alert 박스 띄우기
		 * 
		 */
		alert : function ( msg ) {
			
			//document.body.innerHTML = document.body.innerHTML + '<div id="DIV_ABSTRACT_MESSAGE"><span id="SPAN_ABSTRACT_TITLE"></span><span id="SPAN_ABSTRACT_MESSAGE"></span></div>';

			var Title = Message.getMsg ('CFM0000001');
			var alertIcon = '&nbsp;';
			$("#SPAN_ABSTRACT_MESSAGE" ).html ( alertIcon +  msg );
			$(".ui-dialog-title").html ( Title );
			
			
			var clientHeight  = document.documentElement.clientHeight;
			var clientWidth  = document.documentElement.clientWidth;
			
			var centerStartY = ( document.documentElement.clientHeight / 2 ) - 100;
			var centerStartX = ( document.documentElement.clientWidth / 2 )  - 200;
			
			$( "#DIV_ABSTRACT_MESSAGE" ).dialog({
				resizable: false,
				autoOpen : true,
				title : Title,
			    height: "auto",
			    width: 400,
			    dialogClass: "innerPopup modal",
			    modal: true,
			    //position : [centerStartX, centerStartY], 
			    position : { collison:'none', my:'center', at:'center'}, 
			    buttons: [{
			    	text : "확인" ,
			    	"id" : "btnOk" ,
			    	click : function() {
			    		$( this ).dialog( "close" );
			       }
			    }]
			});
		}	
}
