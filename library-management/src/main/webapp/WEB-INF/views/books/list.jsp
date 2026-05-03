<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<h1 class="page-title"><span class="icon">&#128218;</span> Books</h1>

<c:if test="${not empty successMessage}">
    <div class="alert alert-success">&#10003; ${successMessage}</div>
</c:if>

<div class="card">
    <div class="card-header">
        <span class="card-title">All Books (${books.size()} records)</span>
        <div style="display:flex;gap:.5rem;">
            <a href="${pageContext.request.contextPath}/books/report" class="btn btn-info">
                &#128202; Join Report
            </a>
            <a href="${pageContext.request.contextPath}/books/new" class="btn btn-primary">
                &#43; Add New Book
            </a>
        </div>
    </div>
    <div class="table-wrapper">
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Title</th>
                    <th>ISBN</th>
                    <th>Year</th>
                    <th>Genre</th>
                    <th>Author</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty books}">
                        <tr><td colspan="7" style="text-align:center;padding:2rem;color:#999;">No books found.</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="book" items="${books}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td><strong>${book.title}</strong></td>
                                <td><code>${book.isbn}</code></td>
                                <td>${book.publicationYear}</td>
                                <td><span class="badge badge-genre">${book.genre}</span></td>
                                <td>${book.author.name}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/books/edit/${book.id}"
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
