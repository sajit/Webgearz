$(document).ready(function() {
	var templateids = [];
	var selectTemplates = {};
	$(":checkbox").click(function(){
		var currentId = $(this).attr('id');
		
		$('#'+currentId).is(':checked') ? $("#domain_"+currentId).show() : $("#domain_"+currentId).hide();
		var n = $("input:checked").length;
		var checkedboxes = $("input:checked");
		templateids = [];
		for(var i=0;i<n;i++){
			templateids[i] = $(checkedboxes[i]).attr('id');
		}
		//alert(templateids);
		
	});
	$('#templateSelectButton').click(function() {
		
		var url = '/tb/cms/addDomain';
		var dataParam = getPostData(templateids);
		$.ajax({
			  type: 'POST',
			  url: url,
			  data: {'domainName':dataParam.domainName,'templateid':dataParam.templateid},
			  success: function(){
				  alert("In successallbcalk");
				  top.location = '/tb/cms/user_dashboard';
			  },
			  dataType: 'json'
			});
	});
});

var getPostData = function(templateids){
	var tempDomainName = {'templateid':'',
		'domainName':''};
	for(var i=0;i<templateids.length;i++){
		var domainName = $('#domain_'+templateids[i]).val();
		tempDomainName.templateid = templateids[i];
		tempDomainName.domainName = domainName;
		//tempDomainName[templateids[i]] = domainName;
	}
	return tempDomainName;
};