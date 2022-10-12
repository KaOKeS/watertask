package com.akvelon.watertask.entity.contract;

import com.akvelon.watertask.entity.Container;

public interface ContainerFactory {
    Container createContainer(String containerType, double capacity);
}
