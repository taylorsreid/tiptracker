<?php

use App\Http\Controllers\JobController;
use App\Http\Controllers\ShiftController;
use App\Http\Controllers\UserController;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::controller(UserController::class)->group(function () {
    Route::get('/user', 'read');
    Route::patch('/user', 'update');
    Route::delete('/user', 'delete');
});

Route::controller(JobController::class)->group(function () {
    Route::post('/job', 'create');
    Route::get('/job', 'read');
    Route::patch('/job', 'update');
    Route::delete('/job', 'delete');
});

Route::controller(ShiftController::class)->group(function () {
    Route::post('/shift', 'create');
    Route::get('/shift', 'read');
    Route::patch('/shift', 'update');
    Route::delete('/shift', 'delete');
});

// must be the last route listed as a catch all
Route::get('{any?}', function () {
    return view('app');
})->where('any', '.*');