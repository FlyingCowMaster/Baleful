package balefulmod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Random;

import static balefulmod.BalefulMod.makeID;

public class NegativeSlopPower extends BasePower{
    private static final String POWER_NAME = "NegativeSlop";
    public static final String POWER_ID = makeID(POWER_NAME);
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    private static final Random RNG = new Random();

    public NegativeSlopPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        applyRandomPower(p, p);
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            applyRandomPower(mo, p);
        }
    }

    private void applyRandomPower(AbstractCreature target, AbstractPlayer source) {
        switch (RNG.nextInt(4)) {
            case (1):
                addToBot(new ApplyPowerAction(target, source, new WeakPower(target, amount, false), amount, true));
                break;
            case (2):
                addToBot(new ApplyPowerAction(target, source, new VulnerablePower(target, amount, false), amount, true));
                break;
            case (3):
                addToBot(new ApplyPowerAction(target, source, new PoisonPower(target, source, amount), amount, true));
                break;
            default:
                addToBot(new ApplyPowerAction(target, source, new OmenPower(target, amount), amount, true));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
