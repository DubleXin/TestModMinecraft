package net.miraistd.testmod.client;

public record HUDSettings() {
    private static float _scale = 1.0f;

    public static float GetScale() {
        return _scale;
    }

    public static void SetScale(float _scale) {
        if(_scale < 0.2f || _scale > 8.0f)
            return;

        HUDSettings._scale = _scale;
    }

    public HUDSettings {
        _scale = 1.0f;
    }
}
