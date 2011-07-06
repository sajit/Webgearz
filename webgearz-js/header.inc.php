<?php $currentPage = basename($_SERVER['SCRIPT_NAME']); ?>

<div id="header">

    <h1 id="logo"><div class="editable" id="data_logo"><a href="index.php"><?php include("content/data_logo.txt");?></a></div></h1>

    <ul id="navigation">
    <li <?php if ($currentPage == 'about.php') {echo 'class="current"';} ?>><a href="about.php">About</a></li>
    <li <?php if ($currentPage == 'what.php') {echo 'class="current"';} ?>><a href="what.php">What I Do</a></li>
    <li <?php if ($currentPage == 'news.php') {echo 'class="current"';} ?>><a href="news.php">News</a></li>
    <li <?php if ($currentPage == 'contact.php') {echo 'class="current"';} ?>><a href="contact.php">Contact</a></li>
    </ul>
    
    <div><img src="img/dotted_line.jpg" /></div>
    
</div>