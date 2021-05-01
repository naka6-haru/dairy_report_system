<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${feedback != null}">
                <h2>コメント  編集ページ</h2>

                 <c:import url="_form.jsp"/>
                <form method="post" action="<c:url value='/feedback/update?id=${feedback.id}'/>">
                    <label for="name">氏名</label><br />
                    <c:out value="${sessionScope.login_employee.name}"/>
                    <br /><br />

                    <label for="content">コメント</label><br />
                    <textarea name="content" rows="5" cols="50">${feedback.content}</textarea>
                    <br /><br />

                    <input type="hidden" name="_token" value="${_token}"/>
                    <button type="submit">追加</button>
                </form>
                <p><a href="#" onclick="confirmDestroy();">このコメントを削除する</a></p>
                <form method="post" action="<c:url value='/feedback/destroy?id=${feedback.id}'/>">
                    <input type="hidden" name="_token" value="${_token}" />
                </form>
                <p><a href="<c:url value='/feedback/index?id=${feedback.report_id.id}'/>">コメント一覧に戻る</a></p>
                <script>
                    function confirmDestroy(){
                        if(confirm("本当に削除してよろしいですか？")){
                            document.forms[1].submit();
                        }
                    }
                </script>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>