package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//INSERT INTO location (name, address, capacity, description)
//VALUES ('Central Park', '123 Main St', '5000', 'An iconic venue for events.');
//
//INSERT INTO location (name, address, capacity, description)
//VALUES ('Grand Hall', '456 Elm St', '2000', 'A spacious hall suitable for conferences and weddings.');
//
//INSERT INTO location (name, address, capacity, description)
//VALUES ('Riverside Arena', '789 River Rd', '10000', 'An open-air venue perfect for concerts and festivals.');
//
//INSERT INTO location (name, address, capacity, description)
//VALUES ('Skyline Lounge', '1010 Sunset Blvd', '500', 'A rooftop lounge offering stunning city views.');
//
//INSERT INTO location (name, address, capacity, description)
//VALUES ('Heritage Center', '321 Oak St', '1500', 'A historic venue ideal for exhibitions and cultural events.');


@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String capacity;
    private String description;

    @OneToMany(mappedBy = "location")
    private List<Event> events;

    public Location(String name, String address, String capacity, String description) {
        this.id=(long) (Math.random()*1000);
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.description = description;
    }
}
