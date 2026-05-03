<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<h1 class="page-title"><span class="icon">&#43;</span> Add New Book</h1>

<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger">&#10007; ${errorMessage}</div>
</c:if>

<div class="card">
    <form:form method="POST"
               action="${pageContext.request.contextPath}/books/new"
               modelAttribute="book">

        <div class="form-grid">
            <div class="form-group" style="grid-column: span 2;">
                <label for="title">Book Title *</label>
                <form:input path="title" id="title" placeholder="e.g. Pride and Prejudice" />
                <form:errors path="title" cssClass="error" />
            </div>
            <div class="form-group">
                <label for="isbn">ISBN *</label>
                <form:input path="isbn" id="isbn" placeholder="e.g. 9780141439518" />
                <form:errors path="isbn" cssClass="error" />
            </div>
            <div class="form-group">
                <label for="publicationYear">Publication Year *</label>
                <form:input path="publicationYear" id="publicationYear" type="number" placeholder="e.g. 1813" />
                <form:errors path="publicationYear" cssClass="error" />
            </div>
            <div class="form-group">
                <label for="genre">Genre *</label>
                <form:select path="genre" id="genre">
                    <form:option value="" label="-- Select Genre --" />
                    <form:option value="Adventure"          label="Adventure" />
                    <form:option value="Absurdist Fiction"  label="Absurdist Fiction" />
                    <form:option value="Classic"            label="Classic" />
                    <form:option value="Dystopian Fiction"  label="Dystopian Fiction" />
                    <form:option value="Fantasy"            label="Fantasy" />
                    <form:option value="Historical Fiction" label="Historical Fiction" />
                    <form:option value="Literary Fiction"   label="Literary Fiction" />
                    <form:option value="Magical Realism"    label="Magical Realism" />
                    <form:option value="Mystery"            label="Mystery" />
                    <form:option value="Romance"            label="Romance" />
                    <form:option value="Science Fiction"    label="Science Fiction" />
                    <form:option value="Thriller"           label="Thriller" />
                    <form:option value="Other"              label="Other" />
                </form:select>
                <form:errors path="genre" cssClass="error" />
            </div>
            <div class="form-group">
                <label for="authorId">Author *</label>
                <select name="authorId" id="authorId">
                    <option value="">-- Select Author --</option>
                    <c:forEach var="author" items="${authors}">
                        <option value="${author.id}">${author.name}</option>
                    </c:forEach>
                </select>
                <form:errors path="author" cssClass="error" />
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-success">&#10003; Save Book</button>
            <a href="${pageContext.request.contextPath}/books" class="btn btn-secondary">&#8592; Cancel</a>
        </div>
    </form:form>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
