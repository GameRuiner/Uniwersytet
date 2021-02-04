<?xml version="1.0" encoding="ISO-8859-2"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
			      xmlns:fo="http://www.w3.org/1999/XSL/Format">

<xsl:template match="/dane_osobowe">

<fo:table-and-caption>
<fo:table-caption>
  <xsl:value-of select="tytul"/>
</fo:table-caption>

<fo:table>

<fo:table-header>
  <fo:table-row>
    <fo:table-cell>
      <fo:block font-weight="bold">Pesel</fo:block>
    </fo:table-cell>
    <fo:table-cell>
      <fo:block font-weight="bold">Imię</fo:block>
    </fo:table-cell>
    <fo:table-cell>
      <fo:block font-weight="bold">Nazwisko</fo:block>
    </fo:table-cell>
    <fo:table-cell>
      <fo:block font-weight="bold">Adres</fo:block>
    </fo:table-cell>
    <fo:table-cell>
      <fo:block font-weight="bold">Telefon</fo:block>
    </fo:table-cell>
  </fo:table-row>
</fo:table-header>

<fo:table-body>
  <xsl:for-each select="osoba">
    <fo:table-row>
      <fo:table-cell><fo:block><xsl:value-of select="pesel"/></fo:block></fo:table-cell>
      <fo:table-cell><fo:block><xsl:value-of select="imie"/></fo:block></fo:table-cell>
      <fo:table-cell><fo:block><xsl:value-of select="nazwisko"/></fo:block></fo:table-cell>
      <fo:table-cell><fo:block><xsl:value-of select="adres"/></fo:block></fo:table-cell>
      <fo:table-cell><fo:block><xsl:value-of select="telefon|komorka"/></fo:block></fo:table-cell>
   </fo:table-row>
 </xsl:for-each>
</fo:table-body>

</fo:table>
</fo:table-and-caption>

</xsl:template>

</xsl:stylesheet>