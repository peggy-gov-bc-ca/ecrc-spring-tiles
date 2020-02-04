<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
    $(function(){
        $('#captchaImg').click(function () { $(this).attr('src', '${pageContext.request.contextPath}/captcha.htm?' + Math.floor(Math.random()*100) ); })
    });
</script>

<div id="MainContent">
	
	<br/>
	<div class="introduction sizable" style="text-decoration: underline;"><H3>PLEASE DO NOT USE YOUR BACK BUTTON ON YOUR INTERNET BROWSER</H3></div>
	
	<div class="introduction sizable" style="text-decoration: underline;"><H3>ALWAYS USE THE NAVIGATION BUTTONS AS INDICATED IN THE APPLICATION</h3></div>
	<br/>
	
	<div class="introduction sizable"><b>Request a New Criminal Record Check</b></div>

   	<div class="BodyContent sizable">
       	<p>To Submit an online request for a criminal record check, you must:</p>
		<ul>
			<li>
				Be at least 12 years of age as of today's date. 
			</li>
			<li>
				Have your identity verified through the Electronic Identity Verification <b>(EIV)</b> process.<br/>
				<span style="color: red;">Please Note:</span>  Not all individuals will be able to use the EIV process and may be required to submit the request for a criminal record check through the manual paper process.
			</li>
		</ul>
		
		<p>To use the EIV:</p>
		<ul>
			<li>
				Individuals must have a minimum Canadian credit history of at least six months. 
			</li>
			<li>
				Individuals must have been residing in Canada for two years or longer. 
			</li>
			<li>
				Individuals must correctly answer a set of security questions unique to their personal credit history.
			</li>
			<li>
				Must have a current Canadian address. (Please do not enter a foreign address through the on-line service) 
			</li>
		</ul>

   </div>

	<br/>

    <div class="introduction sizable"><b>Share the Result of a Completed Criminal Record Check</b></div>

	<div class="BodyContent sizable">
		<p>To Submit an online request to share the result of a completed criminal record check:
		<ul>
			<li>
				The criminal record check must have been completed within the last 5 years through the Ministry of Justice Criminal Records Review Program.
				Note: A criminal record check is considered complete once the result of the check has been issued to an organization  
			</li>
			<li>
				The request must be for the same type of check as previously completed, either for children, vulnerable adults, or both children and vulnerable adults
			</li>
			<li>
				Have your identity verified through the Electronic Identity Verification (EIV) process.<br/>
				<span style="color: red;">Please Note:</span>  Not all individuals will be able to use the EIV process and may be required to submit the request for a criminal record check through the manual paper process.
			</li>
		</ul>
		
		<p>To use the EIV: <b>Please see requirements outlined above.</b></p>
		
	</div>	
	<br/>
	
	<form:form commandName="accessForm" action="home.htm" method="post">	
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="40%" valign="top">
					Enter the access code provided by your organization. An access code is required to proceed with the online submission:
				</td>
				<td width="60%" style="margin-left: 20px" valign="top">
					<form:input path="accessCode" /><font color="red">*</font><br/>
					<form:errors path="accessCode" cssClass="vcrcError"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<br/><br/>
				</td>
			</tr>

			<tr>
				<td colspan="2">
					<img id="captchaImg" style="display:block;margin-left:auto;margin-right:auto" src="${pageContext.request.contextPath}/captcha.htm"><br/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<p style="text-align:center;font-size: xx-small">If you cannot read the text above, click on the image to regenerate the text.</p><br/>
				</td>
			</tr>
			<tr>
				<td>
					Please type in the characters shown above.
				</td>
				<td>
					<form:input path="captchaTextEntered" /><font color="red">*</font><br/>
					<form:errors path="captchaTextEntered" cssClass="vcrcError"/>
				</td>
			</tr> 
		</table>
		
		<br/>
		<br/>
		
		<div class="vcrcBox">
		<div style="display:inline-block;">
		
			<input type="submit" name="action" class="vcrcButton" value="Request a New Criminal Record Check">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" name="action" class="vcrcButton" value="Share the result of a Completed Criminal Record Check">
			
		</div>
		</div>
		
	</form:form>
	
	<div class="vcrcVersion"><br/><br/><br/><br/><br/><br/>Version: <%=session.getServletContext().getInitParameter("version")%></div>
	

</div>