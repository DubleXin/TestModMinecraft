package mirai;

import java.util.Objects;

public class event<ReturnType, ArgumentTypes> extends delegate<ReturnType, ArgumentTypes>{
    @Override
    public ReturnType[] Invoke(ArgumentTypes args){
        _storedActions.removeIf(Objects::isNull);
        return super.Invoke(args);
    }
}
