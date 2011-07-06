$(document).ready(function() {
//	GENTICS.Aloha.settings = {
//			"i18n": {"current": "en"},
//			"ribbon": false,
//			"plugins": { 
//				"com.gentics.aloha.plugins.GCN": { 
//					"enabled": false 
//				},
//			 	"com.gentics.aloha.plugins.Format": { 
//					config : [ 'b', 'i','u','del','sub','sup', 'p', 'title', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'pre', 'removeFormat']
//				} 
//		 	}
//		};
	getDivData('data_home_one');
	getDivData('data_home_two');
    var editFlag = false;
	
	$('#editModeButton').click(function(){
		var newMode = (editFlag==true)?"View" : "Edit";
		alert("Going to " + newMode + " mode");
		editFlag = prepareModeChange(editFlag);
		if(editFlag){
			alert("Editflag == " + editFlag);
			//$('.editablediv').aloha();
		}		
	});
	
//	$('#saveEditedStuff').click(function(){
//		//var editableList = GENETICS.Aloha.editables;
//		alert(GENTICS.Aloha.getEditableById("data_home_one"));
//		$.each(GENTICS.Aloha.editables,function (index,editable) {
//			// and get their clean and valid html5 content, and remember it to fake saving 
//			var content="";
//			content = content + "Editable ID: " + editable.getId() +"\nHTML code: " + editable.getContents() + "\n\n";
//			alert(content);
//		});
//
//		
//	});
});
//Make an ajax call to backend to get divContent
var getDivData = function(divId){
	var url = '/tb/cms/getDivContent';
		
	$.ajax({
		  url: url,
		  data: {'domainId': domainId,'divId': divId},
		  success: function(data){
		     $("#"+divId).html(data.divContent);
		   },
		  dataType: 'json'
		});
	
	
}
//This function changes the display value of the mode button
// Also add/remove editablediv class from the divIds depending on the mode
var prepareModeChange = function(isCurrentlyEditMode){
	var editButtonSelector = $('#editModeButton');
	if(isCurrentlyEditMode){
		editButtonSelector.val('Edit Mode');
		$('#data_home_one').removeClass('editablediv');
		$('#data_home_two').removeClass('editablediv');
		return false;
	}
	else{
		$('#data_home_one').addClass('editablediv');
		$('#data_home_two').addClass('editablediv');
		editButtonSelector.val('View Mode');	
		return true;
	}
}
