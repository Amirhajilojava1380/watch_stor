<?php
include'conect.php';
$id =@$_POST["id"];
$quera = "SELECT * FROM pr  WHERE id=:id";
$stmt = $conn->prepare($quera);
$stmt->bindparam( ":id" , $id );
$stmt->execute();
 $res = array();
while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
	$res['id'] = $row['id'];
}

$quera1 = "SELECT * FROM coment  WHERE id_pr=:id";
$stmt1 = $conn->prepare($quera1);
$stmt1->bindparam( ":id" , $res["id"]  );
$stmt1->execute();
$slider = array();
while($row1 = $stmt1->fetch(PDO::FETCH_ASSOC)){
    $res1 = array();
	$res1['id'] = $row1['id'];
	$res1['user_email'] = $row1['user_email'];
	$res1['id_pr'] = $row1['id_pr'];
	$res1['decreption'] = $row1['decreption'];
	$res1['data'] = $row1['data'];
	$res1['rating'] = $row1['rating'];
	$slider[] = $res1;
}
echo json_encode($slider);

?>