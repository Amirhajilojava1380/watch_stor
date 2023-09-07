<?php
include 'conect.php';

if($_POST){
$id_pr      = filter_input(INPUT_POST,'id_pr',FILTER_SANITIZE_STRING);
$phon       = filter_input(INPUT_POST,'phon',FILTER_SANITIZE_STRING);
$response   = [];

$userquery = $conn->prepare("SELECT * FROM 	favorite WHERE id_pr = ?");
$userquery->execute(array($id_pr));
$query = $userquery->fetch();

if($userquery->rowCount()!=0){
	$response['status'] = false;
	$response['massage'] = "از قبل به لیست اضافه شده بود";
}else{
	$insertAccount ='INSERT INTO favorite (id_pr,phon)
	                             VALUES   (:id_pr,:phon)';
	$statment = $conn->prepare($insertAccount);
	
	try{
	$statment->execute([
	':id_pr'   => $id_pr,
	':phon'    => $phon
	]);

     $response['status']= true ;	
     $response['massage']="اضافه شد به علاقه مندی ها";
	
	}catch(Exception $e){
		die($e->getMessage());
	
	}
	}

$Json = json_encode($response,JSON_PRETTY_PRINT);
	echo $Json;
}

?>