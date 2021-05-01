<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${feedback != null}">
                <h2>コメント 詳細ページ</h2>

                <c:import url="_form.jsp"/>
                <table>
                    <tbody>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${feedback.comment_employee.name}"/></td>
                        </tr>
                        <tr>
                            <th>コメント内容</th>
                            <td>
                                <pre><c:out value="${feedback.content}"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${feedback.created_at}" pattern="yyyy-MM-dd:mm:ss"/>
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${feedback.updated_at}" pattern="yyyy-MM-dd:mm:ss"/>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_employee.id == feedback.comment_employee.id}">
                    <p><a href="<c:url value="/feedback/edit?id=${feedback.id}"/>">このコメントを編集する</a></p>
                </c:if>
                <p><a href="<c:url value="/feedback/index?id=${feedback.report_id.id}"/>">コメント一覧に戻る</a></p>
        </c:when>
        <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>