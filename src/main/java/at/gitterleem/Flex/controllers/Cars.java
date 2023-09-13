package at.gitterleem.Flex.controllers;

import at.gitterleem.Flex.models.Car;
import at.gitterleem.Flex.models.Fuel;
import at.gitterleem.Flex.models.Transmission;
import at.gitterleem.Flex.repositories.CarRepository;
import at.gitterleem.Flex.repositories.ManufacturerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Cars {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@GetMapping("/cars")
	public String listCars(Model model) {
		model.addAttribute("cars", carRepository.findAll());
		return "cars/list";
	}

	@GetMapping("/cars/create")
	public String showAddCarForm(Model model) {
		model.addAttribute("car", new Car());
		model.addAttribute("manufacturers", manufacturerRepository.findAll());
		model.addAttribute("fuels", Fuel.values());
		model.addAttribute("transmissions", Transmission.values());
		return "cars/add-car";
	}

	@PostMapping("/cars/create")
	public String addCar(@ModelAttribute("car") Car car, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "cars/add-car";
		}

		carRepository.save(car);
		return "redirect:/cars";
	}

	@GetMapping("/cars/{id}/edit")
	public String showEditCarForm(@PathVariable("id") Long id, Model model) {
		Car car = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car id: " + id));

		model.addAttribute("car", car);
		model.addAttribute("manufacturers", manufacturerRepository.findAll());
		model.addAttribute("fuels", Fuel.values());
		model.addAttribute("transmissions", Transmission.values());
		return "cars/edit-car";
	}

	@PostMapping("/cars/{id}/edit")
	public String editCar(@PathVariable("id") Long id, @Valid Car car, BindingResult result, Model model) {
		if (result.hasErrors()) {
			car.setId(id);
			return "cars/edit-car";
		}

		carRepository.save(car);
		return "redirect:/cars";
	}

	@PostMapping("/cars/{id}/delete")
	public String deleteCar(@PathVariable("id") Long id, Model model) {
		Car car = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car id: " + id));

		carRepository.delete(car);
		return "redirect:/cars";
	}

}
