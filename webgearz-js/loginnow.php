<?php
session_start();

include("config.php");

/*Check array against user and pass combinations*/
if($userNames[$_GET['username']]===$_GET['password']&&$_GET['username']&$_GET['password']){
		$_SESSION['WebGearz'] = 'wgLoggedIn';
		echo 'success';
}else{
		echo 'fail';
}

