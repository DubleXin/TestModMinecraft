package mirai;

import java.util.HashSet;

public class delegate<ReturnType, ArgumentTypes> {
    protected final HashSet<action<ReturnType, ArgumentTypes>> _storedActions;

    public delegate(){
        _storedActions = new HashSet<>();
    }

    public void Add(action<ReturnType, ArgumentTypes> action){
        _storedActions.add(action);
    }
    public void Subtract(action<ReturnType, ArgumentTypes> action){
        _storedActions.remove(action);
    }
    public void Reset(){
        _storedActions.clear();
    }

    @SuppressWarnings("unchecked")
    public ReturnType[] Invoke(ArgumentTypes args) {
        ReturnType[] returns = (ReturnType[]) new Object[_storedActions.size()];
        int index = 0;
        for (mirai.action<ReturnType, ArgumentTypes> action : _storedActions) {
            ReturnType result = action.Invoke(args);
            returns[index++] = result;
        }
        return returns;
    }
}