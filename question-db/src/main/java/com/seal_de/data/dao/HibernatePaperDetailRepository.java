package com.seal_de.data.dao;

import com.seal_de.data.PaperDetailRepository;
import com.seal_de.domain.PaperDetail;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sealde on 5/3/17.
 */
@Repository
public class HibernatePaperDetailRepository extends AbstractRepository<PaperDetail> implements PaperDetailRepository {
    public List<PaperDetail> findByPaperId(String paperId) {
        return createCriteria()
                .add(Restrictions.eq("paperId", paperId))
                .addOrder(Order.asc("parentIndex"))
                .list();
    }

    public PaperDetail getByPaperIdAndParentIndex(String paperId, Integer parentIndex) {
        return (PaperDetail) createCriteria()
                .add(Restrictions.eq("paperId", paperId))
                .add(Restrictions.eq("parentIndex", parentIndex))
                .uniqueResult();
    }
}
