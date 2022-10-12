package com.akvelon.watertask.entity.contracts;

import com.akvelon.watertask.entity.Container;

public interface ContainerFactory {
    Container createContainer(String type, double capacity);
}
