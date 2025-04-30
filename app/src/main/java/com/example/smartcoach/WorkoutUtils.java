package com.example.smartcoach;

import android.widget.ImageView;

public class WorkoutUtils {

    public static String determineMuscleGroup(String suggestedWorkout) {
        if (suggestedWorkout == null) return "Full Body";

        String workoutLower = suggestedWorkout.toLowerCase();
        if (workoutLower.contains("push-ups") || workoutLower.contains("bench press")) {
            return "Chest";
        } else if (workoutLower.contains("pull-ups") || workoutLower.contains("deadlifts")) {
            return "Back";
        } else if (workoutLower.contains("bicep curls") || workoutLower.contains("tricep dips")) {
            return "Arms";
        } else if (workoutLower.contains("squats") || workoutLower.contains("lunges")) {
            return "Legs";
        } else if (workoutLower.contains("plank") || workoutLower.contains("crunches")) {
            return "Abs";
        } else if (workoutLower.contains("shoulder press") || workoutLower.contains("lateral raises") || workoutLower.contains("front raises")) {
            return "Shoulders"; // Додано перевірку на "Front Raises"
        } else {
            return "Full Body";
        }
    }

    public static String translateMuscleGroup(String muscleGroup) {
        if (muscleGroup == null) return "";
        switch (muscleGroup.toLowerCase()) {
            case "chest":
                return "Груди";
            case "back":
                return "Спина";
            case "arms":
                return "Руки";
            case "legs":
                return "Ноги";
            case "abs":
                return "Прес";
            case "shoulders":
                return "Плечі";
            case "full body":
                return "Все тіло";
            default:
                return muscleGroup;
        }
    }

    public static String translateWorkoutGoal(String goal) {
        if (goal == null) return "";
        switch (goal.toLowerCase()) {
            case "weight loss":
                return "Схуднення";
            case "muscle gain":
                return "Набір м’язової маси";
            case "maintain shape":
                return "Підтримка форми";
            default:
                return goal;
        }
    }

    public static String translateIntensityLevel(String intensity) {
        if (intensity == null) return "";
        switch (intensity.toLowerCase()) {
            case "low":
                return "Низька";
            case "medium":
                return "Середня";
            case "high":
                return "Висока";
            default:
                return intensity;
        }
    }

    public static String translateSuggestedWorkout(String suggestedWorkout) {
        if (suggestedWorkout == null) return "";
        StringBuilder translatedWorkout = new StringBuilder();
        String[] exercises = suggestedWorkout.split("\n");
        for (String exercise : exercises) {
            if (exercise.contains("Push-ups")) {
                translatedWorkout.append(exercise.replace("Push-ups", "Віджимання")).append("\n");
            } else if (exercise.contains("Bench Press")) {
                translatedWorkout.append(exercise.replace("Bench Press", "Жим лежачи")).append("\n");
            } else if (exercise.contains("Dumbbell Flyes")) {
                translatedWorkout.append(exercise.replace("Dumbbell Flyes", "Розведення гантелей")).append("\n");
            } else if (exercise.contains("Pull-ups")) {
                translatedWorkout.append(exercise.replace("Pull-ups", "Підтягування")).append("\n");
            } else if (exercise.contains("Deadlifts")) {
                translatedWorkout.append(exercise.replace("Deadlifts", "Станова тяга")).append("\n");
            } else if (exercise.contains("Bent-over Rows")) {
                translatedWorkout.append(exercise.replace("Bent-over Rows", "Тяга штанги в нахилі")).append("\n");
            } else if (exercise.contains("Bicep Curls")) {
                translatedWorkout.append(exercise.replace("Bicep Curls", "Згинання рук з гантелями")).append("\n");
            } else if (exercise.contains("Tricep Dips")) {
                translatedWorkout.append(exercise.replace("Tricep Dips", "Віджимання на трицепс")).append("\n");
            } else if (exercise.contains("Hammer Curls")) {
                translatedWorkout.append(exercise.replace("Hammer Curls", "Молоткові згинання")).append("\n");
            } else if (exercise.contains("Squats")) {
                translatedWorkout.append(exercise.replace("Squats", "Присідання")).append("\n");
            } else if (exercise.contains("Lunges")) {
                translatedWorkout.append(exercise.replace("Lunges", "Випади")).append("\n");
            } else if (exercise.contains("Leg Press")) {
                translatedWorkout.append(exercise.replace("Leg Press", "Жим ногами")).append("\n");
            } else if (exercise.contains("Plank")) {
                translatedWorkout.append(exercise.replace("Plank", "Планка")).append("\n");
            } else if (exercise.contains("Bicycle Crunches")) {
                translatedWorkout.append(exercise.replace("Bicycle Crunches", "Скручування «велосипед»")).append("\n");
            } else if (exercise.contains("Leg Raises")) {
                translatedWorkout.append(exercise.replace("Leg Raises", "Підйом ніг")).append("\n");
            } else if (exercise.contains("Shoulder Press")) {
                translatedWorkout.append(exercise.replace("Shoulder Press", "Жим плечима")).append("\n");
            } else if (exercise.contains("Lateral Raises")) {
                translatedWorkout.append(exercise.replace("Lateral Raises", "Бокові підйоми гантелей")).append("\n");
            } else if (exercise.contains("Front Raises")) {
                translatedWorkout.append(exercise.replace("Front Raises", "Фронтальні підйоми гантелей")).append("\n");
            } else if (exercise.contains("Running")) {
                translatedWorkout.append(exercise.replace("Running", "Біг")).append("\n");
            } else if (exercise.contains("Jumping Jacks")) {
                translatedWorkout.append(exercise.replace("Jumping Jacks", "Стрибки «Джек»")).append("\n");
            } else if (exercise.contains("Burpees")) {
                translatedWorkout.append(exercise.replace("Burpees", "Берпі")).append("\n");
            } else if (exercise.contains("Stretching")) {
                translatedWorkout.append(exercise.replace("Stretching", "Розтяжка")).append("\n");
            } else {
                translatedWorkout.append(exercise).append("\n");
            }
        }
        return translatedWorkout.toString().trim();
    }

    public static void setMuscleImage(ImageView muscleImage, String muscleGroup) {
        if (muscleImage == null || muscleGroup == null) return;
        switch (muscleGroup.toLowerCase()) {
            case "chest":
                muscleImage.setImageResource(R.drawable.ic_chest);
                break;
            case "back":
                muscleImage.setImageResource(R.drawable.ic_back);
                break;
            case "arms":
                muscleImage.setImageResource(R.drawable.ic_arms);
                break;
            case "legs":
                muscleImage.setImageResource(R.drawable.ic_legs);
                break;
            case "abs":
                muscleImage.setImageResource(R.drawable.ic_abs);
                break;
            case "shoulders":
                muscleImage.setImageResource(R.drawable.ic_shoulders);
                break;
            default:
                muscleImage.setImageResource(R.drawable.ic_default_workout);
                break;
        }
    }
}