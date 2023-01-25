<?php

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    
    //flags
    $validEmail = false;
    $validPassword = false;

    //password validation regex
    $uppercase = preg_match('@[A-Z]@', $_POST["password"]);
    $lowercase = preg_match('@[a-z]@', $_POST["password"]);
    $number = preg_match('@[0-9]@', $_POST["password"]);
    $specialChars = preg_match('@[^\w]@', $_POST["password"]);
    
    //validate email and check password requirements
    //TODO: move email validation to client side
    if (filter_var($_POST["email"], FILTER_VALIDATE_EMAIL)) {
        $validEmail = true;
    }
    else{
        //TODO: send response of invalid email
        echo "invalid email";
    }
    
    if ($uppercase && $lowercase && $number && $specialChars && strlen($_POST["password"]) >= 8){
        $validPassword = true;
    }
    else{
        //TODO: send response of invalid password
        echo "invalid password";
    }
    
    if($validEmail && $validPassword){
        // Create connection
        try {

            //import connection
            require_once "../DatabaseConnection.php";
            $conn = new DatabaseConnection();

            //check if email already exists in db
            $ps = $conn->prepare("SELECT EXISTS(SELECT * FROM `tip-tracker`.`users` WHERE `email`=?) AS isExist;");
            $ps->bind_param("s", $_POST["email"]);
            $ps->execute();

            //TODO: FIXME
            $result = $ps->get_result()->fetch_row();        
            if($result[0] == 1){
                //email already exists in db
                echo $_POST["email"] . " is already taken!";
            }
            else{
                //new user
                echo $_POST["email"] . " is available!";
            }

            $conn->close();
        }
        catch(Throwable $th){
            //TODO: add exception handling for db down
            var_dump($th);
        }
    } 
}
?>