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

/*############# START fslib items #############*/
/*cruise down directories*/
function downDirectory() {
	var	dirData = jQuery(this).attr("rel").split("|");

		jQuery.post(baseFiles+"/fslib.php",{ dirList: dirData[0], dirBase: dirData[1], changeDir: true, startDir: jQuery("#startDir").val() },
			function(data){
				jQuery('#dir-files tbody').html(data);
				pageBindings();
			}
		);
}

/*cruise up directories*/
function upDirectory(){
	var	dirData = jQuery(this).attr("rel").split("|");

	jQuery.post(baseFiles+"/fslib.php",{ dirList: dirData[0], dirBase: dirData[1], changeDirUp: true, startDir: jQuery("#startDir").val() },
			function(data){
					jQuery('#dir-files tbody').html(data);
					pageBindings();
					if(jQuery("#upDirData").val() === jQuery("#startDir").val()){ jQuery("#dirUp").css({display:"none"}); }
			}
	);
}

/*check to see if delete is what user is after*/
function deleteItemCheck() {
	jQuery(".inputName").css({display:"none"});
	var deleteData = jQuery(this).attr("rel").split(",");
	jQuery(".choice-yes").attr({id:"choice-yes"});

	if(deleteData[2] === 'file'){
		jQuery("#fslibDialog .header span").text("DELETE THIS FILE");
		jQuery("#fslibDialog .text").text("Are you sure you want to permanently delete this file?");
	}else{
		jQuery("#fslibDialog .header span").text("DELETE THIS DIRECTORY");
		jQuery("#fslibDialog .text").text("Are you sure you want to permanently delete this directory?");
	}
		jQuery("#choice-yes").attr({id:""+deleteData+",delete"});
		
		
	jQuery("#fslibDialog").modal({
		onOpen: function (dialog) {
				dialog.overlay.fadeIn('slow', function () {
					dialog.data.hide();
					dialog.container.fadeIn('fast', function () {
						dialog.data.fadeIn('slow');
					  }); 
				  }); 
			  },
	    onClose: function (dialog) {
				  dialog.data.fadeOut('slow', function () {
					  dialog.container.hide('fast', function () {
						  dialog.overlay.slideUp('normal', function () {
							  $.modal.close();
							  });
						  });
					});
			  },
		closeHTML: "<a href='#' title='Close' class='modal-close'>x</a>",
		position: ["20%",],
		containerCss: {
			backgroundColor:"#ffffff",		
			width: 390,
			scroll: 'none'
		},
		overlayClose: false
	});
}

/*check to see if delete is what user is after*/
function changeName(){
	var renameData = jQuery(this).attr("rel").split(",");
	jQuery(".choice-yes").attr({id:"choice-yes"});

		jQuery("#fslibDialog .header span").text("RENAME THIS FILE OR DIRECTORY");
		jQuery("#fslibDialog .text").text("Enter new name:");

		jQuery("#choice-yes").attr({id:""+renameData+",rename"});
		jQuery(".inputName").val(renameData[1]);
		jQuery(".inputName").css({display:"block"});
	
	jQuery("#fslibDialog").modal({
		onOpen: function (dialog) {
				dialog.overlay.fadeIn('slow', function () {
					dialog.data.hide();
					dialog.container.fadeIn('fast', function () {
						dialog.data.fadeIn('slow');
					  }); 
				  }); 
			  },
	    onClose: function (dialog) {
				  dialog.data.fadeOut('slow', function () {
					  dialog.container.hide('fast', function () {
						  dialog.overlay.slideUp('normal', function () {
							  $.modal.close();
							  });
						  });
					});
			  },
		closeHTML: "<a href='#' title='Close' class='modal-close'>x</a>",
		position: ["20%",],
		containerCss: {
			backgroundColor:"#ffffff",		
			width: 390,
			scroll: 'none'
		},
		overlayClose: false
	});
}

/*change - rename/remove item (file or directory) from server*/
function changeItem(data){
	var dirData = data.split(",");

	if(dirData[4] === 'delete'){

		jQuery.post(baseFiles+"/fslib.php",{ dirFile: dirData[1], dirList: dirData[0], dirBase: dirData[3], deleteFile: true, deleteType: dirData[2]  },
			function(data){
					jQuery.post(baseFiles+"/fslib.php",{ dirList: dirData[3], dirBase: dirData[0], changeDir: true, startDir: jQuery("#startDir").val() },
						function(data){
							jQuery('#dir-files tbody').html(data);
							pageBindings();
							if(jQuery("#upDirData").val() === jQuery("#startDir").val()){ jQuery("#dirUp").css({display:"none"}); }
					});
			}
		);

	}else{

		jQuery.post(baseFiles+"/fslib.php",{ dirFile: dirData[1], dirList: dirData[0], dirBase: dirData[3], reName: true, newName: jQuery("#newName").val(), oldName: dirData[1]  },
			function(data){
					jQuery.post(baseFiles+"/fslib.php",{ dirList: dirData[3], dirBase: dirData[0], changeDir: true, startDir: jQuery("#startDir").val() },
						function(data){
							jQuery('#dir-files tbody').html(data);
							pageBindings();
							if(jQuery("#upDirData").val() === jQuery("#startDir").val()){ jQuery("#dirUp").css({display:"none"}); }
					});
			}
		);

	}
	
	jQuery.modal.close();
	
}

/*highlight on mouseover the table rows*/
function dirHighlight(){
    jQuery("#dir-files tr").bind("mouseenter",function(){
      jQuery(this).addClass("hover");
    }).bind("mouseleave",function(){
      jQuery(this).removeClass("hover");
    });
}

/*display image via simpleModal*/
function imageModal(imageData){
	var src = 'WebGearz/_fslib/image-display.php?imagedata='+imageData;
	var maskHeight = $(src).height();  
    var maskWidth = $(src).width(); 
	$.modal('<iframe src="' + src + '" height="'+maskHeight+'" width="'+maskWidth+'" border="0">', {
		closeHTML:"",
		containerCss:{
			height:500,
			width:800
		},
		overlayClose:true
	});
	return false;
}


/*Bindings / unbindings for file system actions*/
function pageBindings(){
pageUnBindings(); dirHighlight(); jQuery("a.directory").bind("click", downDirectory); jQuery(".rename").bind("click", changeName); jQuery(".deleteThis").bind("click", deleteItemCheck); jQuery(".directoryUp").bind("click", upDirectory);jQuery(".modalImage").bind("click", function(){ imageModal(this.rel);})
}
function pageUnBindings(){
	jQuery("#dir-files tr").unbind("mouseenter");jQuery("#dir-files tr").unbind("mouseleave");jQuery("a.directory").unbind("click");jQuery(".deleteThis").unbind("click");jQuery(".directoryUp").unbind("click");jQuery(".modalImage").unbind("click");
}

/*File Menu items*/
function createDirectory(){
	closeFile();
	jQuery("#responseDir").css({display:"none"});
	var currentHeight = jQuery("#dirCreate").height();
	if(currentHeight === 0){
		jQuery("#dirCreate").css({display:"block"}).animate({"height": jQuery(".fileSystemWrap").height()+"px"}, 500);
	}else{
		closeDir();
	}
}
function uploadFile(){
	closeDir();
	var currentHeight = jQuery("#uploadFile").height();
	if(currentHeight === 0){
		jQuery("#uploadFile").css({display:"block"}).animate({"height": jQuery(".fileSystemWrap").height()+"px"}, 500);
	}else{
		closeFile();
	}
		jQuery(".progressWrapper").remove();
}

function closeDir(){
	refreshStaus();
	jQuery("#dirCreate").animate({"height": 0+"px"}, 250,function(){jQuery(this).css({display:"none"});});
}

function closeFile(){
	refreshStaus();
	jQuery("#uploadFile").animate({"height": 0+"px"}, 250,function(){jQuery(this).css({display:"none"});});
}

function refreshStaus(){
jQuery.post(baseFiles+"/fslib.php",{ dirList: jQuery("#dirBase").val(), dirBase: jQuery("#dirDisplay").val(), changeDir: true, startDir: jQuery("#startDir").val() },
	function(data){
		jQuery('#dir-files tbody').html(data);
		pageBindings();
		if(jQuery("#upDirData").val() === jQuery("#startDir").val()){ jQuery("#dirUp").css({display:"none"}); }
});
}
/*############# End fslib items #############*/


/* #########################################################################################
	Written by: From:  http://www.swfupload.org
	Queue Plug-in

	Features:
		cancelQueue method for cancelling the entire queue.
		All queued files are uploaded when startUpload() is called.
		If false is returned from uploadComplete then the queue upload is stopped.  If false is
		not returned (strict comparison) then the queue upload is continued.

	*/
	
/*
	Queue Plug-in
	
	Features:
		*Adds a cancelQueue() method for cancelling the entire queue.
		*All queued files are uploaded when startUpload() is called.
		*If false is returned from uploadComplete then the queue upload is stopped.
		 If false is not returned (strict comparison) then the queue upload is continued.
		*Adds a QueueComplete event that is fired when all the queued files have finished uploading.
		 Set the event handler with the queue_complete_handler setting.
		
	*/

var SWFUpload;
if (typeof(SWFUpload) === "function") {
	SWFUpload.queue = {};
	
	SWFUpload.prototype.initSettings = (function (oldInitSettings) {
		return function () {
			if (typeof(oldInitSettings) === "function") {
				oldInitSettings.call(this);
			}
			
			this.customSettings.queue_cancelled_flag = false;
			this.customSettings.queue_upload_count = 0;
			
			this.settings.user_upload_complete_handler = this.settings.upload_complete_handler;
			this.settings.user_upload_start_handler = this.settings.upload_start_handler;
			this.settings.upload_complete_handler = SWFUpload.queue.uploadCompleteHandler;
			this.settings.upload_start_handler = SWFUpload.queue.uploadStartHandler;
			
			this.settings.queue_complete_handler = this.settings.queue_complete_handler || null;
		};
	})(SWFUpload.prototype.initSettings);

	SWFUpload.prototype.startUpload = function (fileID) {
		this.customSettings.queue_cancelled_flag = false;
		this.callFlash("StartUpload", [fileID]);
	};

	SWFUpload.prototype.cancelQueue = function () {
		this.customSettings.queue_cancelled_flag = true;
		this.stopUpload();
		
		var stats = this.getStats();
		while (stats.files_queued > 0) {
			this.cancelUpload();
			stats = this.getStats();
		}
	};
	
	SWFUpload.queue.uploadStartHandler = function (file) {
		var returnValue;
		if (typeof(this.customSettings.user_upload_start_handler) === "function") {
			returnValue = this.customSettings.user_upload_start_handler.call(this, file);
		}
		
		// To prevent upload a real "FALSE" value must be returned, otherwise default to a real "TRUE" value.
		returnValue = (returnValue === false) ? false : true;
		
		this.customSettings.queue_cancelled_flag = !returnValue;

		return returnValue;
	};
	
	SWFUpload.queue.uploadCompleteHandler = function (file) {
		var user_upload_complete_handler = this.settings.user_upload_complete_handler;
		var continueUpload;
		
		if (file.filestatus === SWFUpload.FILE_STATUS.COMPLETE) {
			this.customSettings.queue_upload_count++;
		}

		if (typeof(user_upload_complete_handler) === "function") {
			continueUpload = (user_upload_complete_handler.call(this, file) === false) ? false : true;
		} else {
			continueUpload = true;
		}
		
		if (continueUpload) {
			var stats = this.getStats();
			if (stats.files_queued > 0 && this.customSettings.queue_cancelled_flag === false) {
				this.startUpload();
			} else if (this.customSettings.queue_cancelled_flag === false) {
				this.queueEvent("queue_complete_handler", [this.customSettings.queue_upload_count]);
				this.customSettings.queue_upload_count = 0;
			} else {
				this.customSettings.queue_cancelled_flag = false;
				this.customSettings.queue_upload_count = 0;
			}
		}
	};
}


/*
	A simple class for displaying file information and progress
	Note: This is a demonstration only and not part of SWFUpload.
	Note: Some have had problems adapting this class in IE7. It may not be suitable for your application.
*/

// Constructor
// file is a SWFUpload file object
// targetID is the HTML element id attribute that the FileProgress HTML structure will be added to.
// Instantiating a new FileProgress object with an existing file will reuse/update the existing DOM elements
function FileProgress(file, targetID) {
	this.fileProgressID = file.id;

	this.opacity = 100;
	this.height = 0;

	this.fileProgressWrapper = document.getElementById(this.fileProgressID);
	if (!this.fileProgressWrapper) {
		this.fileProgressWrapper = document.createElement("div");
		this.fileProgressWrapper.className = "progressWrapper";
		this.fileProgressWrapper.id = this.fileProgressID;

		this.fileProgressElement = document.createElement("div");
		this.fileProgressElement.className = "progressContainer";

		var progressCancel = document.createElement("a");
		progressCancel.className = "progressCancel";
		progressCancel.href = "#";
		progressCancel.style.visibility = "hidden";
		progressCancel.appendChild(document.createTextNode(" "));

		var progressText = document.createElement("div");
		progressText.className = "progressName";
		progressText.appendChild(document.createTextNode(file.name));

		var progressBar = document.createElement("div");
		progressBar.className = "progressBarInProgress";

		var progressStatus = document.createElement("div");
		progressStatus.className = "progressBarStatus";
		progressStatus.innerHTML = "&nbsp;";

		this.fileProgressElement.appendChild(progressCancel);
		this.fileProgressElement.appendChild(progressText);
		this.fileProgressElement.appendChild(progressStatus);
		this.fileProgressElement.appendChild(progressBar);

		this.fileProgressWrapper.appendChild(this.fileProgressElement);

		document.getElementById(targetID).appendChild(this.fileProgressWrapper);
	} else {
		this.fileProgressElement = this.fileProgressWrapper.firstChild;
	}

	this.height = this.fileProgressWrapper.offsetHeight;

}
FileProgress.prototype.setProgress = function (percentage) {
	this.fileProgressElement.className = "progressContainer green";
	this.fileProgressElement.childNodes[3].className = "progressBarInProgress";
	this.fileProgressElement.childNodes[3].style.width = percentage + "%";
};
FileProgress.prototype.setComplete = function () {
	this.fileProgressElement.className = "progressContainer blue";
	this.fileProgressElement.childNodes[3].className = "progressBarComplete";
	this.fileProgressElement.childNodes[3].style.width = "";

	var oSelf = this;
	setTimeout(function () {
		oSelf.disappear();
	}, 10000);
};
FileProgress.prototype.setError = function () {
	this.fileProgressElement.className = "progressContainer red";
	this.fileProgressElement.childNodes[3].className = "progressBarError";
	this.fileProgressElement.childNodes[3].style.width = "";

	var oSelf = this;
	setTimeout(function () {
		oSelf.disappear();
	}, 5000);
};
FileProgress.prototype.setCancelled = function () {
	this.fileProgressElement.className = "progressContainer";
	this.fileProgressElement.childNodes[3].className = "progressBarError";
	this.fileProgressElement.childNodes[3].style.width = "";

	var oSelf = this;
	setTimeout(function () {
		oSelf.disappear();
	}, 2000);
};
FileProgress.prototype.setStatus = function (status) {
	this.fileProgressElement.childNodes[2].innerHTML = status;
};

// Show/Hide the cancel button
FileProgress.prototype.toggleCancel = function (show, swfUploadInstance) {
	this.fileProgressElement.childNodes[0].style.visibility = show ? "visible" : "hidden";
	if (swfUploadInstance) {
		var fileID = this.fileProgressID;
		this.fileProgressElement.childNodes[0].onclick = function () {
			swfUploadInstance.cancelUpload(fileID);
			return false;
		};
	}
};

// Fades out and clips away the FileProgress box.
FileProgress.prototype.disappear = function () {

	var reduceOpacityBy = 15;
	var reduceHeightBy = 4;
	var rate = 30;	// 15 fps

	if (this.opacity > 0) {
		this.opacity -= reduceOpacityBy;
		if (this.opacity < 0) {
			this.opacity = 0;
		}

		if (this.fileProgressWrapper.filters) {
			try {
				this.fileProgressWrapper.filters.item("DXImageTransform.Microsoft.Alpha").opacity = this.opacity;
			} catch (e) {
				// If it is not set initially, the browser will throw an error.  This will set it if it is not set yet.
				this.fileProgressWrapper.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity=" + this.opacity + ")";
			}
		} else {
			this.fileProgressWrapper.style.opacity = this.opacity / 100;
		}
	}

	if (this.height > 0) {
		this.height -= reduceHeightBy;
		if (this.height < 0) {
			this.height = 0;
		}

		this.fileProgressWrapper.style.height = this.height + "px";
	}

	if (this.height > 0 || this.opacity > 0) {
		var oSelf = this;
		setTimeout(function () {
			oSelf.disappear();
		}, rate);
	} else {
		this.fileProgressWrapper.style.display = "none";
	}
};


/* Demo Note:  This demo uses a FileProgress class that handles the UI for displaying the file name and percent complete.
The FileProgress class is not part of SWFUpload.
*/


/* **********************
   Event Handlers
   These are my custom event handlers to make my
   web application behave the way I went when SWFUpload
   completes different tasks.  These aren't part of the SWFUpload
   package.  They are part of my application.  Without these none
   of the actions SWFUpload makes will show up in my application.
   ********************** */

function swfUploadPreLoad() {
	var self = this;
	var loading = function () {
		document.getElementById("divLoadingContent").style.display = "";

		var longLoad = function () {
			document.getElementById("divLoadingContent").style.display = "none";
			document.getElementById("divLongLoading").style.display = "";
		};
		this.customSettings.loadingTimeout = setTimeout(function () {
				longLoad.call(self)
			},
			15 * 1000
		);
	};
	
	this.customSettings.loadingTimeout = setTimeout(function () {
			loading.call(self);
		},
		1*1000
	);
}
function swfUploadLoaded() {
	var self = this;
	clearTimeout(this.customSettings.loadingTimeout);
	document.getElementById("divLoadingContent").style.display = "none";
	document.getElementById("divLongLoading").style.display = "none";
	document.getElementById("divAlternateContent").style.display = "none";
	
	document.getElementById("btnCancel").onclick = function () { self.cancelQueue(); };
}
   
function swfUploadLoadFailed() {
	clearTimeout(this.customSettings.loadingTimeout);
	document.getElementById("divLoadingContent").style.display = "none";
	document.getElementById("divLongLoading").style.display = "none";
	document.getElementById("divAlternateContent").style.display = "";
}
   
   
function fileQueued(file) {
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setStatus("Pending...");
		progress.toggleCancel(true, this);

	} catch (ex) {
		this.debug(ex);
	}

}

function fileQueueError(file, errorCode, message) {
	try {
		if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
			alert("You have attempted to queue too many files.\n" + (message === 0 ? "You have reached the upload limit." : "You may select " + (message > 1 ? "up to " + message + " files." : "one file.")));
			return;
		}

		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);

		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			progress.setStatus("File is too big.");
			this.debug("Error Code: File too big, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			progress.setStatus("Cannot upload Zero Byte files.");
			this.debug("Error Code: Zero byte file, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			progress.setStatus("Invalid File Type.");
			this.debug("Error Code: Invalid File Type, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		default:
			if (file !== null) {
				progress.setStatus("Unhandled Error");
			}
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

function fileDialogComplete(numFilesSelected, numFilesQueued) {
	try {
		if (numFilesSelected > 0) {
			document.getElementById(this.customSettings.cancelButtonId).disabled = false;
		}
		
		/* I want auto start the upload and I can do that here */
		this.startUpload();
	} catch (ex)  {
        this.debug(ex);
	}
}

function uploadStart(file) {
	try {
		/* I don't want to do any file validation or anything,  I'll just update the UI and
		return true to indicate that the upload should start.
		It's important to update the UI here because in Linux no uploadProgress events are called. The best
		we can do is say we are uploading.
		 */
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setStatus("Uploading...");
		progress.toggleCancel(true, this);
	}
	catch (ex) {}
	
	return true;
}

function uploadProgress(file, bytesLoaded, bytesTotal) {
	try {
		var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);

		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setProgress(percent);
		progress.setStatus("Uploading...");
	} catch (ex) {
		this.debug(ex);
	}
}

function uploadSuccess(file, serverData) {
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setComplete();
		progress.setStatus("Complete.");
		progress.toggleCancel(false);

	} catch (ex) {
		this.debug(ex);
	}
}

function uploadError(file, errorCode, message) {
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);

		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			progress.setStatus("Upload Error: " + message);
			this.debug("Error Code: HTTP Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			progress.setStatus("Upload Failed.");
			this.debug("Error Code: Upload Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			progress.setStatus("Server (IO) Error");
			this.debug("Error Code: IO Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			progress.setStatus("Security Error");
			this.debug("Error Code: Security Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			progress.setStatus("Upload limit exceeded.");
			this.debug("Error Code: Upload Limit Exceeded, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
			progress.setStatus("Failed Validation.  Upload skipped.");
			this.debug("Error Code: File Validation Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			// If there aren't any files left (they were all cancelled) disable the cancel button
			if (this.getStats().files_queued === 0) {
				document.getElementById(this.customSettings.cancelButtonId).disabled = true;
			}
			progress.setStatus("Cancelled");
			progress.setCancelled();
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			progress.setStatus("Stopped");
			break;
		default:
			progress.setStatus("Unhandled Error: " + errorCode);
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

function uploadComplete(file) {
	if (this.getStats().files_queued === 0) {
		document.getElementById(this.customSettings.cancelButtonId).disabled = true;
	}
}

// This event comes from the Queue Plugin
function queueComplete(numFilesUploaded) {
	var status = document.getElementById("divStatus");
	status.innerHTML = numFilesUploaded + " file" + (numFilesUploaded === 1 ? "" : "s") + " uploaded.";
}
