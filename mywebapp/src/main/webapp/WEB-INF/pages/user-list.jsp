<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	
</head>
<body>

<jsp:include page="../template/header.jsp"/> 

Hello, I am now a list

<ul>
<c:forEach items="${userList}" var="user">
	<li>
	<a href="?action=detail&id=${user.firstname}">${user.firstname} ${user.lastname}</a> - 
	
	<c:choose>
		<c:when test="${user.salary < 800}">Low</c:when>
		<c:when test="${user.salary > 1800}">High</c:when>
		<c:otherwise>Medium</c:otherwise>
	</c:choose>
	 
	</li>
</c:forEach>

</ul>

<jsp:include page="../template/footer.jsp"/>

</body>
</html>