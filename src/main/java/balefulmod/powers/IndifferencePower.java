package balefulmod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static balefulmod.BalefulMod.makeID;

public class IndifferencePower extends BasePower{
    private static final String POWER_NAME = "Indifference";
    public static final String POWER_ID = makeID(POWER_NAME);
    private static final AbstractPower.PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public IndifferencePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }


    @Override
    public int onLoseHp(int damageAmount) {
        if (damageAmount != 0) {
            addToBot(new ApplyPowerAction(owner, owner, new OmenPower(owner, (int)damageAmount), (int)damageAmount));
        }
        return 0;
    }

    @Override
    public void atEndOfRound() {
        addToBot(new ApplyPowerAction(owner, owner, new ImminentHarmPower(owner, 1), 1));
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
