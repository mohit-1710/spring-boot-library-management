<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<h1 class="page-title"><span class="icon">&#9998;</span> Edit Author</h1>

<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger">&#10007; ${errorMessage}</div>
</c:if>

<div class="card">
    <form:form method="POST"
               action="${pageContext.request.contextPath}/authors/edit/${author.id}"
               modelAttribute="author">

        <div class="form-grid">
            <div class="form-group">
                <label for="name">Full Name *</label>
                <form:input path="name" id="name" />
                <form:errors path="name" cssClass="error" />
            </div>
            <div class="form-group">
                <label for="email">Email Address *</label>
                <form:input path="email" id="email" type="email" />
                <form:errors path="email" cssClass="error" />
            </div>
            <div class="form-group">
                <label for="birthYear">Birth Year</label>
                <form:input path="birthYear" id="birthYear" type="number" />
                <form:errors path="birthYear" cssClass="error" />
            </div>
            <div class="form-group">
                <label for="nationality">Nationality *</label>
                <form:input path="nationality" id="nationality" />
                <form:errors path="nationality" cssClass="error" />
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">&#10003; Update Author</button>
            <a href="${pageContext.request.contextPath}/authors" class="btn btn-secondary">&#8592; Cancel</a>
        </div>
    </form:form>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
