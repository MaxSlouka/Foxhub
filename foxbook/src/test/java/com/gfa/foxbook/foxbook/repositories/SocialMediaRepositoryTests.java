package com.gfa.foxbook.foxbook.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase // there is problem with DB connection - need to fix that
public class SocialMediaRepositoryTests {
    
    @Autowired
    private SocialMediaRepository socialMediaRepository;

}
