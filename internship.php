<?php
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

require_once __DIR__ . '/firebase.php';
require_once __DIR__ . '/push.php';

$firebase = new Firebase();
$push = new Push();

$cname = $_POST["companyname"];
$data = $_POST["data"];
$link = $_POST["link"];

$sql = "INSERT INTO `internship`(`cname`, `info`, `link`) VALUES ('$cname','$data','$link')";

if ($conn->query($sql) === TRUE) {
    echo "done";

    $push->setTitle("Internship");
	$push->setMessage("New Internship has been added, Check it out.");

	$json = $push->getPush();
    $response = $firebase->sendToTopic('global', $json);

} else {
    echo "Error".$sql;

}