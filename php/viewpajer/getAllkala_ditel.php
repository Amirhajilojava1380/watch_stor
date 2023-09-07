<?php
include'conect.php';
$quera = "SELECT * FROM pr WHERE off=0";
$stmt = $conn->prepare($quera);
$stmt->execute();
$slider = array();
while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
	$res = array();
    $value_off = $row['value_off'];
	$price =$row ['price'];
	$totaloffprice = $price * $value_off / 100 ;
	$finalprice = $price - $totaloffprice;
	$res ['offprice'] = $finalprice;
    if($value_off==0){$totaloffprice = 0;}

	$res['id'] = $row['id'];
	$res['img_link'] = $row['img_link'];
	$res['name'] = $row['name'];
	$res['id_list'] = $row['id_list'];
	$res['value_off'] = $row['value_off'];
	$res['price'] = $row['price'];
	$slider[] = $res ;
}
echo json_encode($slider);

?>