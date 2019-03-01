<?php 
	//PHPMailer lib
	use PHPMailer\PHPMailer\PHPMailer;
	use PHPMailer\PHPMailer\Exception;

	//Load Composer's autoloader
	require 'vendor/autoload.php';
	
	//sms send
	$fname = $_POST["fname"];
	$phno = $_POST["phno"];
	$eid = $_POST["eid"];
	$uid = $_POST["uid"];
	$pass = $_POST["pass"];

	$url="www.way2sms.com/api/v1/sendCampaign";
	$message = urlencode("Dear $fname,\nThank you registering with Nagindas Khandwala College's Career Development Cell\nFuture ready Today !!\n\nYour Username : $uid \nPassword : $pass \n\nRegards,\nTeam CDC");// urlencode your message
	$curl = curl_init();
	curl_setopt($curl, CURLOPT_POST, 1);// set post data to true
	curl_setopt($curl, CURLOPT_POSTFIELDS, "apikey=MODXXD41NL3ESE4G3RNK0C1LDEX1JK0B&secret=Y4LICHHPVMIDHCME&usetype=stage&phone=$phno&senderid=CDC&message=$message");// post data
	// query parameter values must be given without squarebrackets.
	 // Optional Authentication:
	curl_setopt($curl, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
	curl_setopt($curl, CURLOPT_URL, $url);
	curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
	$result = curl_exec($curl);
	curl_close($curl);
	//echo $result;

	$mail = new PHPMailer(true);                              // Passing `true` enables exceptions
	try {
	    //Server settings
	    $mail->SMTPDebug = 0;                                 // Enable verbose debug output
	    $mail->isSMTP();    
	    $mail->Host = 'smtp.gmail.com';
	    $mail->SMTPAuth = true;                               // Enable SMTP authentication
	    $mail->Username = "nkcdcofficial@gmail.com";                 // SMTP username
	    $mail->Password = 'cdcexecutives';                           // SMTP password
	    $mail->SMTPSecure = 'tls';                            // Enable TLS encryption, `ssl` also accepted
	    $mail->Port = 587;                                    // TCP port to connect to

	    //Recipients
	    $mail->setFrom("nkcdcofficial@gmail.com", 'CDC');
	    $mail->addAddress($eid,$fname);     // Add a recipient
	   
	    //Content
	    $mail->isHTML(true);                                  // Set email format to HTML
	    $mail->Subject = 'Registration - Career Development Cell';
	    $mail->Body    = "Dear <b>$fname</b>,<br>Thank you registering with Nagindas Khandwala College's Career Development Cell<br>Future ready Today !!<br><br>Your Username : <b>$uid</b> <br>Password : <b>$pass</b> <br><br>Regards,<br>Team CDC";
	    $mail->AltBody = "Dear <b>$fname</b>,<br>Thank you registering with Nagindas Khandwala College's Career Development Cell<br>Future ready Today !!<br><br>Your Username : <b>$uid</b> <br>Password : <b>$pass</b> <br><br>Regards,<br>Team CDC";

	    $mail->send();

	    //echo 'Message has been sent';
	    echo "done";
	    //header("Location: send.php");
		//die();
	} catch (Exception $e) {
	    //echo 'Message could not be sent. Mailer Error: ', $mail->ErrorInfo;
	}

	