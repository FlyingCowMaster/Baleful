package balefulmod.cards.uncommon;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.powers.OmenPower;
import balefulmod.util.CardStats;
import balefulmod.util.CustomUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Apathy extends BaseCard {
    private static final String ID = makeID(Apathy.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );

    public Apathy() {
        super(ID,info);
        setSelfRetain(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amt = CustomUtils.getDebuffCount(p);
        addToBot(new RemoveAllPowersAction(p, true));
        addToBot(new ApplyPowerAction(p, p, new OmenPower(p, amt), amt));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Apathy();
    }
}
