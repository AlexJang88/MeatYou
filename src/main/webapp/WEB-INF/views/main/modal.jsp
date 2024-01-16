<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>모달창연습</title> 
    <style> 
        .modal{
            position:absolute;
            display:none;
            
            justify-content: center;
            top:0;
            left:0;

            width:100%;
            height:100%;

            background-color: rgba(0,0,0,0.4);
        }
    </style>
</head>
<body>
    <div class="modal">
        <div class="modal_body">
            <h2>모달창 제목</h2>
            <p>모달창 내용 </p>
        </div>
    </div>
    <button class="btn-open-modal">Modal열기</button>
    <script>
        const modal = document.querySelector('.modal');
        const btnOpenModal=document.querySelector('.btn-open-modal');

        btnOpenModal.addEventListener("click", ()=>{
            modal.style.display="flex";
        });
    </script>
</body>