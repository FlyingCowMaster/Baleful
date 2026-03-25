package balefulmod.cards.rare;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ChokePower;

public class NarrowVision extends BaseCard {
    public static final String ID = makeID(NarrowVision.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.SELF,
            0
    );
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public NarrowVision() {
        super(ID,info);

        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber));
        addToBot(new GainEnergyAction(magicNumber-1));
        addToBot(new ApplyPowerAction(p, p, new ChokePower(p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new NarrowVision();
    }
}
