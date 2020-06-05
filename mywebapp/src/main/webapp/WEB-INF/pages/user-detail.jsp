<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
</head>
<body>
<jsp:include page="../template/header.jsp"/> 
<!-- OK show user and my name is ${name} -->
<br/>
My user name is ${user.firstname} (${user.getFirstname()})

<br/>
<br/>
<br/>
<a href="?action=list">&lt;-- Back on list</a>

<jsp:include page="../template/footer.jsp"/> 
</body>
</html>