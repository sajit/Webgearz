$(document).ready(function() {

	$('#loginForm').ajaxForm({
		// dataType identifies the expected content type of the server response 
        dataType:  'json', 
 
        // success identifies the function to invoke when the server response 
        // has been received 
        success:   processJson });
//	
//	$('#loginForm').submit(function(data){
//		var result = jQuery.parseJSON(data);
//		if(result.msg=='Authentication successful'){
//			
//			top.location='/tb/user_dashboard';
//		}
//	});
	function processJson(data) { 
	    // 'data' is the json object returned from the server 
	    
	    if(data.msg=='Authentication successful'){
	    	top.location = '/tb/user_dashboard';
	    }
	    alert(data.msg); 
	}
});
