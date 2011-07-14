$(document).ready(function() {
		$('#userCreateFormId').ajaxForm({
	   
				dataType:  'json', 
	
				// success identifies the function to invoke when the server response 
				// has been received 
				success:   processJson });
		function processJson(data) { 
            // 'data' is the json object returned from the server 
            alert("User registered, now log in to start using the system");
            if(data.msg=='User created'){
                top.location = '/tb/cms/user_dashboard';
            }
            
        }
});
