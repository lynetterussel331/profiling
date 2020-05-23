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
import org.sj.profiling.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary @Repository
public class MemberRepository {

    @Autowired @Lazy
    private MemberJpaRepository jpaRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Member> list() {
        return jpaRepository.findAll();
    }

    public Member create(Member member) {
        return jpaRepository.save(member);
    }

    public Member get(UUID UUID) {
        return jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("Member"));
    }

    public Member update(UUID UUID, Member member) {
        Member existingData = jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("Member"));

        member.setUUID(UUID);
        member.setAddedDate(existingData.getAddedDate());

        return jpaRepository.save(member);
    }

    public void delete(UUID UUID) {
        Member existingData = jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("Member"));
        jpaRepository.delete(existingData);
    }

    public List<?> findDistinctValuesByColumnName(String columnName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery(Member.class);

        Root<?> root = criteriaQuery.from(Member.class);
        criteriaQuery.select(root.get(columnName)).distinct(true);

        List<Order> orderList = new ArrayList();
        orderList.add(criteriaBuilder.asc(root.get(columnName)));
        criteriaQuery.orderBy(orderList);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
