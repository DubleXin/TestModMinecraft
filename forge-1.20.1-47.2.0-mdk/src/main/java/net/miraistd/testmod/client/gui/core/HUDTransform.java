package net.miraistd.testmod.client.gui.core;

import lombok.AccessLevel;
import lombok.Getter;
import org.joml.Vector2f;

public class HUDTransform {

    //region Constraints
    private static final float
            _minScale = 0.2f, _maxScale = 0.8f;
    private static final Vector2f
            _minPosition = new Vector2f(0f, 0f),
            _maxPosition = new Vector2f(640f, 320f);

    //endregion

    //region Properties

    @Getter(AccessLevel.PROTECTED) private static float Scale;
    @Getter(AccessLevel.PROTECTED) private static Vector2f Position;

    public static void setScale(float scale) {
        if(scale < _minScale || scale > _maxScale)
            return;

        Scale = scale;
    }

    public static void setPosition(Vector2f position) {
        if(position.x < _minPosition.x || position.x > _maxPosition.x ||
           position.y < _minPosition.y || position.y > _maxPosition.y)
            return;

        Position = position;
    }

    //endregion

    public HUDTransform(){
        Scale = 1.0f;
        Position = new Vector2f(0,0);
    }
}