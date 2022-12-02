package edu.apps.ucu.midterm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responses")
public class ScrapperController {
    private ScrapperService scrapperService;

    @Autowired
    public ScrapperController(ScrapperService scrapperService) {
        this.scrapperService = scrapperService;
    }

    @GetMapping
    public List<Response> getResponses() {
        return scrapperService.getResponses();
    }

    @PostMapping
    public void addResponse(@RequestBody Response response) {
        scrapperService.addResponse(response);
    }
}
