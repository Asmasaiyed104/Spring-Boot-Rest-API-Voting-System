package ca.sheridancollege.saiyedas.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import ca.sheridancollege.saiyedas.beans.Capstone;
import ca.sheridancollege.saiyedas.database.DatabaseAccess;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/capstones")
@AllArgsConstructor
public class CapstoneController {

    private final DatabaseAccess da; // Use constructor injection to initialize da

    @GetMapping
    public List<Capstone> getCapstoneCollection() {
        return da.findAll();
    }

    @GetMapping("/{id}")
    public Capstone getIndividualCapstone(@PathVariable Long id) {
        return da.findById(id);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> postCapstone(@RequestBody Capstone capstone) {
        Long generatedId = da.save(capstone);
        return ResponseEntity.ok("Created Capstone with ID: " + generatedId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putCapstone(@PathVariable Long id, @RequestBody Capstone updatedCapstone) {
        Capstone existingCapstone = da.findById(id);
        if (existingCapstone != null) {
            existingCapstone.setName(updatedCapstone.getName());
            existingCapstone.setVote(updatedCapstone.getVote());
            existingCapstone.setInformation(updatedCapstone.getInformation());
            da.save(existingCapstone);
            return ResponseEntity.ok("Updated Capstone with ID: " + existingCapstone.getId());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
  
  
    @GetMapping("/editCapstoneById/{id}")
    public String editCapstoneById(@PathVariable Long id, Model model) {
        Capstone capstone = da.findById(id);
        model.addAttribute("capstone", capstone);
        return "editCapstone"; // Return the name of the Thymeleaf template for editing a Capstone
    }


    @PostMapping("/updateCapstone")
    public String updateCapstone(@ModelAttribute("capstone") Capstone capstone) {
        // Handle the update logic and save the changes to the database
        da.save(capstone);
        return "redirect:/"; // Redirect to the main Capstones page or any other appropriate page
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCapstone(@PathVariable Long id) {
        Capstone existingCapstone = da.findById(id);
        if (existingCapstone != null) {
            da.delete(existingCapstone);
            return ResponseEntity.ok("Deleted Capstone with ID: " + id);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
