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
$id =  $_POST["id"];
// $uid = $_GET["uname"];
// $id = $_GET["id"];


$sql = "SELECT `uid` FROM `updateinternship` WHERE iid='$id' and  uid=(select id from mycdc WHERE uid='$uid')";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    $flag = 0;
    while($row = $result->fetch_assoc()) {
        $flag = $flag + 1;
    }
    if($flag<1){
        echo "go ".$flag;
    }else{
        echo "no";
    }
} else {
    echo "go";
}

$conn->close();