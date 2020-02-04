<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 <!-- ***** Header Start ***** //-->
 <div class="ls-row" id="row-header">
    <div class="ls-fxr" id="ls-gen3-ls-fxr">
       <div class="ls-area" id="area-header">
          <div class="ls-area-body" id="ls-gen4-ls-area-body">
             <div class="ls-cmp-wrap ls-1st" id="w1328897957809">
                <div class="iw_component" id="1328897957809">
                   <div id="banner">
                      <a href="http://www2.gov.bc.ca/gov/index.page" class="logo" title="B.C. Home"></a>
                      <h1 id="activetheme">Government</h1>
                      <script type="text/javascript">
                         // find the active theme from the global navigation component, and print the theme name in the header
                         $(document).ready(function() {
                         	var current = $('.themes .horizontalMenu .current a').text();
                         	var site = "gov";
                         	var page = "topic";

                         	if(current) {
                         		$('#activetheme').text(current);
                         	} else {
                         		if(site == "en") {
                         			// Special case for en/index page
                         			if(page == "index")
                         				$('#activetheme').text('Welcome');
                         			else
                         				$('#activetheme').text('Government');
                         		} else if (site == "dbc"){
                         			$('#activetheme').html('DataBC');
                         		} else {
                         			$('#activetheme').text('Government');
                         		}
                         	}
                         });
                      </script>
                      <div id="searchArea">
                         <form onsubmit="javascript:this.q.value=this.q.value.toLowerCase();" method="get" id="search" action="/gov/topic.page?submit=true&amp;componentID=1328897957809">
                            <input class="placeholder" autocomplete="off" name="keywords" id="searchBox" type="text"><input value="gov" name="config" type="hidden"><a style="text-decoration:none;" href="#" class="SubmitLinkButton">Search</a>
                            <br clear="all">
                            <div id="typeAhead">
                               <ul id="keyword_list"></ul>
                            </div>
                         </form>
                      </div>
                   </div>
                </div>
             </div>
          </div>
       </div>
       <div class="ls-row-clr"></div>
    </div>
 </div>
 <!-- ***** Header End ***** //-->
 
 <!-- ***** Top Nav Start ***** //-->
 <div class="ls-row" id="row-topsubthemenav">
    <div class="ls-fxr" id="ls-gen5-ls-fxr">
       <div class="ls-area" id="area-topsubthemenav">
          <div class="ls-area-body" id="ls-gen6-ls-area-body">
             <div class="ls-cmp-wrap ls-1st" id="w1328897957810">
                <div class="iw_component" id="1328897957810">
                   <div class="subtheme_navigation">
                      <div class="subthemes overlayControls">
                         <menu>
                         	<li class="">
                               <a href="${ sessionVars.ecrc_props['url.justice.system'] }">B.C.'s Justice System</a>
                            </li>
                            <li class="">
                               <a href="${ sessionVars.ecrc_props['url.ministry.justice'] }">Ministry of Justice</a>
                            </li>
                            <!-- 
                            <li class="current">
                               <a href="http://www.url.com">Main menu 5</a>
                            </li>
                            -->
                         </menu>
                      </div>
                      <div class="topics">
                         <menu>
                            <li class="current">
                            	<a href="${ sessionVars.ecrc_props['url.criminal.record.checks'] }">Criminal Record Checks</a>
                            </li>
                            <li style="display: none;" class="">Sub menu 4 (end class)</li>
                         </menu>
                      </div>
                   </div>
                </div>
             </div>
          </div>
       </div>
       <div class="ls-row-clr"></div>
    </div>
 </div>
 <!-- ***** Top Nav End ***** //-->
 
<!-- ***** Bread Crumb Trail Start ***** //-->
 <div class="ls-row" id="row-subheader">
 	<div class="ls-fxr" id="ls-gen7-ls-fxr">
		<div class="ls-area" id="area-breadcrumb">
			<div class="ls-area-body" id="ls-gen8-ls-area-body">
				<div class="ls-cmp-wrap ls-1st" id="w1328897957811">
					<div class="iw_component" id="1328897957811">
						<div id="breadcrumbs">
							<ul>
								<c:forEach var="bc" items="${sessionVars.breadcrumbTree.breadcrumbs}" varStatus="status">
									<c:choose>
										<c:when test="${ status.index == fn:length(sessionVars.breadcrumbTree.breadcrumbs)-1 }">
											<li><c:out value="${bc.label}"/></li>
										</c:when>
										<c:otherwise>
											<li><c:out value="${bc.label}"/> &gt; </li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="ls-area" id="area-tools">
			<div class="ls-area-body" id="ls-gen9-ls-area-body">
				<div class="ls-cmp-wrap ls-1st" id="w1328897957812">
					<div class="iw_component" id="1328897957812"></div>
				</div>
			</div>
		</div>
		<div class="ls-row-clr"></div>
	</div>
</div>
<!-- ***** Bread Crumb Trail End ***** //-->
 
