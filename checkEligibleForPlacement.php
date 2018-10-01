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

$uid = $_POST["uname"];


$sql = "SELECT `uid` FROM `updateplacement` WHERE uid=(select id from mycdc WHERE uid='$uid')";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    $flag = 0;
    while($row = $result->fetch_assoc()) {
        $flag = $flag + 1;
    }
    if($flag<2){
        echo "go";
    }else{
        echo "no";
    }
} else {
    echo "go";
}

$conn->close();