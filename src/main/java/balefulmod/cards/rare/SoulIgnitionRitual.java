package balefulmod.cards.rare;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static balefulmod.util.CustomUtils.getDebuffCount;

public class SoulIgnitionRitual extends BaseCard {
    private static final String ID = makeID(SoulIgnitionRitual.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL,
            2
    );

    public SoulIgnitionRitual() {
        super(ID,info);
        setExhaust(true,false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(p, p, getDebuffCount(p), AbstractGameAction.AttackEffect.FIRE));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new LoseHPAction(mo, mo, getDebuffCount(mo), AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SoulIgnitionRitual();
    }
}
