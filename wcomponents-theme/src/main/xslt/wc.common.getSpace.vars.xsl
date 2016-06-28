<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:ui="https://github.com/bordertech/wcomponents/namespace/ui/v1.0" 
	xmlns:html="http://www.w3.org/1999/xhtml" version="1.0">
	
	<!-- 
		Variables for hgap, vgap, gap and margin.
		Split out here to ease implementation overrides.
	-->

	<!-- an arbitrary number which defines the upper limit of when a space is 'small'. 4px is ~ 0.25rem. -->
	<xsl:variable name="smallgap" select="4"/>
	<!-- an arbitrary number which defines the upper limit of when a space is 'medium'. 8px is ~ 0.5rem. -->
	<xsl:variable name="medgap" select="8"/>
	<!-- an arbitrary number which defines the upper limit of when a space is 'large'. 16px is ~ 1rem. -->
	<xsl:variable name="lggap" select="16"/>
	<!-- Any space larger than $lggap is 'extra large' -->
	
	<xsl:template name="getSizeClassExtension">
		<xsl:param name="gap"/>
		<xsl:choose>
			<xsl:when test="$gap &lt;= $smallgap">
				<xsl:text>sm</xsl:text>
			</xsl:when>
			<xsl:when test="$gap &lt;= $medgap">
				<xsl:text>med</xsl:text>
			</xsl:when>
			<xsl:when test="$gap &lt;= $lggap">
				<xsl:text>lg</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>xl</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
