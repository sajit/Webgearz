<?php
include("config.php");
$htmlData = $_POST[$_POST['tVal']];
$htmlData = str_replace("\r\n", " ", $htmlData); //remove carriage returns. why ? (I think to assist the transfer of data via JS) 
$htmlData = str_replace("\n", " ", $htmlData); //remove carriage returns. why ? (I think to assist the transfer of data via JS)
?>
<script type="text/javascript">
<!--
onload=self.parent.WebGearz.closeEditing('<?php echo $_POST['file'];?>','<?php echo $htmlData;?>');
-->
</script>