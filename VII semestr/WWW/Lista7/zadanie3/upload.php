<?php
$IsPostFormData = (isset($_POST["sent"]) && ($_POST["sent"]=="y"));
$IsGetFormData = (isset($_GET["sent"]) && ($_GET["sent"]=="y"));
$UploadDir="".date("YmdHis")."\\";
$UploadCodes = array( "UPLOAD_ERR_OK", "UPLOAD_ERR_INI_SIZE", "UPLOAD_ERR_FORM_SIZE", "UPLOAD_ERR_PARTIAL", "UPLOAD_ERR_NO_FILE" );
$MaxFileSize=100000;
$maindoc = new DOMDocument(); 
session_start();

?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Wysyłanie plików przez formularz</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
td,th,body { font-family:Verdana, Arial, Helvetica, sans-serif; font-size:10pt; }
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="script.js"></script>
</head>

<body>

<div align="center">

<h3>Wysyłanie plików przez formularz</h3>

 <table cellpadding="4" cellspacing="2" border="1" rules="groups">
<?php
  if ( $IsPostFormData )
  {
	mkdir( $UploadDir );
   
      $UploadFile = $UploadDir.$_FILES['plik']['name'];
      $tableEl = "<tbody>";
      $tableEl .= "<tr>";
 	  if ( move_uploaded_file( $_FILES['plik']['tmp_name'], $UploadFile ) )
	  {
        $tableEl .= "<td colspan=\"2\" style=\"color:green\">Plik został poprawnie uploadowany i skopiowany w nowe miejsce<br>$UploadFile</td></tr>";
      }
      else
      {
        $tableEl .= "<td colspan=\"2\" style=\"color:green\">Błąd przy uploadowaniu</td>";
      }
	  $tableEl .= "</tr>";
      $tableEl .= "<tr><td style=\"color: navy; text-align: left\">Nazwa wysłanego pliku (po stronie klienta):</td><td>".$_FILES["plik"]["name"]."</td></tr>\n";
      $tableEl .= "<tr><td style=\"color: navy; text-align: left\">Typ zawartości wysłanego pliku:</td><td>".$_FILES["plik"]["type"]."</td></tr>\n";
      $tableEl .= "<tr><td style=\"color: navy; text-align: left\">Rozmiar wysłanego pliku:</td><td>".$_FILES["plik"]["size"]."</td></tr>\n";
      $tableEl .= "<tr><td style=\"color: navy; text-align: left\">Nazwa pliku na serwerze:</td><td>".$_FILES["plik"]["tmp_name"]."</td></tr>\n";
      $tableEl .= "<tr><td style=\"color: navy; text-align: left\">Kod błędu związany z wysłaniem pliku:</td><td>".$UploadCodes[$_FILES["plik"]["error"][$i]].":".$_FILES["plik"]["error"]."</td></tr>\n";
	  $tableEl .= "</tbody>";
   echo $tableEl;
   array_push($_SESSION["files"], $_FILES["plik"]["name"]);
	} else {
      $_SESSION["files"] = [];
   }
?>
</table>

<table cellpadding="4" cellspacing="2" border="1" rules="groups">
<?php
   echo "<tbody>";
   for( $i=0; $i<count( $_SESSION["files"] ); $i++ )
   {
      echo "<tr>";
      echo "<td style=\"color: navy; text-align: left\">Nazwa wysłanego pliku (po stronie klienta):</td><td>".$_SESSION["files"][$i]."</td>\n";
      echo "<td><button class=\"btnDelete\">Delete</button></td>";
      echo "</tr>";
   }
   echo "</tbody>";
?>
</table>
 
<form action="upload.php" method="post" enctype="multipart/form-data" name="plik">
<input type="hidden" name="MAX_FILE_SIZE" value="<?php echo $MaxFileSize;?>">
<table cellpadding="2" cellspacing="0" border="0" bgcolor="#00FF00">
<tr><td>Plik:</td><td><input type="file" name="plik" size="40"></td></tr>
<tr><td colspan="2"><input type="submit" name="Submit" value="Wyślij"></td></tr>
</table>
<input type="hidden" name="sent" value="y">
</form>

</div>

</body>
</html>
