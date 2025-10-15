package com.google.firebase.components;

import java.util.Arrays;
import java.util.List;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\components\DependencyCycleException.smali */
public class DependencyCycleException extends DependencyException {
    private final List<Component<?>> componentsInCycle;

    public DependencyCycleException(List<Component<?>> componentsInCycle) {
        super("Dependency cycle detected: " + Arrays.toString(componentsInCycle.toArray()));
        this.componentsInCycle = componentsInCycle;
    }

    public List<Component<?>> getComponentsInCycle() {
        return this.componentsInCycle;
    }
}
