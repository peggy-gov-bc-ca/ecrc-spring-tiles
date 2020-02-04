<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="ca.bc.gov.ag.ecrc.utils.Consts" %>

<c:set var="CONF_TYPE_PAY" value="<%=Consts.CONF_TYPE_PAY%>" />
<c:set var="CONF_TYPE_NOPAY" value="<%=Consts.CONF_TYPE_NOPAY%>" />  
<c:set var="CONF_TYPE_SHARE" value="<%=Consts.CONF_TYPE_SHARE%>" />  

<div id="MainContent">
	<c:choose>
		<c:when test="${ sessionVars.confirmationType == CONF_TYPE_PAY }">
			<div class="introduction sizable"><b>Receipt</b></div>
			<div class="BodyContent sizable">
				<p>Payment Approved. Thank you.</p>
			</div>
			<div class="BodyContent sizable">
				<p>A request to conduct a criminal record check has been submitted to the Criminal Records Review Program at the Ministry of Justice. Please 
				allow 7 to 10 business days for processing. Once complete, the results will be provided directly to the organization requesting the check.</p>
				
				<p>The service number below can be used to help locate your file when contacting the Ministry and when submitting a request to share a completed 
				criminal record check.</p>
			</div>
		</c:when>
		<c:when test="${ sessionVars.confirmationType == CONF_TYPE_NOPAY }">
			<div class="BodyContent sizable">
				<p>Submission Complete</p>
			</div>
			<div class="BodyContent sizable">
				<p>A request to conduct a criminal record check has been submitted to the Criminal Records Review Program at the Ministry of Justice. Please 
				allow 7 to 10 business days for processing. Once complete, the results will be provided directly to the organization requesting the check.</p>
				
				<p>The service number below can be used to help locate your file when contacting the Ministry and when submitting a request to share a completed 
				criminal record check.</p>
			</div>
		</c:when>
		<c:when test="${ sessionVars.confirmationType == CONF_TYPE_SHARE }">
			<div class="BodyContent sizable">
				<p>Submission Complete</p>
				
				<p>A request to share the result of a criminal record check has been submitted to the Criminal Records Review Program at the Ministry of Justice. 
				Please allow a minimum of 3 business days to complete your request. Once complete, the results will be provided directly to the organization 
				requesting the check.</p>
				
				<p>The service number below can be used to help locate your file when contacting the Ministry.</p>
			</div>
		</c:when>
	</c:choose>
	
	<div class="BodyContent sizable">
		<p><b>Session ID:</b> ${ sessionVars.sessionId }</p>
				
		<div class="vcrcBox">		
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td>
								<b>Service #</b>
							</td>
							<td>
								<b>Surname</b>
							</td>
							<td>
								<b>First Name</b>
							</td>
							<td>
								<b>Date of Birth</b>
							</td>
							<c:if test="${ sessionVars.confirmationType == CONF_TYPE_PAY }">
								<td>
									<b>Paid</b>
								</td>
							</c:if>
						</tr>
						
						<tr>
							<td>
								${ sessionVars.serviceIdAsString }
							</td>
							<td>
								${ sessionVars.recordCheckForm.lastName }
							</td>
							<td>
								${ sessionVars.recordCheckForm.firstName }
							</td>
							<td>
								${ sessionVarHelpers.prettyFormatBirthDate }
							</td>
							<c:if test="${ sessionVars.confirmationType == CONF_TYPE_PAY }">
								<td>
									${ sessionVars.paymentInformation.chargeAmountAsString }
								</td>
							</c:if>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</div>
		<br/>
		<br/>
			
		<c:choose>
			<c:when test="${ sessionVars.confirmationType == CONF_TYPE_PAY }">
				<div class="vcrcBox">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<table width="100%">
								<tr>
									<td>
										<b>Date:</b>
									</td>
									<td>
										${ sessionVars.paymentInformation.date }
									</td>
									<td>
										<b>Transaction Type:</b>
									</td>
									<td>
										Purchase
									</td>
								</tr>
								<tr>
									<td>
										<b>Card Type:</b> 
									</td>
									<td>
										${ sessionVars.paymentInformation.cardTypeAsString }
									</td>
									<td>
										<b>Card Number:</b>
									</td>
									<td>
										${ sessionVars.paymentInformation.cardNumber }
									</td>
								</tr>
								<tr>
									<td>
										<b>Invoice Number:</b>
									</td>
									<td>
										${ sessionVars.paymentInformation.invoiceNumber }
									</td>
									<td>
										<b>Amount:</b>
									</td>
									<td>
										${ sessionVars.paymentInformation.chargeAmountAsString }
									</td>
								</tr>
								<tr>
									<td>
										<b>Transaction ID:</b>
									</td>
									<td>
										${ sessionVars.paymentInformation.transactionId }
									</td>
									<td colspan="2">
										<i>(Note: Credit card number has been masked for privacy)</i>
									</td>
								</tr>
								<tr>
									<td>
										<b>Approval Code:</b>
									</td>
									<td>
										${ sessionVars.paymentInformation.authorizationNumber }
									</td>
									<td>
										<b>Response Message:</b>
									</td>
									<td>
										${ sessionVars.paymentInformation.bankResponseMessage }
									</td>
								</tr>
								<tr>
									<td>
										<b>Host Date/Time:</b>
									</td>
									<td>
										${ sessionVars.paymentInformation.paymentDateTime }
									</td>
									<td>
										<b>Sequence Number:</b>
									</td>
									<td>
										${ sessionVars.paymentInformation.sequenceNumber }
									</td>
								</tr>
								<tr>
									<td>
										<b>ISO Response Code:</b>
									</td>
									<td>
										${ sessionVars.paymentInformation.bankISOResponseCode }
									</td>
									<td>
										<b>Term Number:</b>
									</td>
									<td>
										${ sessionVars.paymentInformation.terminalNumber }
									</td>
								</tr>
								<tr>
									<td>
										<b>Response Code:</b>
									</td>
									<td>
										${ sessionVars.paymentInformation.bankResponseCode }
									</td>
									<td>
										<b>WS Response Code:</b>
									</td>
									<td>
										${sessionVars.paymentInformation.paymentStatus }
									</td>
								</tr>
								<tr>
									<td>
										<b>WS Response Description</b> 
									</td>
									<td>
										${ sessionVars.paymentInformation.paymentStatusAsString }
									</td>
									<td>
										
									</td>
									<td>
										
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			 </div>
			</c:when>
		</c:choose>
	

		<br/>	
		<p><b>Please print this form for your records.</b></p>
		<div class="vcrcBox">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="right" width="90%"><input type="button" value="Print" onClick="window.print()" class="vcrcButton"></td>
			</tr>	
		</table>
		</div>	
			
	</div>
	
</div>