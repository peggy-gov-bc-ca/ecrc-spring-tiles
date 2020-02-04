<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
$(document).ready(function() {

  $('#reset').click(function() {
 	    window.document.location = "${pageContext.request.contextPath}/test/eiv_test.htm";
  });
  
});
</script>

<div id="MainContent">

<P>Test cancelled by User</P>
<br/>
	<input type="button" id="reset" value="Reset Test"/>
<br/>
	
</div>

