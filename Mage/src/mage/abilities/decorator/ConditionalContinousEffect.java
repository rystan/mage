package mage.abilities.decorator;

import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.condition.Condition;
import mage.abilities.condition.FixedCondition;
import mage.abilities.condition.LockedInCondition;
import mage.abilities.effects.ContinuousEffect;
import mage.abilities.effects.ContinuousEffectImpl;
import mage.constants.Duration;
import mage.constants.Layer;
import mage.constants.SubLayer;
import mage.game.Game;

/**
 * Adds condition to {@link ContinuousEffect}. Acts as decorator.
 *
 * @author nantuko
 */
public class ConditionalContinousEffect extends ContinuousEffectImpl {

    protected ContinuousEffect effect;
    protected ContinuousEffect otherwiseEffect;
    protected Condition baseCondition;
    protected Condition condition;
    protected boolean initDone = false;


    public ConditionalContinousEffect(ContinuousEffect effect, Condition condition, String text) {
        this(effect, null, condition, text);
    }

    /**
     * Only use this if both effects have the same layers
     *
     * @param effect
     * @param otherwiseEffect
     * @param condition
     * @param text
     */
    public ConditionalContinousEffect(ContinuousEffect effect, ContinuousEffect otherwiseEffect, Condition condition, String text) {
        super(effect.getDuration(), effect.getLayer(), effect.getSublayer(), effect.getOutcome());
        this.effect = effect;
        this.otherwiseEffect = otherwiseEffect;
        this.baseCondition = condition;
        this.staticText = text;
    }

    public ConditionalContinousEffect(final ConditionalContinousEffect effect) {
        super(effect);
        this.effect = effect.effect.copy();
        if (effect.otherwiseEffect != null) {
            this.otherwiseEffect = effect.otherwiseEffect.copy();
        }
        this.condition = effect.condition;
        this.baseCondition = effect.baseCondition;
        this.initDone = effect.initDone;
    }

    @Override
    public boolean isDiscarded() {
        return this.discarded || effect.isDiscarded() || (otherwiseEffect != null && otherwiseEffect.isDiscarded());
    }
    
    @Override
    public void init(Ability source, Game game) {
        if (baseCondition instanceof LockedInCondition) {
            condition = new FixedCondition(((LockedInCondition) baseCondition).getBaseCondition().apply(game, source));
        } else {
            condition = baseCondition;
        }
        effect.setTargetPointer(this.targetPointer);
        effect.init(source, game);        
        if (otherwiseEffect != null) {
            otherwiseEffect.setTargetPointer(this.targetPointer);
            otherwiseEffect.init(source, game);
        }
        initDone = true;
    }

    @Override
    public boolean apply(Layer layer, SubLayer sublayer, Ability source, Game game) {
        if (!initDone) { // if simpleStaticAbility, init won't be called
            init(source, game);
        }
        boolean conditionState = condition.apply(game, source);
        if (conditionState) {
            return effect.apply(layer, sublayer, source, game);
        } else if (otherwiseEffect != null) {
            return otherwiseEffect.apply(layer, sublayer, source, game);
        }
        if (!conditionState && effect.getDuration() == Duration.OneUse) {
            used = true;
        }
        if (!conditionState && effect.getDuration() == Duration.Custom) {
            this.discard();
        }
        return false;
    }

    @Override
    public boolean apply(Game game, Ability source) {
        boolean conditionState = condition.apply(game, source);
        if (conditionState) {
            effect.setTargetPointer(this.targetPointer);
            return effect.apply(game, source);
        } else if (otherwiseEffect != null) {
            otherwiseEffect.setTargetPointer(this.targetPointer);
            return otherwiseEffect.apply(game, source);
        }
        if (!conditionState && effect.getDuration() == Duration.OneUse) {
            used = true;
        }
        if (!conditionState && effect.getDuration() == Duration.Custom) {
            this.discard();
        }
        return false;
    }

    @Override
    public String getText(Mode mode) {
        if ((staticText == null || staticText.isEmpty()) && this.effect != null) { // usefull for conditional night/day card abilities
            return effect.getText(mode);
        }
        return staticText;
    }

    @Override
    public boolean hasLayer(Layer layer) {
        return effect.hasLayer(layer);
    }

    @Override
    public ConditionalContinousEffect copy() {
        return new ConditionalContinousEffect(this);
    }
}
