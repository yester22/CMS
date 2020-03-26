/**
 *  yyyyMMdd 포맷으로 반환
 */

class CommonUtil {

	/**
	 * 오늘 날짜 가져오는 함수
	 */
	static getToday( divChar ){
		var divChar2 = ( divChar == null ) ? "" : divChar;   
		var date = new Date();
	    var year = date.getFullYear();              //yyyy
	    var month = (1 + date.getMonth());          //M
	    month = ( month >= 10 ) ? month : '0' + month;  //month 두자리로 저장
	    var day = date.getDate();                   //d
	    day = ( day >= 10 ) ? day : '0' + day;          //day 두자리로 저장
	    
	    return  year + divChar2 + month + divChar2 + day;
	}
	
	/**
	 * 오늘 날짜 가져오는 함수
	 */
	static getPreviousDate( nDay , divChar) {
		var nowDate = new Date();
		var prevDate =  nowDate.getTime() - ( nDay * 24 * 60 * 60 * 1000 );
		nowDate.setTime(prevDate); 
		
		var year = nowDate.getFullYear();          //yyyy
	    var month = (1 + nowDate.getMonth());      //M
	    var day = nowDate.getDate();               //d
	    
	    month = ( month >= 10 ) ? month : '0' + month;  //month 두자리로 저장
	    day = ( day >= 10 ) ? day : '0' + day;          //day 두자리로 저장
	    
	    return  year + divChar + month + divChar + day;
	}
	
}