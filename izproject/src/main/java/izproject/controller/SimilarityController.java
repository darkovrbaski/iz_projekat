package izproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import izproject.dto.PCSimilarityDTO;
import izproject.service.SimilarityService;

@RestController
@RequestMapping(value = "/similarity", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class SimilarityController {
	
	@Autowired
	SimilarityService similarityService;
	
	@PostMapping("/pc")
    public ResponseEntity<List<PCSimilarityDTO>> getSimilarPCs(@RequestBody PCSimilarityDTO pcSimilarityDTO){
        List<PCSimilarityDTO> response = similarityService.getSimilarPCs(pcSimilarityDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
