package balefulmod.cards.rare;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ExpelSoul extends BaseCard {
    public static final String ID = makeID(ExpelSoul.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL,
            1
    );
    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 1;

    public ExpelSoul() {
        super(ID,info);

        setDamage(DAMAGE,UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int totalDebuffs = 0;
        p.powers.stream()
                .filter(pow->pow.type == AbstractPower.PowerType.DEBUFF)
                .forEach(pow-> {for (int i = 0; i<pow.amount;i++) {
                    addToBot(new DamageRandomEnemyAction(new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
                }});
        addToBot(new RemoveAllPowersAction(p, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ExpelSoul();
    }
}
