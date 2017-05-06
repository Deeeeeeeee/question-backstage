package com.seal_de.data.dao;

import com.seal_de.data.PaperItemRepository;
import com.seal_de.domain.PaperItem;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sealde on 5/5/17.
 */
@Repository
public class HibernatePaperItemRepository extends AbstractRepository<PaperItem> implements PaperItemRepository {
    public PaperItem getByPaperDetailIdAndChildIndex(String paperDetailId, Integer childIndex) {
        return (PaperItem) createCriteria()
                .add(Restrictions.eq("paperDetailId", paperDetailId))
                .add(Restrictions.eq("childIndex", childIndex))
                .uniqueResult();
    }

    public List<PaperItem> findByPaperDetailId(String paperDetailId) {
        return createCriteria()
                .add(Restrictions.eq("paperDetailId", paperDetailId))
                .addOrder(Order.asc("childIndex"))
                .list();
    }
}
