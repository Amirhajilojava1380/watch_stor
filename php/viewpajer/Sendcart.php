<?php
include 'conect.php';

if($_POST){
$id_pr = filter_input(INPUT_POST,'id_pr',FILTER_SANITIZE_STRING);
$phon = filter_input(INPUT_POST,'phon',FILTER_SANITIZE_STRING);
$response =[];


	$insertAccount ='INSERT INTO cart (phon,id_pr)
	                            VALUES(:phon,:id_pr)';
	$statment = $conn->prepare($insertAccount);
	try{
	$statment->execute([
	':id_pr'  => $id_pr,
	':phon'  => $phon
	]);
     $response['status']=true;	
     $response['massage']=" ارسال شد به صندوق ";
	 
	}catch(Exception $e){
		die($e->getMessage());
	
	}

$Json = json_encode($response,JSON_PRETTY_PRINT);
	echo $Json;
}

?>