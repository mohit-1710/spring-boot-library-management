<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${not empty pageTitle ? pageTitle.concat(' | ') : ''}Library ms</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
    <div class="header-inner">
        <a class="brand" href="${pageContext.request.contextPath}/">
            <span>Library</span> ms
        </a>
        <nav>
            <a href="${pageContext.request.contextPath}/">Home</a>
            <a href="${pageContext.request.contextPath}/authors">Authors</a>
            <a href="${pageContext.request.contextPath}/authors/new">+ Author</a>
            <a href="${pageContext.request.contextPath}/books">Books</a>
            <a href="${pageContext.request.contextPath}/books/new">+ Book</a>
            <a href="${pageContext.request.contextPath}/books/report">Join Report</a>
        </nav>
    </div>
</header>
<div class="container">
