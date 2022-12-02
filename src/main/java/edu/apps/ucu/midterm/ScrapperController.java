package edu.apps.ucu.midterm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ScrapperController {

    private ScrapperService scrapperService;

    @Autowired
    public ScrapperController(ScrapperService scrapperService) {
        this.scrapperService = scrapperService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/responses")
    public List<Response> getResponses() {
        return scrapperService.getResponses();
    }

    @PostMapping("/responses")
    public void addResponse(@RequestBody Response response) {
        scrapperService.addResponse(response);
    }

    @PostMapping("/submit")
    public String postRequest(@ModelAttribute Request request, Model model) {
        Response response = RequestSender.getInstance().sendRequest(request);
        addResponse(response);
        model.addAttribute("domain", response.getDomain());
        model.addAttribute("name", response.getName());
        model.addAttribute("twitter", response.getTwitter());
        model.addAttribute("facebook", response.getFacebook());
        model.addAttribute("logo", response.getLogo());
        model.addAttribute("icon", response.getIcon());
        model.addAttribute("employees", response.getEmployees());
        model.addAttribute("address", response.getAddress());
        return "submit";
    }
}
