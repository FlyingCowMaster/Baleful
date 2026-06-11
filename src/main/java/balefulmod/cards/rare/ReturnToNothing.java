package balefulmod.cards.rare;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.powers.OmenPower;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ReturnToNothing extends BaseCard {
    public static final String ID = makeID(ReturnToNothing.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL,
            3
    );


    public ReturnToNothing() {
        super(ID,info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower(OmenPower.POWER_ID) && mo.currentHealth / 2 <= mo.getPower(OmenPower.POWER_ID).amount) {
                addToBot(new InstantKillAction(mo));
            }
        }
        if (p.hasPower(OmenPower.POWER_ID) && p.currentHealth / 2 <= p.getPower(OmenPower.POWER_ID).amount) {
            addToBot(new InstantKillAction(p));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ReturnToNothing();
    }
}
