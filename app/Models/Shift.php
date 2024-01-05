<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Support\Facades\Validator;

class Shift extends Model
{
    use HasFactory;

    protected $fillable = [
        'user_id',
        'job_id',
        'date',
        'hours_worked_regular',
        'hour_worked_overtime',
        'hourly_rate_regular', // this will create data that duplicates data in the job model, but we need to be able to account for raises
        'hourly_rate_overtime', // same
        'tips_cash',
        'tips_charge'
    ];

    protected $hidden = [
        'user_id',
        'laravel_through_key'
    ];

    protected $casts = [
        'hours_worked_regular' => 'double',
        'hour_worked_overtime' => 'double',
        'hourly_rate_regular' => 'double',
        'hourly_rate_overtime' => 'double',
        'tips_cash' => 'double',
        'tips_charge' => 'double'
    ];

    public function user(): BelongsTo
    {
        return $this->belongsTo(User::class);
    }

    public static function makeValidator(array $shiftData)
    {
        return Validator::make($shiftData, [
            'job_id' => ['required', 'numeric'],
            'date' => ['required', 'date'],
            'hours_worked_regular' => ['nullable', 'numeric'],
            'hours_worked_overtime' => ['nullable', 'numeric'],
            'hourly_rate_regular' => ['nullable', 'numeric'],
            'hourly_rate_overtime' => ['nullable', 'numeric'],
            'tips_cash' => ['nullable', 'numeric'],
            'tips_charge' => ['nullable', 'numeric']
        ]);
    }

}
