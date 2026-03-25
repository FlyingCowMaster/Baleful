package balefulmod.cards.common;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.powers.OmenPower;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SlowTime extends BaseCard {
    public static final String ID = makeID(SlowTime.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF_AND_ENEMY,
            0
    );
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public SlowTime() {
        super(ID,info);
        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(OmenPower.POWER_ID)) {
            addToBot(new DrawCardAction(p, magicNumber));
            addToBot(new ReducePowerAction(m, p, OmenPower.POWER_ID, 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SlowTime();
    }
}
