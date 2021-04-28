<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>コメント  一覧</h2>
        <table>
            <tbody>
                <tr>
                    <th class="comment_name">氏名</th>
                    <th class="comment">コメント</th>
                    <th class="comment_action">操作</th>
                </tr>
                <c:forEach var="feedback" items="${feedback}" varStatus="status">
                    <tr>
                        <td class="comment_name"><c:out value="${feedback.comment_employee.name}" /></td>
                        <td class="comment"><c:out value="${feedback.content}"/></td>
                        <td class="comment_action"><a href="<c:url value='/feedback/show?id=${feedback.id}'/>">コメント詳細</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="<c:url value="/feedback/new?id=${report_id.id}"/>">コメント追加</a>
        <p><a href="<c:url value="/reports/show?id=${report_id.id}"/>">日報詳細に戻る</a></p>
    </c:param>
</c:import>