package com.gfa.foxbook.foxbook.repositories;

import com.gfa.foxbook.foxbook.models.nonusermodels.DonutFilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonutFillingRepository extends JpaRepository<DonutFilling, Long> {

}
