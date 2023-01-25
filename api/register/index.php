<?php

require '../vendor/autoload.php';
require '../bootstrap.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {

    //example of how to echo back json
    // $requestAssoc = json_decode(file_get_contents('php://input'));
    // header("Content-type:application/json");
    // echo json_encode($requestAssoc);

    /* sample json request
        {
            "username": "johndoe",
            "password": "hisPassword",
            "apiVersion": 0.1
        }
    */

    try{

        //connect to database
        $conn = new DatabaseConnection();

        //get body of request that was sent as json
        $requestAssoc = json_decode(file_get_contents('php://input'), true);

        //
        $ps = $conn->prepare('SELECT EXISTS(SELECT 1 FROM `users` WHERE email=?) as emailExists;');
        $ps->bind_param("s", $requestAssoc["email"]);
        $ps->execute();
        $result = $ps->get_result();
        $emailAlreadyExists = $result->fetch_array(MYSQLI_NUM)[0];

        //
        $responseAssoc = array(
            
        );
        
        if($emailAlreadyExists){
            //TODO add actual code lol
            echo "already exists";
        }
    }
    catch(Throwable $th){
        //TODO: add sql server logging here
        echo $th->__toString();
    }

}
else{

    http_response_code(400);

}

?>