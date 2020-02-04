<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.Date" %>

<% 
	Date date = new Date();
%>

<div class="introduction sizable"></div>
	<div id="MainContent">
    	<div class="BodyContent sizable">
    	
    		<%=date%>
    		
			<br/>
			
			<c:if test="${ ecrcUtils.getTestConfig() == true }">
				<H3>Details of the error:</H3>
			
				<P><b>Failure: </b>${exception}</P>
				
				<P><b>Stack Trace: </b></P>
				<table>
				<c:forEach items="${exception.stackTrace}" var="element">
					<tr><td>
	    				<c:out value="${element}"/>
	    			</td></tr>
				</c:forEach>
				</table>
			</c:if>
			
			<h3>
			
			<p><b>The server is temporarily unavailable, please try again at a later time or contact the Criminal Records Review Program at 1-855-587-0185 for assistance.</b></p>
			
			<%-->	
			<b>An unexpected application error occurred on <font color="red"><%=date%></font>.</b>
			</font></h3>
			<p><b>Please try again or contact the Criminal Records Review Program by phone at 1-855-587-0185 to report this error with the time the error occurred.</b></p>
			--%>
		</div>
	</div>
