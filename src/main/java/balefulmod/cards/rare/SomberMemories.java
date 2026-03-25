package balefulmod.cards.rare;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;

public class SomberMemories extends BaseCard {
    public static final String ID = makeID(SomberMemories.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.ALL,
            1
    );
    private static final int UPG_COST = 0;

    public SomberMemories() {
        super(ID,info);

        setCostUpgrade(UPG_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new SlowPower(p,1),1, true));
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new SlowPower(mo,1),1,true ));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SomberMemories();
    }
}
