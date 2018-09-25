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


$sql = "SELECT id,cname,status FROM `placementdata`";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) { 
    	if($row["status"]=="open"){
    		$str[]=  array(
    			'id' => $row["id"],
	    		'cname' => $row["cname"]
	    	);
    	}
    }
    echo json_encode($str);
}else{
	echo "0";
}

$conn->close();