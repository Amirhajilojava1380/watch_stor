<?php
include'conect.php';
$quera = "SELECT * FROM br";
$stmt = $conn->prepare($quera);
$stmt->execute();
$slider = array();
while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
    $res = array();
	$res['id'] = $row['id'];
	$res['img_link'] = $row['img_link'];
	$res['name'] = $row['name'];
	$slider[] = $res ;
}
echo json_encode($slider);

?>