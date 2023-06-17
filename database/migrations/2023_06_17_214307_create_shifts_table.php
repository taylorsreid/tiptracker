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
            $table->unsignedDecimal('hours_worked_regular');
            $table->unsignedDecimal('hour_worked_overtime');
            $table->unsignedDecimal('hourly_rate_regular'); // this will create data that duplicates data in the job model, but we need to be able to account for raises
            $table->unsignedDecimal('hourly_rate_overtime'); // same
            $table->unsignedDecimal('tips_cash');
            $table->unsignedDecimal('tips_charge');
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
