<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>

<table>
    <tbody>
        <tr>
            <th>氏名</th>
            <td><c:out value="${report.employee.name}" /></td>
        </tr>
        <tr>
            <th>日付</th>
            <td><fmt:formatDate value="${report.report_date}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <th>内容</th>
            <td>
                <pre><c:out value="${report.content}"/></pre>
            </td>
        </tr>
        <tr>
            <th>登録日時</th>
            <td>
                <fmt:formatDate value="${report.created_at}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
        </tr>
        <tr>
            <th>更新日時</th>
            <td>
                <fmt:formatDate value="${report.updated_at}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
        </tr>
    </tbody>
</table>
<br /><br/>
<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_employee.name}"/>
<br /><br />

<label for="content">コメント</label><br />
<textarea name="content" rows="5" cols="50">${feedback.content}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}"/>
<button type="submit">追加</button>