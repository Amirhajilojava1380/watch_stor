<?php
include 'conect.php';
if($_POST){
$phon = filter_input(INPUT_POST,'phon',FILTER_SANITIZE_STRING);
$name = filter_input(INPUT_POST,'name',FILTER_SANITIZE_STRING);
$user_email = filter_input(INPUT_POST,'user_email',FILTER_SANITIZE_STRING);
$password = filter_input(INPUT_POST,'password',FILTER_SANITIZE_STRING);
$response =[];


$userquery = $conn->prepare("SELECT * FROM 	users WHERE phon = ?");
$userquery->execute(array($phon));

if($userquery->rowCount()!=0){
	$response['status'] = false;
	$response['massage'] = "این شماره مبایل قبلن ثبت شده ";
}else{
	$insertAccount ='INSERT INTO users (phon,name,user_email,password)VALUES(:phon,:name,:user_email,:password)';
	$statment = $conn->prepare($insertAccount);
	try{
	$statment->execute([
	':phon'  => $phon,
	':name'  => $name,
	':user_email' => $user_email,
	':password'=>md5($password)
	]);
	
	
     $response['status']=true;	
     $response['massage']="ٍثبت نام سامانه انجام شد";
     $response['data'] = [
     'phon'  => $phon,
	 'user_email' => $user_email
	 ];
	
	}catch(Exception $e){
		die($e->getMessage());
	
	}
}
$Json = json_encode($response,JSON_PRETTY_PRINT);
	echo $Json;
}




?>