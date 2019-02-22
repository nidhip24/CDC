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

$id = $_POST["id"];

$sql = "UPDATE `event` SET `status`='close' WHERE id='$id'";

if ($conn->query($sql) === TRUE) {
    echo "done";
} else {
    echo "Error".$sql;

}
