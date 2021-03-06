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
package mage.cards.p;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.DealsCombatDamageToACreatureTriggeredAbility;
import mage.abilities.common.DealsCombatDamageToAPlayerTriggeredAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.condition.InvertCondition;
import mage.abilities.condition.common.CastFromHandSourceCondition;
import mage.abilities.decorator.ConditionalTriggeredAbility;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.LoseGameSourceControllerEffect;
import mage.abilities.effects.common.LoseGameTargetPlayerEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.watchers.common.CastFromHandWatcher;

/**
 *
 * @author LevelX2
 */
public class PhageTheUntouchable extends CardImpl {

    public PhageTheUntouchable(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{3}{B}{B}{B}{B}");
        addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.AVATAR);
        this.subtype.add(SubType.MINION);

        this.power = new MageInt(4);
        this.toughness = new MageInt(4);

        // When Phage the Untouchable enters the battlefield, if you didn't cast it from your hand, you lose the game.
        this.addAbility(new ConditionalTriggeredAbility(
                new EntersBattlefieldTriggeredAbility(new LoseGameSourceControllerEffect(), false),
                new InvertCondition(CastFromHandSourceCondition.instance),
                "When {this} enters the battlefield, if you didn't cast it from your hand, you lose the game"
        ), new CastFromHandWatcher());

        // Whenever Phage deals combat damage to a creature, destroy that creature. It can't be regenerated.
        this.addAbility(new DealsCombatDamageToACreatureTriggeredAbility(new DestroyTargetEffect(true), false, true));

        // Whenever Phage deals combat damage to a player, that player loses the game.
        this.addAbility(new DealsCombatDamageToAPlayerTriggeredAbility(new LoseGameTargetPlayerEffect(), false, true));
    }

    public PhageTheUntouchable(final PhageTheUntouchable card) {
        super(card);
    }

    @Override
    public PhageTheUntouchable copy() {
        return new PhageTheUntouchable(this);
    }
}
