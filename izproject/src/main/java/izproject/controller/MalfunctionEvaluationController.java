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

import izproject.dto.BayesEvaluationDTO;
import izproject.dto.MalfunctionSpecsDTO;
import izproject.service.MalfunctionEvaluationService;

@RestController
@RequestMapping(value = "/malfunctionEvaluation", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class MalfunctionEvaluationController {
	
	@Autowired
	private MalfunctionEvaluationService malfunctionEvaluationService;
	
	@PostMapping
	public ResponseEntity<List<BayesEvaluationDTO>> getEvaluation(@RequestBody MalfunctionSpecsDTO malfunctionSpecsDTO) {
		List<BayesEvaluationDTO> bayesEvaluationDTOs = malfunctionEvaluationService.getEvaluation(malfunctionSpecsDTO);
		return new ResponseEntity<>(bayesEvaluationDTOs, HttpStatus.OK);
	}

}
