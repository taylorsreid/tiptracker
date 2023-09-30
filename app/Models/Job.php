<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

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

}
