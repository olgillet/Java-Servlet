<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
</head>
<body>
<jsp:include page="../template/header.jsp"/> 

User Add page<br/>

<c:url var="postUserUrl" value="/userdetail"></c:url>

<ul>
	<li>PageScope : ${pageScope.postUserUrl}</li>
	<li>RequestScope : ${requestScope.postUserUrl}</li>
	<li>SessionScope : ${sessionScope.postUserUrl}</li>
	<li>ApplicationScope : ${applicationScope.postUserUrl}</li>
</ul>

<form action="${postUserUrl}" method="post">
	Last name : <input type="text" name="lastname"><br/>
	First name : <input type="text" name="firstname"><br/>
	Salary : <input type="number" name="salary"><br/>
	<button type="submit">OK</button>
</form>

<jsp:include page="../template/footer.jsp"/> 
</body>
</html>