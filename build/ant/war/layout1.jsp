<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld"  prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-nested.tld"  prefix="nested" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld"  prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld"  prefix="tiles" %>
<html:html >
    <head>
        <title><tiles:getAsString name="title"/></title>

    </head>
    <body id="public">

        <bean:message key="error.no.cookies"/>
        <!-- HEADER -->
        <tiles:insert attribute="header"/>

        <tiles:insert attribute="body"/>
        <!-- FOOTER -->
        <tiles:insert attribute="footer"/>
    </body>
</html:html>