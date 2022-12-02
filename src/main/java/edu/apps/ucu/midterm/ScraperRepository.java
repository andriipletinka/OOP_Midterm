package edu.apps.ucu.midterm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScraperRepository extends JpaRepository<Response, String> {
}
