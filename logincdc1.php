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

$uid = $_POST["uid"];
$pass = $_POST["pass"];


$sql = "SELECT `uid`, `pass` FROM `mycdc` WHERE uid='".$uid."' and pass='".$pass."'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        if($uid == $row["uid"] && $pass == $row["pass"]){
        	$flag = -1;
        	$sql = "SELECT `username`,`type` FROM `user_privilege` WHERE username='$uid'";
        	$result = $conn->query($sql);
        	if ($result->num_rows > 0) {
        		while($row = $result->fetch_assoc()) {
        			if($uid == $row["username"] && $row["type"]=="admin"){
        				echo "admin";
        				$flag = 1;
        			}
        		}
        		if ($flag==-1) {
        			echo "done";
        		}
        	}else{	
        		echo "done";
        	}
			break;
        }
    }
} else {
    echo "failed";
}

$conn->close();