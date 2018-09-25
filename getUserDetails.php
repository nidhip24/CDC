<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mycdc";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " );//. $conn->connect_error);
}

$id = $_GET["id"];

$sql = "SELECT fname,rollno,grno,clas FROM `mycdc` WHERE id='$id'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) { 
    		$str[]=  array(
    			'fname' => $row["fname"],
	    		'rollno' => $row["rollno"],
                'grno' => $row["grno"],
                'clas' => $row["clas"]
	    	);
    }
    echo json_encode($str);
}else{
    $str[]=  array(
    'fname' => "-1",
    'rollno' => "-1",
    'grno' => "-1",
    'clas' => "-1"
    );
}

$conn->close();