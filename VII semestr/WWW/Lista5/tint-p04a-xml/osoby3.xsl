<?xml version="1.0" encoding="ISO-8859-2"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

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
      <td><xsl:value-of select="pesel"/></td>
      <td><xsl:value-of select="imie"/></td>
      <td><xsl:value-of select="nazwisko"/></td>
      <td><xsl:value-of select="adres"/></td>
      <td><xsl:value-of select="telefon|komorka"/></td>
    </tr>
    </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>