<?php  header('Content-type: text/css'); ?>
.display-1,
.display-2,
.display-3,
.display-4 {
	font-family: <?php echo $display_font; ?>;
	color: <?php echo $display_color; ?>;
	font-weight: 600;
}

h1,
.h1,
h2,
.h2,
h3,
.h3,
h4,
.h4,
h5,
.h5,
h6,
.h6 {
	color: <?php echo $heading_color; ?>;
	font-weight: bold;
}

body {
	font-family: <?php echo $body_font; ?>;
	font-size: 1rem;
	line-height: 1.5rem;
	color: <?php echo $body_color; ?>;
}
h1 {
	font-size: 2rem;
	line-height: 2rem;
	font-weight: 900;
}
h2 {
	font-size: 1.5rem;
	line-height: 2rem;
}
h3 {
	font-size: 1.5rem;
	line-height: 2rem;
}
h4 {
	font-size: 1rem;
	line-height: 1.4rem;
	font-weight: bold;
}
h5 {
	font-size: 0.8rem;
	line-height: 1rem;
	font-weight: 600;
}
h6 {
	font-size: 0.75rem;
}
p {
	font-size: 0.85rem;
	line-height: 1.4rem;
}
.lead {
	font-size: 1.1rem;
}

.ps-input {
	width: 100%;
	padding: 0 10px;

	background: #f0f0f0;
	color: #666;

	border-color: transparent;
	box-sizing: border-box;
	margin: 0;
	border-radius: 0;

	font-size: .8rem;

	-webkit-transition: 0.2s ease-in-out;
	transition: 0.2s ease-in-out;

	outline: none;
}

a, a:hover {
	color: inherit;
	text-decoration: inherit;
}

@media (min-width: 576px) {
	.heading .display-4{
		font-size: 2.8rem;
	}
}

@media (min-width: 768px) {
	.heading .display-4{
		font-size: 3rem;
	}
}

@media (min-width: 992px) {
	.heading .display-4{
		font-size: 3.3rem;
	}
}

@media (min-width: 1200px) {
	.heading .display-4{
		font-size: 3.5rem;
	}
}


/*$bgDefault            : <?php echo $bg_default; ?>;
$bgHighlight        : <?php echo $bg_highlight; ?>;
$colDefault         : <?php echo $color_default; ?>;
$colHighlight     : <?php echo $color_highlight; ?>;*/
/* Navigation */
.navigation {
	font-family: Tahoma, sans-serif;
	border: 1px solid <?php echo $main_nav_border; ?>;
	border-left: 0;
	border-right: 0;
	box-shadow: 0 3px 4px 0 rgba(0,0,0,.03);
	background: <?php echo $nav_bg_color; ?>;
}
.navigation .navbar {
	background-color: <?php echo $bg_default; ?>;
	padding: 0;
}
.navigation .navbar .navbar-nav .nav-link {
	color: <?php echo $color_default; ?>;
	border-radius: 0;
	margin: 0 0.25em;
	font-size: 0.85rem;
	font-weight: 500;
	padding: 10px 20px;
	/*text-align: center;*/
	letter-spacing: 0.08rem;
	outline: none;
}
.navigation .navbar .navbar-nav .nav-item.active .nav-link {
	background: <?php echo $bg_default; ?>;
}
.navigation .navbar .navbar-nav .nav-link:not(.disabled):hover,
.navigation .navbar .navbar-nav .nav-link:not(.disabled):focus {
	color: <?php echo $color_highlight; ?>;
}
.navigation .navbar .navbar-nav .nav-item.active .nav-link,
.navigation .navbar .navbar-nav .nav-item.active .nav-link:hover,
.navigation .navbar .navbar-nav .nav-item.active .nav-link:focus,
.navigation .navbar .navbar-nav .nav-item.show .nav-link,
.navigation .navbar .navbar-nav .nav-item.show .nav-link:hover,
.navigation .navbar .navbar-nav .nav-item.show .nav-link:focus {
	color: <?php echo $color_highlight; ?>;
	display: block;
}
.navigation .navbar .navbar-toggle {
	border-color: <?php echo $bg_highlight; ?>;
}
.navigation .navbar .navbar-toggle:hover,
.navigation .navbar .navbar-toggle:focus {
	background-color: <?php echo $bg_highlight; ?>;
}
.navigation .navbar .navbar-toggle .navbar-toggler-icon {
	color: <?php echo $color_default; ?>;
}
.navigation .navbar .navbar-collapse {
	border-color: <?php echo $color_default; ?>;
}
.navigation .navbar .navbar-link {
	color: <?php echo $color_default; ?>;
}
.navigation .navbar .navbar-link:hover {
	color: <?php echo $color_highlight; ?>;
}
/* Dropdown Item */
.dropdown-item {	
	border-radius: 0;
	font-size: 0.85rem;
	letter-spacing: 0.08rem;
}
.navbar-expand .navbar-nav .show .dropdown-menu .dropdown-item {
	color: <?php echo $color_default; ?>;
}
.navbar-expand .navbar-nav .show .dropdown-menu .dropdown-item:hover,
.navbar-expand .navbar-nav .show .dropdown-menu .dropdown-item:focus {
	color: <?php echo $color_highlight; ?>;
}
.navbar-expand .navbar-nav .show .dropdown-menu .dropdown-item.active {
	color: <?php echo $color_highlight; ?>;
	background-color: <?php echo $bg_highlight; ?>;
}

/* Dropdown Menu */
.navigation .dropdown-menu {
	border-radius: 0;
}

/* Custom */
.sticky {
    position: fixed;
    width: 100%;
    left: 0;
    top: 0;
    z-index: 100;
    border-top: 0;
}

/* Common */
.news-heading {
	text-transform: uppercase;
	font-size: 14px;
	letter-spacing: .02em;
	margin: 10px 0px 20px 0px;
	padding-left: 10px;
	color: <?php echo $news_heading_color; ?>;
	background:<?php echo $news_heading_bg_color; ?>;
	border-top: 1px solid <?php echo $news_heading_bdr_color; ?>;
	border-bottom: 1px solid <?php echo $news_heading_bdr_color; ?>;
    border-bottom-width: 2px;
}
.sidebar-heading {
	font-size: 14px;
	letter-spacing: .02em;
	margin: 10px 0px 20px 0px;
	padding-left: 10px;
	color: <?php echo $sidebar_heading_color; ?>;
	background: <?php echo $sidebar_heading_bg_color; ?>;
}

/* 
 * Home Page 
 */
/* Heading */
.heading {
	background: <?php echo $heading_bg_color; ?>;
	color: <?php echo $heading_text_color; ?>;
}

/* Ticker Headline */
.ticker-headline a {
	color: <?php echo $body_color; ?>;
	font-size: 0.8rem;
	vertical-align: middle;
}
.breaking-news .carousel-control {
	color: #bada55;
}

/* editor pick */
.editor-pick .card, .editor-pick .card-img, .editor-pick .card-img-top,
.featured-article .card, .featured-article .card-img, .featured-article .card-img-top,
.featuired-media .card, .featuired-media .card-img, .featuired-media .card-img-top {
	border-radius: 0;
}
.editor-pick .caption {
	position: absolute;
	width: 80%;
	bottom: 0;
	padding: 30px 15px;
}
.editor-pick .card-title {
	color: <?php echo $editorpick_heading_color; ?>;
}
.editor-pick .card-text {
	font-size: 0.75rem;
	color: <?php echo $editorpick_text_color; ?>;
}
.editor-pick .card-img-link {
	display: block;
	transition: all .25s ease-in-out;
}
.editor-pick .card-img-link:hover {
	opacity: .90;
}
.editor-pick .card-img-link:after {
	content: '';
	position: absolute;
	bottom: 0;
	width: 100%;
	height: 100%;
	display: block;
	background: linear-gradient(to bottom,rgba(0,0,0,0) 0,rgba(0,0,0,0.42) 50%,rgba(0,0,0,0.88) 100%);
	opacity: .92;
}

/* featured article */
.featured-article .card {
	border: 0;
}
.featured-article .card-body {
	padding-left: 0;
}
.featured-article .meta {
	font-size: 0.75rem;
}
.featured-article-sm .meta {
	font-size: 0.75rem;
	margin-bottom: 5px;	
}
.featured-article .card, .featured-article-sm li {
	transition: all .25s ease-in;
}
.featured-article .card:hover, .featured-article-sm li:hover {
	color: <?php echo $heading_color; ?>;
	background-color: rgba(238,238,238, .7);	
}

/* featured article small */
.featured-media .meta {
	font-size: 0.75rem;
	margin-bottom: 5px;
}
.featured-media .media {
	padding-bottom: 15px;
	border-bottom: 1px solid <?php echo $media_bdr_color; ?>;
}
.featured-media li {
	transition: all .25s ease-in;
}
.featured-media li:hover {
	color: <?php echo $heading_color; ?>;
	background-color: rgba(238,238,238, .7);	
}

/* Recent Articles */
.recent-media .media-date {
	font-size: 0.75rem;
	margin-bottom: 5px;
}
.recent-media .media {
	padding-bottom: 15px;
	border-bottom: 1px solid <?php echo $media_bdr_color; ?>;	
}
/* Latest Articles */
.latest-media h4 {
	font-size: 1.3rem;
	line-height: 2.3rem;
	font-weight: bold;
	border-bottom: 1px solid #ddd;	
}
.latest-media h5 {
	font-size: 0.9rem;
	font-weight: normal;
	color: #000;
}
.latest-media .media-date {
	font-size: 0.75rem;
	margin-bottom: 0.5rem;
}
.latest-media .media {
	border-bottom: 1px solid <?php echo $media_bdr_color; ?>;
}
.latest-media li {
	opacity: .8;
	transition: all .1s ease-in-out;
}
.latest-media li:hover {
	color: #111;
	opacity: 1;
}

/* Article List */
.article-list a {
	color: inherit;
	text-decoration: inherit;
}
.article-list li {
	transition: all .25s ease-in;
}
.article-list li:hover {
	color: <?php echo $heading_color; ?>;
	border-color: <?php echo $heading_color; ?>;
	background-color: rgba(238,238,238, .7);
}

/* Categories */
.categories .card-body {
	color: <?php echo $category_color; ?>;
	background: <?php echo $category_bg_color; ?>;
	padding: 20px;
}
.categories .card-text {
	color: <?php echo $category_color; ?>;
}
.categories .card-img-top {
	height: 105px;
}
.categories .cat{
	opacity: .9;
	transition: all .25s ease-in-out;
}
.categories .cat:hover {
	opacity: .7;
}
.btn-subscribe {
	position: absolute;
	z-index: 100;
	font-size: 1.1rem;
	top: -0.6rem;
	right: 0.6rem;
	/*background-color: #B2DFDB;*/
	margin:0;
	/*color: green;*/
}
.btn-subscribe:hover{
	opacity: 1;
}
.btn-subscribe .grow { transition: all .2s ease-in-out; }
.btn-subscribe .grow:hover { transform: scale(1.5); }

/* Main Footer */
.main-footer {
	color: <?php echo $footer_color; ?>;
	background: <?php echo $footer_bg_color; ?>;
}
.main-footer p {
	font-size: 0.9rem;
}
.main-footer h5 {
	color: <?php echo $footer_color; ?>;
}
.main-footer .footer-title {
	color: <?php echo $footer_title_color; ?>;
	padding: 10px 0px;
	border-bottom: 1px solid <?php echo $footer_title_bdr_color; ?>;
}

/* Contact Us */
.contact-us .ps-input{
	color: <?php echo $footer_input_color; ?>;
	background-color: <?php echo $footer_input_bg_color; ?>;
}

/* Footer Popular Articles */
.popular-posts .media-date {
	font-size: 0.75rem;
	margin-bottom: 5px;
}
.popular-posts .media {
	padding-bottom: 15px;
	border-bottom: 1px solid <?php echo $media_bdr_color; ?>;
}

/* Footer Recent Comments */
.recent-comments .media-date {
	font-size: 0.75rem;
	margin-bottom: 5px;
}
.recent-comments .media {
	padding-bottom: 15px;
	border-bottom: 1px solid <?php echo $media_bdr_color; ?>;
}

/* Lower Footer */
.lower-footer {
	font-size: 0.85rem;
    background: <?php echo $footer_lower_bg_color; ?>;
    color: <?php echo $footer_color; ?>;
}

/* Comment */
.comments .comment {
	border-bottom: 1px solid #ddd;
}

/* Grow Effect */
.grow { transition: all .2s ease-in-out; }
.grow:hover { transform: scale(1.1); }

.social a {
	transition: all .2s ease-in-out;
	border-radius: 5px;
	background: #eee;
}
.social a:hover {
	background: #ccc;
}

/** Youtube Videos */
.youtube_video {
	margin: 0 auto;
	border: 5px solid #ddd;
	border-radius: 5px;
}
.videoWrapper {
	position: relative;
	padding-bottom: 56.25%; /* 16:9 */
	padding-top: 25px;
	height: 0;
}
.videoWrapper iframe {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}

/** News Slider */
/* Indicators list style */
#newsSlider .carousel-item img {
	width: 100%;
	object-fit: cover;
}
#newsSlider .nav {
	
}
#newsSlider .nav-link {
	padding: 5px;
}
#newsSlider .nav-link img {
	padding: 0;
	width: 100%;
	object-fit: cover;
}

/* Search box small */
.searchbox input{
	vertical-align: middle;
	padding: 0 10px;
	font-size: .8rem;

	-webkit-transition: 0.2s ease-in-out;
	transition: 0.2s ease-in-out;

	outline: none;
}

/* Search Box Large */
.search-form-lg .bootstrap-select .dropdown-toggle:focus, 
.search-form-lg .dropdown-item  {
	outline: none !important;
}