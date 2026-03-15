package balefulmod.cards.rare;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.powers.OmenPower;
import balefulmod.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DawnOfCalamity extends BaseCard {
    public static final String ID = makeID(DawnOfCalamity.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            CardRarity.RARE,
            AbstractCard.CardTarget.ALL,
            0
    );

    private static final int MAGIC = 15;
    private static final int UPG_MAGIC = 5;

    public DawnOfCalamity() {
        super(ID,info);

        setMagic(MAGIC,UPG_MAGIC);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new OmenPower(p, magicNumber), magicNumber));
        addToBot(new AllEnemyApplyPowerAction(p, magicNumber, a->new OmenPower(a, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DawnOfCalamity();
    }
}
