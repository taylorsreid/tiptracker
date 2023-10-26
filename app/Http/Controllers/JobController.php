<?php

namespace App\Http\Controllers;

use App\Models\Job;
use Illuminate\Http\Request;

class JobController extends Controller
{

    public function __construct() {
        $this->middleware('auth:sanctum');
    }

    /**
     * Store a newly created resource in storage.
     */
    public function create(Request $request)
    {
        $this->authorize('create', Job::class);
        Job::makeValidator($request->all())->validate();
        $request->user()->jobs()->create($request->all());
    }

    /**
     * Display the specified resource.
     */
    public function read(Request $request)
    {
        $jobs = $request->user()->jobs()->get();
        foreach ($jobs as $job) {
            $this->authorize('view', $job);
        }
        return $jobs;
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request)
    {
        $job = $request->user()->jobs()->findOrFail($request->input('id'));
        $this->authorize('update', $job);
        $job->update($request->all());
    }

    /**
     * Remove the specified resource from storage.
     */
    public function delete(Request $request)
    {
        $job = $request->user()->jobs()->findOrFail($request->input('id'));
        $this->authorize('delete', $job);
        $job->delete();
    }
}
