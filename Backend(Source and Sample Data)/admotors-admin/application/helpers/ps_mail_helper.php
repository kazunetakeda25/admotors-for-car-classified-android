<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Send Booking Request Email to hotel
 * @param  [type] $booking_id [description]
 * @return [type]             [description]
 */
if ( !function_exists( 'send_user_register_email' )) {

  function send_user_register_email( $user_id, $subject = "" )
  {
    // get ci instance
    $CI =& get_instance();
    
    $user_info_obj = $CI->User->get_one($user_id);

    $user_name  = $user_info_obj->user_name;
    $user_email = $user_info_obj->user_email;
    $code = $user_info_obj->code;
    

    $to = $user_email;

		$sender_name = $CI->config->item( 'sender_name' );

    $msg = <<<EOL
<p>Hi {$user_name},</p>

<p>Your new User Account has been created. Welcome to PS Buy&Sell. Please verified with the code at below to actived your account.</p>

<p>
Verified Code : {$code}<br/>
</p>


<p>
Best Regards,<br/>
{$sender_name}
</p>
EOL;
    
    
    

    // send email from admin
    return $CI->ps_mail->send_from_admin( $to, $subject, $msg );
  }
}
