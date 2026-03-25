package balefulmod.cards.uncommon;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.powers.OmenPower;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PervasiveThreat extends BaseCard {
    public static final String ID = makeID(PervasiveThreat.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            0
    );
    private static final int MAGIC = 3;

    public PervasiveThreat() {
        super(ID,info);

        setMagic(MAGIC);
        setInnate(false,true);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower("Artifact")) {
                addToBot(new RemoveSpecificPowerAction(mo, p, "Artifact"));
            }
            addToBot(new ApplyPowerAction(mo, p, new OmenPower(mo, magicNumber), magicNumber));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PervasiveThreat();
    }
}
