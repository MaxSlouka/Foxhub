package com.gfa.foxbook.foxbook.repositories;

import com.gfa.foxbook.foxbook.models.nonusermodels.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long>{
}
