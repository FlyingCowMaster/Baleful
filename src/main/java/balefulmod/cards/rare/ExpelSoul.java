package balefulmod.cards.rare;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.util.CardStats;
import balefulmod.util.CustomUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ExpelSoul extends BaseCard {
    public static final String ID = makeID(ExpelSoul.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL,
            1
    );
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public ExpelSoul() {
        super(ID,info);

        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i=0; i< CustomUtils.getDebuffCount(p); i++) {
            addToBot(new DamageRandomEnemyAction(new DamageInfo(p, magicNumber, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
        }
        addToBot(new RemoveAllPowersAction(p, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ExpelSoul();
    }
}
