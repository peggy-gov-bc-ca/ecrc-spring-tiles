<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<script type="text/javascript">
	<c:forEach items="${highlightFields}" var="item" varStatus="loop">
    highlightArray[${loop.index}] = "${item}";
	</c:forEach>
</script>

<body>

<form:form action="correction_test.htm" method="post">
<p>Correction Test Page</p><br/>
<p><input type="submit" value="Start Correction Test" /></p>
<p><label for="myField">MyField: </label><input type="text" id="myField" name="myField" size="25"></p>
</form:form>
</body>
</html>