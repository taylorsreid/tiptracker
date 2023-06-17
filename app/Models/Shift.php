<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class Shift extends Model
{
    use HasFactory;

    protected $fillable = [
        'hours_worked_regular',
        'hour_worked_overtime',
        'hourly_rate_regular', // this will create data that duplicates data in the job model, but we need to be able to account for raises
        'hourly_rate_overtime', // same
        'tips_cash',
        'tips_charge'
    ];

    public function user(): BelongsTo
    {
        return $this->belongsTo(User::class);
    }

}
