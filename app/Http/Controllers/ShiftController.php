<?php

namespace App\Http\Controllers;

use App\Models\Shift;
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
            return $request->user()->shifts()->where($merged)->get();
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
        $request->user()->shifts()->findOrFail($request->input('id'))->update($request->all());
    }

    /**
     * Remove the specified resource from storage.
     */
    public function delete(Request $request)
    {
        $request->user()->shifts()->findOrFail($request->input('id'))->delete();
    }
}
