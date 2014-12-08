/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.sets.commander2014;

import java.util.UUID;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.UntapTargetEffect;
import mage.abilities.effects.common.combat.BlocksIfAbleTargetEffect;
import mage.abilities.effects.common.continious.GainControlTargetEffect;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Rarity;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.permanent.AttackingPredicate;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author LevelX2
 */
public class DomineeringWill extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("nonattacking creatures");

    static {
        filter.add(Predicates.not(new AttackingPredicate()));
    }

    public DomineeringWill(UUID ownerId) {
        super(ownerId, 13, "Domineering Will", Rarity.RARE, new CardType[]{CardType.INSTANT}, "{3}{U}");
        this.expansionSetCode = "C14";

        this.color.setBlue(true);

        // Target player gains control of up to three target nonattacking creatures until end of turn. Untap those creatures. They block this turn if able.
        this.getSpellAbility().addEffect(new GainControlTargetEffect(Duration.EndOfTurn));
        Effect effect = new UntapTargetEffect();
        effect.setText("Untap those creatures");
        this.getSpellAbility().addEffect(effect);
        effect = new BlocksIfAbleTargetEffect(Duration.EndOfTurn);
        effect.setText("They block this turn if able");
        this.getSpellAbility().addEffect(effect);
        this.getSpellAbility().addTarget(new TargetCreaturePermanent(0,3, filter, false));
    }

    public DomineeringWill(final DomineeringWill card) {
        super(card);
    }

    @Override
    public DomineeringWill copy() {
        return new DomineeringWill(this);
    }
}
