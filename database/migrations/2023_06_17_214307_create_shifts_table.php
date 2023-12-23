<?php

use App\Models\Job;
use App\Models\User;
use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('shifts', function (Blueprint $table) {
            $table->id();
            $table->date('date');
            $table->unsignedDecimal('hours_worked_regular')->nullable();
            $table->unsignedDecimal('hour_worked_overtime')->nullable();
            $table->unsignedDecimal('hourly_rate_regular')->nullable(); // this will create data that duplicates data in the job model, but we need to be able to account for raises
            $table->unsignedDecimal('hourly_rate_overtime')->nullable(); // same
            $table->unsignedDecimal('tips_cash')->nullable();
            $table->unsignedDecimal('tips_charge')->nullable();
            $table->foreignIdFor(User::class)->constrained()->cascadeOnDelete();
            $table->foreignIdFor(Job::class)->constrained()->cascadeOnDelete();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('shifts');
    }
};
