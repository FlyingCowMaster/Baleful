package balefulmod.cards.common;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.powers.OmenPower;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LingeringMemory extends BaseCard {
    public static final String ID = makeID(LingeringMemory.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public LingeringMemory() {
        super(ID,info);
        setMagic(MAGIC,UPG_MAGIC);
        setSelfRetain(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p, new OmenPower(p, magicNumber+1), magicNumber+1, true));
    }

    @Override
    public void atTurnStart() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p,p, new OmenPower(p, magicNumber), magicNumber, true));
    }



    @Override
    public AbstractCard makeCopy() {
        return new LingeringMemory();
    }
}
