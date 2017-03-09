package com.softgroup.test;


import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface CacheMap extends Map {
    @Override
    void clear();

    public boolean autoRemove() ;
}
