<?php

namespace App\Http\Controllers;

use App\Models\Job;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class JobController extends Controller
{

    /**
     * Store a newly created resource in storage.
     */
    public function create(Request $request)
    {
        Validator::make($request->all(), [
            'title' => ['required', 'string', 'max:255']
        ])->validate();

        $request->user()->jobs()->create($request->all());
    }

    /**
     * Display the specified resource.
     */
    public function read(Request $request)
    {
        return $request->user()->jobs()->get();
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request)
    {
        $request->user()->jobs()->findOrFail($request->input('id'))->update($request->all());
    }

    /**
     * Remove the specified resource from storage.
     */
    public function delete(Request $request)
    {
        $request->user()->jobs()->findOrFail($request->input('id'))->delete();
    }
}
