<?xml version="1.0" encoding="ISO-8859-2"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fn="http://www.w3.org/2005/02/xpath-functions">

<xsl:template match="/dostawy/dostawa">
 <xsl:for-each select="child::produkt/child::nazwa/parent::*/attribute::produktid[ . &lt; 30000]">
   <div><xsl:value-of select="."/></div>
 </xsl:for-each>
 
 </xsl:template>

</xsl:stylesheet>