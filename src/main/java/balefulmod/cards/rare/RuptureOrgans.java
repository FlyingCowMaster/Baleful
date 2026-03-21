package balefulmod.cards.rare;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.powers.OmenPower;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class RuptureOrgans extends BaseCard {
    public static final String ID = makeID(RuptureOrgans.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1
    );

    public RuptureOrgans() {
        super(ID,info);
        setCostUpgrade(0);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(OmenPower.POWER_ID)) {
            int amt = m.getPower(OmenPower.POWER_ID).amount;
            addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -amt), -amt));
            addToBot(new ApplyPowerAction(m, p, new WeakPower(m, amt, false), amt));
            addToBot(new RemoveSpecificPowerAction(m, p, OmenPower.POWER_ID));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new RuptureOrgans();
    }
}
