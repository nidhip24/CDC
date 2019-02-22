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

$feedback = $_POST["feedback"];
$uid = $_POST["uid"];

// $cname = $_GET["companyname"];
// $data = $_GET["data"];
// $link = $_GET["link"];

$sql = "INSERT INTO `feedback`(`uid`, `feedback`) VALUES ('$uid','$feedback')";

if ($conn->query($sql) === TRUE) {
    echo "done";
} else {
    echo "Error".$sql;
}