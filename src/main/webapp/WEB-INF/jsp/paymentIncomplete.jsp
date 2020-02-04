<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<div id="MainContent">
	<div class="introduction sizable"><b>Receipt</b></div>
	
	<div class="BodyContent sizable">
		<p>Payment could not be completed.</p>
		<p><b>A request to conduct a criminal record check has not been submitted.</b></p>
		<p>A payment could not be completed with the information provided. It's possible that your credit card information was 
		entered incorrectly, the credit card has expired or the billing information does not match the information used to submit 
		the criminal record check request. Select Try Again if you would like to enter your credit card information again.</p>
		
		<form:form action="${ sessionVars.requestType }PaymentIncomplete.htm" method="post">	
		
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="right"><input type="submit" name="action" value="Try Again" class="vcrcButton"/></td>
				</tr>	
			</table>
		
			<p><b>Session ID:</b> ${ sessionVars.sessionId }</p>
			
			<div class="vcrcBox">		
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<table width="100%">
							<tr>
								<%-- PT 22850
								<td>
									<b>Service #</b>
								</td>
								--%>
								<td>
									<b>Surname</b>
								</td>
								<td>
									<b>First Name</b>
								</td>
								<td>
									<b>Date of Birth</b>
								</td>
								<td>
									<b>Paid</b>
								</td>
							</tr>
							
							<tr>
								<%-- PT 22850
								<td>
									${ sessionVars.serviceIdAsString }
								</td>
								--%>
								<td>
									${ sessionVars.recordCheckForm.lastName }
								</td>
								<td>
									${ sessionVars.recordCheckForm.firstName }
								</td>
								<td>
									${ sessionVarHelpers.prettyFormatBirthDate }
								</td>
								<td>
									${ sessionVars.paymentInformation.chargeAmountAsString }
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</div>
			<br/>
			<br/>
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
	
			<br/>	
			<p><b>Please print this form for your records.</b></p>
			<div class="vcrcBox">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><input id="cancelButton" type="button" name="action" value="Cancel" class="vcrcButton"/></td>
					<td align="right" width="90%"><input type="button" value="Print" onClick="window.print()" class="vcrcButton"></td>
				</tr>	
			</table>
			</div>
		</form:form>
		
	</div>
	
</div>