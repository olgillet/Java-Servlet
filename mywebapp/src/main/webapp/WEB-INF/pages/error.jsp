<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
</head>
<body>
<jsp:include page="../template/header.jsp"/>
You reached the Error page. Sorry.
<br/>
<br/>

<br/>
<br/>
<br/>
<a href="<%=request.getContextPath()%>/userdetail?action=list">Go on list</a><br/>
<a href="${pageContext.request.contextPath}/login?action=login">Go on Login</a>

<jsp:include page="../template/footer.jsp"/>
</body>
</html>