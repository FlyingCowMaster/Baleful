package balefulmod.util;

import balefulmod.powers.OmenPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomUtils {
//    public static ArrayList<AbstractPower> DEBUFFS = new ArrayList<>(Arrays.asList(OmenPower, ));

    /**
     * @param c Creature to check
     * @return Number of debuffs on specific creature
     */
    public static int getDebuffCount(AbstractCreature c) {
        int amt = 0;
        for (AbstractPower pow : c.powers) {
            if (pow.type == AbstractPower.PowerType.DEBUFF) {
                amt+=pow.amount;
            }
        }
        return amt;
    }

    /**
     * @return number of debuffs on all monsters the player
     */
    public static int getAllDebuffsCount() {
        int amt = 0;
        for (AbstractCreature c : AbstractDungeon.getCurrRoom().monsters.monsters) {
            amt += getDebuffCount(c);
        }
        amt += getDebuffCount(AbstractDungeon.player);
        return amt;
    }

    /**
     * @param c - creature to check
     * @param debuff - debuff to check
     * @return Number of specified debuffs on target
     */
    public static int getSpecificDebuffCount(AbstractCreature c, String debuff) {
        if (c.hasPower(debuff)) {
            return c.getPower(debuff).amount;
        }
        return 0;
    }
}
