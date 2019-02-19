

<?php

$pno = "9967188022";
$name = "Nidhip kathiriya";
$email = "nidhipkathiriya@gmail.com";
$pass = "nkcdc2019";

//post
$url="www.way2sms.com/api/v1/sendCampaign";
$message = urlencode("Dear $name,\nThank you registering with Nagindas Khandwala College's Career Development Cell\nFuture ready Today !!\n\nYour Username : $email \nPassword : $pass \n\nRegards,\nTeam CDC");// urlencode your message
$curl = curl_init();
curl_setopt($curl, CURLOPT_POST, 1);// set post data to true
curl_setopt($curl, CURLOPT_POSTFIELDS, "apikey=QXC6RH2G9FPI3QM7CO7SJEOP061RXGEV&secret=NIP502YD8HTBFR80&usetype=stage&phone=$pno&senderid=CDC&message=$message");// post data
// query parameter values must be given without squarebrackets.
 // Optional Authentication:
curl_setopt($curl, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
curl_setopt($curl, CURLOPT_URL, $url);
curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
$result = curl_exec($curl);
curl_close($curl);
echo $result;
?>