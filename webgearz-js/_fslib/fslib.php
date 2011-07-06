<?php
// +----------------------------------------------------------------------+
// | fsLib ssee config.php file for more details 										      |
// +----------------------------------------------------------------------+


/* ### LISTDIR desciption ###
 * listDir(string $string, string $string, string $string[default 0], string $string );
 * Four variables first is base path, second is path to files, third variable is
 * used internally for the system positioning and is to be FALSE on first call,
 * fifth variable is optional and is for overidding the default directory of the
 * library and its files
 *
*/
error_reporting(0);

session_start();
include ('../config.php');
function listDir($path,$display,$startDir=0){
error_reporting(0);
global $filePages;

$_SESSION['dirCur'] = $path.$display;

	$pathDis = $path.''.$display;

		function previewTrue($display,$file,$type){
		global $filePages;
			$previewFiles = array('bmp','gif','jpg','png');
			if (in_array(strtolower($type), $previewFiles)) {
				return "
				<a href='".$display."".$file."' target='_blank'><img src='$filePages/res/world_go.png' alt='Download File' title='Download File' /></a>
				<a href='".$display."".$file."' rel='prettyPhoto'><img src='$filePages/res/zoom.png' alt='File Name: ".$display."".$file."' title='Preview File' /></a>";
			}else{
				return "<a href='".$display."".$file."' target='_blank'><img src='$filePages/res/world_go.png' alt='Download File' title='Download File' /></a>";
			}
		}

		function sizeConv($size){
				$i=0; $iec = array("b", "kb", "mb", "gb", "tb");
				while (($size/1024)>1) { $size=$size/1024; $i++;}
				return(round($size,1)." ".$iec[$i]);
		}

		function fileImg($fImg){
		global $filePages;

		$fDisplay = $filePages.'/res/'.strtolower($fImg).'.gif';
		  /* add in more file extensions here with the associated graphics (and for gods sake I really should order these by alpha)*/
			$fileTypeArr = array('asp','bmp','doc','file','gif','html','jpg','mdb','pdf','php','psd','txt','csv','xls','xml','odt','ods','zip','png','docx','sql','xlsx','gz','tar','rar','ai','css','js','swf','fla','wmv','mov','qt','real','rm','mpeg','mp2','mp3','mp3','avi');
				if (in_array(strtolower($fImg), $fileTypeArr)) {
						return $fDisplay;
				}else{
						return $filePages.'/res/unknown.gif';
				}
		}

		if ($handle = opendir($pathDis)) {
				while (false !== ($file = readdir($handle))) {
						if ($file != "." && $file != "..") {
								$pathfile = $pathDis . '/' . $file;
								$fileExtension = explode(".",$file);
								$fileDetails = array('type' => filetype($pathfile),'name' => $file,'filepath' => $pathfile,'size' => filesize($pathfile),'ext' => $fileExtension[1],'time' => date("d M y H:i", filemtime($pathfile)),'perm' => substr(sprintf('%o', fileperms($pathfile)), -4));
								$fileData[] = $fileDetails;
						}
				}
		closedir($handle);
}

if( $startDir===0 ){
echo "
<div id='fileSystem'>
<div id='filePanel'>
<a href='javascript:void(0)' id='createDirectory' title='Create Directory' ><img src='$filePages/res/folder_add.png' alt='Create Directory' /> Create Directory</a>
&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' id='fileUpload' title='Upload Files' ><img src='$filePages/res/page_add.png' alt='Upload Files' /> Upload Files </a>
<div id='dirCreate' style='display:none;'><div class='inner'> <img src='$filePages/res/cancel.png'  title='Close Window' alt='Close Window' id='cancelDir'/>

<form action='javascript:void(0)' method='post' id='form-directory'>
<fieldset><legend>Create Directory</legend>
<div class='inner'>
<label for='dirName'>New Directory Name:</label>&nbsp;&nbsp;<input type='text' name='dirName' id='dirName' maxlength='30' />&nbsp;&nbsp;
<button type='submit'>Create Directory</button>
<p><em>Limited to 30 characters all lower case, with no spaces or special characters.</em></p>
<p id='responseDir' style='display:none;'>response</p>
</div>
</fieldset>
</form>

</div>
</div>

<div id='uploadFile' style='display:none;'><div class='inner'> <img src='$filePages/res/cancel.png' title='Close Window' alt='Close Window' id='cancelFile'/>

						<div id='flashTarget'>
							<fieldset class='flash' id='fsUploadProgress'>
								<legend>Uploaded File/s</legend>
								<p>Upload limit of 12 items at a time, with a maximum upload size of 10mb per file.</p>
								<p>Hold the shift key while clicking on files to select multiple files for simultaneous upload.</p><br />
							<div class='btnholder'>
								<p id='divStatus'>0 Files Uploaded</p>
								<span id='spanButtonPlaceholder'></span> 
								<input id='btnCancel' type='button' value='Cancel All Uploads' disabled='disabled' style='margin-left: 2px; height: 22px; font-size: 8pt;' />
								<br/>
								
							</div>
							
							<noscript style='background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px;'>
								Were sorry.  SWFUpload could not load.  You must have JavaScript enabled to enjoy SWFUpload.
							</noscript>
							<div id='divLoadingContent' class='content' style='background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;'>
					
								SWFUpload is loading. Please wait a moment...
							</div>
							<div id='divLongLoading' class='content' style='background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;'>
								SWFUpload is taking a long time to load or the load has failed.  Please make sure that the Flash Plugin is enabled and that a working version of the Adobe Flash Player is installed.
							</div>
							<div id='divAlternateContent' class='content' style='background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;'>
								Were sorry.  SWFUpload could not load.  You may need to install or upgrade Flash Player.
								Visit the <a href='http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash'>Adobe website</a> to get the Flash Player.
							</div>


							</fieldset>
						</div>

</div></div>
</div>

<div class='fileSystemWrap'>
<table id='dir-files' cellspacing='0' cellpadding='0'>
<thead>
	<tr>
		<th>File Name</th>
		<th class='size'>Size</th>
		<th class='modTime'>Modified</th>
		<th class='options' width='110'>Options</th>
	</tr>
</thead>
<tbody>

";
}

if( $startDir===0 ){
echo "
<tr style='display:none;'>
<td><input type='hidden' id='upDirData' value='".substr($display, 0, -1)."/'/><input type='hidden' name='currentDirCreate' value='".$pathDis."' id='currentDirCreate' /> <input type='hidden' name='dirDisplay' id='dirDisplay' value='".$display."'/> <input type='hidden' name='dirBase' id='dirBase' value='".$path."'/></td><td class='size'></td><td class='modTime'> </td><td class='options'></td>
</tr>";
}else{
echo "
<tr class='directory ..root' id='dirUp'>
<td><a href='javascript:void(0);' title='Directory: go up one directory' class='directoryUp' rel='$path|".substr($display, 0, -1)."|..root'><img src='".$filePages."/res/folder-up.png' alt='Directory: go up one directory' /> <span>..</span></a> <input type='hidden' id='upDirData' value='".substr($display, 0, -1)."/'/>
<input type='hidden' name='currentDirCreate' value='".$pathDis."' id='currentDirCreate' /> <input type='hidden' name='dirDisplay' id='dirDisplay' value='".$display."'/> <input type='hidden' name='dirBase' id='dirBase' value='".$path."'/></td><td class='size'></td><td class='modTime'> </td><td class='options'></td>
</tr>";
}
if(count($fileData) > 0){
// start display
		asort($fileData); // sort by name directory count then file path etc...
				$fCount = count($fileData);
				foreach ($fileData as &$v) {

/*Aligned in this manner for ease of view source*/
if($stripe==='stripe '){$stripe='';}else{$stripe='stripe ';}

if($v[type]!=='dir'&&$v[perm]==='0777'||$v[type]!=='dir'&&$v[perm]==='0757'||$v[type]!=='dir'&&$v[perm]==='0755'){
	echo "
		<tr class='".$stripe."$display$v[name]'>";
	echo "<td><img src='".fileImg($v[ext])."' alt='$v[name]' /> $v[name]  <span class='pathDisplay'>Path: $display$v[name]</span></td><td class='size'>".sizeConv($v[size])."</td><td class='modTime'>$v[time]</td><td class='options'>".previewTrue($display,$v[name],$v[ext])."  <a href='javascript:void(0);' rel='$display,$v[name],$v[type],$path' class='deleteThis' title='Delete this item'><img src='$filePages/res/cross.png' alt='Delete File'/></a>  <a href='javascript:void(0);' rel='$display,$v[name],$v[type],$path' class='rename' title='Rename File'><img src='$filePages/res/page_edit.png' alt='Rename File' /></a></td>";
	echo "</tr>";

}elseif($v[type]!=='dir'&&$v[perm]!=='0777'||$v[type]!=='dir'&&$v[perm]!=='0757'||$v[type]!=='dir'&&$v[perm]!=='0755'){
	echo "
		<tr class='".$stripe."$display$v[name]'>";
	echo "<td><img src='".fileImg($v[ext])."' alt='$v[name]' />  $v[name]  <span class='pathDisplay'>Path: $display$v[name]</span></td><td class='size'> </td><td class='modTime'> </td><td class='options'>".previewTrue($display,$v[name],$v[ext])."</td>";
	echo "</tr>";

}elseif($v[type]!=='dir'){
	echo "
		<tr class='".$stripe."$display$v[name]'>";
	echo "<td><img src='".fileImg($v[ext])."' alt='$v[name]' />  $v[name]  <span class='pathDisplay'>Path: $display$v[name]</span></td><td class='size'>".sizeConv($v[size])."</td><td class='modTime'>$v[time]</td><td class='options'>".previewTrue($display,$v[name],$v[ext])."</td>";
	echo "</tr>";

}elseif($v[type]==='dir'&&$v[perm]==='0777'||$v[type]==='dir'&&$v[perm]==='0757'||$v[type]==='dir'&&$v[perm]==='0755'){
	echo "
		<tr class='".$stripe."directory $display$v[name]'>";
	echo "<td><a href='javascript:void(0);' title='Directory: $v[name]' class='directory' rel='$path|$display$v[name]/'><img src='".$filePages."/res/folder.png' alt='Directory: $v[name]' /> <span>$v[name]</span></a></td><td class='size'></td><td class='modTime'>$v[time]</td><td class='options'> <a href='javascript:void(0);' rel='$display,$v[name],$v[type],$path' class='deleteThis' title='Delete this item'><img src='$filePages/res/cross.png' alt='Delete Directory'/></a>  <a href='javascript:void(0);' rel='$display,$v[name],$v[type],$path' class='rename' title='Rename File'><img src='$filePages/res/page_edit.png' alt='Rename File' /></a></td>";
	echo "</tr>";

}elseif($v[type]==='dir'){
	echo "
		<tr class='".$stripe."directory $display$v[name]'>";
	echo "<td><a href='javascript:void(0);' title='Directory: $v[name]' class='directory' rel='$path|$display$v[name]/'><img src='".$filePages."/res/folder.png' alt='Directory: $v[name]' /> <span>$v[name]</span></a></td><td class='size'></td><td class='modTime'>$v[time]</td><td class='options'></td>";
	echo "</tr>";
}

	 }

	 }

if( $startDir===0 ){
echo "

 </tbody>
 </table>

</div>
</div>";

echo "
<input type='hidden' id='startDir' value='$display'/>
<div id='fslibDialog'>
	<div class='fslibDialog'>
		<div class='header'><span>DELETE</span></div>
		<span class='text'>Warning</span>
		<input class='inputName' id='newName' name='newName' type='text' maxlength='30'/>
		<div class='buttons'>
		<button type='submit' id='choice-no' class='no simplemodal-close'>NO</button>
		<button type='submit' id='choice-yes' class='choice-yes' class='simplemodal-close' onclick='changeItem(this.id);'>YES</button>
		</div>
	</div>
</div>
";
}
}

/*### File Actions ###*/

/*
 * clean inputs allowing alphanumeric only with underscore hyphen and period only
 * also replace all spaces with a hyphen
*/
function cleanInputs($data){
	return ereg_replace("[^A-Za-z0-9._-]", "", str_replace(" ", "-", strtolower($data)) );
}

/*Change directory down*/
if(isset($_POST['changeDir'])){
	listDir($_POST['dirList'],$_POST['dirBase'],$_POST['startDir']);
}

/*Change directory up*/
if(isset($_POST['changeDirUp'])){
	$dirBase = explode("/",$_POST['dirBase']);
	$dirBaseRem = array_pop($dirBase);
	foreach ($dirBase as &$value) {
			$stringArray .= $value.'/';
	}
	listDir($_POST['dirList'],$stringArray,$_POST['startDir']);
}

/*create new directory*/
if(isset($_POST['createDirectoryNow'])){

	$dirName = cleanInputs($_POST['dirName']);
	if(file_exists($_POST['currentDir'] . $dirName)) {
		echo 'ALERT:  Sorry, cant create the directory "'.$dirName.'", name already exists!';
	}else{
		mkdir ($_POST['currentDir'] . $dirName, 0757);
		echo 'Your directory of "'.$dirName.'" has been created successfully.';
	}
	/* need to work out chown details */
	/*$owner = explode("/",$_POST['currentDir']); chown ($_POST['currentDir'] . $dirName, $owner[2]); chgrp ($_POST['currentDir'] . $dirName, $owner[2]);*/
}

/*delete file or directory*/
if(isset($_POST['deleteFile'])){
	if($_POST['deleteType'] === 'dir'){
    function deldir($dir){
      $current_dir = opendir($dir);
      while($entryname = readdir($current_dir)){
       if(is_dir("$dir/$entryname") and ($entryname != "." and $entryname!="..")){
           deldir("${dir}/${entryname}");
       }elseif($entryname != "." and $entryname!=".."){
           if(unlink("${dir}/${entryname}") ) {};
       }
      }
      closedir($current_dir);
      rmdir(${dir});
    }
		$deleteDir = $_POST['dirBase'] . $_POST['dirList'] ."/". $_POST['dirFile'] . "/";
  	deldir($deleteDir);
	}else{
  	$dirlist = $_POST['dirBase']. $_POST['dirList'] ."/". $_POST['dirFile'];
  	unlink($dirlist);
	}
}

/*rename file or directory*/
if(isset($_POST['reName'])){
	$newName = cleanInputs($_POST['newName']);
	$newFileList = $_POST['dirBase'] . $_POST['dirList'] . $newName . $_POST['fType'];
	$oldFileList = $_POST['dirBase'] . $_POST['dirList'] . $_POST['oldName'];
	rename("$oldFileList", "$newFileList");
}

