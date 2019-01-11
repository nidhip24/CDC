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
$sheet->setCellValue('D3', 'Compnay name');    //placementdata
$sheet->setCellValue('E3', 'Designation');     //updateplacement
$sheet->setCellValue('F3', 'Package');         //updateplacement
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

$sql = "SELECT m.fname,m.rollno,m.clas,m.phno,m.eid,p.cname,u.designation,u.package FROM placementdata as p,mycdc as m,updateplacement as u  WHERE p.id = u.pid and u.uid = m.id";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    $i=4;
    while($row = $result->fetch_assoc()) {
    	$sheet->setCellValue('A'.$i, $row["fname"]);          //mycdc
		$sheet->setCellValue('B'.$i, $row["rollno"]);         //mycdc
		$sheet->setCellValue('C'.$i, $row["clas"]);           //mycdc
		$sheet->setCellValue('D'.$i, $row["cname"]);          //placementdata
		$sheet->setCellValue('E'.$i, $row["designation"]);    //updateplacement
		$sheet->setCellValue('F'.$i, $row["package"]);        //updateplacement
		$sheet->setCellValue('G'.$i, $row["phno"]);           //mycdc
		$sheet->setCellValue('H'.$i, $row["eid"]);            //mycdc
		$i++;
    }
    foreach (range('A', $spreadsheet->getActiveSheet()->getHighestDataColumn()) as $col) {
		$spreadsheet->getActiveSheet()->getColumnDimension($col)->setAutoSize(true);
	}

	$writer = new Xlsx($spreadsheet);
	$writer->save('doc/placementdata.xlsx');

	echo "placementdata.xlsx";
	
}else{
	echo "No result";
}
?>