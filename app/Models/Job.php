<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Support\Facades\Validator;

class Job extends Model
{
    use HasFactory;

    /**
     * The attributes that are mass assignable.
     *
     * @var array<int, string>
     */
    protected $fillable = [
        'title',
        'default_hourly_rate',
        'default_overtime_rate'
    ];

    protected $casts = [
        'default_hourly_rate' => 'double', // decimal:2 doesn't work for some reason
        'default_overtime_rate' => 'double'
    ];

    protected $hidden = [
        'user_id'
    ];

    public function users(): BelongsTo
    {
        return $this->belongsTo(User::class);
    }

    public static function makeValidator(array $jobData)
    {
        return Validator::make($jobData, [
            'title' => ['required', 'string', 'max:255']
        ]);
    }

}
