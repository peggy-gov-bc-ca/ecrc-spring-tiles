<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  

 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--    <title><tiles:insertAttribute name="title" ignore="true" /></title> -->
		<title>${ pageTitle }</title>
        
        <!--  provides page context for js if required -->
        <script>var ctx = "${pageContext.request.contextPath}"</script> 
       
        <!-- NAMESPACE DECLARATIONS -->
		<link href="http://purl.org/dc/terms/" rel="schema.DCTERMS">
	    <link href="http://www.gov.bc.ca/metadata/mbc/terms/" rel="schema.MBCTERMS">
	    <meta name="keywords" content="user experience design, ux, design research">
	    <meta name="description" content="Introduces the benefits of design research in creating web strategy, design, content and information architecture.">
	    <link href="${pageContext.request.contextPath}/assets/css/theme1/cmf-main.css" type="text/css" rel="stylesheet">
	    <link xmlns="" type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/reset.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/layout.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/main.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/vcrc.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/cmf_footer.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/cmf_header.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/cmf_top_site_navigation.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/cmf_theme_navigation.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/cmf_topic_navigation.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/cmf_breadcrumbs.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/cmf_page_tools.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/cmf_right_column.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/cmf_theme_body.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/cmf_topic_body.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/cmf_static_content.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/print.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/jquery-ui-1.10.3.custom.min.css" rel="stylesheet">
	    
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/print.css" rel="stylesheet">
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/embedYouTube.css" rel="stylesheet">  
	    
	    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/misc.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/navigation_global.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquerycookie.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/text_sizer.js"></script>
	    <link type="text/css" href="${pageContext.request.contextPath}/assets/css/theme1/embedYouTube.css" rel="stylesheet">
	    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/carousel_controller.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/embedYouTube.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/common.js"></script>
	    
	    
	    <!-- MANDATORY METADATA BEGINS  -->
	    
	    <%-- web trends --%>
	    <meta name="WT.si_n" content="${ si_n }">
	    <meta name="WT.si_p" content="${ si_p }">
	    <meta name="DCSext.Scenario_result" content="${ scenario_result }">
	    
	    <!-- MANDATORY METADATA ENDS  -->
	    
    </head>  
	
	<body class="gov_site topic_page" onload="<tiles:insertAttribute name="body_onload" />" >
		
        <div xmlns="" class="ls-canvas" id="ls-canvas">

         <!-- ***** Top Nav Theme Start ***** //-->
         <div class="ls-row" id="row-navtheme">
            <div class="ls-fxr" id="ls-gen1-ls-fxr">
               <div class="ls-area" id="area-navtheme">
                  <div class="ls-area-body" id="ls-gen2-ls-area-body">
                     <div class="ls-cmp-wrap ls-1st" id="w1328897957808">
                        <div class="iw_component" id="1328897957808">
                           <div style="left:-3000px;position:absolute;width:500px" class="access">
                              <p>
                                 <a href="#row-content" accesskey="1">Skip to main content</a>
                              </p>
                              <p>
                                 <a href="#navigation" accesskey="n">Skip to navigation</a>
                              </p>
                              <p>The access keys for this page are:</p>
                              <ul class="access">
                                 <li>ALT plus 0 links to this site's <a href="http://www2.gov.bc.ca/gov/admin/accessibility.page?" accesskey="0">Accessibility Statement.</a></li>
                                 <li>ALT plus 1 skips to main content.</li>
                                 <li>ALT plus N skips to navigation.</li>
                              </ul>
                           </div>
                           <div id="services_header">
                              <a href="https://extranet.gov.bc.ca/forms/gov/contact/index.html" class="serviceLink">Contact Government</a><a href="http://www.newsroom.gov.bc.ca/" class="serviceLink">News</a><a href="http://www2.gov.bc.ca/en/services_finder/finder.page" class="serviceLink forms">Services &amp; Forms</a>
                           </div>
                           <div style="height: 1864px;" id="overlay"></div>
                           <div id="navigation">
                              <div class="themes overlayControls">
                                 <menu class="horizontalMenu">
                                    <li class="flyoutMenuItem current" section="topperFlyout_8CB0A2C5DC3B19401323F1A3F22D5769">
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=8CB0A2C5DC3B19401323F1A3F22D5769">Government</a>
                                       <div class="clickTrigger"></div>
                                    </li>
                                    <li class="flyoutMenuItem" section="topperFlyout_B7D67ECD8DD382E2A9F6E8F8149011E4">
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=B7D67ECD8DD382E2A9F6E8F8149011E4">Residents</a>
                                       <div class="clickTrigger"></div>
                                    </li>
                                    <li class="flyoutMenuItem" section="topperFlyout_F5415B90D214B10A6245D30D397A05A1">
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=F5415B90D214B10A6245D30D397A05A1">Education</a>
                                       <div class="clickTrigger"></div>
                                    </li>
                                    <li class="flyoutMenuItem" section="topperFlyout_1629C69A7117AC65E91C018EA07D7E44">
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=1629C69A7117AC65E91C018EA07D7E44">Business</a>
                                       <div class="clickTrigger"></div>
                                    </li>
                                    <li class="flyoutMenuItem" section="topperFlyout_E0EA3A213DB57A098786BF9F390CA86A">
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=E0EA3A213DB57A098786BF9F390CA86A">Environment</a>
                                       <div class="clickTrigger"></div>
                                    </li>
                                    <li class="flyoutMenuItem" section="topperFlyout_D5340F7BFE2793E612F265125067E05C">
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=D5340F7BFE2793E612F265125067E05C">Health &amp; Safety</a>
                                       <div class="clickTrigger"></div>
                                    </li>
                                    <li class="flyoutMenuItem" section="topperFlyout_154F929B67DE04862F97AAA8EB273445">
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=154F929B67DE04862F97AAA8EB273445">Employment</a>
                                       <div class="clickTrigger"></div>
                                    </li>
                                    <li class="flyoutMenuItem" section="topperFlyout_F9A8A09A65CE26DDE77E10C5CB32A392">
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=F9A8A09A65CE26DDE77E10C5CB32A392">About B.C.</a>
                                       <div class="clickTrigger"></div>
                                    </li>
                                 </menu>
                              </div>
                           </div>
                           <div id="flyouts">
                              <div class="flyout" id="topperFlyout_8CB0A2C5DC3B19401323F1A3F22D5769">
                                 <h2>Government</h2>
                                 <img src="${pageContext.request.contextPath}/assets/img/nav_government.png" alt="Government">
                                 <ul class="sectionLinks">
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=259D3A1F1BCB8D601B13742603415970">The Premier, Cabinet &amp; MLAs</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=36AEE53A6FC100320499FECC00FD9934">Ministries &amp; Organizations</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=FCF067811855E3B53710C9A084F94066">About the B.C. Government</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=8B6FCA5E2DBD9E7D98334EB60D6F77FE">Other Levels of Government</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=12784E4B87551818643212879D592866">Open Government</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=DCE5D2A5AB44B3928DB60555D892E611">Laws &amp; Publications</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=5154F1F564CE60C41B692056A13DEBA6">Contact Us</a>
                                    </li>
                                 </ul>
                                 <div class="topDestinations">
                                    <h3>Top Destinations</h3>
                                    <ul>
                                       <li>
                                          <a href="http://www2.gov.bc.ca/gov/theme.page?id=36AEE53A6FC100320499FECC00FD9934">B.C. Ministries</a>
                                       </li>
                                       <li>
                                          <a href="http://www.newsroom.gov.bc.ca/ministries/office-of-the-premier/biography/honourable-christy-clark.html">The Premier of B.C.</a>
                                       </li>
                                       <li>
                                          <a href="http://dir.gov.bc.ca/">B.C. Government Employee Directory</a>
                                       </li>
                                       <li>
                                          <a href="http://www.data.gov.bc.ca/dbc/search/result.page?ms=url%3Aapps.gov.bc.ca">Open Data Catalogue</a>
                                       </li>
                                    </ul>
                                 </div>
                              </div>
                              <div class="flyout" id="topperFlyout_B7D67ECD8DD382E2A9F6E8F8149011E4">
                                 <h2>Residents</h2>
                                 <img src="${pageContext.request.contextPath}/assets/img/nav_families.png" alt="Residents">
                                 <ul class="sectionLinks">
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=B18FA5C86281B72BB9914EC541216F21">People</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=C20086F4FDF627499800D60391C384E3">Government ID</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=4DE87E7EE0859251309F8142B9BD4824">Taxes &amp; Rebates</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=E2F17E8BD56D50D8D4BAA2892C68960F">Seniors</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=043ED83FA4319EF9EC01F4F5C5FD94FD">Housing</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=06309B540C84E3FCA1D318EBDF199C75">Daily Life</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=A6B43F4DDD40B1070E53BC362867CD37">Sports &amp; Recreation</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=CE442A092BA5D412CF418DFDEC3A488D">Driving &amp; Transportation</a>
                                    </li>
                                 </ul>
                                 <div class="topDestinations">
                                    <h3>Top Destinations</h3>
                                    <ul>
                                       <li>
                                          <a href="http://www.rto.gov.bc.ca/">Residential Tenancy Branch</a>
                                       </li>
                                       <li>
                                          <a href="http://www.vs.gov.bc.ca/births/index.html?SMSESSION=NO">Birth Certificates</a>
                                       </li>
                                       <li>
                                          <a href="http://www.vs.gov.bc.ca/marriage/index.html?SMSESSION=NO">Marriage Certificates</a>
                                       </li>
                                       <li>
                                          <a href="http://www.eia.gov.bc.ca/bcea.htm">Income Assistance</a>
                                       </li>
                                       <li>
                                          <a href="https://www.addresschange.gov.bc.ca/">Change of Address</a>
                                       </li>
                                       <li>
                                          <a href="http://www.welcomebc.ca/Immigrate/Immigrate.aspx">Immigration</a>
                                       </li>
                                       <li>
                                          <a href="http://www.seniorsbc.ca/">Seniors</a>
                                       </li>
                                    </ul>
                                 </div>
                              </div>
                              <div class="flyout" id="topperFlyout_F5415B90D214B10A6245D30D397A05A1">
                                 <h2>Education</h2>
                                 <img src="${pageContext.request.contextPath}/assets/img/nav_education.png" alt="Education">
                                 <ul class="sectionLinks">
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=F2DF0F6E09AD8EB24078E5EEAA8DCC68">Early Learning</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=1D321409279193982AF02BB22F985851">Kindergarten to Grade 12</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=F390915858CC9CA2072459222CBCB67C">Post-secondary Education</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=B8FE58BB8300F1E1BE88911299FEF364">Adult Learning</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=61929F8C840224021F033289E28BFCD8">Places to Learn</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=80B26AE3A0ED92412A3341AD77C50C9F">Administration</a>
                                    </li>
                                 </ul>
                                 <div class="topDestinations">
                                    <h3>Top Destinations</h3>
                                    <ul>
                                       <li>
                                          <a href="http://www2.gov.bc.ca/gov/topic.page?id=13992E4EC93F4F36B5478D37D441D626">Provincial Exams</a>
                                       </li>
                                       <li>
                                          <a href="http://www2.gov.bc.ca/gov/topic.page?id=040EB8CF78CF4F2090D9C6FFF6F3CDA0">Transcripts</a>
                                       </li>
                                       <li>
                                          <a href="http://www.studentaidbc.ca/">Student Financial Assistance</a>
                                       </li>
                                       <li>
                                          <a href="http://www.bced.gov.bc.ca/early_learning/strongstart_bc/">StrongStart BC</a>
                                       </li>
                                    </ul>
                                 </div>
                              </div>
                              <div class="flyout" id="topperFlyout_1629C69A7117AC65E91C018EA07D7E44">
                                 <h2>Business</h2>
                                 <img src="${pageContext.request.contextPath}/assets/img/nav_business.png" alt="Business">
                                 <ul class="sectionLinks">
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=A758E4D3F7E4B0B2765E9B762A328310">Business Management</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=34950904C49722331EF1DED42D10A013">Construction Industry</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=443783272A80626685CF33D5999B9E1F">Natural Resource Industries</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=00A261A2168BFDB1003DBE30998466EE">Land Use</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=4DA4C0F9EA4A8A26605350E5097DEADD">Taxes &amp; Rebates</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=1DB4C5A365801352D049F10D3923DA9A">Permits, Licences &amp; Forms</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=A28F1E9BB1BAE2E8E0E1F1877AB40D95">Water Use</a>
                                    </li>
                                 </ul>
                                 <div class="topDestinations">
                                    <h3>Top Destinations</h3>
                                    <ul>
                                       <li>
                                          <a href="http://www.bcbid.gov.bc.ca/open.dll/welcome">BC Bid</a>
                                       </li>
                                       <li>
                                          <a href="http://www.smallbusinessbc.ca/">Small Business BC</a>
                                       </li>
                                       <li>
                                          <a href="https://trade.britishcolumbia.ca/Pages/Home.aspx">Trade and Invest British Columbia</a>
                                       </li>
                                       <li>
                                          <a href="http://www2.gov.bc.ca/gov/topic.page?id=589542DDDB6347F7A7C80C1783F4BA6D">Return to PST</a>
                                       </li>
                                    </ul>
                                 </div>
                              </div>
                              <div class="flyout" id="topperFlyout_E0EA3A213DB57A098786BF9F390CA86A">
                                 <h2>Environment</h2>
                                 <img src="${pageContext.request.contextPath}/assets/img/nav_environment.png" alt="Environment">
                                 <ul class="sectionLinks">
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=4D9B65E26DFA11EF78C200B82FAD10BD">Climate Change</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=A4E25FF82206B7E3549AD2423494005C">Land</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=5CBA2ED61F5F6A004388CD25D5CBABD4">Outdoor Recreation</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=D2AEA65E1B0995C028F5415CDA3491DD">Plants &amp; Animals</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=1D1E3C96BFEE11B9960E8FFEC5AF406A">Waste Management</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=A107794D5085039872470FDB7C7FE62D">Water &amp; Air</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=3AA8459D7BBB83148579662A6237100D">Research &amp; Publications</a>
                                    </li>
                                 </ul>
                                 <div class="topDestinations">
                                    <h3>Top Destinations</h3>
                                    <ul>
                                       <li>
                                          <a href="http://www.env.gov.bc.ca/bcparks/">BC Parks</a>
                                       </li>
                                       <li>
                                          <a href="http://livingwatersmart.ca/">Living Water Smart</a>
                                       </li>
                                       <li>
                                          <a href="http://www.env.gov.bc.ca/epd/">Environmental Protection</a>
                                       </li>
                                       <li>
                                          <a href="http://www.bcairquality.ca/">Air Quality</a>
                                       </li>
                                    </ul>
                                 </div>
                              </div>
                              <div class="flyout" id="topperFlyout_D5340F7BFE2793E612F265125067E05C">
                                 <h2>Health &amp; Safety</h2>
                                 <img src="${pageContext.request.contextPath}/assets/img/nav_health.png" alt="Health &amp; Safety">
                                 <ul class="sectionLinks">
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=92F701F25B620CC26E22D695055825CA">Health Services</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=77259C98A670AC930366E75F02DB4DCB">Health Insurance</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=CB0AD8D8AEADDDD7AF685489802F31A1">Healthy Families</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=1E928AC24AE0DD5C3095566AA56AE9D1">Healthy Living</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=7E0ACCE65CCB2AA02B2F065A35515B8B">Living Safely</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=FC067FB53B135AFBB6D25E46BA3E8465">Consumer Information</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=42B39E1C633870CE99CB4A588FA3B965">B.C.'s Justice System</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=A8F32056E4192102A51A3F0FF373223C">Home &amp; Community Care</a>
                                    </li>
                                 </ul>
                                 <div class="topDestinations">
                                    <h3>Top Destinations</h3>
                                    <ul>
                                       <li>
                                          <a href="http://www.health.gov.bc.ca/msp/">Medical Services Plan</a>
                                       </li>
                                       <li>
                                          <a href="http://www.health.gov.bc.ca/msp/infoben/carecard.html">CareCard</a>
                                       </li>
                                       <li>
                                          <a href="http://www.health.gov.bc.ca/pharmacare/">PharmaCare</a>
                                       </li>
                                       <li>
                                          <a href="http://www.health.gov.bc.ca/pharmacare/plani/planiindex.html">Fair PharmaCare</a>
                                       </li>
                                       <li>
                                          <a href="http://www.ag.gov.bc.ca/courts/">Court Services</a>
                                       </li>
                                    </ul>
                                 </div>
                              </div>
                              <div class="flyout" id="topperFlyout_154F929B67DE04862F97AAA8EB273445">
                                 <h2>Employment</h2>
                                 <img src="${pageContext.request.contextPath}/assets/img/nav_employment.png" alt="Employment">
                                 <ul class="sectionLinks">
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=64FFA94D3DD30AC8BCA3EDAFC1DDE890">Work for the B.C. Government</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=B3752C36297F2A17229B69D2A8DF5C92">Job Seekers &amp; Employees</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=534F924CA519AD88CF3501C0083F18BD">Employers</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=6CE2133CB253E2842349EC69BBBC64D3">Job &amp; Career Training</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=F8DE3833782B9D2F9E6465CE12207B8E">Employment Assistance Programs</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=69A831C7AD3D243EE4E0F740B8D4990C">Employment Standards, Tribunals &amp; Boards</a>
                                    </li>
                                 </ul>
                                 <div class="topDestinations">
                                    <h3>Top Destinations</h3>
                                    <ul>
                                       <li>
                                          <a href="http://www.employment.gov.bc.ca/">B.C. Government Jobs</a>
                                       </li>
                                       <li>
                                          <a href="http://www.labour.gov.bc.ca/esb/">Employment Standards</a>
                                       </li>
                                       <li>
                                          <a href="http://www.eia.gov.bc.ca/bcea.htm">Income Assistance</a>
                                       </li>
                                       <li>
                                          <a href="http://www.servicecanada.gc.ca/eng/sc/ei/benefits/regular.shtml">Employment Insurance</a>
                                       </li>
                                       <li>
                                          <a href="http://www.labour.gov.bc.ca/esb/facshts/min-wage.htm">Minimum Wage in B.C.</a>
                                       </li>
                                       <li>
                                          <a href="http://www.pssg.gov.bc.ca/criminal-records-review/index.htm">Criminal Record Check</a>
                                       </li>
                                    </ul>
                                 </div>
                              </div>
                              <div class="flyout" id="topperFlyout_F9A8A09A65CE26DDE77E10C5CB32A392">
                                 <h2>About B.C.</h2>
                                 <img src="${pageContext.request.contextPath}/assets/img/nav_discoverbc.png" alt="About B.C.">
                                 <ul class="sectionLinks">
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=B12CD167A475FAADF24D6FB532FAEFFB">Geography &amp; Demographics</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=0339B87162CF4D379BE87E7DE5E72656">Immigrating to B.C.</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=B9142824C50F65617ABD7E2ADB07FADE">Travel</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=35C7FB7E0B28B011F57FA052DEA7A007">Recreation &amp; the Outdoors</a>
                                    </li>
                                    <li>
                                       <a href="http://www2.gov.bc.ca/gov/theme.page?id=1BC2B2B9E83F5AB87884E60783F0221B">Arts &amp; Culture</a>
                                    </li>
                                 </ul>
                                 <div class="topDestinations">
                                    <h3>Top Destinations</h3>
                                    <ul>
                                       <li>
                                          <a href="http://www.env.gov.bc.ca/fw/wildlife/hunting/regulations/">Hunting</a>
                                       </li>
                                       <li>
                                          <a href="http://www.env.gov.bc.ca/fw/fish/">Fishing</a>
                                       </li>
                                       <li>
                                          <a href="http://www.hellobc.com/british-columbia/transportation-maps/maps.aspx">Maps of B.C.</a>
                                       </li>
                                       <li>
                                          <a href="http://www.welcomebc.ca/wbc/immigration/index.page?WT.svl=Topnav">Immigration</a>
                                       </li>
                                    </ul>
                                 </div>
                              </div>
                              <div class="clear"></div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="ls-row-clr"></div>
            </div>
         </div>
         <!-- ***** Top Nav Theme End ***** //-->
         
        <tiles:insertAttribute name="header" />
        
          <!-- ***** Main Content Area (including left menu) Start ***** //-->
         <!-- ***** OPTION 1 - with content ***** //-->

         <div class="ls-row" id="row-content">
            <div class="ls-fxr" id="ls-gen10-ls-fxr">
        
                    <tiles:insertAttribute name="menu" />
                    <tiles:insertAttribute name="right"/> 
                    
                    
                    <%-- start cancel form div (used with cancel modal dialog below) --%>
					
					<div id="cancelFormDiv">
						<form:form name="cancelForm" action="${ currentPage }Cancel.htm" method="post">	
						</form:form>
					</div>
                    
                    <%-- end cancel form div --%>
                    
                    <%-- start div for simple confirmation dialog --%>
                    <c:if test="${not empty simpleDialog}">
                    <div id="simpleDialog" title="${ simpleDialog.title }">
						<p>${ simpleDialog.messageLine1 }</p>
						<p>${ simpleDialog.messageLine2 }</p>
					</div>
                    </c:if> 
                    <%-- end div for simple confirmation dialog --%>
                   
                    
                    <!--  still to be inserted --> 
                    <!-- ***** Main Content Start ***** //-->
	               <div class="ls-area" id="area-contentmain">
	                  <div class="ls-area-body" id="ls-gen13-ls-area-body">
	                     <div class="ls-cmp-wrap ls-1st" id="w1328897957819">
	                        <div class="iw_component" id="1328897957819">
	                           <div xmlns:page="ca.bc.gov.cmf.cmslite.pageContent-1.1" class="topic_body" id="wrapper">
	                           		<h1 class="heading sizable"><tiles:insertAttribute name="page_heading" ignore="true" /></h1>           
                    				<tiles:insertAttribute name="body" />      				
                    			</div>
                        	</div>
                     	</div>
                  	</div>
               	</div>
               <div class="ls-row-clr"></div>
            </div>
         </div>
         <!-- //-->
         <!-- ***** Main Content Area (including left menu) End ***** //-->	
         
         <!-- ***** Bottom Nav Start ***** //-->
         <!-- 
         <div class="ls-row" id="row-botsubthemenav">
            <div class="ls-fxr" id="ls-gen14-ls-fxr">
               <div class="ls-area" id="area-botsubthemenav">
                  <div class="ls-area-body" id="ls-gen15-ls-area-body">
                     <div class="ls-cmp-wrap ls-1st" id="w1328897957813">
                        <div class="iw_component" id="1328897957813">
                           <div class="subtheme_navigation">
                              <div class="subthemes overlayControls">
                                 <menu>
                                    <li class="">
                                       <a href="http://www.url.com">Main menu 1</a>
                                    </li>
                                    <li class="">
                                       <a href="http://www.url.com">Main menu 2</a>
                                    </li>
                                    <li class="">
                                       <a href="http://www.url.com">Main menu 3</a>
                                    </li>
                                    <li class="">
                                       <a href="http://www.url.com">Main menu 4</a>
                                    </li>
                                    <li class="current">
                                       <a href="http://www.url.com">Main menu 5</a>
                                    </li>
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
         -->
         <!-- ***** Bottom Nav End ***** //-->  
         
         <div id="cancelDialog" title="Online Submission">
			<p>Select Continue to return to the online submission process.</p>
			<p>Select Cancel to end the current submission process. Any information entered will not be saved.</p>
		 </div>
	
         <div id="eivNextDialog" title="Identity Verification Processing">
		<p>Click Continue to begin the identity verification process.</p>
		<p>Select Cancel to return and review your personal details.</p>
	 </div>		 				
                    				
         <tiles:insertAttribute name="footer" />
         
		</div>
		
		<jsp:include page="/assets/js/webtrendsHTML-jag-crc.js" />
		
    </body>
</html>
