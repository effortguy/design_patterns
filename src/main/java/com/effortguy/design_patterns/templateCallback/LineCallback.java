package com.effortguy.design_patterns.templateCallback;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
