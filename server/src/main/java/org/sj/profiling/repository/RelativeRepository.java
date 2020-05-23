package org.sj.profiling.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import org.sj.profiling.exception.ResourceNotFoundException;
import org.sj.profiling.model.Relative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary @Repository
public class RelativeRepository {

    @Autowired @Lazy
    private RelativeJpaRepository jpaRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Relative> list() {
        return jpaRepository.findAll();
    }

    public Relative create(Relative relative) {
        return jpaRepository.save(relative);
    }

    public Relative get(UUID UUID) {
        return jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("Relative"));
    }

    public Relative update(UUID UUID, Relative relative) {
        Relative existingData = jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("Relative"));

        relative.setUUID(UUID);
        relative.setAddedDate(existingData.getAddedDate());

        return jpaRepository.save(relative);
    }

    public void delete(UUID UUID) {
        Relative existingData = jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("Relative"));
        jpaRepository.delete(existingData);
    }

    public List<?> findDistinctValuesByColumnName(String columnName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery(Relative.class);

        Root<?> root = criteriaQuery.from(Relative.class);
        criteriaQuery.select( root.get(columnName) ).distinct(true);

        List<Order> orderList = new ArrayList();
        orderList.add(criteriaBuilder.asc(root.get(columnName)));
        criteriaQuery.orderBy(orderList);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
