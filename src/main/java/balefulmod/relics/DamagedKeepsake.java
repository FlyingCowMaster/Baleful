package balefulmod.relics;

import balefulmod.character.Baleful;
import balefulmod.powers.OmenPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static balefulmod.BalefulMod.makeID;

public class DamagedKeepsake extends BaseRelic{
    private static final String NAME = DamagedKeepsake.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.CLINK;

    private static final int OMEN = 2;

    public DamagedKeepsake() {
        super(ID, NAME, Baleful.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        flash();
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new RelicAboveCreatureAction(m,this));
            addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new OmenPower(m,OMEN), OMEN, true));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + OMEN + DESCRIPTIONS[1];
    }
}
