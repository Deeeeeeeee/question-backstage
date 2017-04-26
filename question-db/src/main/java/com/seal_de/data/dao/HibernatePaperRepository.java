package com.seal_de.data.dao;

import com.seal_de.data.PaperRepository;
import com.seal_de.domain.Paper;
import org.springframework.stereotype.Repository;

/**
 * Created by sealde on 4/25/17.
 */
@Repository
public class HibernatePaperRepository extends AbstractRepository<Paper> implements PaperRepository{
}
