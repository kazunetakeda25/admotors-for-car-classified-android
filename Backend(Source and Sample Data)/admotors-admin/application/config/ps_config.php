<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/** Home Page Config */
/* 1) if both frontend and backend folder exist */
// $config['homepage'] = "home";
// $config['reset_url'] = "reset";

/* 2) if only backend folder */
$config['homepage'] = "admin";
$config['reset_url'] = "reset_email";

/** Themes File Names */
$config['themes'] = array( 'default', 'green', 'blue', 'orange', 'blue-grey' );

/** System Email */
$config['sender_name'] = "Team PS";
$config['sender_email'] = "admin@panacea-soft.com";

$config['fcm_api_key']  = "AIzaSyAzKBPuzGuR0nlvY0AxPrXsEMBuRUxO4WE";

/** Validation */
$config['client_side_validation'] = true;
$config['ajax_request_checking'] = true;

/** Comments */
$config['comments_display_limit'] = 3;
$config['news_display_limit'] = 6;
$config['fav_display_limit'] = 3;
$config['like_display_limit'] = 3;

/** FrontEnd Template Path */
$config['fe_view_path'] = 'frontend';
$config['fe_url'] = '';

/** Backend Teamplate Path */
$config['be_view_path'] = 'backend';
$config['be_url'] = 'admin';

/** Uploads Folder Path */
$config['upload_path'] = 'uploads/';
$config['upload_thumbnail_path'] = 'uploads/thumbnail/';
$config['image_type'] = 'jpg|jpeg|png|csv';

/** Pagination */
$config['pagination']['per_page'] = 20;
$config['pagination']['num_links'] = 5;
$config['pagination']['uri_segment'] = 4;
$config['pagination']['attributes'] = array('class' => 'page-link');
$config['pagination']['full_tag_open'] =  '<ul class="pagination">';
$config['pagination']['full_tag_close'] = '</ul>';
$config['pagination']['num_tag_open'] = '<li class="page-item">';
$config['pagination']['num_tag_close'] = '</li>';
$config['pagination']['first_link'] = '&laquo;';
$config['pagination']['first_tag_open'] = '<li class="page-item">';
$config['pagination']['first_tag_close'] = '</li>';
$config['pagination']['last_link'] = '&raquo;';
$config['pagination']['last_tag_open'] = '<li class="page-item">';
$config['pagination']['last_tag_close'] = '</li>';
$config['pagination']['next_link'] = '&raquo;';
$config['pagination']['next_tag_open'] = '<li class="page-item">';
$config['pagination']['next_tag_close'] = '</li>';
$config['pagination']['prev_link'] = '&laquo;';
$config['pagination']['prev_tag_open'] = '<li class="page-item">';
$config['pagination']['prev_tag_close'] = '</li>';
$config['pagination']['cur_tag_open'] = '<li class="page-item active"><a class="page-link">';
$config['pagination']['cur_tag_close'] = '</a></li>';