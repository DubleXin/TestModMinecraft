package net.miraistd.testmod.client.gui;

import lombok.Getter;

@Getter
public enum Jobs{
    Novice(0), HighNovice(1), SuperNovice(2),
    Swordsman(3), Mage(4), Archer(5), Merchant(6),
    Thief(7), Acolyte(8), Knight(9), Crusader(10),
    Wizard(11), Sage(12), Hunter(13), Bard(14),
    Dancer(15), Blacksmith(16), Alchemist(17), Assassin(18),
    Rogue(19), Priest(20), Monk(21), LordKnight(22),
    Paladin(23), HighWizard(24), Scholar(25), Sniper(26),
    Minstrel(27), Gypsy(28), Mastersmith(29), Biochemist(30),
    AssassinCross(31), Stalker(32), HighPriest(33), Champion(34),
    RuneKnight(35), RoyalGuard(36), DragonKnight(37), ImperialGuard(38),
    Warlock(39), Sorcerer(40), ArchMage(41), ElementalMaster(42),
    Ranger(43), Maestro(44), Wanderer(45), WindHawk(46), Troubadour(47),
    Trouvere(48), Mechanic(49), Geneticist(50), Meister(51), Biolo(52),
    GuillotineCross(53), ShadowChaser(54), ShadowCross(55), AbyssChaser(56),
    ArchBishop(57), Sura(58), Cardinal(59), Inquisitor(60),
    TaeKwonKid(61), Ninja(62), Gunslinger(63), SoulLinker(64),
    TaeKwonMaster(65), SoulReaper(66), StarEmperor(67), Kagerou(68),
    Oboro(69), Rebel(70);

    private final int value;
    private Jobs(int value) {
        this.value = value;
    }

}
