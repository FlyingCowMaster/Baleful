package balefulmod.cards.rare;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

public class UtterDespair extends BaseCard {
    public static final String ID = makeID(UtterDespair.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            -2
    );

    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 5;

    public UtterDespair() {
        super(ID, info);
        setMagic(MAGIC,UPG_MAGIC);
        setSelfRetain(true);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {}

    @Override
    public void atTurnStart() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -magicNumber), -magicNumber));
        addToBot(new ApplyPowerAction(p,p, new WeakPower(p, magicNumber, false), magicNumber));
        addToBot(new ApplyPowerAction(p, p, new FrailPower(p,magicNumber, false), magicNumber));
        addToBot(new ApplyPowerAction(p,p, new VulnerablePower(p, magicNumber, false), magicNumber));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new UtterDespair();
    }
}
