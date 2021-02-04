<?php
session_start();
if (!isset($_SESSION['count'])) {
   $_SESSION['count'] = 1;
} else {
   $_SESSION['count']++;
}
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Licznik</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<body>

<?php
  echo "<p>Odwiedziłeś tą stronę: ".$_SESSION["count"]." razy</p>";
  if ($_SESSION["count"]>9) unset($_SESSION["count"]);
?>

</body>
</html>
