package balefulmod.cards.uncommon;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.powers.OmenPower;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ConjureWounds extends BaseCard {
    public static final String ID = makeID(ConjureWounds.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );
    private static final String OMEN = OmenPower.POWER_ID;
    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 1;

    public ConjureWounds() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(OMEN)) {
            for (int i=0; i< m.getPower(OMEN).amount; i++) {
                addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
            addToBot(new RemoveSpecificPowerAction(m, p, OMEN));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ConjureWounds();
    }
}
