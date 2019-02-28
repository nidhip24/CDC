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

// $cname = $_GET["companyname"];
// $data = $_GET["data"];
// $link = $_GET["link"];

$sql = "INSERT INTO `event`(`cname`, `info`, `link`) VALUES ('$cname','$data','$link')";

if ($conn->query($sql) === TRUE) {
    echo "done";

    $push->setTitle("Event");
	$push->setMessage("New Event is added.");

	$json = $push->getPush();
    $response = $firebase->sendToTopic('global', $json);
} else {
    echo "Error".$sql;
}