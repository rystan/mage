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
package mage.cards.j;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.PayEnergyCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.effects.common.MayTapOrUntapTargetEffect;
import mage.abilities.effects.common.counter.GetEnergyCountersControllerEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.filter.StaticFilters;
import mage.target.TargetPermanent;

/**
 *
 * @author LevelX2
 */
public class JanjeetSentry extends CardImpl {

    public JanjeetSentry(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{2}{U}");
        this.subtype.add("Vedalken");
        this.subtype.add("Soldier");
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        // When Janjeet Sentry enters the battlefield, you get {E}{E}.
        this.addAbility(new EntersBattlefieldTriggeredAbility(new GetEnergyCountersControllerEffect(2)));

        // Tap, Pay {E}{E}: You may tap or untap target artifact or creature.
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new MayTapOrUntapTargetEffect(), new TapSourceCost());
        ability.addCost(new PayEnergyCost(2));
        ability.addTarget(new TargetPermanent(StaticFilters.FILTER_PERMANENT_ARTIFACT_OR_CREATURE));
        this.addAbility(ability);
    }

    public JanjeetSentry(final JanjeetSentry card) {
        super(card);
    }

    @Override
    public JanjeetSentry copy() {
        return new JanjeetSentry(this);
    }
}