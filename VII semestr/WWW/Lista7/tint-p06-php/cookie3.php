<?php
  $dwatygodnie = time() + ( 60 * 60 * 24 * 14 );
  if ( isset( $_COOKIE["odwiedziny"] ) )
  {
    $ostatnie_odwiedziny = $_COOKIE["odwiedziny"];
  }
  else
  {
    $ostatnie_odwiedziny = "dawnotemu";
  }
  setcookie("odwiedziny", time(), $dwatygodnie);
  //setcookie("odwiedziny", "", time()-3600)
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Odwiedziny</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
td,th,body { font-family:Verdana, Arial, Helvetica, sans-serif; font-size:10pt; }
</style>
</head>

<body>

<h3>Odwiedziny</h3>

<?php
  if ( $ostatnie_odwiedziny=="dawnotemu" )
  {
    echo "<p>Nie było Cię tu już ponad 2 tygodnie</p>";
  }
  else
  {
    echo "<p>Ostatni raz byłeś tutaj ".date("d F Y", $ostatnie_odwiedziny)." o ".date("h:i:s a", $ostatnie_odwiedziny)."</p>";
  }
?>

</body>
</html>
