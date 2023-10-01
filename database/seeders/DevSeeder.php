<?php

namespace Database\Seeders;

// use Illuminate\Database\Console\Seeds\WithoutModelEvents;

use App\Models\Job;
use App\Models\User;
use Illuminate\Database\Seeder;

class DevSeeder extends Seeder
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

        $job = (new Job([
            'title' => 'Test Job 1',
            'default_hourly_rate' => 10,
            'default_overtime_rate' => 15
        ]));

        $user->save();
        $user->jobs()->save($job);

        // \App\Models\User::factory(10)->create();

        // \App\Models\User::factory()->create([
        //     'name' => 'Test User',
        //     'email' => 'test@example.com',
        // ]);
    }
}
