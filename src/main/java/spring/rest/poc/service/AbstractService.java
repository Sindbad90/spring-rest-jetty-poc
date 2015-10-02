package spring.rest.poc.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.rest.poc.exception.DuplicateRecordException;
import spring.rest.poc.exception.ResourceNotFoundException;

import java.util.List;

public abstract class AbstractService<T> {

    private static Logger logger = LoggerFactory.getLogger(AbstractService.class);

    public abstract T create(T t);

    public abstract List<T> getAll();

    public abstract T findById(Long id);

    public abstract void update(T t);

    public abstract void delete(Long id);

    public static <T> T recordFound(final T resource) {
        if (resource == null) {
            logger.error("throwing record not found exception");
            throw new ResourceNotFoundException("Record not found");
        }
        return resource;
    }

    public static void checkDuplicateRecord(Long id) {
        if (id != null && id > 0) {
            logger.error("throwing record already exists exception");
            throw new DuplicateRecordException("Duplicate record");
        }
    }
}
