package net.miraistd.testmod.client.gui.core;

import lombok.Getter;
import org.joml.Vector2f;

@Getter
public abstract class HUDTransform {

    //region Instancing
    public HUDTransform() {
        Scale = 1.0f;
        Position = new Vector2f(0,0);
    }
    //endregion

    //region Constraints
    private static final float
            _minScale = 0.2f, _maxScale = 0.8f;
    private static final Vector2f
            _minPosition = new Vector2f(0f, 0f),
            _maxPosition = new Vector2f(640f, 320f);

    //endregion

    //region Properties

    protected float Scale;
    protected Vector2f Position;

    public void setScale(float scale) {
        if(scale < _minScale || scale > _maxScale)
            return;

        Scale = scale;
    }

    public void setPosition(Vector2f position) {
        if(position.x < _minPosition.x || position.x > _maxPosition.x ||
           position.y < _minPosition.y || position.y > _maxPosition.y)
            return;

        Position = position;
    }

    //endregion
}