package com.baeldung.springboot;

import com.baeldung.springboot.services.impl.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

@Slf4j
public abstract class AbstractReadOnlyController<T> {

    @Autowired
    protected ApplicationEventPublisher eventPublisher;

    protected Class<T> clazz;

    public AbstractReadOnlyController(final Class<T> clazzToSet) {
        super();
        clazz = clazzToSet;
    }

    public List<T> findAllInternal(){
       return getService().findAll();
    }

    protected abstract ApplicationService<T> getService();
}
