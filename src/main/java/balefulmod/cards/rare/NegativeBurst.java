package balefulmod.cards.rare;

import balefulmod.cards.BaseCard;
import balefulmod.character.Baleful;
import balefulmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static balefulmod.util.CustomUtils.getAllDebuffsCount;

public class NegativeBurst extends BaseCard {
    public static final String ID = makeID(NegativeBurst.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Baleful.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            1
    );
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 4;

    public NegativeBurst() {
        super(ID,info);

        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, getAllDebuffsCount() * magicNumber, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public AbstractCard makeCopy() {
        return new NegativeBurst();
    }
}
