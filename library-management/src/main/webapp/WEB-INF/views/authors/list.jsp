<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<h1 class="page-title"><span class="icon">&#128100;</span> Authors</h1>

<c:if test="${not empty successMessage}">
    <div class="alert alert-success">&#10003; ${successMessage}</div>
</c:if>

<div class="card">
    <div class="card-header">
        <span class="card-title">All Authors (${authors.size()} records)</span>
        <a href="${pageContext.request.contextPath}/authors/new" class="btn btn-primary">
            &#43; Add New Author
        </a>
    </div>
    <div class="table-wrapper">
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Birth Year</th>
                    <th>Nationality</th>
                    <th>Books</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty authors}">
                        <tr><td colspan="7" style="text-align:center;padding:2rem;color:#999;">No authors found.</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="author" items="${authors}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td><strong>${author.name}</strong></td>
                                <td>${author.email}</td>
                                <td>${author.birthYear}</td>
                                <td><span class="badge badge-nation">${author.nationality}</span></td>
                                <td>${author.books.size()}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/authors/edit/${author.id}"
                                       class="btn btn-warning btn-sm">&#9998; Edit</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
