<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../../header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">장바구니 수정</h1>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">
        장바구니 수정
    </div>

    <div class="panel-body">
        <form action="/shoppingCart/modify" method="post">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th>NO</th>
                        <th>상품 내용</th>
                        <th>상품 수량</th>
                        <th>상태</th>
                        <th>금액</th>
                        <th>체크</th>
                    </tr>
                </thead>
			  <c:forEach var = "cartItem" items = "${dto}">
			        <tr>
			            <td><c:out value="${cartItem.shop_num}" /></td>
			            <td><c:out value="${cartItem.shop_p_num}" /></td>
			            <td>
			                <input type="number" name="quantity" value="${cartItem.quantity}" />
			                <!-- 여러 아이템을 수정할 경우, 각각의 아이템을 식별할 수 있는 값들을 hidden으로 전송 -->
			                <input type="hidden" name="shop_num" value="${cartItem.shop_num}" />
			                <input type="hidden" name="shop_m_id" value="${cartItem.shop_m_id}" />
			                <input type="hidden" name="shop_p_num" value="${cartItem.shop_p_num}" />
			            </td>
			            <td>
			                <!-- 수정 버튼 클릭 시 폼을 서버로 전송하는 동작을 추가 -->
			                <button type="submit" class="btn btn-primary">수정</button>
			            </td>
			            <td>상태</td>
			            <td>   <input type="hidden" name="shop_p_num" value="${cartItem.shop_p_num}" /></td>
			            <td>체크박스</td>
			        </tr>
			    </c:forEach>
            </table>
        </form>

        <!-- 아래는 검색과 페이지네이션 부분 수정 -->
        <div class='row'>
            <div class="col-lg-12">
                <form id='searchForm' action="/member/shoppingCartForm" method='get'>
                    <select name='type'>
                        <!-- 이 부분은 검색 옵션을 선택할 수 있도록 하는 부분입니다. -->
                        <option value="" <c:if test="${pageMaker.cri.type == null}">selected</c:if>>--</option>
                        <option value="T" <c:if test="${pageMaker.cri.type eq 'T'}">selected</c:if>>제목</option>
                        <option value="C" <c:if test="${pageMaker.cri.type eq 'C'}">selected</c:if>>내용</option>
                        <option value="W" <c:if test="${pageMaker.cri.type eq 'W'}">selected</c:if>>작성자</option>
                        <option value="TC" <c:if test="${pageMaker.cri.type eq 'TC'}">selected</c:if>>제목 or 내용</option>
                        <option value="TW" <c:if test="${pageMaker.cri.type eq 'TW'}">selected</c:if>>제목 or 작성자</option>
                        <option value="TWC" <c:if test="${pageMaker.cri.type eq 'TWC'}">selected</c:if>>제목 or 내용 or 작성자</option>
                    </select>
                    <input type='text' name='keyword' value='<c:out value="${pageMaker.cri.keyword}"/>' />
                    <input type='hidden' name='pageNum' value='<c:out value="${pageMaker.cri.pageNum}"/>' />
                    <input type='hidden' name='amount' value='<c:out value="${pageMaker.cri.amount}"/>' />
                    <button class='btn btn-default'>Search</button>
                </form>
            </div>
        </div>

        <!-- 페이지네이션 부분 -->
        <div class='pull-right'>
            <ul class="pagination">
                <c:if test="${pageMaker.prev}">
                    <li class="paginate_button previous"><a href="${pageMaker.startPage - 1}">Previous</a></li>
                </c:if>
                <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                    <li class="paginate_button ${pageMaker.cri.pageNum == num ? "active":""}">
                        <a href="${num}">${num}</a>
                    </li>
                </c:forEach>
                <c:if test="${pageMaker.next}">
                    <li class="paginate_button next"><a href="${pageMaker.endPage + 1}">Next</a></li>
                </c:if>
            </ul>
        </div>
        <!-- Pagination End -->
    </div>

    <form id='actionForm' action="/member/shoppingCart" method='get'>
        <input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
        <input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
        <input type='hidden' name='type' value='<c:out value="${pageMaker.cri.type }"/>'>
        <input type='hidden' name='keyword' value='<c:out value="${pageMaker.cri.keyword }"/>'>
    </form>

    <!-- Modal 추가 -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                </div>
                <div class="modal-body">처리가 완료되었습니다.</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->

</div>

<script type="text/javascript">
    $(document).ready(function() {
        var result = '<c:out value="${result}"/>';
        checkModal(result);
        history.replaceState({}, null, null);

        function checkModal(result) {
            if (result === '' || history.state) {
                return;
            }
            if (parseInt(result) > 0) {
                $(".modal-body").html("게시글 " + parseInt(result) + " 번이 등록되었습니다.");
            }
            $("#myModal").modal("show");
        }

        $("#regBtn").on("click", function() {
            self.location = "/board/register";
        });

        var actionForm = $("#actionForm");

        $(".paginate_button a").on("click", function(e) {
            e.preventDefault();
            console.log('click');
            actionForm.find("input[name='pageNum']").val($(this).attr("href"));
            actionForm.submit();
        });

        $(".move").on("click", function(e) {
            e.preventDefault();
            actionForm.append("<input type='hidden' name='bno' value='" + $(this).attr("href") + "'>");
            actionForm.attr("action", "/board/get");
            actionForm.submit();
        });

        var searchForm = $("#searchForm");

        $("#searchForm button").on("click", function(e) {
            if (!searchForm.find("option:selected").val()) {
                alert("검색종류를 선택하세요");
                return false;
            }
            if (!searchForm.find("input[name='keyword']").val()) {
                alert("키워드를 입력하세요");
                return false;
            }
            searchForm.find("input[name='pageNum']").val("1");
            e.preventDefault();
            searchForm.submit();
        });
    });
</script>

<%@ include file="../../footer.jsp" %>
