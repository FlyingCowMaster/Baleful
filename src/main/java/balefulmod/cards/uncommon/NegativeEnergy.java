package balefulmod.cards.uncommon;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NegativeEnergy extends BaseCard {
    public static final String ID = makeID(NegativeEnergy.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = -1;

    public NegativeEnergy() {
        super(ID,info);

        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int debuffCount = 0;
        for (AbstractCreature c : AbstractDungeon.getCurrRoom().monsters.monsters) {
            for (AbstractPower pow : c.powers) {
                if (pow.type == AbstractPower.PowerType.DEBUFF) {
                    debuffCount += pow.amount;
                }
            }
        }
        for (AbstractPower pow : p.powers) {
            if (pow.type == AbstractPower.PowerType.DEBUFF) {
                debuffCount += pow.amount;
            }
        }
        p.gainEnergy(debuffCount/this.magicNumber);
    }

    @Override
    public AbstractCard makeCopy() {
        return new NegativeEnergy();
    }
}
