<?php
$IsPostFormData = (isset($_POST["sent"]) && ($_POST["sent"]=="y"));
$IsGetFormData = (isset($_GET["sent"]) && ($_GET["sent"]=="y"));
$UploadDir="C:\\WWWUpload\\".date("YmdHis")."\\";
$UploadCodes = array( "UPLOAD_ERR_OK", "UPLOAD_ERR_INI_SIZE", "UPLOAD_ERR_FORM_SIZE", "UPLOAD_ERR_PARTIAL", "UPLOAD_ERR_NO_FILE" );
$MaxFileSize=100000;
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Wysyłanie plików przez formularz</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
td,th,body { font-family:Verdana, Arial, Helvetica, sans-serif; font-size:10pt; }
</style>
</head>

<body>

<div align="center">

<h3>Wysyłanie plików przez formularz</h3>

<?php
  if ( $IsPostFormData )
  {
	mkdir( $UploadDir );
 ?>
 <table cellpadding="4" cellspacing="2" border="1" rules="groups">
 <?php
    for( $i=0; $i<count( $_FILES["pliki"]["name"] ); $i++ )
	{
      $UploadFile = $UploadDir.$_FILES['pliki']['name'][$i];
	  if ( is_uploaded_file( $_FILES['pliki']['tmp_name'][$i] ) )
	  {
        if ( $_FILES['pliki']['size'][$i] > $MaxFileSize )
        { 
          echo "<td colspan=\"2\" style=\"color:green\">Plik nr $i jest za duży</td>"; 
        }
        else
        { 
  	      echo "<tbody>";
	      echo "<tr>";
  	      if ( move_uploaded_file( $_FILES['pliki']['tmp_name'][$i], $UploadFile ) )
	      {
            echo "<td colspan=\"2\" style=\"color:green\">Plik został poprawnie uploadowany i skopiowany w nowe miejsce<br>$UploadFile</td></tr>";
          }
          else
          {
            echo "<td colspan=\"2\" style=\"color:green\">Błąd przy uploadowaniu</td>";
          }
	      echo "</tr>";
          echo "<tr><td style=\"color: navy; text-align: left\">Nazwa wysłanego pliku (po stronie klienta):</td><td>".$_FILES["pliki"]["name"][$i]."</td></tr>\n";
          echo "<tr><td style=\"color: navy; text-align: left\">Typ zawartości wysłanego pliku:</td><td>".$_FILES["pliki"]["type"][$i]."</td></tr>\n";
          echo "<tr><td style=\"color: navy; text-align: left\">Rozmiar wysłanego pliku:</td><td>".$_FILES["pliki"]["size"][$i]."</td></tr>\n";
          echo "<tr><td style=\"color: navy; text-align: left\">Nazwa pliku na serwerze:</td><td>".$_FILES["pliki"]["tmp_name"][$i]."</td></tr>\n";
          echo "<tr><td style=\"color: navy; text-align: left\">Kod błędu związany z wysłaniem pliku:</td><td>".$UploadCodes[$_FILES["pliki"]["error"][$i]].":".$_FILES["pliki"]["error"][$i]."</td></tr>\n";
	      echo "</tbody>";
        }
	  }
	}
?>
</table>
<?php
  }
?>

<form action="upload2.php" method="post" enctype="multipart/form-data" name="plik">
<input type="hidden" name="MAX_FILE_SIZE" value="<?php echo $MaxFileSize;?>">
<table cellpadding="2" cellspacing="0" border="0" bgcolor="#00FF00">
<tr><td>Plik 1:</td><td><input type="file" name="pliki[]" size="40"></td></tr>
<tr><td>Plik 2:</td><td><input type="file" name="pliki[]" size="40"></td></tr>
<tr><td>Plik 3:</td><td><input type="file" name="pliki[]" size="40"></td></tr>
<tr><td>Plik 4:</td><td><input type="file" name="pliki[]" size="40"></td></tr>
<tr><td colspan="2"><input type="submit" name="Submit" value="Wyślij"></td></tr>
</table>
<input type="hidden" name="sent" value="y">
</form>

</div>

</body>
</html>
