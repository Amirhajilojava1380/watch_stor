<?php
include 'conect.php';
try{
$id_cart =@$_GET["id_cart"];

$query ="DELETE FROM cart WHERE id_cart=:id_cart";
$stmt = $conn->prepare($query);
$stmt->bindparam(":id_cart" , $id_cart);
$stmt->execute();
    if($stmt){
		echo  '{"status":"succes","massage":"ok"}';
		
	}else{echo'{"status":"failed","massage":"no"}';}
	  
	  
	  }catch(Exception $e){
		die($e->getMessage());
	
	}
?>