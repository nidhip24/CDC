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

$sql = "INSERT INTO `placementdata`(`cname`, `info`, `link`) VALUES ('$cname','$data','$link')";

if ($conn->query($sql) === TRUE) {
    echo "done";
} else {
    echo "Error".$sql;

}