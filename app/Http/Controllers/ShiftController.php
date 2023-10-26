<?php

namespace App\Http\Controllers;

use App\Models\Job;
use App\Models\Shift;
use Illuminate\Database\RecordsNotFoundException;
use Illuminate\Http\Request;

class ShiftController extends Controller
{

    public function __construct() {
        $this->middleware('auth:sanctum');
    }

    /**
     * Store a newly created resource in storage.
     */
    public function create(Request $request)
    {
        $this->authorize('create', Shift::class);

        // check that the user isn't creating shifts for a job that doesn't belong to them
        $jobUserId = Job::findOrFail($request->input('job_id'))->user_id;
        if ($request->user()->id !== $jobUserId) {
            abort(404, "You do not have a job by that ID.");
        }

        $shiftData = $request->all();
        $shiftData['user_id'] = $request->user()->id;
        Shift::makeValidator($shiftData)->validate();
        $request->user()->shifts()->create($shiftData);
    }

    /**
     * Display the specified resource.
     */
    public function read(Request $request)
    {
        if ($request->isJson()) {
            //convert JSON request into an array usable by Eloquent
            $merged = array();
            foreach ($request->json()->all() as $params) {
                array_push($merged, [$params['column'], $params['operator'], $params['value']]);
            };

            $shifts = $request->user()->shifts()->where($merged)->get();
            foreach ($shifts as $shift) {
                $this->authorize('view', $shift);
            }
            return $shifts;
        }
        else {
            abort(400, 'Requests for this endpoint are only accepted in JSON format.');
        }
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request)
    {
        $shift = $request->user()->shifts()->findOrFail($request->input('id'));
        $this->authorize('update', $shift);
        $shift->update($request->all());
    }

    /**
     * Remove the specified resource from storage.
     */
    public function delete(Request $request)
    {
        $shift = $request->user()->shifts()->findOrFail($request->input('id'));
        $this->authorize('delete', $shift);
        $shift->delete();
    }
}
