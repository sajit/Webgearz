<?php
// +----------------------------------------------------------------------+
// | fsLib see config.php file for more details 										      |
// +----------------------------------------------------------------------+

/**
 *
 * fslib ** came about as a nice drop and go solution that is ordered by name and can be used
 * anywhere with very little config required at all.
 * The upload files uses the SWFUpload http://www.swfupload.org/ code to make life easy which
 * is also under the MIT License (included in the directory for swfupload
 *
**/

	if (isset($_POST["PHPSESSID"])) {
		session_id($_POST["PHPSESSID"]);
	}

	session_start();
	
	if (!isset($_FILES["Filedata"]) || $_FILES["Filedata"]["error"] != 0) {
		echo "There was a problem with the upload";
		exit(0);
	} else {
		$fd = print_r($_FILES);
		// actual upload process moving data to the active folder :)
		$uploaddir = $_SESSION['dirCur'];
		$fileObject = str_replace(" ", "-", strtolower($_FILES["Filedata"]["name"]));
		$target_path = $uploaddir;
		$target_path = $target_path . basename( $fileObject );
		move_uploaded_file($_FILES['Filedata']['tmp_name'], $target_path);
		chmod($target_path, 0757);	
		echo $fd;
	}
	
		

?>