package balefulmod.cards.common;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.powers.OmenPower;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ScornfulRetreat extends BaseCard {
    public static final String ID = makeID(ScornfulRetreat.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF_AND_ENEMY,
            1
    );

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public ScornfulRetreat() {
        super(ID,info);

        setBlock(BLOCK,UPG_BLOCK);
        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(m, p, new OmenPower(m, magicNumber), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ScornfulRetreat();
    }
}
