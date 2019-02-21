<?php
	
	$floc = $_POST["file"];
	$filename = $_POST["filename"] . '.pdf';
	$id = $_POST["id"];

	//connecting to database
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

	$sql = "INSERT INTO `offerletter`(`docid`, `lname`) VALUES ('$id','$filename')";
	if ($conn->query($sql) === TRUE) {
	    //echo "done";
	} else {
	    echo "error";
	}

	//decode base64 string
	$pdf_decoded = base64_decode ($floc);
	
	//Write data back to pdf file
	$pdf = fopen ("doc/".$filename,'w');
	fwrite ($pdf,$pdf_decoded);

	//close output file
	fclose ($pdf);
	
	echo 'done';
?>