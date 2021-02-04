<?php
$IsPostFormData = (isset($_POST["sent"]) && ($_POST["sent"]=="y"));
$IsGetFormData = (isset($_GET["sent"]) && ($_GET["sent"]=="y"));
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Zapłata kartą kredytowa</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
td,th,body { font-family:Verdana, Arial, Helvetica, sans-serif; font-size:10pt; }
</style>
<link rel="stylesheet" href="style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="script.js"></script>
</head>

<body>

<h3 align="center">Zapłata kartą kredytowa</h3>

<form name="dane" action="daneget.php" method="get">
<table align="center" cellpadding="4" cellspacing="2" border="0" bgcolor="#FF9900">
<tr><th align="left">Numer karty:</th><td><input name="numer_karty[]" type="text" size="4" maxlength="4" value="<?php echo isset($_GET["numer_karty"]) ? $_GET["numer_karty"][0] : ""; ?>">
                                          <input name="numer_karty[]" type="text" size="4" maxlength="4" value="<?php echo isset($_GET["numer_karty"]) ? $_GET["numer_karty"][1] : ""; ?>">
                                          <input name="numer_karty[]" type="text" size="4" maxlength="4" value="<?php echo isset($_GET["numer_karty"]) ? $_GET["numer_karty"][2] : ""; ?>">
                                          <input name="numer_karty[]" type="text" size="4" maxlength="4" value="<?php echo isset($_GET["numer_karty"]) ? $_GET["numer_karty"][3] : ""; ?>"></td></tr>
<tr><th align="left">Data ważności:</th><td><input name="data_waznosci" type="text" size="5" maxlength="5" value="<?php echo isset($_GET["data_waznosci"]) ? $_GET["data_waznosci"] : ""; ?>"></td></tr>
<tr><th align="left">CVC:</th><td><input name="cvc" type="password" size="3" maxlength="3" value=""></td></tr>
<tr><th align="left">Imię:</th><td><input name="imie" type="text" size="15" maxlength="20" value="<?php echo isset($_GET["imie"]) ? $_GET["imie"] : ""; ?>"></td></tr>
<tr><th align="left">Nazwisko:</th><td><input name="nazwisko" type="text" size="20" maxlength="40"  value="<?php echo isset($_GET["nazwisko"]) ? $_GET["nazwisko"] : ""; ?>"></td></tr>
<tr><th align="left">e-mail:</th><td><input name="e-mail" type="text" size="20" maxlength="40"  value="<?php echo isset($_GET["e-mail"]) ? $_GET["e-mail"] : ""; ?>"></td></tr>	
<tr><th align="left">Numer telefonu:</th><td><input name="numer_telefonu" type="text" size="20" maxlength="40"  value="<?php echo isset($_GET["numer_telefonu"]) ? $_GET["numer_telefonu"] : ""; ?>"></td></tr>
<tr><th align="left">Kwota:</th><td><input name="kwota" type="number" size="5" maxlength="9" value="<?php echo isset($_GET["kwota"]) ? $_GET["kwota"] : ""; ?>"></td></tr>	
<tr><td align="right" colspan="2"><input id="submit_button" type="button" value="Wyślij"></td></tr>
</table>
<input name="sent" type="hidden" value="y">
</form>

<?php
if ( $IsGetFormData ):
?>
<table id="response_table" cellpadding="4" cellspacing="2" border="1" align="center">
<tr><th>Numer karty:</th><td><?php echo join($_GET["numer_karty"],"-"); ?></td></tr>
<tr><th>Data ważności:</th><td><?php echo $_GET["data_waznosci"]; ?></td></tr>
<tr><th>Imię:</th><td><?php echo $_GET["imie"]; ?></td></tr>
<tr><th>Nazwisko:</th><td><?php echo $_GET["nazwisko"]; ?></td></tr>
<tr><th>e-mail:</th><td><?php echo $_GET["e-mail"]; ?></td></tr>
<tr><th>Numer telefonu:</th><td><?php echo $_GET["numer_telefonu"]; ?></td></tr>
<tr><th>Kwota:</th><td><?php echo $_GET["kwota"]; ?></td></tr>
</table>
<?php
endif;
?>

</body>
</html>