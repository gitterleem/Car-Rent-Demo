package at.gitterleem.Flex.controllers;

import at.gitterleem.Flex.models.Client;
import at.gitterleem.Flex.models.Rental;
import at.gitterleem.Flex.repositories.CarRepository;
import at.gitterleem.Flex.repositories.ClientRepository;
import at.gitterleem.Flex.repositories.RentalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class Clients {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private RentalRepository rentalRepository;

	@GetMapping("/clients")
	public String listClients(Model model) {
		model.addAttribute("clients", clientRepository.findAll());
		return "clients/list";
	}


	@GetMapping("/clients/create")
	public String showCreateClientForm(Model model) {
		model.addAttribute("client", new Client());
		return "clients/add-client";
	}

	@PostMapping("/clients/create")
	public String addClient(@ModelAttribute("client") Client client, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "clients/add-client";
		}

		clientRepository.save(client);
		return "redirect:/clients";
	}

	@GetMapping("/clients/{id}/edit")
	public String showEditClientForm(@PathVariable("id") Long id, Model model) {
		Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid client id: " + id));

		model.addAttribute("client", client);
		return "clients/edit-client";
	}

	@PostMapping("/clients/{id}/edit")
	public String editClient(@PathVariable("id") Long id, @Valid Client client, BindingResult result, Model model) {
		if (result.hasErrors()) {
			client.setId(id);
			return "clients/edit-client";
		}

		clientRepository.save(client);
		return "redirect:/clients";
	}

	@GetMapping("/clients/{id}/delete")
	public String deleteClient(@PathVariable("id") Long id, Model model) {
		Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid client id: " + id));

		clientRepository.delete(client);
		return "redirect:/clients";
	}

	@GetMapping("/clients/{id}/rent-a-car")
	public String showRentForm(@PathVariable("id") Long id, Model model) {
		Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid client id: " + id));

		model.addAttribute("client", client);
		model.addAttribute("cars", carRepository.findAll());
		model.addAttribute("rental", new Rental());
		return "clients/rent-a-car";
	}

	@PostMapping("/clients/{id}/rent-a-car")
	public String rentACar(@PathVariable("id") Long id, @Valid Rental rental, BindingResult result, Model model) {
		Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid client id: " + id));

		rental.setClient(client);

		if (rental.getStartTime() == null) {
			// html "date" does seem to not bind to this local date time... I am NOT going to fix this properly ;)
			rental.setStartTime(LocalDateTime.now());
		}

		System.out.println(rental);

		//if (result.hasErrors()) {
		//	model.addAttribute("client", client);
		//	return "clients/rent-a-car";
		//}

		rentalRepository.save(rental);
		return "redirect:/clients";
	}

}
