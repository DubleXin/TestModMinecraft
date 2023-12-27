package mirai;

@FunctionalInterface
public interface action<ReturnType, ArgumentTypes> {
    ReturnType Invoke(ArgumentTypes args);
}
