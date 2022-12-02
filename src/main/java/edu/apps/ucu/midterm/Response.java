package edu.apps.ucu.midterm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Response {
    private String domain;
    private String name;
    private String twitter;
    private String facebook;
    private String logo;
    private String icon;
    private int employees;
    private String address;
    @Id @GeneratedValue
    private int id;

    @Override
    public String toString() {
        return "Response{" +
                "domain='" + domain + '\'' +
                ", name='" + name + '\'' +
                ", twitter='" + twitter + '\'' +
                ", facebook='" + facebook + '\'' +
                ", logo='" + logo + '\'' +
                ", icon='" + icon + '\'' +
                ", employees=" + employees +
                ", address='" + address + '\'' +
                ", id=" + id +
                '}';
    }
}
