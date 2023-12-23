<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>TipTracker</title>
        @vite('resources/js/main.ts')
        @csrf
    </head>
    <body>
        <div id="app"></div>
    </body>
</html>