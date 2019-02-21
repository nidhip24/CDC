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
$iid = $_POST["iid"];
$feedback = $_POST["feedback"];
$letter = $_POST["letter"];

$sql = "INSERT INTO `updateinternship`(`iid`, `uid`, `letter`, `feedback`) VALUES ('$iid','$userID','$letter','$feedback')";
if ($conn->query($sql) === TRUE) {
    echo "done";
} else {
    echo "Error";
}