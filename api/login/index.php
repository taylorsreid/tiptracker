<?php

require '../vendor/autoload.php';
require '../bootstrap.php';
use Firebase\JWT\JWT;

try{
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {

        //example of how to echo back json
        // $requestAssoc = json_decode(file_get_contents('php://input'));
        // header("Content-type:application/json");
        // echo json_encode($requestAssoc);

        // try{

            //connect to database
            $conn = new DatabaseConnection();

            //get body of request that was sent as json
            $requestAssoc = json_decode(file_get_contents('php://input'), true);
            $apiVersion = $requestAssoc["apiVersion"]; //not necessary at the moment but may be in the future
            $email = $requestAssoc["email"];
            $password = $requestAssoc["password"];
            $uuid = null;

            //get password hash and uuid
            $ps = $conn->prepare('SELECT password_hash, uuid FROM `users` WHERE email=?;');
            $ps->bind_param("s", $requestAssoc["email"]);
            $ps->execute();
            $result = $ps->get_result()->fetch_array(MYSQLI_NUM);
            
            $passwordHash = $result[0];
            $uuid = $result[1];

            //
            if(password_verify($password, $passwordHash)){

                $privateKey  = getenv("PRIVATE_KEY");
                $issuedAt   = new DateTimeImmutable();
                $expire     = $issuedAt->modify('+60 minutes')->getTimestamp();      // Add 60 minutes
                $serverName = "localhost";

                $payload = [
                    'iss'  => $serverName,                       // Issuer
                    'iat'  => $issuedAt->getTimestamp(),         // Issued at: time when the token was generated
                    'nbf'  => $issuedAt->getTimestamp(),         // Not before
                    'exp'  => $expire,                           // Expire

                    //custom fields
                    'uuid' => $uuid,                             // unique user id
                    'apiVersion' => $apiVersion                  // api version for future use
                ];

                //
                echo JWT::encode($payload, $privateKey, 'HS256');

            }

    }
    else{
        http_response_code(400);
    }

}
catch(Throwable $th){
    //TODO: add sql server logging here
}

?>