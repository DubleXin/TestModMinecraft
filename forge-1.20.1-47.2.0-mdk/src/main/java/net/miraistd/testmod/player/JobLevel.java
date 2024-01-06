package net.miraistd.testmod.player;

import lombok.Getter;

@Getter
public enum JobLevel{
    FIRST(50),
    SECOND(70),
    EXPERT(70),
    THIRD(70),
    FOURTH(50),
    EXPANDED_FIRST(70),
    EXPANDED_SECOND(70),
    EXPANDED_THIRD(50);

    private final int jobLevelCap;
    JobLevel(int jobLevelCap){
        this.jobLevelCap = jobLevelCap;
    }
}
