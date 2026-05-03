<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="error-box">
    <div class="err-icon">&#9888;</div>
    <h2>${not empty errorTitle ? errorTitle : 'Error'}</h2>
    <p>${not empty errorMessage ? errorMessage : 'Something went wrong. Please try again.'}</p>
    <a href="${pageContext.request.contextPath}/" class="btn btn-primary">&#8592; Back to Home</a>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
