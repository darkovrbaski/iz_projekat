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

import izproject.dto.ComponentPropertyDTO;
import izproject.service.ComponentService;

@RestController
@RequestMapping(value = "/component", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ComponentController {

	@Autowired
	private ComponentService componentService;

	@PostMapping()
	public ResponseEntity<List<String>> getComponents(@RequestBody String component) {
		List<String> response = componentService.getComponents(component);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("property")
	public ResponseEntity<String> getComponentProperty(@RequestBody ComponentPropertyDTO componentPropertyDTO) {
		String response = componentService.getComponentDataProperty(componentPropertyDTO.getComponentName(),
				componentPropertyDTO.getProperty());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("type")
	public ResponseEntity<String> getComponentType(@RequestBody ComponentPropertyDTO componentPropertyDTO) {
		String response = componentService.getComponentType(componentPropertyDTO.getComponentName());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
