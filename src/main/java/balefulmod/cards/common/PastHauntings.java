package balefulmod.cards.common;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.watcher.MeditateAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawReductionPower;

public class PastHauntings extends BaseCard {
    public static final String ID = makeID(PastHauntings.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            -2
    );

    public PastHauntings() {
        super(ID,info);
        setCostUpgrade(0);
        setSelfRetain(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterDiscardPileToHandAction(1));
    }

    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new MeditateAction(1));
        addToBot(new ApplyPowerAction(p, p, new DrawReductionPower(p, 1), 1));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            return true;
        }
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new PastHauntings();
    }
}
