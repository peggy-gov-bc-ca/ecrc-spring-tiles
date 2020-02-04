<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="ca.bc.gov.ag.ecrc.utils.Consts" %>

<c:set var="REQUEST_TYPE_CHECK" value="<%=Consts.REQUEST_TYPE_CHECK%>" />
<c:set var="REQUEST_TYPE_SHARE" value="<%=Consts.REQUEST_TYPE_SHARE%>" />

<c:set var="SCHEDULE_D" value="<%=Consts.SCHEDULE_D%>" />
<c:set var="SCHEDULE_OT_D" value="<%=Consts.SCHEDULE_OT_D%>" />

<c:set var="ONETIME" value="<%=Consts.ORG_APPL_RELATION_ONETIMETIK%>" />

<c:set var="COUNTRY_CANADA" value="<%=Consts.COUNTRY_CANADA%>" />

<script>
$(function() {	
$("#birthDate").datepicker({
	changeMonth: true,
	changeYear: true,
	minDate: "-${sessionVars.ecrc_props['maxAge']}Y",
	maxDate: "-${sessionVars.ecrc_props['minAge']}Y",
	yearRange: "-${sessionVars.ecrc_props['maxAge']}:-${sessionVars.ecrc_props['minAge']}"
	});
});

<%-- removed for PT 22780
 $(function(){
     $('#scheduleType').change(function(){
         var selected = $(this).find(':selected').attr("value");
         if (selected == "${ SCHEDULE_OT_D }") {
             $("#careFacility").show();
         }
         else {
         	$("#careFacility").hide();
         }
     }).change()
 });
--%> 

$(function(){
	if ("${sessionVars.requestType}" == "${REQUEST_TYPE_CHECK}" && ("${sessionVars.authenticateUser.accessCodeResponse.defaultScheduleTypeCd}" == "${SCHEDULE_D}" || $("#scheduleType").find(':selected').attr("value") == "${SCHEDULE_OT_D}")) {
		$("#careFacility").show();
	} 
	else {
		$("#careFacility").hide();
	}
});

<c:if test="${ not empty alternateAction }">
	$("#recordCheckForm").attr('action', '${alternateAction}');
</c:if>

</script>

<div id="MainContent">
	
	<form:form id="recordCheckForm" commandName="recordCheckForm" action="${ sessionVars.requestType }RecordCheckForm.htm" method="post">
	
		<form:hidden path="scheduleType"/>
		<form:hidden path="offenseCategory"/>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<table width="100%">
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
							<td width="50%">
								<label>Surname: </label>
							</td>
							<td width="50%">
								<form:input path="lastName" maxlength="25" /><font color="red">*</font><br/>
								<form:errors path="lastName" cssClass="vcrcError"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>First Name: </label>
							</td>
							<td>
								<form:input path="firstName" maxlength="25" /><font color="red">*</font><br/>
								<form:errors path="firstName" cssClass="vcrcError"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Middle Name: </label>
							</td>
							<td>
								<form:input path="middleName" maxlength="25" /><br/>
								<form:errors path="middleName" cssClass="vcrcError" />
							</td>
						</tr>
						<tr>
							<td>
								<label>Date of Birth: </label><span class="style2">(MM/DD/YYYY)</span>
							</td>
							<td>
								<form:input path="birthDate" /><font color="red">*</font><br/>
								<form:errors path="birthDate" cssClass="vcrcError"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Gender: </label>
							</td>
							<td>
								<form:radiobutton path="gender" value="F"/>Female
								<form:radiobutton path="gender" value="M"/>Male
								<font color="red">*</font><br/>
								<form:errors path="gender" cssClass="vcrcError"/>
							</td>
						</tr>
						<c:if test="${ sessionVars.requestType == REQUEST_TYPE_CHECK }">
							<tr>
								<td>
									<label>Birth Place: </label> <span class="style2">(City,
								Province/State, Country) </span>
								</td>
								<td>
									<form:input path="birthPlace" maxlength="40" /><font color="red">*</font><br/>
									<form:errors path="birthPlace" cssClass="vcrcError"/>
								</td>
							</tr>
						</c:if>
						<tr>
							<td>
								<label><font style="font-weight: bold">Driver's Licence #</font>: </label> <span class="style2">(Current or Expired)</span>
							<span style="font-size: xx-small">Providing a Driver's Licence may expedite the criminal record check process</span>
							</td>
							<td>
								<form:input path="licenceNo" maxlength="25" /><br/>
								<form:errors path="licenceNo" cssClass="vcrcError" />
							</td>
						</tr>
						<tr>
							<td>
								<label><font style="font-weight: bold">Driver's Licence Province of Issue</font>: </label>
							</td>
							<td>
								<form:select path="licenceProvince">
									<c:forEach var="provinceItem" items="${provinces}">
										<form:option value="${provinceItem.name}" label="${provinceItem.name}"/>
									</c:forEach>
								</form:select>
								<br/>
								<form:errors path="licenceProvince" cssClass="vcrcError"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Applicant's Position/Job Title: </label>
							</td>
							<td>
								<form:input path="applicantPosition" maxlength="80" /><font color="red">*</font><br/>
								<form:errors path="applicantPosition" cssClass="vcrcError" />
							</td>
						</tr>
						<tr>
							<td>
								<label>Category of Offences: </label>
							</td>
							<td>
								<%-- PT xxxxx - Scope Level R/O
								<form:select path="offenseCategory" >
									<c:forEach var="offenseCategoryItem" items="${ sessionVars.authenticateUser.scopeLevels.scopeLevel }">
										<form:option value="${offenseCategoryItem.crcScopeLevelCd}" label="${offenseCategoryItem.crcScopeLevelDsc}"/>
									</c:forEach>
								</form:select>
								<form:errors path="offenseCategory" cssClass="vcrcError"/>
								--%>
								${ sessionVarHelpers.scopeLevelDesc }
							</td>
						</tr>
						
						<%-- schedule type selection only available when user is 'ONETIME' ticket type --%>
						<c:if test="${ sessionVars.requestType == REQUEST_TYPE_CHECK && sessionVars.authenticateUser.accessCodeResponse.orgApplicantRelationship == ONETIME }">
							<tr>
								<td>
										<label>Schedule Type: </label>
								</td>
								<td>
									<%-- PT 22780 - schedule type R/O 
									<form:select path="scheduleType" id="scheduleType">
										<c:forEach var="scheduleTypeItem" items="${ sessionVars.authenticateUser.scheduleTypes.scheduleType }">
											<form:option value="${scheduleTypeItem.crcScheduleTypeCd}" label="${scheduleTypeItem.crcScheduleTypeDsc}"/>
										</c:forEach>
									</form:select>
									<form:errors path="scheduleType" cssClass="vcrcError"/>
									--%>
									${ sessionVarHelpers.scheduleTypeDesc }
								</td>
							</tr>
						</c:if>
						<tbody id="careFacility">
							<tr>
								<td>
									<label>Licensed Child Care or Adult Care Facility Name: </label>
								</td>
								<td>
									<form:input path="careFacility" maxlength="100" /><font color="red">*</font><br/>
									<form:errors path="careFacility" cssClass="vcrcError" />
								</td>
							</tr>
						</tbody>
						<c:if test="${ sessionVars.requestType == REQUEST_TYPE_SHARE }">
							<tr>
								<td>
									<label>Service Number: </label><span class="style2">(Of completed criminal record check, if known)</span>
								</td>
								<td>
									<form:input path="serviceNumber" maxlength="15" /><br/>
									<form:errors path="serviceNumber" cssClass="vcrcError"/>
								</td>
							</tr>
						</c:if>
						<tr>
							<td colspan="2">
							<hr />
							</td>
						</tr> 
						<tr>
							<td colspan="2">
								<b>Other Names Used</b>
								<span style="font-size: xx-small">(e.g. maiden name, birth name, or previous married name(s)) </span><br/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Surname: </label>
							</td>
							<td>
								<form:input path="lastName1" maxlength="40" /><br/>
								<form:errors path="lastName1" cssClass="vcrcError"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>First Name: </label>
							</td>
							<td>
								<form:input path="firstName1" maxlength="25" /><br/>
								<form:errors path="firstName1" cssClass="vcrcError" />
							</td>
						</tr>
						<tr>
							<td>
								<label>Middle Name: </label>
							</td>
							<td>
								<form:input path="middleName1" maxlength="25" /><br/>
								<form:errors path="middleName1" cssClass="vcrcError"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
							</td>
						</tr>
						<tr>
							<td>
								<label>Surname: </label>
							</td>
							<td>
								<form:input path="lastName2" maxlength="40" /><br/>
								<form:errors path="lastName2" cssClass="vcrcError"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>First Name: </label>
							</td>
							<td>
								<form:input path="firstName2" maxlength="25" /><br/>
								<form:errors path="firstName2" cssClass="vcrcError"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Middle Name: </label>
							</td>
							<td>
								<form:input path="middleName2" maxlength="25" /><br/>
								<form:errors path="middleName2" cssClass="vcrcError" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
							</td>
						</tr>
						<tr>
							<td>
								<label>Surname: </label>
							</td>
							<td>
								<form:input path="lastName3" maxlength="40" /><br/>
								<form:errors path="lastName3" cssClass="vcrcError" />
							</td>
						</tr>
						<tr>
							<td>
								<label>First Name: </label>
							</td>
							<td>
								<form:input path="firstName3" maxlength="25" /><br/>
								<form:errors path="firstName3" cssClass="vcrcError" />
							</td>
						</tr>
					<tr>
						<td>
						<label>Middle Name: </label>
						</td>
						<td>
								<form:input path="middleName3" maxlength="25" /><br/>
								<form:errors path="middleName3" cssClass="vcrcError" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<hr />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<b>Contact Information</b>
								<span style="font-size: xx-small">(If time at current address is less than 24 months, please include at least 1 previous address) </span>
							</td>
						</tr>
						<tr>
							<td class="content">
								<label>Mailing Address: </label>
							</td>
							<td class="content">
								<form:input path="mailingAddress" maxlength="40" /><font color="red">*</font><br/>
								<form:errors path="mailingAddress" cssClass="vcrcError" />
							</td>
						</tr>
						<tr>
							<td>
								<label>City: </label>
							</td>
							<td>
								<form:input path="city" maxlength="25" /><font color="red">*</font><br/>
								<form:errors path="city" cssClass="vcrcError"/>
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
						<tbody id="province">
							<tr>
								<td>
									<label>Province: </label>
								</td>
								<td>
									<form:select path="province">
										<c:forEach var="provinceItem" items="${provinces}">
											<form:option value="${provinceItem.name}" label="${provinceItem.name}"/>
										</c:forEach>
									</form:select>
									<br/>
									<form:errors path="province" cssClass="vcrcError"/>
								</td>
							</tr>
						</tbody>
						<tr>
							<td>
								<label>Postal Code: </label><span class="style2">(X#X #X#)</span>
							</td>
							<td>
								<form:input path="postalCode" maxlength="7" /><font color="red">*</font><br/>
								<form:errors path="postalCode" cssClass="vcrcError"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Time at Address: (Months) </label>
							</td>
							<td>
								<form:input path="months" maxlength="3" size="3" /><font color="red">*</font><br/>
								<form:errors path="months" cssClass="vcrcError"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Contact Phone #: </label><span class="style2">(###-###-####)</span>
							</td>
							<td>
								<form:input path="contactPhone" maxlength="12" /><font color="red">*</font><br/>
								<form:errors path="contactPhone" cssClass="vcrcError" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<hr />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<b>Previous Address Information</b><br/>
								<form:errors  path="monthsAtPrevious" cssClass="vcrcError"/>
							</td>
						</tr>
						<tr>
							<td class="content">
								<label>Mailing Address: </label>
							</td>
							<td class="content">
								<form:input path="prevMailingAddress1" maxlength="40" /><br/>
								<form:errors path="prevMailingAddress1" cssClass="vcrcError" />
							</td>
						</tr>
						<tr>
							<td>
								<label>City: </label>
							</td>
							<td>
								<form:input path="prevCity1" maxlength="25" /><br/>
								<form:errors path="prevCity1" cssClass="vcrcError"/>
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
						<tbody id="province">
							<tr>
								<td>
									<label>Province: </label>
								</td>
								<td>
									<form:select path="prevProvince1">
										<c:forEach var="provinceItem" items="${provinces}">
											<form:option value="${provinceItem.name}" label="${provinceItem.name}"/>
										</c:forEach>
									</form:select>
									<br/>
									<form:errors path="prevProvince1" cssClass="vcrcError"/>
								</td>
							</tr>
						</tbody>
						<tr>
							<td>
								<label>Postal Code: </label><span class="style2">(X#X #X#)</span>
							</td>
							<td>
								<form:input path="prevPostalCode1" maxlength="7" /><br/>
								<form:errors path="prevPostalCode1" cssClass="vcrcError"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Time at Address: (Months) </label>
							</td>
							<td>
								<form:input path="prevMonths1" maxlength="3" size="3" /><br/>
								<form:errors path="prevMonths1" cssClass="vcrcError"/>
							</td>
						</tr>
						<tr>
							<td colspan="2"><br/>
							</td>
						</tr>
						<tr>
							<td class="content">
								<label>Mailing Address: </label>
							</td>
							<td class="content">
								<form:input path="prevMailingAddress2" maxlength="40" /><br/>
								<form:errors path="prevMailingAddress2" cssClass="vcrcError" />
							</td>
						</tr>
						<tr>
							<td>
								<label>City: </label>
							</td>
							<td>
								<form:input path="prevCity2" maxlength="25" /><br/>
								<form:errors path="prevCity2" cssClass="vcrcError"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Country: </label>
							</td>
							<td>
								${ recordCheckForm.prevCountry2 }
							</td>
						</tr>
						<tbody id="province">
							<tr>
								<td>
									<label>Province: </label>
								</td>
								<td>
									<form:select path="prevProvince2">
										<c:forEach var="provinceItem" items="${provinces}">
											<form:option value="${provinceItem.name}" label="${provinceItem.name}"/>
										</c:forEach>
									</form:select>
									<br/>
									<form:errors path="prevProvince2" cssClass="vcrcError"/>
								</td>
							</tr>
						</tbody>
						<tr>
							<td>
								<label>Postal Code: </label><span class="style2">(X#X #X#)</span>
							</td>
							<td>
								<form:input path="prevPostalCode2" maxlength="7" /><br/>
								<form:errors path="prevPostalCode2" cssClass="vcrcError"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Time at Address: (Months) </label>
							</td>
							<td>
								<form:input path="prevMonths2" maxlength="3" size="3" /><br/>
								<form:errors path="prevMonths2" cssClass="vcrcError"/>
							</td>
						</tr>
						</table>
						
				</td>
			</tr>
		</table>
		
		<br/>
		
		<div class="vcrcBox">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><input id="cancelButton" type="button" name="action" value="Cancel" class="vcrcButton"/></td>
			<td align="right"><input type=submit name="action" value="Next" class="vcrcButton"/></td>
		</tr>
		</table>
		</div>
		
		
	</form:form>
</div>
