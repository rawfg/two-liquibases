package com.problem.two.liquibases.entity.b;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityBRepository extends CrudRepository<EntityB, Long> {
}
