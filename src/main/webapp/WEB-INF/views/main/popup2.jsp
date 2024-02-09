<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>POPUP TEST</title>
<script type="text/javascript">
	function openPopUp(p_num,p_m_id) {
		popupWindow = window.open("reviewVeiw?p_num="+p_num+"&p_m_id="+p_m_id, "mypopup", "width=450, height=250, top=150, left=200");
		popupWindow.resizeTo(500, 500);
		popupWindow.onresize = (_=>{
		popupWindow.resizeTo(700,900);
		})
	}
																	
	function showHidden() {
		alert(document.testForm.flag.value);
	}
</script>
</head>
<body>
</body>
</html>