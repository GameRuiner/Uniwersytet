<?php
$IsPostFormData = (isset($_POST["sent"]) && ($_POST["sent"]=="y"));
$IsGetFormData = (isset($_GET["sent"]) && ($_GET["sent"]=="y"));
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Dane osobowe</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
td,th,body { font-family:Verdana, Arial, Helvetica, sans-serif; font-size:10pt; }
</style>
</head>

<body>

<h3 align="center">Dane osobowe</h3>

<form name="dane" action="dane.php" method="get">
<table align="center" cellpadding="4" cellspacing="2" border="0" bgcolor="#FF9900">
<tr><th align="left">Imię:</th><td><input name="imie" type="text" size="15" maxlength="20" value=""></td></tr>
<tr><th align="left">Nazwisko:</th><td><input name="nazwisko" type="text" size="20" maxlength="40" value=""></td></tr>
<tr><th align="left">Adres:</th><td><input name="adres[]" type="text" size="6" maxlength="6" value="">
                                    <input name="adres[]" type="text" size="10" maxlength="20" value="">
                                    <input name="adres[]" type="text" size="20" maxlength="30" value=""></td></tr>
<tr><th align="left">PESEL:</th><td><input name="pesel" type="text" size="11" maxlength="11" value=""></td></tr>
<tr><th align="left">Płeć:</th><td><input name="plec" type="radio" value="m"> Mężczyzna&nbsp;&nbsp;
                                   <input name="plec" type="radio" value="k"> Kobieta</td></tr>
<tr><th align="left">Ulubione kolory:</th><td>
<select name="kolory[]" size="4" multiple>
<option value="zielony">Zielony</option>
<option value="niebieski">Niebieski</option>
<option value="czerwony">Czerwony</option>
<option value="czarny">Czarny</option>
<option value="fioletowy">Fioletowy</option>
</select>
</td></tr>								   
<tr><td align="right" colspan="2"><input type="submit" value="Wyślij"></td></tr>
</table>
<input name="sent" type="hidden" value="y">
</form>

<?php
if ( $IsGetFormData ):
?>
<table cellpadding="4" cellspacing="2" border="1" align="center">
<tr><th>Imi�:</th><td><?php echo $_GET["imie"]; ?></td></tr>
<tr><th>Nazwisko:</th><td><?php echo $_GET["nazwisko"]; ?></td></tr>
<tr><th>Adres:</th><td><?php echo join($_GET["adres"],", "); ?></td></tr>
<tr><th>PESEL:</th><td><?php echo $_GET["pesel"]; ?></td></tr>
<tr><th>P�e�</th><td><?php echo $_GET["plec"]; ?></td></tr>
<tr><th>Kolorki:</th><td><?php echo join( ( isset( $_GET["kolory"] ) ? $_GET["kolory"] : array() ), ", " ); ?></td></tr>
</table>
<?php
endif;
?>

</body>
</html>