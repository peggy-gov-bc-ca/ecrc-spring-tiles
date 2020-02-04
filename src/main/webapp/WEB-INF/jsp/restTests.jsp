<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


 <script type="text/Javascript">
 function doTest(testName) {
	 $('#testType').val(testName); 
	 $("#restTestForm").submit();
 }
 </script>

<div class="introduction sizable"></div>
	<div id="MainContent">
    	<div class="BodyContent sizable">

			<form:form id="restTestForm" name="restTestForm" action="rest_test.htm" method="post">
			 <input type="hidden" id="testType" name="testType" value="notSet"/>
			</form:form>
			<table width="600px">
				<tbody>
					<tr>
						<td width="40%">GetCountriesList</td>
						<td width="60%" align="left"><input type="button" value="Get Countries List"
							onclick="doTest('GetCountriesList')" class="vcrcButton" /></td>
					</tr>
					<tr>
						<td>GetProvinceList</td>
						<td><input type="button" value="Get Province List"
							onclick="doTest('GetProvinceList')" class="vcrcButton" /></td>
					</tr>
					<tr>
						<td>GetNextSessionId (1)</td>
						<td><input type="button" value="Get Next Session Id"
							onclick="doTest('GetNextSessionId')" class="vcrcButton" /></td>
					</tr>
					<tr>
						<td>GetNextInvoiceId (2)</td>
						<td><input type="button" value="Get Next Invoice Id"
							onclick="doTest('GetNextInvoiceId')" class="vcrcButton" /></td>
					</tr>
					<tr>
						<td>AuthenticateUser</td>
						<td><input type="button" value="Authenticate User"
							onclick="doTest('DoAuthenticateUser')" class="vcrcButton" /></td>
					</tr>
					<tr>
						<td>CreateApplicant (3)</td>
						<td><input type="button" value="Create Applicant"
							onclick="doTest('CreateApplicant')" class="vcrcButton" /></td>
					</tr>
					<tr>
						<td>CreateNewCRCService (4a) </td>
						<td><input type="button" value="Create New CRC Service"
							onclick="doTest('CreateNewCRCService')" class="vcrcButton" /></td>
					</tr>
					<tr>
						<td>CreateSharingService (4b)</td>
						<td><input type="button" value="Create Sharing Service"
							onclick="doTest('CreateSharingService')" class="vcrcButton" /></td>
					</tr>
					<tr>
						<td>UpdateServiceFinancialTxn (5)</td>
						<td><input type="button" value="Update Service Financial Txn Service"
							onclick="doTest('UpdateServiceFinancialTxn')" class="vcrcButton" /></td>
					</tr>
					<tr>
						<td>LogPaymentFailure</td>
						<td><input type="button" value="Log Payment Failure"
							onclick="doTest('LogPaymentFailure')" class="vcrcButton" /></td>
					</tr>
					<tr>
						<td>GetServiceFeeAmount</td>
						<td><input type="button" value="Get Service Fee Amount"
							onclick="doTest('GetServiceFeeAmount')" class="vcrcButton" /></td>
					</tr>
					<tr>
						<td>CheckApplicantForPrevCRC</td>
						<td><input type="button" value="Check Applicant for Prev CRC"
							onclick="doTest('CheckApplicantForPrevCRC')" class="vcrcButton" /></td>
					</tr>
					<tr>
						<td>LogEivFailure</td>
						<td><input type="button" value="Log Eiv Failure"
							onclick="doTest('LogEivFailure')" class="vcrcButton" /></td>
					</tr>
				</tbody>
			</table>
			
			</div>
			
		</div>