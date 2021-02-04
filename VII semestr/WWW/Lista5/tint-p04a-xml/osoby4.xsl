<?xml version="1.0" encoding="ISO-8859-2"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="pesel">
<td><xsl:value-of select="."/></td>
</xsl:template>

<xsl:template match="imie">
<td><xsl:value-of select="."/></td>
</xsl:template>

<xsl:template match="nazwisko">
<td><xsl:value-of select="."/></td>
</xsl:template>

<xsl:template match="adres">
 <xsl:element name="td">
   <xsl:value-of select="."/>
 </xsl:element>
</xsl:template>

<xsl:template match="telefon">
 <xsl:element name="td">
   <xsl:value-of select="."/>
 </xsl:element>
</xsl:template>

<xsl:template match="komorka">
 <xsl:element name="td">
   <xsl:value-of select="."/>
 </xsl:element>
</xsl:template>

<xsl:template match="/dane_osobowe">
  <html>
  <body>
    <h2><xsl:value-of select="tytul"/></h2>
    <table border="1">
    <tr bgcolor="lightgreen">
      <th align="left">Pesel</th>
      <th align="left">Imię</th>
      <th align="left">Nazwisko</th>
      <th align="left">Adres</th>
      <th align="left">Telefon</th>
    </tr>
    <xsl:for-each select="osoba">
    <tr>
    <xsl:apply-templates/>
    </tr>
    </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>