<?php
include("config.php");
?>


<!--/* This is the WebGearz Panel  */-->

<div id="WebGearz_panel">
   <div id="WebGearz_panel_wrap">

		<div class="WebGearz_logo">
        <img src="<?php echo $wgPath;?>/img/panel_logo.jpg" alt="WebGearz panel" />
        </div>
        
		<div class="WebGearz_panel_links">
			<?php if($fileSystem==1){?><a href="javascript:void(0)" id="panel_file_manager" title="WebGearz File Manager"><img class="wg_imgSwap" src="<?php echo $wgPath;?>/img/panel_file_manager_off.png" class="panel_file_manager" alt="WebGearz File Manager"/></a><?php } ?>
            <a href="javascript:void(0)" id="panel_help" title="WebGearz Help"><img class="wg_imgSwap" src="<?php echo $wgPath;?>/img/panel_help_off.png" alt="WebGearz Help"/></a>
            <a href="javascript:void(0)" id="panel_about" title="About WebGearz"><img class="wg_imgSwap" src="<?php echo $wgPath;?>/img/panel_about_off.png" alt="about WebGearz"/></a>
            <a href="javascript:void(0)" id="panel_logout" title="Logout of WebGearz Editing Session"><img class="wg_imgSwap" src="<?php echo $wgPath;?>/img/panel_logout_off.png" alt="logout of WebGearz editing session"/></a>
		</div>
        
   </div> <!-- End WebGearz_panel_wrap -->

<!--/* WebGearz_panel End all the way at the bottom!!  */-->



<?php
if($fileSystem==1){
include("_fslib/fslib.php");
?>

<script type="text/javascript" src="<?php echo $wgPath;?>/_fslib/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<?php echo $wgPath;?>/_fslib/fslib.js"></script>

<!--/* This activates prettyPhoto for displaying file system images */-->
<script type="text/javascript" charset="utf-8">
	$(document).ready(function(){
		$("a[rel^='prettyPhoto']").prettyPhoto({
			theme: 'dark_square', /* light_rounded / dark_rounded / light_square / dark_square / facebook */
			animationSpeed: 'normal', /* fast/slow/normal */
			opacity: 0.50, /* Value between 0 and 1 */
			showTitle: true, /* true/false */
		});
	});
</script>


<!--/* Below are the WebGearz Panel Drop-Down Windows  */-->



<!--/* This is the WebGearz Panel File Manager Drop-Down Panel */-->

<div id="WebGearz_files">
	<div class="WebGearz_dropDown">
    <br />
    <br />
	<?php
    /* LISTDIR desciption in code fslib.php */
    listDir($serverPath,$wgFiles,0,$filePages);
    ?>
	</div>
</div>



<script type="text/javascript">
<!--
/*
only needs changing if installed in a different directory, or will
need to be full path if used in conjunction with mod rewrite urls.
*/
var baseFiles = '<?php echo $filePages ;?>';

var swfu;
//var uploadFilesJS = '';

/*On Ready requirements*/
jQuery(document).ready(function(){
	pageBindings();
	jQuery("#createDirectory").bind("click", createDirectory);jQuery("#fileUpload").bind("click", uploadFile);jQuery("#cancelDir").bind("click", closeDir);jQuery("#cancelFile").bind("click", closeFile);

	jQuery("#form-directory").submit( function () {
		jQuery.ajax({
			type: "POST",
			url: baseFiles+"/fslib.php",
			data: {currentDir: jQuery("#currentDirCreate").val(), dirName: jQuery("#dirName").val(), createDirectoryNow: true } ,
			success: function(resp){
				jQuery("#responseDir").text(resp).css({display:"block"});
				jQuery("#dirName").val('');
			}
		});
		return false;
	});

swfu = new SWFUpload({
				// Backend Settings
				upload_url: baseFiles+"/swfupload/upload.php",	// Relative to the SWF file
				post_params: {"PHPSESSID": "<?php echo session_id(); ?>"},

				// File Upload Settings
				file_size_limit : "100 MB",	// 10MB
				file_types : "*.asp;*.bmp;*.doc;*.docx;*.file;*.gif;*.html;*.jpg;*.jpeg;*.sql;*.xlsx;*.mdb;*.pdf;*.php;*.psd;*.txt;*.xls;*.xml;*.csv;*.ods;*.odt;*.zip;*.png;*.ai;*.css;*.js;*.swf;*.fla;*.wmv;*.mov;*.qt;*.mpeg;*.mp2;*.mp3;*.mp3;*.avi",
				file_types_description : "Allowed Files",
				file_upload_limit : 12,

				custom_settings : {
					progressTarget : "fsUploadProgress",
					cancelButtonId : "btnCancel"
				},

				// Button Settings
				button_image_url : baseFiles+"/swfupload/XPButtonUploadText_61x22.png",	// Relative to the SWF file
				button_placeholder_id : "spanButtonPlaceholder",
				button_width: 61,
				button_height: 22,

				// The event handler functions are defined in handlers.js
				swfupload_loaded_handler : swfUploadLoaded,
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,
				queue_complete_handler : queueComplete,	// Queue plugin event
				
				// SWFObject settings
				minimum_flash_version : "9.0.28",
				swfupload_pre_load_handler : swfUploadPreLoad,
				swfupload_load_failed_handler : swfUploadLoadFailed,
				
				// Flash Settings
				flash_url : baseFiles+"/swfupload/swfupload.swf",


				
				// Debug Settings
				debug: false


			});

});
//-->
</script>
<?php } ?>

<!--/* End of File Manager Drop-Down Panel */-->



<!--/* This is the WebGearz Panel Help Drop-Down Panel */-->

<div id="WebGearz_help"><div class="WebGearz_dropDown">

         <div class="WebGearz_dropDown_container">
				<h2>WEBGEARZ HELP</h2>
				<p>Documentation for WebGearz is coming soon!!!</p>
		        
				<h2>Found a problem?</h2>
				<p>Please let me know if you find any problems. Include details such as the browser and operating system you are using and the nature of the problem.  Send me an email at matt.hofstadt@gmail.com</p>              
		</div>
        
</div></div>

<!--/* End of Help Drop-Down Panel */-->



<!--/* This is the WebGearz Panel About Drop-Down Panel */-->

<div id="WebGearz_about"><div class="WebGearz_dropDown">

		<div class="WebGearz_dropDown_container">
				<h2>ABOUT WEBGEARZ</h2>
				<p>WebGearz is an innovative content management technology that creates a new and intuituve way for users to interface with the content of their websites.</p>
		</div>
        
</div></div>

<!--/* End of About Drop-Down Panel */-->


</div> <!--/* End of WebGearz_panel!!!  */-->

