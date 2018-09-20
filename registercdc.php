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

$fname = $_POST["fname"];
$rollno = $_POST["rollno"];
$grno = $_POST["grno"];
$phno = $_POST["phno"];
$eid = $_POST["eid"];
$clas = $_POST["class"];
$ssc = $_POST["ssc"];
$hsc = $_POST["hsc"];
$sem1 = $_POST["sem1"];
$sem2 = $_POST["sem2"];
$sem3 = $_POST["sem3"];
$sem4 = $_POST["sem4"];
$uid = $_POST["uid"];
$pass = $_POST["pass"];
$cnfpass = $_POST["cnfpass"];
$memno = $_POST["membership"];



$sql = "INSERT INTO `mycdc`(`fname`, `rollno`, `grno`, `phno`, `eid`, `clas`, `ssc`, `hsc`, `sem1`, `sem2`, `sem3`, `sem4`, `uid`, `pass`, `cnfpass`, `memno`) VALUES ('".$fname."','".$rollno."','".$grno."','".$phno."','".$eid."','".$clas."','".$ssc."','".$hsc."','".$sem1."','".$sem2."','".$sem3."','".$sem4."','".$uid."','".$pass."','".$cnfpass."','".$memno."')";

if ($conn->query($sql) === TRUE) {
    echo "Done";
} else {
    echo "Error";
}

$conn->close();