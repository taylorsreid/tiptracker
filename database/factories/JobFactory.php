<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Job>
 */
class JobFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        $defaultHourlyRate = fake()->numberBetween(8, 100);
        return [
            'title' => fake()->jobTitle(),
            'default_hourly_rate' => $defaultHourlyRate,
            'default_overtime_rate' => $defaultHourlyRate * 1.5,
        ];
    }
}
