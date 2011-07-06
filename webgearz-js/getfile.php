<?php

include("config.php");


/*Cancel open file and replace with existing data*/
if($_POST['eData']==='cancel'){
		$realData = file_get_contents($_SERVER['DOCUMENT_ROOT'].$wgCdata."/".$_POST['file']."".$wgCextn."");
		echo $realData;

/*Save new data to open file and close file for editing*/
}elseif($_POST['eData']==='save'){

		// write data to file, and double check it can be written to
		$dataPath = $_SERVER['DOCUMENT_ROOT'].$wgCdata."/".$_POST['file']."".$wgCextn."";

		if (is_writable($dataPath)) {
				if (!$dataPath = fopen($dataPath, 'w+')) {
						 echo "Sorry I cant find the file you are looking for :(";
						 exit;
				}

				// if magic quotes turned on
				if ( get_magic_quotes_gpc() ) { 
					$_POST['nData'] = stripslashes($_POST['nData']); 
				} 
				
				if (fwrite($dataPath, $htmlData = $_POST['nData']) === FALSE) {
						echo "The file ".$_POST['file']."".$wgCextn." is not writable, please CHMOD permissions to 757 or 777";
						exit;
				}
				$replacedData = file_get_contents($_SERVER['DOCUMENT_ROOT'].$wgCdata."/".$_POST['file']."".$wgCextn."");
				echo $replacedData;
				fclose($dataPath);
		} else {
				echo "The file ".$_POST['file']."".$wgCextn." is not writable, please CHMOD permissions to 757 or 777";
		}


/*Open file to edit*/
}elseif($_POST['eData']==='edit'){
$realData = file_get_contents($_SERVER['DOCUMENT_ROOT'].$wgCdata."/".$_POST['file']."".$wgCextn."");

/*Height and width adjusted for a better fit across all browers*/
$height = ($_POST['height']-3);
$width = ($_POST['width']-5);

?>
<!-- This is the text area for editing data fetched from content files -->
<textarea name="wgta-<?php echo $_POST['file'];?>" id="wgta-<?php echo $_POST['file'];?>" cols="40" rows="6" style="margin-left:1px;border:0;height:<?php echo $height;?>px;width:<?php echo $width;?>px;overflow:hidden; Overflow-y:hidden;"><?php echo $realData;?></textarea>

<!-- This activates the in place editing text erea to be elastic -->
<script type="text/javascript">
	// <![CDATA[
	$(document).ready(function(){			
		$('#eeta-<?php echo $_POST['file'];?>').elastic();
	});	
	// ]]>
</script>

<?php
}
?>
