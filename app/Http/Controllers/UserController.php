<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class UserController extends Controller
{

    public function __construct() {
        $this->middleware('auth:sanctum');
    }

    // create function is handled by auth/register endpoint

    /**
     * Display the specified resource.
     */
    public function read(Request $request)
    {
        return $request->user()->where('id', $request->user()->id)->with('jobs')->firstOrFail();
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request)
    {
        $request->user()->update($request->all());
    }

    /**
     * Remove the specified resource from storage.
     */
    public function delete(Request $request)
    {
        $request->user()->delete();
    }
}
