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
$cid = $_POST["cid"];
$des = $_POST["designation"];
$pack = $_POST["package"];

$sql = "INSERT INTO `update`(`uid`,`cid`,`designation`,`package`) VALUES ('$userID','$cid','$des','$pack')";
if ($conn->query($sql1) === TRUE) {
    echo "Done";
} else {
    echo "Error";
}
