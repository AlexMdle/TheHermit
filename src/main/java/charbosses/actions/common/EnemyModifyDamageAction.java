package charbosses.actions.common;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.helpers.*;

import charbosses.helpers.EnemyGetAllInBattleInstances;

import com.megacrit.cardcrawl.cards.*;
import java.util.*;

public class EnemyModifyDamageAction extends AbstractGameAction
{
    private UUID uuid;
    
    public EnemyModifyDamageAction(final UUID targetUUID, final int amount) {
        this.setValues(this.target, this.source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.uuid = targetUUID;
    }
    
    @Override
    public void update() {
        for (final AbstractCard abstractCard : EnemyGetAllInBattleInstances.get(this.uuid)) {
            final AbstractCard c = abstractCard;
            abstractCard.baseDamage += this.amount;
            if (c.baseDamage < 0) {
                c.baseDamage = 0;
            }
        }
        this.isDone = true;
    }
}
