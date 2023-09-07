<?php
include 'conect.php';
try{
if($_POST){
$id_pr      = filter_input(INPUT_POST,'id_pr',FILTER_SANITIZE_STRING);
$user_email = filter_input(INPUT_POST,'user_email',FILTER_SANITIZE_STRING);
$decreption = filter_input(INPUT_POST,'decreption',FILTER_SANITIZE_STRING);
$data       = filter_input(INPUT_POST,'data',FILTER_SANITIZE_STRING);
$rating     = filter_input(INPUT_POST,'rating',FILTER_SANITIZE_STRING);
$response =[];

	$insertAccount ='INSERT INTO coment (id_pr,user_email,decreption,data,rating)
	                             VALUES(:id_pr,:user_email,:decreption,:data,:rating)';
	$statment = $conn->prepare($insertAccount);
	
	
	$statment->execute([
	':id_pr'       => $id_pr,
	':user_email'  => $user_email,
	':decreption'  => $decreption,
	':data'        => $data,
	':rating'      => $rating
	]);

     $response['status']=true;	
     $response['massage']="ٍ نظر شما ثبت شد";
	
	

$Json = json_encode($response,JSON_PRETTY_PRINT);
	echo $Json;
}}catch(Exception $e){
		die($e->getMessage());
	
	}

?>