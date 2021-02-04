<?php
$values = array( 'one'=>'cookieone', 'two'=>'cookietwo', 'three'=>'cookiethree' );
foreach ( $values as $k => $v ) setcookie( "cookie[$k]", $v );
//foreach ( $values as $k => $v ) setcookie( "cookie[$k]", "", time()-3600 );
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Ciasteczka</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
td,th,body { font-family:Verdana, Arial, Helvetica, sans-serif; font-size:10pt; }
</style>
</head>

<body>

<h3>Ciacha</h3>

<?php if ( isset($_COOKIE['cookie'] ) )
{
	while ( list( $name, $value )=each( $_COOKIE['cookie'] ) )
	{
		echo "$name: $value<br>\n";
	}
}
?>
</body>
</html>