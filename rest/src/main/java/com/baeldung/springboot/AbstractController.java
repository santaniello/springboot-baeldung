package com.baeldung.springboot;

import com.baeldung.springboot.models.Resource;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController<T extends Resource> extends AbstractReadOnlyController<T> {

    @Autowired
    public AbstractController(final Class<T> clazzToSet) {
        super(clazzToSet);
    }

    public final void saveInternall(T dto){
        getService().save(dto);
    }


    protected final void updateInternal(final long id, final T resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestElementNotNull(resource.getId());
        RestPreconditions.checkRequestState(resource.getId() == id);
        //RestPreconditions.checkNotNull(getService().findOne(resource.getId()));
        //getService().update(resource);
    }
}
