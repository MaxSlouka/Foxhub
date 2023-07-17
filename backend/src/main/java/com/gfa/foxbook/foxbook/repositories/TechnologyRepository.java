package com.gfa.foxbook.foxbook.repositories;

import com.gfa.foxbook.foxbook.models.nonusermodels.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}
