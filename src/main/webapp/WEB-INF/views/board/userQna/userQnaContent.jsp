<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

<style>
        .ui-datepicker-calendar {
            display: none;
        }
        
     /*      .bodyArea {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
        } */
        /*전체 규격 끝*/
/* 헤더  */
  .out-table {
            margin: 0 auto;
            align-items: center;
            justify-content: center;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            
              font-family: 'Arial', sans-serif; /* Change the font family as needed */
    font-size: 24px; /* You can adjust the font size as needed */
    color: #333; /* Change the color as needed */
    font-weight: bold;
}    
/*헤더끝*/
/* 카테고리 시작*/
#bigfont {
    font-weight: bold;
    font-size: 14px; /* You can adjust the font size as needed */
    font-family: 'Poppins', sans-serif; /* Change the font family as needed for bigfont */
    border: 1px solid #ddd;
          width: 140px;
      border-right:none;
         box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
         margin-bottom:0;
         color:black;
      
}

/* Add this style for the smallfont element */
#smallfont {
    font-size: 12px; /* You can adjust the font size as needed */
    font-family: 'Quicksand', sans-serif; /* Change the font family as needed for smallfont */
            color:black;
}
 .vertical-menu {
            order: -1;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            margin-right: 20px;
            width: 100%;
            margin-top: 0;
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
          
        }

        .vertical-menu a {
            margin-bottom: 10px;
            text-align: left;
        }


        .btn {
            display: inline-block;
            padding: 8px 20px;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
 .vertical-menu a.btn {
    font-size: 14px; /* You can adjust the font size as needed */
}

/* Add this style for the lower-level menu items (sub-menu items) */
.vertical-menu a.btn + .collapse a.btn {
    font-weight: normal;
    font-size: 14px; /* You can adjust the font size as needed */
}
   .category-menu {
            width: 8%; /* Adjust the width as needed */
            height: 100%; /* Adjust the height as needed */
            position: relative;
            margin-right:0;
            left:18%;
            z-index: 2
        }



/* 카테고리 끝*/
/* 메인  시작*/
 .main-container{
   display: flex;
        justify-content: center; /* Center the children horizontally */
        align-items: flex-start; /* Align the children at the top */
        margin-top: 40px;
        margin-bottom: 50px;
 }
       .summary-table {
       margin-top:1%;
            width: 100%;
            margin-bottom:5%;
            margin-left: auto;
            margin-right: auto;
            
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            font-size: xx-small;
        }   
  .summary-table th, .summary-table td {
    font-family: 'Arial', sans-serif;
    font-size: 12px;
    border: 1px solid #ddd;
    padding: 10px;
    text-align: center;
} 
.summary-table th {
            background-color: #f2f2f2;
        }
        .summary-table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

 .graph-and-summary {
               width: 80%;
        margin: 0 auto; /* Center the element horizontally */
        text-align: center; /* Center the content inside the element */
 		margin-left:auto;
 		margin-right:auto;
 		
		position: relative;
        }




     .main-table {
            width: 50%;
            margin: 0 auto;
            margin-left:24%;
            height: 60%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
           
            
        }
        
        .main-table th {
    font-family: 'Arial', sans-serif;
    font-size: 16px;
    font-weight: bold;
    background-color: #f2f2f2;
    padding: 10px;
    text-align: center;
}
.main-table td {
    font-family: 'Arial', sans-serif;
    font-size: 14px;
    padding: 10px;
    text-align: center;
}


                
       select {
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 12px;
        margin-right: 5px;
     /*    height: 30px; */
        border:none;
    }

    /* Style for submit button */
    input[type="submit"] {
        padding: 8px 20px;
        border: none;
        border-radius: 4px;
        background-color:lightgray;
        color: #fff;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: gray;
    }
 
    
    input[type="submit"] {
        padding: 8px 20px;
        border: none;
        border-radius: 4px;
        background-color:lightgray;
        color: #fff;
        cursor: pointer;
    }
    input[type="text"] {
        border-radius: 4px;
        margin-top:3%;
    }
        
           button{
           padding: 8px 20px;
        border: none;
        border-radius: 4px;
        background-color:lightgray;
        color: #fff;
        cursor: pointer; 
           }
button:hover {
        background-color: gray;
    }
    input[type="button"]:hover {
        background-color: gray;
    }
    input[type="button"] {
        padding: 8px 20px;
        border: none;
        border-radius: 4px;
        background-color:lightgray;
        color: #fff;
        cursor: pointer;
    }
    input[type="reset"]:hover {
        background-color: gray;
    }
    input[type="reset"] {
        padding: 8px 20px;
        border: none;
        border-radius: 4px;
        background-color:lightgray;
        color: #fff;
        cursor: pointer;
    }
      


</style>
    <%@ include file="../../header.jsp" %>
    <meta charset="UTF-8">
    <title>Q&A Details</title>
</head>
<body>
    <div class="main-container" align="center" style="width: 100%;"> 
        <table class="summary-table" align="center">
                <td class="graph-and-summary" align="center">
                    <a href="/board/userQna?p_num=${content.pq_p_num}">목록으로</a> <br/>
                    <table class="content-table" width="800" border="1" cellspacing="0" cellpadding="0" align="center">
                        <tr>
                            <td width="100" align="center"><strong>문의한 사람</strong></td>
                            <td>${content.pq_m_id}</td>
                        </tr>
                        <tr>
                            <td width="100" align="center"><strong>문의 날짜</strong></td>
                            <td><fmt:formatDate value="${content.pq_reg_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <td width="100" align="center"><strong>문의 제목</strong></td>
                            <td>${content.pq_title}</td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><strong>문의 내용</strong></td>
                        </tr>
                        <td colspan="2">${content.pq_content}</td>
                    </table>

                    <br/><br/>

                    <c:if test="${answerCount < 2 && p_id eq id}">
                        <form method="post" action="userQnaContentPro">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <input type="hidden" name="pq_p_num" value="${content.pq_p_num}">
                            <input type="hidden" name="pq_num" value="${content.pq_num}">
                            <input type="hidden" name="pq_ref" value="${content.pq_ref}">
                            <input type="hidden" name="pq_status" value="${content.pq_status}">

                            <table class="reply-table" border="1" cellspacing="0" cellpadding="0" align="center">
                                <tr>
                                    <td ><strong>답글 달기</strong></td>
                                    <td>
                                        <textarea rows="7" cols="70" name="pq_content" required="required"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"><input type="submit" value="답글 달기"> </td>
                                </tr>
                            </table>
                        </form>
                    </c:if>

                    <br/><br/>

                    <c:if test="${cuserContent.pq_status >= 1}">
                        <table class="user-content-table" align="center">
                            <tr>
                                <td><strong>${cuserContent.pq_title}</strong></td>
                            </tr>
                            <tr>
                                <td>${cuserContent.pq_content}</td>
                            </tr>
                        </table>
                    </c:if>

                </td>
        	</table>
    </div>

    <%@ include file="../../footer.jsp" %>
</body>
</html>
