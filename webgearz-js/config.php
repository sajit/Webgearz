<?php
if( !isset($_SESSION) ){ 
	session_start(); // required for file system
}
/* Valid Login Data - username / password for each array item */
$userNames =  array('admin'=>'admin');

/* main WebGearz path - NO trailing slash */
$wgPath = '/WebGearz';

/* Path to file system images/documents storage - WITH trailing slash */
$wgFiles = '/files/';

/* File extension for content data includes - you can use .php, .inc, .txt etc, all WebGearz includes must be the same extension */
$wgCextn = '.txt';

/* Path to page content data - NO trailing slash*/
$wgCdata = '/content';


/* Config for local "FILE MANAGER" */
$fileSystem = 1; 							// Show local file system - true = 1, false = 0 */
$serverPath = $_SERVER['DOCUMENT_ROOT']; 	// true path  - NO trailing slash
$filePages = $wgPath.'/_fslib'; 			// location of file system pages - NO trailing slash
