package balefulmod.cards.common;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.powers.OmenPower;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CurseBarrier extends BaseCard {
    public static final String ID = makeID(CurseBarrier.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 3;

    public CurseBarrier() {
        super(ID,info);
        setBlock(BLOCK,UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(OmenPower.POWER_ID)) {
            addToBot(new GainBlockAction(p, block));
            addToBot(new ReducePowerAction(p, p, OmenPower.POWER_ID, 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CurseBarrier();
    }
}
