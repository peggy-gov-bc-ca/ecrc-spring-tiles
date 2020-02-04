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
	
<p>Assessment Complete</p> 
<p>Score: ${ resultResponse.score}: RiskStrategyDec: ${resultResponse.riskStrategyDecision}: Atomic Score: ${resultResponse.interactiveScore} </p>
<p>Reason Codes: ${resultResponse.reasonCodes} </p>
<p>Descriptions:</p>
<ul>
<c:forEach var="items" items="${equifaxUtils.GetCSVasList(resultResponse.reasonCodeDescriptions) }">       
     <li><c:out value="${items}" /></li>       
    </c:forEach>
 </ul>
<br/>
<input type="button" id="reset" value="Reset Test"/>
<br/>