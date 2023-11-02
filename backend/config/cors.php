<?php

$config = [

    /*
    |--------------------------------------------------------------------------
    | Cross-Origin Resource Sharing (CORS) Configuration
    |--------------------------------------------------------------------------
    |
    | Here you may configure your settings for cross-origin resource sharing
    | or "CORS". This determines what cross-origin operations may execute
    | in web browsers. You are free to adjust these settings as needed.
    |
    | To learn more: https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
    |
    */

    'paths' => ['*'], // must be * since api and frontend will be on separate subdomains

    'allowed_methods' => ['*'],

    'allowed_origins' => ['https://tiptracker.taylorsreid.com/*'],

    'allowed_origins_patterns' => [],

    'allowed_headers' => ['*'],

    'exposed_headers' => [],

    'max_age' => 0,

    'supports_credentials' => true, // must be true since api and frontend will be on separate subdomains

];

// add allow localhost if app is in debug mode
if (config('app.debug')) {
    array_push($config['allowed_origins'], 'http://localhost:*');
}

return $config;