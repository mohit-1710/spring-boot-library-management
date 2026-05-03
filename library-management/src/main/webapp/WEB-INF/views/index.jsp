<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<h1 class="page-title">Dashboard</h1>

<c:if test="${not empty successMessage}">
    <div class="alert alert-success">&#10003; ${successMessage}</div>
</c:if>

<!-- Stats -->
<div class="stats-grid">
    <div class="stat-card">
        <div class="stat-number">${authorCount}</div>
        <div class="stat-label">Authors in Library</div>
    </div>
    <div class="stat-card">
        <div class="stat-number">${bookCount}</div>
        <div class="stat-label">Books in Collection</div>
    </div>
</div>

<!-- Quick Links -->
<div class="card">
    <div class="card-header">
        <span class="card-title">Quick Actions</span>
    </div>
    <div class="quick-links">
        <a class="quick-link-card" href="${pageContext.request.contextPath}/authors">
            <div class="ql-label">View All Authors</div>
            <div class="ql-desc">Browse the full author list</div>
        </a>
        <a class="quick-link-card" href="${pageContext.request.contextPath}/authors/new">
            <div class="ql-label">Add Author</div>
            <div class="ql-desc">Register a new author</div>
        </a>
        <a class="quick-link-card" href="${pageContext.request.contextPath}/books">
            <div class="ql-label">View All Books</div>
            <div class="ql-desc">Browse the full book catalogue</div>
        </a>
        <a class="quick-link-card" href="${pageContext.request.contextPath}/books/new">
            <div class="ql-label">Add Book</div>
            <div class="ql-desc">Add a new book to the library</div>
        </a>
        <a class="quick-link-card" href="${pageContext.request.contextPath}/books/report">
            <div class="ql-label">Books &amp; Authors Report</div>
            <div class="ql-desc">Inner join across both entities</div>
        </a>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
