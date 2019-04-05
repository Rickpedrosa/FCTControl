package com.example.fctcontrol.base.interfaces;

public interface OnClickedItemToNavigate<T> {

    void navigate(String className, T object, String[] visits);

}
