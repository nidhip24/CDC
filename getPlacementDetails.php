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

$cname = $_GET["cname"];

$sql = "SELECT * FROM `placementdata` WHERE cname='$cname'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) { 
		$str[]=  array(
        'cname' => $row["cname"],
        'info' => $row["info"],
        'link' => $row["link"]
       );
    }
    echo json_encode($str);
}else{
	echo "0";
}

$conn->close();