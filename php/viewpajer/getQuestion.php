<?php
include'conect.php';
$quera = "SELECT * FROM qusetion";
$stmt = $conn->prepare($quera);
$stmt->execute();
$slider = array();
while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
	$res = array();
 
	$res['id'] = $row['id'];
	$res['titel'] = $row['titel'];
	$res['description'] = $row['description'];

	$slider[] = $res ;
}
echo json_encode($slider);

?>