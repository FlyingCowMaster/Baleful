package balefulmod.cards.rare;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static balefulmod.util.CustomUtils.getDebuffCount;

public class FesteringFury extends BaseCard {
    public static final String ID = makeID(FesteringFury.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2
    );
    private static final int DAMAGE = 6;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public FesteringFury() {
        super(ID,info);

        setDamage(DAMAGE);
        setMagic(MAGIC,UPG_MAGIC);
        setSelfRetain(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
    }

    @Override
    public void onRetained() {
        upgradeDamage(getDebuffCount(AbstractDungeon.player));
    }

    @Override
    public AbstractCard makeCopy() {
        return new FesteringFury();
    }
}
