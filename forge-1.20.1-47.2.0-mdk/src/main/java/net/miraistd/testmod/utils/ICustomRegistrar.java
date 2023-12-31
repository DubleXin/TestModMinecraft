package net.miraistd.testmod.utils;

import mirai.action;
import net.minecraftforge.eventbus.api.IEventBus;

public interface ICustomRegistrar {
    static void register(IEventBus eventBus){}
    static void add(String name, action<?, Void> action){}
    static void registerAll(){}

}
