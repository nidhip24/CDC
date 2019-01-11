
<?php
	
	$floc = $_POST["file"];
	$filename = $_POST["filename"] . '.pdf';
	$id = $_POST["id"];
	$wpdf = $_POST["wpdf"];

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

	$sql = "SELECT `docid` FROM `document` WHERE `docid`='$id'";
	//echo "$sql";
	$result = $conn->query($sql);

	if ($result->num_rows > 0) {
	    // output data of each row
	    while($row = $result->fetch_assoc()) {
	        if($id == $row["docid"]){
	 			$sql = "UPDATE `document` SET `$wpdf`='$filename' WHERE docid = '$id'";	
				if ($conn->query($sql) === TRUE) {
				    //echo "done";
				} else {
				    echo "error";
				}
	        }
	    }
	}else{
		//echo "not found";
		$sql = "INSERT INTO `document`(`docid`,`$wpdf`) VALUES ('$id','$filename')";
		if ($conn->query($sql) === TRUE) {
		    //echo "done";
		} else {
		    echo "error";
		}
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