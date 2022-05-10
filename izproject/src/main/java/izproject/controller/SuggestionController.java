package izproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import izproject.service.SuggestionService;


@RestController
@RequestMapping(value = "/suggestion", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class SuggestionController {
	
	@Autowired
	SuggestionService suggestionService;
	
	@GetMapping()
    public ResponseEntity<List<String>> getComponents(){
        List<String> response = suggestionService.getComponents();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
}
