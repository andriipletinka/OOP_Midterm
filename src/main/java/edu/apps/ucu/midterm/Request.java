package edu.apps.ucu.midterm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class Request {
    private String domain;

    @Override
    public String toString() {
        return "Request{" +
                "domain='" + domain + '\'' +
                '}';
    }
}
