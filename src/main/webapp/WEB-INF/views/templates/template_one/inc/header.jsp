
<div id="header">

    <h1 id="logo"><!--div class="editable" id="data_logo"></div--></h1>


     
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/aloha/aloha.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/aloha/plugins/com.gentics.aloha.plugins.Format/plugin.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/aloha/plugins/com.gentics.aloha.plugins.Table/plugin.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/aloha/plugins/com.gentics.aloha.plugins.List/plugin.js"></script>
	
  
    <div><img src="${pageContext.request.contextPath}/resources/images/template_one/img/dotted_line.jpg" /></div>
    <script type="text/javascript">
         GENTICS.Aloha.settings = {
		   'i18n' : {
		     'current' : 'en'
		   },
		   'base' : "${pageContext.request.contextPath}/resources/aloha/",
		   'readonly' : true,
		   'ribbon' : false,
		   "plugins": { 
			"com.gentics.aloha.plugins.GCN": { 
				"enabled": false 
			},
		 	"com.gentics.aloha.plugins.Format": { 
				config : [ 'b', 'i','u','del','sub','sup', 'p', 'title', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'pre', 'removeFormat']
			}
		   }
		 };

   </script>
    <input id="editModeButton" type="button" value="Edit Mode" /> <input id="saveEditedStuff" type="button" value="Save" /> <br/>
    hi header
</div>

