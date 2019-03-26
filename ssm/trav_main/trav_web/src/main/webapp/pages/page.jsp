<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="box-footer">
    <div class="pull-left">
        <div class="form-group form-inline">
            总共${pageInfo.pages} 页，共${pageInfo.total} 条数据。
            每页
            <select class="form-control" onchange="gotoPage(1,this.value)">
                <c:forEach var="num" begin="1" end="10">
                    <option value="${num}" ${pageInfo.pageSize==num?'selected':''}>${num}</option>
                </c:forEach>
            </select>
            条
        </div>
    </div>

    <div class="box-tools pull-right">
        <ul class="pagination">
            <li><a href="javascript:gotoPage(1,'${pageInfo.pageSize}')" aria-label="Previous">首页</a></li>
            <li><a href="javascript:gotoPage(${pageInfo.prePage},${pageInfo.pageSize})">上一页</a></li>

            <c:forEach var="num"
                       begin="${pageInfo.pageNum-5>0?(pageInfo.pageNum-5):1}"
                       end="${pageInfo.pageNum+5<pageInfo.pages?(pageInfo.pageNum+5):pageInfo.pages}">
                <li>
                    <a href="javascript:gotoPage(${num},${pageInfo.pageSize})">
                        <c:choose>
                            <c:when test="${num == pageInfo.pageNum}">
                                <span style="color: red">${num}</span>
                            </c:when>
                            <c:otherwise>${num}</c:otherwise>
                        </c:choose>
                    </a>
                </li>
            </c:forEach>

            <li><a href="javascript:gotoPage(${pageInfo.nextPage},${pageInfo.pageSize})">下一页</a></li>
            <li><a href="javascript:gotoPage(${pageInfo.pages},${pageInfo.pageSize})" aria-label="Next">尾页</a></li>
        </ul>
    </div>

</div>

<script type="text/javascript">
    // 分页的函数
    function gotoPage(pageNum,pageSize){
        // 给表单隐藏域赋值
        $("#pageNum").val(pageNum);
        $("#pageSize").val(pageSize);
        // 提交表单
        // document.forms[0] 获取页面的第一个表单
        document.forms[0].submit();
    }
</script>