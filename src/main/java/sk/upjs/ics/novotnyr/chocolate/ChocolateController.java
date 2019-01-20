package sk.upjs.ics.novotnyr.chocolate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/chocolates")
public class ChocolateController {
	private List<Chocolate> chocolates = new CopyOnWriteArrayList<Chocolate>();
	
	public ChocolateController() {
		chocolates.add(new Chocolate("lindt", "Lindt", 72));
		chocolates.add(new Chocolate("choc-o-crap", "Choc'o'crap", 10));
		chocolates.add(new Chocolate("brownella", "Brownella", 52));
	}

	@GetMapping("/{id}")
	public Chocolate get(@PathVariable String id) {
		Chocolate chocolate = findById(id);
		if(chocolate == null) {
			throw new ChocolateNotFoundException();
		}
		return chocolate;
	}
	
	@GetMapping
	public List<Chocolate> list() {
		return chocolates;
	}	

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Chocolate add(@RequestBody Chocolate chocolate) {
		if(alreadyExists(chocolate)) {
			throw new DuplicateChocolateException();
		}
		
		return chocolate;
	}

	private boolean alreadyExists(Chocolate chocolate) {
		return chocolate.getId() != null && findById(chocolate.getId()) != null;
	}

	protected Chocolate findById(String id) {
		for (final Chocolate chocolate : chocolates) {
			if(chocolate.getId().equals(id)) {
				return chocolate;
			}
		}
		return null;
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public class ChocolateNotFoundException extends RuntimeException {
		// no body needed
	}
	
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public class DuplicateChocolateException extends RuntimeException {
		// no body needed
	}	
}
