/*
Copyright (c) 2003-2010, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.addTemplates('default', {
	imagesPath:CKEDITOR.getUrl(CKEDITOR.plugins.getPath('templates')+'templates/images/'),
    templates: [
	
	{
		title:'Headline Image and Text',
		image:'template_one.gif',
		description:'A headline with 2 columns beneath it. The left column is an image and the right is text.',
		html:'<h2><span>Here is a headline for this section</span></h2><div class="submodule_left"><img class="border" src="img/image_one.jpg" /></div><div class="submodule_right"><p>Eu ius apeirian phaedrum, iusto verear no his, est cu utinam persequeris. Ut sonet aliquid nostrum mei, et mei dico detracto perpetua. Dicta alterum epicurei ex nam, an qui nullam graeco laboramus, iudico cetero pro an. Sea ad sanctus concludaturque. Ne primis apeirian sit, falli inimicus electram ne eam. Vix dicam quodsi iudicabit eu, eos dicta volumus singulis ea. Nisl eligendi appetere te vim.</p><p class="submodule_text">&nbsp;</p><br /><a href="contact.php" title="send me a message"><imgsrc="img/button_send_me_a_message.jpg" align="right" /></a></div>'
	},
	
    {
		title:'Image and Title',
		image:'template1.gif',
		description:'One main image with a title and text that surround the image.',
		html:'<h3><img style="margin-right: 10px" height="100" width="100" align="left"/>Type the title here</h3><p>Type the text here</p>'
	},
	
    {
		title:'Strange Template',
		image:'template2.gif',
		description:'A template that defines two colums, each one with a title, and some text.',
		html:'<table cellspacing="0" cellpadding="0" style="width:100%" border="0"><tr><td style="width:50%"><h3>Title 1</h3></td><td></td><td style="width:50%"><h3>Title 2</h3></td></tr><tr><td>Text 1</td><td></td><td>Text 2</td></tr></table><p>More text goes here.</p>'
	},
	
    {
		title:'Text and Table',
		image:'template3.gif',
		description:'A title with some text and a table.',
		html:'<div style="width: 80%"><h3>Title goes here</h3><table style="width:150px;float: right" cellspacing="0" cellpadding="0" border="1"><caption style="border:solid 1px black"><strong>Table title</strong></caption></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></table><p>Type the text here</p></div>'
	}
	
	]
	
});
