package collector.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static collector.CollectorMod.makeID;
import static collector.util.Wiz.*;

public class SapStrength extends AbstractCollectorCard {
    public final static String ID = makeID(SapStrength.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 24, 8, , , , 

    public SapStrength() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 27;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        AbstractPower enemyStr = m.getPower(StrengthPower.POWER_ID);
        if (enemyStr != null) {
            if (enemyStr.amount > 0) {
                int toSteal = Math.min(magicNumber, enemyStr.amount);
                atb(new ReducePowerAction(m, p, StrengthPower.POWER_ID, toSteal));
                applyToSelf(new StrengthPower(p, toSteal));
            }
        }
    }

    public void upp() {
        upgradeDamage(6);
        upgradeMagicNumber(1);
    }

    @Override
    public void triggerOnGlowCheck() {
        for (AbstractMonster m : getEnemies()) {
            if (m.hasPower(StrengthPower.POWER_ID)) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR;
                return;
            }
        }
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }
}