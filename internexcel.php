<?php

require 'vendor/autoload.php';

use PhpOffice\PhpSpreadsheet\Spreadsheet;
use PhpOffice\PhpSpreadsheet\Writer\Xlsx;

$spreadsheet = new Spreadsheet();
$sheet = $spreadsheet->getActiveSheet();
$sheet->setTitle('Placement');
$sheet->setCellValue('A1', 'Placement data');

$sheet->setCellValue('A3', 'Name');            //mycdc
$sheet->setCellValue('B3', 'Roll no');         //mycdc
$sheet->setCellValue('C3', 'Class');           //mycdc
$sheet->setCellValue('D3', 'Compnay name');    //internship
$sheet->setCellValue('E3', 'letter');          //updateinternship
$sheet->setCellValue('F3', 'feedback');        //updateinternship
$sheet->setCellValue('G3', 'Phone number');    //mycdc
$sheet->setCellValue('H3', 'Email ID');        //mycdc

//getting connection
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

$sql = "SELECT m.fname,m.rollno,m.clas,m.phno,m.eid,i.cname,u.letter,u.feedback FROM internship as i,mycdc as m,updateinternship as u  WHERE i.id = u.iid and u.uid = m.id";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    $i=4;
    while($row = $result->fetch_assoc()) {
    	$sheet->setCellValue('A'.$i, $row["fname"]);          //mycdc
		$sheet->setCellValue('B'.$i, $row["rollno"]);         //mycdc
		$sheet->setCellValue('C'.$i, $row["clas"]);           //mycdc
		$sheet->setCellValue('D'.$i, $row["cname"]);          //internship
		$sheet->setCellValue('E'.$i, $row["feedback"]);       //updateinternship
		$sheet->setCellValue('F'.$i, $row["letter"]);         //updateinternship
		$sheet->setCellValue('G'.$i, $row["phno"]);           //mycdc
		$sheet->setCellValue('H'.$i, $row["eid"]);            //mycdc
		$i++;
    }
    foreach (range('A', $spreadsheet->getActiveSheet()->getHighestDataColumn()) as $col) {
		$spreadsheet->getActiveSheet()->getColumnDimension($col)->setAutoSize(true);
	}

	$writer = new Xlsx($spreadsheet);
	$writer->save('doc/interndata.xlsx');

	echo "interndata.xlsx";
	
}else{
	echo "No result";
}
?>