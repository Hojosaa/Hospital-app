package com.group25a.application;

import com.group25a.dependencies.DependencyConfigurator;
import com.group25a.dependencies.DependencyContainer;

public class Application {
    private static DependencyContainer dependencyContainer;

    public static void main(String[] args) {
        dependencyContainer = DependencyConfigurator.configureDependencies();

        ApplicationWindow applicationWindow = ApplicationWindow.getInstance();
        applicationWindow.showDefaultPage();
    }

    public static <TDependency> TDependency resolveDependency(Class<TDependency> type) {
        return dependencyContainer.getDependency(type);
    }

}