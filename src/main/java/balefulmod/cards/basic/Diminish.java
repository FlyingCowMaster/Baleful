package balefulmod.cards.basic;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.powers.OmenPower;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Diminish extends BaseCard {
    public static final String ID = makeID(Diminish.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.BASIC,
            AbstractCard.CardTarget.ENEMY,
            1
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Diminish() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(OmenPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
            addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false)));
            addToBot(new ReducePowerAction(m, p, OmenPower.POWER_ID, 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Diminish();
    }
}
