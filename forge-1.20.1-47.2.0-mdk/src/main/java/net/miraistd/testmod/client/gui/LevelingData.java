package net.miraistd.testmod.client.gui;

public record LevelingData() {
    private static int _level;
    private static float _experience;

    private static int _jobLevel;
    private static float _jobExperience;

    private static int _spareStatPoints;
    private static int _spareAbilityPoints;

    // getters

    public static int GetLevel() {
        return _level;
    }
    public static float GetExperience() {
        return _experience;
    }

    public static int GetJobLevel() {
        return _jobLevel;
    }
    public static float GetJobExperince() {
        return _jobExperience;
    }

    public static int GetSpareStatPoints() {
        return _spareStatPoints;
    }
    public static int GetSpareAbilityPoints() {
        return _spareAbilityPoints;
    }

    // setters

    public static void setLevel(int level) {
        _level = level;
    }

    public static void setExperience(float experience) {
        _experience = experience;
    }

    public static void setJobLevel(int jobLevel) {
        _jobLevel = jobLevel;
    }

    public static void setJobExperience(float jobExperience) {
        _jobExperience = jobExperience;
    }

    public static void setSpareStatPoints(int spareStatPoints) {
        _spareStatPoints = spareStatPoints;
    }

    public static void setSpareAbilityPoints(int spareAbilityPoints) {
        _spareAbilityPoints = spareAbilityPoints;
    }
}
