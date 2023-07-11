package com.gfa.foxbook.foxbook.repositories;

import com.gfa.foxbook.foxbook.models.SocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialMediaRepository extends JpaRepository<SocialMedia, Long> {
}
