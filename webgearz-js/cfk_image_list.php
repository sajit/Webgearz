<?php
header("Cache-Control: no-store, no-cache");
include("config.php");

function scanFiles($base='', &$data=array()) {
	global $serverPath;
  $array = array_diff(scandir($base), array('.', '..')); # remove ' and .. from the array */
  foreach($array as $value){ /* loop through the array at the level of the supplied $base */
    if (is_dir($base.$value)){/* if this is a directory then make a recursive call*/
      $data = scanFiles($base.$value.'/', $data); 
    }elseif (is_file($base.$value)) { /* else if the current $value is a file */
      $data[] = $base.$value; /* just add the current $value to the $data array */
    }
  }

  /* work out return data order */
	$dataManip=array();
  foreach($data as $val){ 
		$count = count( explode('/',str_replace($serverPath,'',$val)) );
		$dataManip[] = $count.'|'.str_replace($serverPath,'',$val); 
	}
	asort($dataManip);

  /* format return data order */
  $returnData=array();
  foreach($dataManip as $val){ 
		$returnData[] = substr($val, 2); 
	}

  return $returnData; // return the $data array
  
}

/* Call for all files in selected directory */
  $fileData = '';
	$validExtensions = array('gif','jpeg','jpg','png','bmp'); // valid images
	foreach(scanFiles($serverPath.$wgFiles) as $val){ 
		$valC = explode('.',$val);
		if( in_array($valC[1], $validExtensions) ){ /* remove non image files */
			$fileData .= '<a href="#" onclick="setLink(\''.$val.'\');">'. str_replace($wgFiles,'',$val).'</a><br/>';
		}
	}
	
//echo substr($fileData, 0,-1).'<br/>';
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <title>Content Editor</title>
	<script type="text/javascript">
		function setLink(url) {
			window.opener.CKEDITOR.tools.callFunction(1, url);
			window.close();
		}
	</script>
	<style type="text/css">
		/*##### START: Reset Style #####*/
		html, body, div, h1, h2, h3, h4, h5, h6, ul, ol, dl, li, dt, dd, p, blockquote, pre, form, fieldset, table, th, td { background: none; margin: 0; padding: 0; border: 0 ; }
		/*##### END: Reset Style #####*/
		
		/*##### START: HTML Main #####*/
		body {  font: 62.5% "Century Gothic", Geneva, Verdana, Arial, Helvetica, sans-serif; color: #4C1803;padding:10px;}
		#content {background: #fff;width:100%;}
	</style>
  </head>
<body>
  <div id="content">
<h2>List of images you have uploaded</h2>  	
<?php

/* echo text file required for JS used in list of images */
print $fileData;


?>
  </div>
</body>
</html>



