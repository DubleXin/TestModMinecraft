package net.miraistd.testmod.player;

import lombok.Getter;

@Getter
public enum Jobs {

    // region 1st Jobs
    Novice(0), Swordsman(1), Mage(2), Archer(3), Merchant(4), Thief(5), Acolyte(6), // endregion

    // region 2nd Jobs
    HighNovice(20), Knight(21), Crusader(22), Wizard(23), Sage(24), Hunter(25), Bard(26),
    Dancer(27), Blacksmith(28), Alchemist(29), Assassin(30), Rogue(31), Priest(32), Monk(33), // endregion

    // region Expert Jobs
    ExpertNovice(40), LordKnight(41), Paladin(42), HighWizard(43), Scholar(44), Sniper(45),
    Minstrel(46), Gypsy(47), Mastersmith(48), Biochemist(49), AssassinCross(50), Stalker(51),
    HighPriest(52), Champion(53), // endregion

    // region 3rd Jobs
    SuperNovice(60), RuneKnight(61), Warlock(62), Sorcerer(63), Ranger(64), Maestro(65),
    Wanderer(66), Mechanic(67), Geneticist(68), GuillotineCross(69), ShadowChaser(70),
    ArchBishop(71), Sura(72), // endregion

    // region 4th Jobs
    UltraNovice(80), RoyalGuard(81), DragonKnight(82), ArchMage(83), ElementalMaster(84),
    WindHawk(85), Troubadour(86), Trouvere(87), Meister(88), Biolo(89), ShadowCross(90),
    AbyssChaser(91), Cardinal(92), Inquisitor(93), // endregion

    // region Expanded 1st Jobs
    TaeKwonKid(100), Ninja(101), Gunslinger(102), // endregion

    // region Expanded 2nd Jobs
    SoulLinker(120), TaeKwonMaster(121), Kagerou(122), Oboro(123), Rebel(124), // endregion

    // region Expanded 3rd Jobs
    SoulReaper(140), StarEmperor(141); // endregion

    private final int value;
    private final JobLevel jobLevel;

    Jobs(int v) {
        this.value = v;
        jobLevel = v < 20? JobLevel.FIRST :
                   v < 40? JobLevel.SECOND :
                   v < 60? JobLevel.EXPERT :
                   v < 80? JobLevel.THIRD :
                   v < 100? JobLevel.FOURTH :
                   v < 120? JobLevel.EXPANDED_FIRST :
                   v < 140? JobLevel.EXPANDED_SECOND :
                            JobLevel.EXPANDED_THIRD;
    }
}

