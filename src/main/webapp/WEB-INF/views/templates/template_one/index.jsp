

<jsp:include page="/WEB-INF/views/templates/template_one/inc/header_data.jsp"></jsp:include>

<body>
<script type="text/javascript">
	var domainId = "${domainId}";
	var templateId = "${templateId}";
</script>
<jsp:include page="/WEB-INF/views/templates/template_one/inc/header.jsp"></jsp:include>



<div id="container">

  <div id="main_content">    
  
  
    
    <div class="module">
  
    <div id="data_home_one"></div>    
    
    <div id="data_home_two"></div>
    
    </div>  <!-- End Module !-->
    body
    
    
    
    <div class="divider"><img src="${pageContext.request.contextPath}/resources/images/template_one/img/dotted_line.jpg" /></div>
    
    
   
    
    
    <div class="divider"><img src="${pageContext.request.contextPath}/resources/images/template_one/img/dotted_line.jpg" /></div>
    
    
 
    
    
    </div> <!-- End Main_Content !-->

</div> <!-- End Container  !-->

<jsp:include page="/WEB-INF/views/templates/template_one/inc/footer.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/templates/template_one/template_one_index.js"></script>
</body>

</html>