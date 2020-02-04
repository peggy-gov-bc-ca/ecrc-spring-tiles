<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="ca.bc.gov.ag.ecrc.utils.Consts" %>

<div id="MainContent">

	<form:form commandName="identifyPayorForm" action="crcIdentifyPayorForm.htm" method="post">
		<div class="introduction sizable">
			<b>Identify Payor</b>
		</div>
		<div class="BodyContent sizable">
			<p>Before you proceed to the next page to enter a criminal record check request (CRC request), you must first indicate
			who will pay the fee. Select one of the following:<br/><br/>
				<form:radiobutton path="payor" value="<%=Consts.ORG_TO_PAY %>"/>Organization credit card payment<br/>
				<form:radiobutton path="payor" value="<%=Consts.APPL_TO_PAY %>"/>Applicant payment via the credit card information provided 
				<span style="font-size: xx-small">(Please have the credit card details ready)</span>
				<br/><br/>
				<form:errors path="payor" cssClass="vcrcError"/>
			</p>
			<div class="vcrcBox">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><input id="cancelButton" type="button" name="action" value="Cancel" class="vcrcButton"/></td>
					<td align="right"><input type="submit" name="action" value="Next" class="vcrcButton"></td>
				</tr>	
			</table>
			</div>
		</div>
	</form:form>
</div>