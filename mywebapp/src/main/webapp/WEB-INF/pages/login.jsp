<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
</head>
<body>
<jsp:include page="../template/header.jsp"/>

<h1>Login</h1>

<c:if test="${sessionScope.loggedEmail != null}">
	User ${sessionScope.loggedEmail} is already logged
	
	<form action="${postUserUrl}?action=logout" method="post">
		<button type="submit">Logout</button>
	</form>
	
	<c:if test="${loginList != null}">
		List of login's email : <br/>
		<ul>
		<c:forEach items="${loginList}" var="login">
			<li>${login.email} (${login.password})  <a href="?action=remove&id=${login.email}">Remove</a></li>
		</c:forEach>
		</ul>
	</c:if>
</c:if>

<c:if test="${sessionScope.loggedEmail == null}">
	<form name="login" action="${postUserUrl}" method="post">
		Email : <input type="text" name="email">
		Password : <input type="text" name="password">
		<button type="submit">OK</button>
	</form>
	
	Or Create
	<form name="create" action="${postUserUrl}?action=create" method="post">
		Email : <input type="text" name="email">
		Password : <input type="text" name="password">
		<button type="submit">Create</button>
	</form>
</c:if>



<jsp:include page="../template/footer.jsp"/>
</body>
</html>