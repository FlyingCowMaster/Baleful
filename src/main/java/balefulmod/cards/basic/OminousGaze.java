package balefulmod.cards.basic;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.powers.OmenPower;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OminousGaze extends BaseCard {
    public static final String ID = makeID(OminousGaze.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 2;

    public OminousGaze() {
        super(ID,info);

        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new OmenPower(m, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new OminousGaze();
    }
}
