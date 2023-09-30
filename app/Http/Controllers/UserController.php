<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;

class UserController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    // public function index()
    // {
    //     //
    // }

    /**
     * Store a newly created resource in storage.
     */
    // public function create(Request $request)
    // {
    //     // controlled by app\Actions\Fortify\CreateNewUser
    // }

    /**
     * Display the specified resource.
     */
    public function read(Request $request)
    {
        return $request->user();
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request)
    {
        if ($request->isJson()) {
            $request->user()->update($request->json()->all());
        }
        else {
            $request->user()->update($request->all());
        }
    }

    /**
     * Remove the specified resource from storage.
     */
    public function delete(Request $request)
    {
        User::destroy($request->user()->id);
    }
}
