<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="ca.bc.gov.ag.ecrc.utils.Consts" %>

<c:set var="REQUEST_TYPE_SHARE" value="<%=Consts.REQUEST_TYPE_SHARE%>" />

<div id="MainContent">
	<div class="introduction sizable"><b>Organization Information</b></div>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<table width="100%">
					<tr>
						<td width="50%">
						Organization Name:
						</td>
						<td width="50%">
						${ sessionVars.authenticateUser.accessCodeResponse.orgNm }
						</td>
					</tr>
					<tr>
						<td>
						Address Line 1:
						</td>
						<td>
						${ sessionVars.authenticateUser.accessCodeResponse.addressLine1 }
						</td>
					</tr>
					<tr>
						<td>
						Address Line 2:
						</td>
						<td>
						${ sessionVars.authenticateUser.accessCodeResponse.addressLine2 }
						</td>
					</tr>
					<tr>
						<td>
						City:
						</td>
						<td>
						${ sessionVars.authenticateUser.accessCodeResponse.cityNm }
						</td>
					</tr>
					<tr>
						<td>
						Province:
						</td>
						<td>
						${ sessionVars.authenticateUser.accessCodeResponse.provinceNm }
						</td>
					</tr>
					<tr>
						<td>
						Country:
						</td>
						<td>
						${ sessionVars.authenticateUser.accessCodeResponse.countryNm }
						</td>
					</tr>
					<tr>
						<td>
						Postal Code:
						</td>
						<td>
						${ sessionVars.authenticateUser.accessCodeResponse.postalCodeTxt }
						</td>
					</tr>
					<tr>
						<td>
						Role:
						</td>
						<td>
						${ sessionVars.authenticateUser.accessCodeResponse.orgApplicantRelationship }
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div class="BodyContent sizable">
		<p>If the information above does not appear to match the organization that has requested that a 
			criminal record check be completed, please do not proceed and contact the organization that has requested 
			the criminal record check.</p>
		<c:choose>
			<c:when test="${ sessionVars.requestType == REQUEST_TYPE_SHARE }">
				<p>Once the request to share a criminal record check result is complete, the organization noted above will receive the results.</p>
			</c:when>
			<c:otherwise>
				<p>For volunteers completing a request for a criminal record check, no payment is required. For all other applicants, 
					a fee payable by credit card (Visa, MasterCard or AMEX) is required. Please have your credit card information ready.</p>
				<p>Once the criminal record check is complete, the organization noted above will receive the results.</p>
			</c:otherwise>
		</c:choose>
		<p>By selecting Next, you are consenting to have your information released to this organization.</p>
	</div>
	<br/>
	<form:form action="${ sessionVars.requestType }OrgDetails.htm" method="post">
			
		<div class="vcrcBox">	
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><input id="cancelButton" type="button" name="action" value="Cancel" class="vcrcButton"/></td>
				<td align="right"><input type="submit" name="action" value="Next" class="vcrcButton"/></td>
			</tr>	
		</table>
		</div>
	</form:form>
	
</div>


