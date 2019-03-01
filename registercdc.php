<?php
	
	session_start();

	//PHPMailer lib
	use PHPMailer\PHPMailer\PHPMailer;
	use PHPMailer\PHPMailer\Exception;

	//Load Composer's autoloader
	require 'vendor/autoload.php';

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mycdc";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 


$fname = $_POST["fname"];
$rollno = $_POST["rollno"];
$grno = $_POST["grno"];
$phno = $_POST["phno"];
$eid = $_POST["eid"];
$clas = $_POST["class"];
$ssc = $_POST["ssc"];
$hsc = $_POST["hsc"];
$sem1 = $_POST["sem1"];
$sem2 = $_POST["sem2"];
$sem3 = $_POST["sem3"];
$sem4 = $_POST["sem4"];
$sem5 = $_POST["sem5"];
$uid = $_POST["uid"];
$pass = $_POST["pass"];
$cnfpass = $_POST["cnfpass"];
$docid = $_POST["docid"];



$sql = "INSERT INTO `mycdc`(`docid`,`fname`, `rollno`, `grno`, `phno`, `eid`, `clas`, `ssc`, `hsc`, `sem1`, `sem2`, `sem3`, `sem4`, `sem5`, `uid`, `pass`, `cnfpass`) VALUES ('".$docid."','".$fname."','".$rollno."','".$grno."','".$phno."','".$eid."','".$clas."','".$ssc."','".$hsc."','".$sem1."','".$sem2."','".$sem3."','".$sem4."','$sem5','".$uid."','".$pass."','".$cnfpass."')";

if ($conn->query($sql) === TRUE) {
    //echo "Done";

		

	// //sms send
	// $url="www.way2sms.com/api/v1/sendCampaign";
	// $message = urlencode("Dear $fname,\nThank you registering with Nagindas Khandwala College's Career Development Cell\nFuture ready Today !!\n\nYour Username : $uid \nPassword : $pass \n\nRegards,\nTeam CDC");// urlencode your message
	// $curl = curl_init();
	// curl_setopt($curl, CURLOPT_POST, 1);// set post data to true
	// curl_setopt($curl, CURLOPT_POSTFIELDS, "apikey=QXC6RH2G9FPI3QM7CO7SJEOP061RXGEV&secret=NIP502YD8HTBFR80&usetype=stage&phone=$phno&senderid=CDC&message=$message");// post data
	// // query parameter values must be given without squarebrackets.
	//  // Optional Authentication:
	// curl_setopt($curl, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
	// curl_setopt($curl, CURLOPT_URL, $url);
	// curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
	// $result = curl_exec($curl);
	// curl_close($curl);
	// //echo $result;

	// //send mail
	// // $to      = $eid;
	// // $subject = 'Registration - Career Development Cell';

	// // $message = "Dear $fname,\nThank you registering with Nagindas Khandwala College's Career Development Cell\nFuture ready Today !!\n\nYour Username : $uid \nPassword : $pass \n\nRegards,\nTeam CDC";

	// // $headers = 'From: CDC' . "\r\n" .
	// //     'Reply-To: webmaster@example.com' . "\r\n" .
	// //     'X-Mailer: PHP/' . phpversion();

	// mail($to, $subject, $message, $headers);
	// $mail = new PHPMailer(true);                              // Passing `true` enables exceptions
	// try {
	//     //Server settings
	//     $mail->SMTPDebug = 0;                                 // Enable verbose debug output
	//     $mail->isSMTP();    
	//     $mail->Host = 'smtp.gmail.com';
	//     $mail->SMTPAuth = true;                               // Enable SMTP authentication
	//     $mail->Username = "nidhipkathiriya@gmail.com";                 // SMTP username
	//     $mail->Password = 'pqrdtiqvbsuzsyft';                           // SMTP password
	//     $mail->SMTPSecure = 'tls';                            // Enable TLS encryption, `ssl` also accepted
	//     $mail->Port = 587;                                    // TCP port to connect to

	//     //Recipients
	//     $mail->setFrom("nidhipkathiriya@gmail.com", 'CDC');
	//     $mail->addAddress($eid,$fname);     // Add a recipient
	   
	//     //Content
	//     $mail->isHTML(true);                                  // Set email format to HTML
	//     $mail->Subject = 'Registration - Career Development Cell';
	//     $mail->Body    = "Dear <b>$fname</b>,<br>Thank you registering with Nagindas Khandwala College's Career Development Cell<br>Future ready Today !!<br><br>Your Username : <b>$uid</b> <br>Password : <b>$pass</b> <br><br>Regards,<br>Team CDC";
	//     $mail->AltBody = "Dear <b>$fname</b>,<br>Thank you registering with Nagindas Khandwala College's Career Development Cell<br>Future ready Today !!<br><br>Your Username : <b>$uid</b> <br>Password : <b>$pass</b> <br><br>Regards,<br>Team CDC";

	//     $mail->send();

	//     //echo 'Message has been sent';
	//     echo "done";
	//     header("Location: send.php");
	// 	die();
	// } catch (Exception $e) {
	//     echo 'Message could not be sent. Mailer Error: ', $mail->ErrorInfo;
	// }
	echo "done";
	//header("Location: dm.php");
	//die();
} else {
    echo "Error";
}

$conn->close();