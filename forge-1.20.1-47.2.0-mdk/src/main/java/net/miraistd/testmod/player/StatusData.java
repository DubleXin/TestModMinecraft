package net.miraistd.testmod.player;

import lombok.Getter;
import net.miraistd.testmod.player.Jobs;

import java.io.*;

@Getter
public class StatusData implements Serializable {

    private Jobs Job;

    private int Level;
    private float Experience;

    private int JobLevel;
    private float JobExperience;

    private int SpareStatPoints;
    private int SpareAbilityPoints;

    public StatusData(Jobs job, int level, float experience, int jobLevel,
                      float jobExperience, int spareStatPoints,
                      int spareAbilityPoints) {
        Job = job;

        Level = level;
        Experience = experience;
        JobLevel = jobLevel;
        JobExperience = jobExperience;
        SpareStatPoints = spareStatPoints;
        SpareAbilityPoints = spareAbilityPoints;
    }
    public StatusData() {
        Job = Jobs.Novice;

        Level = 1;
        Experience = 0;
        JobLevel = 1;
        JobExperience = 0;
        SpareStatPoints = 0;
        SpareAbilityPoints = 0;
    }

}

