<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="MainContent">
    
    
    <form:form action="${ sessionVars.requestType }PaymentRequired.htm" method="post">
    	<div class="vcrcBox">	
		<table width="760" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><input id="cancelButton" type="button" name="action" value="Cancel" class="vcrcButton"/></td>
				<td align="right"><input type="submit" name="action" value="Next" class="vcrcButton"/></td>
			</tr>	
		</table>
		</div>
	</form:form>
	
</div>