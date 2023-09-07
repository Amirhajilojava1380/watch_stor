<?php
include'conect.php';
$Id_list =@$_POST["Id_list"];

$quera = "SELECT * FROM list_home  WHERE id=:id";
$stmt = $conn->prepare($quera);
$stmt->bindparam( ":id" , $Id_list );
$stmt->execute();

$res = array();
while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
	$res['id'] = $row['id'];
}

$quera1 = "SELECT * FROM pr  WHERE id_list =:id" ;
$stmt1 = $conn->prepare($quera1);
$stmt1->bindparam( ":id" , $res["id"]  );
$stmt1->execute();
$slider = array();

while($row1 = $stmt1->fetch(PDO::FETCH_ASSOC)){
    $res1 = array();
	$res1['id']       = $row1['id'];
	$res1['name']     = $row1['name'];
	$res1['img_link'] = $row1['img_link'];
	$res1['price']    = $row1['price'];
	$res1['id_list']  = $row1['id_list'];
	$slider[] = $res1;
}
echo json_encode($slider);

?>