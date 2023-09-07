<?php
include 'conect.php';
if($_POST){
$phon =@$_POST['phon']??'';
$password =@$_POST['password']??'';

$response =[];

$userquery = $conn->prepare("SELECT * FROM 	users WHERE phon = ?");
$userquery->execute(array($phon));
$query = $userquery->fetch();

if($userquery->rowCount()==0){
	$response['status'] = false;
	$response['massage'] = "این شماره موبایل در سامانه ثبت نشده";
}else{
	try{$passwordDB = $query['password'];
	
	if(strcmp(md5($password),$passwordDB)===0){
		
        $response['status']=true;	
		$response['massage']="ٍخوش امدید";
        $response['data'] = [
		        'id' => $query['id'],
                'user_email' => $query['user_email'],
                'phon'  => $query['phon']
				];
	}else{
		$response['status'] = false;
		$response['massage']= " شماره مبایل یا پسورد اشتباه است " ;
		
		
	}
	
	} catch(PDOException $e) { echo "Connection failed: " . $e->getMessage();}
}

	
	$Json = json_encode($response,JSON_PRETTY_PRINT);
	echo $Json;
}
?>