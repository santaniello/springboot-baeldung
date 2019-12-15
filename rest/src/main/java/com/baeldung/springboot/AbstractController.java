package com.baeldung.springboot;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController<T> extends AbstractReadOnlyController<T> {

    @Autowired
    public AbstractController(final Class<T> clazzToSet) {
        super(clazzToSet);
    }

    public final void saveInternall(T dto){
        getService().save(dto);
    }

}
