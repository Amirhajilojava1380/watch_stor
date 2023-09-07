<?php
include'conect.php';
$id =@$_POST["id"];
$quera = "SELECT * FROM br  WHERE id=:id";
$stmt = $conn->prepare($quera);
$stmt->bindparam( ":id" , $id );
$stmt->execute();
 $res = array();
while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
	$res['id'] = $row['id'];
}

$quera1 = "SELECT * FROM pr  WHERE brand_id=:id AND off=0";
$stmt1 = $conn->prepare($quera1);
$stmt1->bindparam( ":id" , $res["id"]  );
$stmt1->execute();
$slider = array();
while($row1 = $stmt1->fetch(PDO::FETCH_ASSOC)){
    $res1 = array();
	$res1['id'] = $row1['id'];
	$res1['img_link'] = $row1['img_link'];
	$res1['name'] = $row1['name'];
	$res1['id_list'] = $row1['id_list'];
	$res1['brand_id'] = $row1['brand_id'];
	$res1['price'] = $row1['price'];
	$slider[] = $res1;
}
echo json_encode($slider);

?>