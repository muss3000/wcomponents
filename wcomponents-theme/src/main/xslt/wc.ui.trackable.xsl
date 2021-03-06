<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ui="https://github.com/bordertech/wcomponents/namespace/ui/v1.0" 
	xmlns:html="http://www.w3.org/1999/xhtml" version="2.0">
	<!--
		ui:analytic is the tracking info element for ui:application and ui:ajaxresponse, and ui:tracking is a complex
		analytics config element for any trackable WComponent.
		
		This is being dropped as it was never implementable and regular web analytics does th job better.
	-->
	<xsl:template match="ui:analytic|ui:tracking"/>
</xsl:stylesheet>
