<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>フォロー  一覧</h2>
        <table id="follow_list">
            <tbody>
                <tr>
                    <th class="follow_id">社員番号</th>
                    <th class="follow_name">氏名</th>
                    <th class="follow_report">日報</th>
                </tr>
                <c:forEach var="follow" items="${follows}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="follow_id"><c:out value="${follow.follow_employee.code}"/></td>
                        <td class="follow_name"><c:out value="${follow.follow_employee.name}"></c:out></td>
                        <td class="follow_report"><a href="<c:url value='/follows/show?=${follow.id}'/>">日報へ</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            (全 ${follows_count} 件)<br />
            <c:forEach var="i" begin="1" end="${((follows_count -1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/follows/index?page=${i}'/>"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>