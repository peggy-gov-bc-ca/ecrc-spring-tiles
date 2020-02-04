<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="ca.bc.gov.ag.ecrc.utils.Consts" %>

<c:set var="REQUEST_TYPE_CHECK" value="<%=Consts.REQUEST_TYPE_CHECK%>" />
<c:set var="REQUEST_TYPE_SHARE" value="<%=Consts.REQUEST_TYPE_SHARE%>" />

<div id="MainContent">

	<form:form commandName="consentForm" action="${sessionVars.requestType}ConsentForm.htm" method="post">
		<div class="introduction sizable">
			<p class="vcrcError">All fields marked with a red asterisk (*) are required.</p><br/>
			<b>Consent Information</b>
		</div>
		<div class="BodyContent sizable">
			<c:choose>
				<c:when test="${ sessionVars.requestType == REQUEST_TYPE_CHECK }">
					<p>Consent to a Criminal Record Check</p>
					<ul>
						<li>
						I hereby consent to a check for records of criminal charges and convictions to determine whether I have a conviction 
						or outstanding charge for any relevant or specified offences under the Criminal Records Review Act;
						</li>
						<li>
						I hereby consent to a check of all available law enforcement systems, including any local police records.
						</li>
						<li>
						I hereby consent to a vulnerable sector search to check if I have been convicted of and been granted a pardon for any sexual offences of the Criminal Records Act.
						</li>
						<li>
						I understand a criminal record check under the Criminal Records Review Act is required at least once every five years.<br/>
						Go to the RCMP website for additional details on vulnerable sector checks: <a href="http://www.rcmp-grc.gc.ca/cr-cj/vulner/index-eng.htm">http://www.rcmp-grc.gc.ca/cr-cj/vulner/index-eng.htm</a>
						</li>
						<li>
						I hereby authorize the release to the Deputy Registrar any documents in the custody of the police, the court 
						and crown counsel relating to an outstanding charge or conviction of any relevant or specified offence 
						as defined under the Criminal Records Review Act.
						</li>
						<li>
						Where the results of this check indicate that a criminal record or outstanding charge for a relevant or specified 
						offence may exist, I agree to provide my fingerprints to verify any such criminal record.
						</li>
						<li>
						The Deputy Registrar will notify me and my organization that I have an outstanding charge or conviction for any 
						relevant or specified offence(s) and the matter has been referred to the Deputy Registrar;
						</li>
						<li>
						The Deputy Registrar will determine whether or not I present a risk of physical or sexual abuse to children 
						and/or physical, sexual or financial abuse to vulnerable adults as applicable.
						</li>
						<li>
						The Deputy Registrar's determination will be disclosed to my organization and it will include consideration 
						of any relevant or specified offence for which I have received a pardon;
						</li>
						<li>
						If I am charged with or convicted of a relevant or specified offence at any time subsequent to the criminal 
						record check authorized herein, I further agree to report the charge or conviction to my organization and 
						provide my organization, in a timely manner, with consent to conduct a Criminal Record Check form.
						</li>
					</ul>
					<p>
						<b>I have read and understand the above: </b>
						<form:radiobutton path="crcConsent" value="true"/>Yes
						<form:radiobutton path="crcConsent" value="false"/>No <font color="red">*</font>
						<br/>
						<form:errors path="crcConsent" cssClass="vcrcError"/>
					</p>
				</c:when>
				
				<c:when test="${ sessionVars.requestType == REQUEST_TYPE_SHARE }">
					<p>Consent for release of information and acknowledgments:</p>
					<ul>
						<li>
						I understand to share the result of a criminal record check, I must have completed a criminal record check within the last 5 years through the Criminal Records Review Program and the sharing request must be for the same type of check as previously completed, either for children, vulnerable adults, or both children and vulnerable adults.
						</li>
						<li>
						I confirm I have completed a criminal record check within the past five years with the Criminal Records Review program which did not result in a determination of risk to children and/or vulnerable adults as defined in the Criminal Records Review Act.  I understand no details will be disclosed to my organization, only the result. I hereby consent to share the result of the completed check with the above indicated organization.
						</li>
						<li>
						I understand that if the registrar determines I do not have criminal record check to share according to the above criteria, I will be promptly notified.
						</li>
						<li>
						I understand that within 5 years of the date of this criminal record check verification authorization, should the Criminal Records Review Program make a determination that I pose a risk to children and/or vulnerable adults, the Deputy Registrar will promptly provide notification to me and to the persons and entities (organizations) identified in the criminal record check verification authorization.  
						</li>
					</ul>	
				</c:when>
				
			</c:choose>

			<p>Consent to Release Personal Information</p>
			<p>I hereby consent to the release of my personal information to Equifax for the confirmation of the personal 
				information I have entered in this form.</p>
			<p>
				<b>I have read and understand the above: </b>
				<form:radiobutton path="personalInfoConsent" value="true"/>Yes
				<form:radiobutton path="personalInfoConsent" value="false"/>No <font color="red">*</font>
				<br/>
				<form:errors path="personalInfoConsent" cssClass="vcrcError"/>
			</p>
			<p>
				FREEDOM OF INFORMATION AND PROTECTION OF PRIVACY ACT (FOIPPA): The information requested on this form is collected 
				under the authority of the Criminal Records Review Act section 4(1) and section 26(c) of the <i>Freedom of 
				Information and Protection of Privacy Act</i> (FOIPPA). The information provided will be used to fulfil the 
				requirements of the Criminal Records Review Act for the release of criminal records information and is in compliance 
				with the FOIPPA. If you have any questions about the collection of your personal information, please contact the Policy Analyst,
				${ sessionVars.ecrc_props['securities.contact.name'] }, ${ sessionVars.ecrc_props['securities.contact.address1'] }, ${ sessionVars.ecrc_props['securities.contact.address2'] } or by phone at ${ sessionVars.ecrc_props['securities.contact.phone'] }.
			</p>
			<br/>
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