<?php

$name = "Nidhip kathiriya";
$email = "nidhipkathiriya@gmail.com";
$pass = "nkcdc2019";

$to      = $email;
$subject = 'Registration - Career Development Cell';

$message = "Dear $name,\nThank you registering with Nagindas Khandwala College's Career Development Cell\nFuture ready Today !!\n\nYour Username : $email \nPassword : $pass \n\nRegards,\nTeam CDC";

$headers = 'From: CDC' . "\r\n" .
    'Reply-To: webmaster@example.com' . "\r\n" .
    'X-Mailer: PHP/' . phpversion();

mail($to, $subject, $message, $headers);
?> 