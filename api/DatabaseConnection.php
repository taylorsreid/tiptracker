<?php

require 'vendor/autoload.php';

Class DatabaseConnection extends mysqli {

    public function __construct(){

        $DB_HOSTNAME = getenv("DB_HOSTNAME");
        $DB_USERNAME = getenv("DB_USERNAME");
        $DB_PASSWORD = getenv("DB_PASSWORD");
        $DB_DATABASE = getenv("DB_DATABASE");

        parent::__construct($DB_HOSTNAME, $DB_USERNAME, $DB_PASSWORD, $DB_DATABASE);

    }
}

?>