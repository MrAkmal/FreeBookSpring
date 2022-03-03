package uz.jl.repository;

import org.springframework.stereotype.Repository;
import uz.jl.models.BaseGenericEntity;

import java.util.List;

/**
 * @author Doston Bokhodirov, Mon 9:54 PM. 2/14/2022
 */
@Repository("abstractRepository")
public abstract class AbstractRepository<E extends BaseGenericEntity> {
    protected List<E> list;

    public AbstractRepository(List<E> list) {
        this.list = list;
    }
}
