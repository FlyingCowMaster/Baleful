package balefulmod.cards.uncommon;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.powers.MaskThePainPower;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MaskThePain extends BaseCard {
    public static final String ID = makeID(MaskThePain.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public static final int MAGIC = 3;
    public static final int UPG_MAGIC = 1;

    public MaskThePain() {
        super(ID, info);

        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(
                p,
                p,
                new MaskThePainPower(p, magicNumber),
                magicNumber
                ));
    }

    @Override
    public AbstractCard makeCopy() {
        return new MaskThePain();
    }
}
