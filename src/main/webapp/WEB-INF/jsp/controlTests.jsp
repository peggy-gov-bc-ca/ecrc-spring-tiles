<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/Javascript">
$(function() {	
$("#datepicker").datepicker({
	changeMonth: true,
	changeYear: true,
	minDate: "-${maxAge}Y",
	maxDate: "-${minAge}Y",
	yearRange: "-${maxAge}:-${minAge}"
	});
});
</script>

<div class="introduction sizable"></div>
	<div id="MainContent">
    	<div class="BodyContent sizable">

			<table>
				<tbody>
					<tr>
						<td>
							<label>Date of Birth: </label><span class="style2">(MM/DD/YYYY)</span>
						</td>
						<td>
							<input type="text" id="datepicker"/><font color="red">*</font>
						</td>
					</tr>
				</tbody>
			</table>
			
			<table>
				<tbody>
					<tr>
						<td>
							<label>VCRC Button (Enabled)</label><button name="subject" type="submit" value="fav_CSS" class="vcrcButton">CSS-enabled</button>
						</td>
					</tr>
					<tr>		
						<td>
							<label>VCRC Button (Disabled)</label><button name="subject" type="submit" value="fav_CSS" class="vcrcButton" disabled="disabled">CSS-disabled</button>
						</td>	
					</tr>
				</tbody>
			</table>
			
			</div>
			
		</div>