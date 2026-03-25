package balefulmod.powers;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static balefulmod.BalefulMod.makeID;
import static balefulmod.util.CustomUtils.getSpecificDebuffCount;

public class ImminentHarmPower extends BasePower{
    private static final String POWER_NAME = "ImminentHarm";
    public static final String POWER_ID = makeID(POWER_NAME);
    private static final AbstractPower.PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public ImminentHarmPower(AbstractCreature owner, int amount) {super(POWER_ID, TYPE, TURN_BASED, owner, amount);}

    @Override
    public void atEndOfRound() {
        addToBot(new LoseHPAction(owner, owner, getSpecificDebuffCount(owner, OmenPower.POWER_ID)));
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.owner.name + DESCRIPTIONS[1];
    }
}
