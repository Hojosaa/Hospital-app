package com.group25a.dependencies;

import java.util.HashMap;

public class DependencyContainer {
    private final HashMap<Class<?>, Object> dependencies = new HashMap<>();

    public <TInterface> void addDependency(Class<TInterface> type, TInterface implementation) {
        dependencies.put(type, implementation);
    }

    public <TInterface> TInterface getDependency(Class<TInterface> type) {
        return type.cast(dependencies.get(type));
    }
}
