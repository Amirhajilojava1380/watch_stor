<?php
include 'conect.php';
try{
$id_pr =@$_GET["id_pr"];

$query ="DELETE FROM favorite WHERE id_pr=:id_pr";
$stmt = $conn->prepare($query);
$stmt->bindparam(":id_pr" , $id_pr);
$stmt->execute();
    if($stmt){
		echo  '{"status":"succes","massage":"ok"}';
		
	}else{echo'{"status":"failed","massage":"no"}';}
	  
	  
	  }catch(Exception $e){
		die($e->getMessage());
	
	}
?>