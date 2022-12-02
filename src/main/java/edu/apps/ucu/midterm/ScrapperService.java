package edu.apps.ucu.midterm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapperService {
    @Autowired
    private ScraperRepository scraperRepository;

    public ScrapperService(ScraperRepository scraperRepository) {
        this.scraperRepository = scraperRepository;
    }

    public List<Response> getResponses() {
        return scraperRepository.findAll();
    }

    public void addResponse(Response response) {
        scraperRepository.save(response);
    }
}
