<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="ca.bc.gov.ag.ecrc.equifax.EquifaxConsts.EquifaxResponseType" %>

<script>
$(document).ready(function() {
    //add more file components if Add is clicked
    $('#addFile').click(function() {
        var fileIndex = $('#fileTable tr').children().length - 1;
        $('#fileTable').append(
                '<tr><td>'+
                '   <input type="file" name="files['+ fileIndex +']" />'+
                '</td></tr>');
    });
    
    $('#files\\[0\\]').change(function() { 
    	  $('#xmlData').submit();
    });
    
    <c:if test="${ not empty alternateAction }">
  		$("#equifaxTestForm").attr('action', '${ alternateAction}');
	</c:if>

});

</script>

<div id="MainContent">
	
    <form:form id="equifaxTestForm" commandName="recordCheckForm" action="eiv_test.htm" method="post">

	<table width="700px" border="0">
		<tr>
			<td colspan="2">
				<p class="vcrcError">All fields marked with a red asterisk (*) are required.</p>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<b>Applicant Information</b>
			</td>
		</tr>
		<tr>
			<td>
				<label>Surname: </label>
			</td>
			<td>
				<form:input path="lastName" maxlength="40" /><font color="red">*</font><br/>
				<form:errors path="lastName" cssClass="vcrcError"/>
			</td>
		</tr>
		<tr>
			<td>
				<label>First Name: </label>
			</td>
			<td>
				<form:input path="firstName" maxlength="25" /><font color="red">*</font><br/>
			</td>
		</tr>
		<tr>
			<td>
				<label>Middle Name: </label>
			</td>
			<td>
				<form:input path="middleName" maxlength="25" /><br/>
			</td>
		</tr>
		<tr>
			<td class="content" width="55%">
				<label>Mailing Address: </label>
			</td>
			<td class="content" width="35%">
				<form:input path="mailingAddress" maxlength="40" /><font color="red">*</font><br/>
			</td>
		</tr>
		<tr>
			<td>
				<label>City: </label>
			</td>
			<td>
				<form:input path="city" maxlength="25" /><font color="red">*</font><br/>
			</td>
		</tr>
		<tr>
			<td>
				<label>Country: </label>
			</td>
			<td>
				${ recordCheckForm.country }
			</td>
		</tr>
		<tr>
			<td>
				<label>Province: </label>
			</td>
			<td class="content">
				<form:input path="province" maxlength="25" /><font color="red">*</font><br/>
			</td>
		</tr>
		<tr>
			<td>
				<label>Postal Code: </label><span class="style2">(X#X #X#)</span>
			</td>
			<td>
				<form:input path="postalCode" maxlength="7" /><font color="red">*</font><br/>
			</td>
		</tr>
		<tr>
			<td>
				<label>Time at Address: (Months) </label>
			</td>
			<td>
				<form:input path="months" maxlength="3" size="3" /><font color="red">*</font><br/>
			</td>
		</tr>
		<tr>
			<td>
				<label>Contact Phone #: </label><span class="style2">(###-###-####)</span>
			</td>
			<td>
				<form:input path="contactPhone" maxlength="12" /><font color="red">*</font><br/>
			</td>
		</tr>
		<%--
		<tr>
			<td>
				<label>SIN (Not used in VCRC): </label><span class="style2">(##########)</span>
			</td>
			<td>
				<form:input path="sin" maxlength="9" /><font color="red">*</font><br/>
			</td>
		</tr>
		--%>
		<tr>
			<td>
				<label>Date of Birth: </label><span class="style2">(MM/DD/YYYY)</span>
			</td>
			<td>
				<form:input path="birthDate" maxlength="10"/><font color="red">*</font><br/>
			</td>
		</tr>
		
		<tr>	
			<td>
				<label>Driver's Licence #: </label> <span class="style2">(Current or Expired)</span>
			</td>
			<td>
				<form:input path="licenceNo" maxlength="25" /><br/>
			</td>
		</tr>
		<tr>
			<td>
				<label>Driver's Licence Province of Issue: </label>
			</td>
			<td>
				<form:select path="licenceProvince">
					<c:forEach var="provinceItem" items="${provinces}">
						<form:option value="${provinceItem.name}" label="${provinceItem.name}"/>
					</c:forEach>
				</form:select>
			</td>
		</tr>
		</table>
						
		<br/>
		
		<table width="700px" border="0">	
		<tr>
			<td colspan="2">
				<b>Previous Address Information</b>
			</td>
		</tr>
		<tr>
			<td class="content" width="61%">
				<label>Mailing Address: </label>
			</td>
			<td class="content" width="39%">
				<form:input path="prevMailingAddress1" maxlength="40" />
			</td>
		</tr>
		<tr>
			<td>
				<label>City: </label>
			</td>
			<td>
				<form:input path="prevCity1" maxlength="25" />
			</td>
		</tr>
		<tr>
			<td>
				<label>Country: </label>
			</td>
			<td>
				${ recordCheckForm.prevCountry1 }
			</td>
		</tr>
		<tr>
			<td>
				<label>Province: </label>
			</td>
			<td class="content">
				<form:input path="prevProvince1" maxlength="25" />
			</td>
		</tr>
		<tr>
			<td>
				<label>Postal Code: </label><span class="style2">(X#X #X#)</span>
			</td>
			<td>
				<form:input path="prevPostalCode1" maxlength="7" />
			</td>
		</tr>
		<tr>
			<td>
				<label>Time at Address: (Months) </label>
			</td>
			<td>
				<form:input path="prevMonths1" maxlength="3" size="3" />
			</td>
		</tr>
		</table>
		
		<br/>
		
		<table width="700px" border="0">	
		<tr>	
			<td width="61%">
				<label>Expected Reason Codes: </label> 
			</td>
			<td width="39%">
				${ expectedReasonCodes }<br/>
			</td>
		</tr>
		</table>

	</form:form>
	
	<br/>
		
	<form:form id="xmlData" method="post" action="eiv_test_upload.htm" modelAttribute="uploadForm" enctype="multipart/form-data">
	    <p>1.) Select file to upload.</p>
	    <table width="100%">
	        <tr>
	            <td><input id="files[0]" name="files[0]" type="file"/></td>
	        </tr>
	    </table>
	    <br/>	
	</form:form>
	
	<p>2.) Click to start Test.</p>
	<table width="100%">
		<tr>
			<td width="25%" align="left"><input type="button" value="Submit to Equifax" onclick=" $('#equifaxTestForm').submit();"/></td>
		</tr>
	</table>
	<br/>

</div>





