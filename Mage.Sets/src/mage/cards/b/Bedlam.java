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

package mage.cards.b;

import java.util.UUID;

import mage.constants.CardType;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.RestrictionEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.Duration;
import mage.constants.Zone;
import mage.game.Game;
import mage.game.permanent.Permanent;

/**
 *
 * @author Backfir3
 */
public class Bedlam extends CardImpl {

    public Bedlam(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ENCHANTMENT},"{2}{R}{R}");


        // Creatures can't block.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new BedlamEffect()));
    }

    public Bedlam(final Bedlam card) {
        super(card);
    }

    @Override
    public Bedlam copy() {
        return new Bedlam(this);
    }
}

class BedlamEffect extends RestrictionEffect {

    BedlamEffect() {
        super(Duration.WhileOnBattlefield);
        staticText = "Creatures can't block";
    }

    BedlamEffect(final BedlamEffect effect) {
        super(effect);
    }

    @Override
    public boolean applies(Permanent permanent, Ability source, Game game) {
        if (permanent.getCardType().contains(CardType.CREATURE)) {
            return true;
        }
        return false;
    }

    @Override
    public BedlamEffect copy() {
        return new BedlamEffect(this);
    }

    @Override
    public boolean canBlock(Permanent attacker, Permanent blocker, Ability source, Game game) {
        return false;
    }
}