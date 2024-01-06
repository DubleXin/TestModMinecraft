package net.miraistd.testmod.player;

import lombok.Getter;
import mirai.delegate;

@Getter
public class StatusData {
    public final delegate<Float, Float> onExperienceAdded = new delegate<>();
    public final delegate<Float, Float> onJobExperienceAdded = new delegate<>();

    public final delegate<Integer, Integer> onLevelUp = new delegate<>();
    public final delegate<Integer, Integer> onJobLevelUp = new delegate<>();

    private Jobs job;

    private int level;
    private float experience;

    private int jobLevel;
    private float jobExperience;

    private int spareStatPoints;
    private int spareAbilityPoints;

    public StatusData(Jobs job, int level, float experience, int jobLevel,
                      float jobExperience, int spareStatPoints,
                      int spareAbilityPoints) {
        this.job = job;

        this.level = level;
        this.experience = experience;
        this.jobLevel = jobLevel;
        this.jobExperience = jobExperience;
        this.spareStatPoints = spareStatPoints;
        this.spareAbilityPoints = spareAbilityPoints;

        Subscribe();
    }
    public StatusData() {
        job = Jobs.Novice;

        level = 1;
        experience = 0;
        jobLevel = 1;
        jobExperience = 0;
        spareStatPoints = 0;
        spareAbilityPoints = 0;

        Subscribe();
    }

    public void Subscribe(){
        onExperienceAdded.Add((Float amount) -> {
            validateLevel();
            return amount;
        });
        onJobExperienceAdded.Add((Float amount) -> {
            validateJobLevel();
            return amount;
        });
    }

    public void addExperience(float amount){
        experience += amount;
        onExperienceAdded.Invoke(amount);
    }
    public void addJobExperience(float amount){
        jobExperience += amount;
        onJobExperienceAdded.Invoke(amount);
    }

    private void validateLevel(){
        if(level >= 320 || experience < (level * 12 + (float)Math.pow(level * 0.5f, 2)))
            return;

        level++;
        experience = 0;

        spareStatPoints += 6 + (level / 10) * 2;
        onLevelUp.Invoke(level);
    }
    private void validateJobLevel(){
        if(jobLevel >= job.getJobLevel().getJobLevelCap() ||
           jobExperience < (jobLevel * 12 + (float)Math.pow(jobLevel * 0.75f, 2)))
            return;

        jobLevel++;
        jobExperience = 0;

        spareAbilityPoints++;
        onJobLevelUp.Invoke(jobLevel);
    }

    public float getLevelPercentage(){
        return experience /
                (level * 12 + (float)Math.pow(level * 0.5f, 2));
    }
    public float getJobLevelPercentage(){
        return jobExperience /
                (jobLevel * 12 + (float)Math.pow(jobLevel * 0.75f, 2));
    }

    public void ChangeJob(Jobs job){
        this.job = job;
    }

}

