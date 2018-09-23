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

$cname = $_POST["companyname"];
$data = $_POST["data"];
$link = $_POST["link"];

$sql1 = "INSERT INTO `placementcompany`(`cname`) VALUES ('$cname')";

if ($conn->query($sql1) === TRUE) {
	$sql2 = "INSERT INTO `placementdata`(`cname`, `info`, `link`) VALUES ('$cname','$data','$link')";
	if ($conn->query($sql2) === TRUE) {
    	echo "Done";
    }else {
    	echo "Error";
	}
} else {
    echo "Error1";
}