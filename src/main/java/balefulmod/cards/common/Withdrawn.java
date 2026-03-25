package balefulmod.cards.common;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Withdrawn extends BaseCard {
    public static final String ID = makeID(Withdrawn.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 9;
    private static final int UPG_BLOCK = 3;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Withdrawn() {
        super(ID,info);

        setBlock(BLOCK,UPG_BLOCK);
        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(p, p, new WeakPower(p,magicNumber,false), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Withdrawn();
    }
}
