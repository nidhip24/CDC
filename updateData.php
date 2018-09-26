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

$userID = $_POST["userID"];
$pid = $_POST["pid"];
$des = $_POST["designation"];
$pack = $_POST["package"];

$sql = "INSERT INTO `updatePlacement`(`uid`,`pid`,`designation`,`package`) VALUES ('$userID','$pid','$des','$pack')";
if ($conn->query($sql) === TRUE) {
    echo "done";
} else {
    echo "Error";
}