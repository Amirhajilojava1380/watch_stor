<?php

include 'conect.php';
$phon = @$_GET["phon"];

$query ="SELECT cart.id_cart  , pr.id , pr.name , pr.img_link , pr.price , pr.value_off 
FROM cart JOIN pr ON cart.id_pr = pr.id WHERE cart.phon=:phon AND pey_off = 0";
$stmt = $conn->prepare($query);
$stmt->bindparam(":phon" , $phon);
$stmt->execute();
    $slider = array();
	while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
		$rec2  =  array();
		
    $value_off        = $row ['value_off'];
	$price            = $row ['price'];
	$totaloffprice    = $price * $value_off / 100 ;
	$finalprice       = $price - $totaloffprice;
	$row ['offprice'] = $finalprice;
    if($value_off==0)   {$totaloffprice = 0;}
		
		
		
	
	$rec2 ['id_cart']   =   $row['id_cart'];
	$rec2 ['id_pr']     =   $row['id'];
	$rec2 ['img_link']  =   $row['img_link'];
	$rec2 ['name']      =   $row['name'];
	$rec2 ['price']     =   $row['price'];
	$rec2 ['offprice']  =   $row['offprice'];

	$slider[] = $rec2;
}
	  echo json_encode($slider);
	  
?>