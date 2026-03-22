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

    public static int getDebuffCount(AbstractCreature c) {
        int amt = 0;
        for (AbstractPower pow : c.powers) {
            if (pow.type == AbstractPower.PowerType.DEBUFF) {
                amt+=pow.amount;
            }
        }
        return amt;
    }
}
