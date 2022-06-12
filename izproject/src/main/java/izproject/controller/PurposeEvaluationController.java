package izproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import izproject.dto.ComputerSpecDTO;
import izproject.dto.PurposeEvaluationDTO;
import izproject.service.PurposeEvaluationService;

@RestController
@RequestMapping(value = "/purposeEvaluation", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class PurposeEvaluationController {

	@Autowired
	private PurposeEvaluationService evaluationService;

	@PostMapping
	public ResponseEntity<PurposeEvaluationDTO> getEvaluation(@RequestBody ComputerSpecDTO computerSpecDTO) {
		PurposeEvaluationDTO purposeEvaluationDTO = evaluationService.getPurposeEvaluation(computerSpecDTO);
		return new ResponseEntity<>(purposeEvaluationDTO, HttpStatus.OK);
	}

}
