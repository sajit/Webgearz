<?php
header("Cache-Control: no-store, no-cache");

/*######    This page is the build page for the editor display    ######*/

// main config, yes you do need it
include("config.php"); 

// realData is the content you are about to edit
$realData = file_get_contents("".$_SERVER['DOCUMENT_ROOT'].$wgCdata."/".$_GET['file']."".$wgCextn."");
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
    
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <title>WebGearz Content Editor</title>
    
	<script type="text/javascript" src="<?php echo $wgPath;?>/lib/ckeditor/ckeditor.js"></script>
	<style type="text/css">
		html, body, div, h1, h2, h3, h4, h5, h6, ul, ol, dl, li, dt, dd, p, blockquote, pre, form, fieldset, table, th, td { background: none; margin: 0; padding: 0; border: 0 ; }
		body {  font: 62.5% "Century Gothic", Geneva, Verdana, Arial, Helvetica, sans-serif; color: #4C1803;}
		<!-- #conent set to 100% width so ckeditor takes up entire width of iFrame -->
		#content {background: #fff;width:100%;} 
		
		.buttons {line-height:26px; width:350px; float:right; padding:5px 8px 0;} 
         button {float:right; margin-left:4px; width:150px; height:26px; color:#666; font-weight:bold; text-align:center; background:url(img/button.gif) repeat-x; border:1px solid #bbb; cursor:pointer;}
	</style>
  </head>
<body>
  <div id="content">
	<form name="edit_now" id="edit_now" action="cfk-action.php" method="post">
		<textarea id="wgta-<?php echo $_GET['file'];?>" name="wgta-<?php echo $_GET['file'];?>" style="height:300px;"><?php echo $realData;?></textarea>
		<script type="text/javascript">
		//<![CDATA[
			CKEDITOR.replace( 'wgta-<?php echo $_GET['file'];?>', {
		        toolbar : [
				    ['Source','-','Save','NewPage','Preview','-','Templates'],
					['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
					['Undo','Redo','-','SelectAll','RemoveFormat'],
					'/',
					['Bold','Italic','Underline','Strike'],
					['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],
					['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
					['Link','Unlink','Anchor'],
					['Image','Flash','jwplayer','gmap','Table','HorizontalRule'],
					'/',
					['Styles','Format','Font','FontSize'],
					['TextColor','BGColor'],
					['Maximize']

				],
				filebrowserBrowseUrl : '<?php echo $wgPath;?>/cfk_image_list.php',
		        filebrowserWindowWidth : 250,
		        filebrowserWindowHeight : 480
    		});
		//]]>
		</script>
        
		<input type="hidden" value="wgta-<?php echo $_GET['file'];?>" name="tVal" />
		<input type="hidden" value="<?php echo $_GET['file'];?>" name="file" />
		<br/>
        <div class="buttons">
		<button type="submit">Save Changes</button>
		<button onclick="self.parent.WebGearz.closeEditing('<?php echo $_GET['file'];?>','WebGearz.wgEscape');" >Cancel Editing</button>
        </div>
	</form>
  </div>
</body>
</html>
