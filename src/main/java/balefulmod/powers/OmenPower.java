package balefulmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static balefulmod.BalefulMod.makeID;

public class OmenPower extends BasePower{
    private static final String POWER_NAME = "Omen";
    public static final String POWER_ID = makeID(POWER_NAME);
    private static final AbstractPower.PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public OmenPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atEndOfRound() {
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        else {
            addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    private void preformAction(AbstractCreature t, AbstractCreature s, AbstractGameAction action, int amountToRemove) {
        addToBot(action);
        addToBot(new ReducePowerAction(t, s, POWER_ID, amountToRemove));
    }

    private void preformAction(AbstractCreature t, AbstractCreature s, AbstractGameAction action, boolean removeAll) {
        addToBot(action);
        if (removeAll) {
            addToBot(new RemoveSpecificPowerAction(t, s, POWER_ID));
        }
    }

    /**
     * Checks if given AbstractCreature has Omen,
     * Preforms action if ture.
     * 1 Omen is removed from creature
     *
     * @param target Creature to check Omen of
     * @param source Creature action is from
     * @param action Action preformed if Creature has Omen
     */
    public static void omenAction(AbstractCreature target, AbstractCreature source, AbstractGameAction action) {
        if (target.hasPower(POWER_ID)) {
            OmenPower p = (OmenPower) target.getPower(POWER_ID);
            p.preformAction(target,source,action,1);
        }
    }

    /**
     * Checks if given AbstractCreature has Omen, preforms action if ture.
     * Declare a non-negative amount of omen to remove.
     *
     * @param target Creature to check Omen of
     * @param source Creature action is from
     * @param action Action preformed if Creature has Omen
     * @param amountToRemove Amount of Omen removed from target, must be non-negative
     */
    public static void omenAction(AbstractCreature target, AbstractCreature source, AbstractGameAction action, int amountToRemove) {
        if (target.hasPower(POWER_ID)) {
            OmenPower p = (OmenPower) target.getPower(POWER_ID);
            p.preformAction(target, source,action, amountToRemove);
        }
    }

    /**
     * Checks if given AbstractCreature has Omen, preforms action if ture.
     * Can remove all Omen
     *
     * @param target Creature to check Omen of
     * @param source Creature action is from
     * @param action Action preformed if Creature has Omen
     * @param removeAll If true, all Omen is removed from target, otherwise no Omen is removed
     */
    public static void omenAction(AbstractCreature target, AbstractCreature source, AbstractGameAction action, boolean removeAll) {
        if (target.hasPower(POWER_ID)) {
            OmenPower p = (OmenPower) target.getPower(POWER_ID);
            p.preformAction(target, source,action, removeAll);
        }
    }
}
