package balefulmod.cards.uncommon;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.powers.OmenPower;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class WaveOfLethargy extends BaseCard {
    public static final String ID = makeID(WaveOfLethargy.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.ALL,
            2
    );
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 2;

    public WaveOfLethargy() {
        super(ID,info);

        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(OmenPower.POWER_ID) && p.getPower(OmenPower.POWER_ID).amount >=2) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -this.magicNumber), -this.magicNumber, true));
            if (!p.hasPower("Artifact")) {
                addToBot(new ApplyPowerAction(p, p, new GainStrengthPower(p, this.magicNumber), this.magicNumber, true));
            }
            OmenPower.omenAction(p, p, new ApplyPowerAction(p, p, new WeakPower(p, this.magicNumber, false), this.magicNumber, true), 2);
        }
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower(OmenPower.POWER_ID) && mo.getPower(OmenPower.POWER_ID).amount >=2) {
                addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -this.magicNumber), -this.magicNumber, true));
                if (!mo.hasPower("Artifact")) {
                    addToBot(new ApplyPowerAction(mo, p, new GainStrengthPower(mo, this.magicNumber), this.magicNumber, true));
                }
                OmenPower.omenAction(mo, p, new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber, true), 2);
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new WaveOfLethargy();
    }
}
