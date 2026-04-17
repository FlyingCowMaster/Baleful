package balefulmod.cards.uncommon;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class ForgetThePast extends BaseCard {
    public static final String ID = makeID(ForgetThePast.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    public static final int MAGIC = 2;
    public static final int UPG_MAGIC = 1;

    public ForgetThePast() {
        super(ID,info);
        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> retainCards = new ArrayList<>();
        for (AbstractCard c : p.hand.group) {
            if (c.selfRetain || c.retain) {
                retainCards.add(c);
            }
        }

        for (AbstractCard c : retainCards) {
            addToBot(new DiscardSpecificCardAction(c));
        }
        addToBot(new DrawCardAction(p, retainCards.size()*magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ForgetThePast();
    }
}
