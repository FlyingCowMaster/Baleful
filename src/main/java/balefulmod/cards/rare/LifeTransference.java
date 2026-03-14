package balefulmod.cards.rare;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.powers.OmenPower;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.unique.VampireDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LifeTransference extends BaseCard {
    public static final String ID = makeID(LifeTransference.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            2
    );
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public LifeTransference() {
        super(ID,info);

        setMagic(MAGIC,UPG_MAGIC);
        setExhaust(true);

        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower(OmenPower.POWER_ID)) {
                addToBot(new VampireDamageAction(mo, new DamageInfo(p, mo.getPower(OmenPower.POWER_ID).amount, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON));
                addToBot(new ReducePowerAction(mo, p, OmenPower.POWER_ID, (mo.getPower(OmenPower.POWER_ID).amount/magicNumber)));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new LifeTransference();
    }
}
