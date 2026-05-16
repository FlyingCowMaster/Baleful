package balefulmod.cards.uncommon;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.powers.OmenPower;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CrashingDisaster extends BaseCard {
    public static final String ID = makeID(CrashingDisaster.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.ENEMY,
            2
    );
    private static final int DAMAGE = 15;
    private static final int UPG_DAMAGE = 5;
    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 3;

    public CrashingDisaster() {
        super(ID,info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
        addToBot(new ApplyPowerAction(m, p, new OmenPower(m, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CrashingDisaster();
    }
}
