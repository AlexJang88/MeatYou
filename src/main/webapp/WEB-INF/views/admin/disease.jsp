<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<div id="view">
	
</div>
<input type="hidden" id="key" value="${key}">
<input type="date" id="checkdate" />
<button type="button" id="check">조회</button>

<script>
$('#check').click(function(){
	var key = $('#key').val();
	var check = $('#checkdate').val();
	if(check=='' || check==null){
		var today = new Date();
	    var year = today.getFullYear();
	    var month = today.getMonth() + 1; 
	    var day = today.getDate();
	
	    if (month < 10) {
	        month = '0' + month;
	    }
	    if (day < 10) {
	        day = '0' + day;
	    }
	    var getdate = '' + year + month + day;
	}else{
		console.log(check);
		var dateParts = check.split('-'); // 날짜를 '-'로 구분하여 배열로 분리
		var getdate = dateParts.join('');
	}
    console.log(getdate);
	console.log(key);
	$.ajax({
		  url:"https://cors-anywhere.herokuapp.com/http://211.237.50.150:7080/openapi/"+key+"/json/Grid_20151204000000000316_1/1/5?OCCRRNC_DE="+getdate+"",
		  type: "GET",
		  contentType: "application/json , charset:UTF-8",
		  dataType: "JSON",
		  success: function(result) {
		      console.log(result);
		      if(result.Grid_20151204000000000316_1.row && result.Grid_20151204000000000316_1.row.length > 0) {
		    	 
		    	  result.Grid_20151204000000000316_1.row.forEach(function(item) {
		    		  
	                	// Add more attributes as needed
	                    if(item.LVSTCKSPC_NM.includes("소")||item.LVSTCKSPC_NM.includes("돼지")){
	                    	var d1=item.ICTSD_OCCRRNC_NO
	                    	var d2= item.LKNTS_NM
	                    	var d3= item.FARM_NM
	                    	var d4= item.FARM_LOCPLC_LEGALDONG_CODE
	                    	var d5= item.FARM_LOCPLC
	                    	var d6= item.OCCRRNC_DE
	                    	var d7= item.LVSTCKSPC_CODE
	                    	var d8= item.LVSTCKSPC_NM
	                    	var d9= item.OCCRRNC_LVSTCKCNT
	                    	var d10= item.DGNSS_ENGN_CODE
	                    	var d11= item.DGNSS_ENGN_NM
	                    	var d12= item.CESSATION_DE
	                    	update(d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12);
	                    	
	                    	console.log("ICTSD_OCCRRNC_NO: " + item.ICTSD_OCCRRNC_NO);
		                    console.log("LKNTS_NM: " + item.LKNTS_NM);
		                    console.log("FARM_NM: " + item.FARM_NM);
		                    console.log("FARM_LOCPLC_LEGALDONG_CODE: " + item.FARM_LOCPLC_LEGALDONG_CODE);
		                    console.log("FARM_LOCPLC :"+item.FARM_LOCPLC);
		                    console.log("OCCRRNC_DE: " + item.OCCRRNC_DE);
		                    console.log("LVSTCKSPC_CODE: " + item.LVSTCKSPC_CODE);
		                    console.log("LVSTCKSPC_NM: " + item.LVSTCKSPC_NM);
		                    console.log("OCCRRNC_LVSTCKCNT: " + item.OCCRRNC_LVSTCKCNT);
		                    console.log("DGNSS_ENGN_CODE: " + item.DGNSS_ENGN_CODE);
		                    console.log("DGNSS_ENGN_NM: " + item.DGNSS_ENGN_NM);
		                    console.log("CESSATION_DE: " + item.CESSATION_DE);
	                    }
	                });
		  	}
		  },
		  error: function() {
		  }
		});
	});
 function update(d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12){
	 console.log("data"+d1)
	 console.log("data"+d2)
	 console.log("data"+d3)
	 console.log("data"+d4)
	 console.log("data"+d5)
	 console.log("data"+d6)
	 console.log("data"+d7)
	 console.log("data"+d8)
	 console.log("data"+d9)
	 console.log("data"+d10)
	 console.log("data"+d11)
	 console.log("data"+d12)
	 
	 $.ajax({
		  url: "/admin/dapi?ICTSD_OCCRRNC_NO="+d1+"&LKNTS_NM="+d2+"&FARM_NM="+d3+"&FARM_LOCPLC_LEGALDONG_CODE="+d4+"&FARM_LOCPLC="+d5+"&OCCRRNC_DE="+d6+"&LVSTCKSPC_CODE="+d7+"&LVSTCKSPC_NM="+d8+"&OCCRRNC_LVSTCKCNT="+9+"&DGNSS_ENGN_CODE="+d10+"&DGNSS_ENGN_NM="+d11+"&CESSATION_DE="+d12+"",
		  type: "get",
		  contentType: "application/json , charset:UTF-8",
		  dataType: "JSON",
		  success: function(result) {
		      console.log(result);
		  },
		  error: function(result) {
		  }
		});
	}; 
</script>