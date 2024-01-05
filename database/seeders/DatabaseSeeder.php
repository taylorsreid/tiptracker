<?php

namespace Database\Seeders;

// use Illuminate\Database\Console\Seeds\WithoutModelEvents;

use App\Models\Job;
use App\Models\User;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        $user = (new User([
            'first_name' => 'Test',
            'last_name' => 'User',
            'email' => 'testuser@example.com',
            'password' => 'password'
        ]));

        $job1 = (new Job([
            'title' => 'Bellman',
            'default_hourly_rate' => 11,
            'default_overtime_rate' => 16.50
        ]));

        $job2 = (new Job([
            'title' => 'Valet',
            'default_hourly_rate' => 10,
            'default_overtime_rate' => 15
        ]));

        $user->save();
        $user->jobs()->saveMany([$job1, $job2]);
    }
}
