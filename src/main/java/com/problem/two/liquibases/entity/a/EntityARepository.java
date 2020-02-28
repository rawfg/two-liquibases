package com.problem.two.liquibases.entity.a;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityARepository extends CrudRepository<EntityA, Long> {
}
