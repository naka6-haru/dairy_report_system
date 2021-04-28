<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>${report.employee.name}さん 新規コメント追加ページ</h2>

        <form method ="post" action="<c:url value='/feedback/create?id=${report.id}'/>">
            <c:import url="_form.jsp"/>

            <label for="name">氏名</label><br />
            <c:out value="${sessionScope.login_employee.name}"/>
            <br /><br />

            <label for="content">コメント</label><br />
            <textarea name="content" rows="5" cols="50">${feedback.content}</textarea>
            <br /><br />

            <input type="hidden" name="_token" value="${_token}"/>
            <button type="submit">追加</button>
        </form>

        <p><a href="<c:url value='/reports/show?id=${report.id}'/>">日報詳細に戻る</a></p>
    </c:param>
</c:import>
