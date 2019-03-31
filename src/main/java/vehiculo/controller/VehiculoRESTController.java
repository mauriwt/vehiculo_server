package vehiculo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import vehiculo.model.Vehiculo;
import vehiculo.repository.VehiculoRepo;
import vehiculo.utilities.ErrorService;

@RestController
@RequestMapping(value = "vehiculos")
@CrossOrigin
public class VehiculoRESTController {
	@Autowired
	private VehiculoRepo rpVehiculo;
	@Autowired
	private ErrorService<ObjectError> erroresValidacion;

	public VehiculoRESTController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Vehiculo> getfindAllIncidentes() {
		return this.rpVehiculo.getVehiculosByEstado();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Vehiculo getOneVehiculo(@PathVariable("id") int id) {
		return this.rpVehiculo.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@Transactional
	public ResponseEntity<?> addVehivulo(@Valid @RequestBody Vehiculo vehiculo, Errors errors,
			HttpServletRequest request) {
		try {
			if (errors.hasErrors())
				return new ResponseEntity<List<ErrorService<ObjectError>>>(this.erroresValidacion
						.generarErroresDeValidacion(errors.getAllErrors(), "POST addVehiculo (Vehiculo)"),
						HttpStatus.BAD_REQUEST);
			return new ResponseEntity<Vehiculo>(this.rpVehiculo.saveAndFlush(vehiculo), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ErrorService<Exception>>(new ErrorService<Exception>(e.getMessage(),
					ErrorService.APP, "POST addVehiculo (Vehiculo)", "Exeption", e), HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<?> editVehiculo(@Valid @RequestBody Vehiculo vehiculo, Errors errors,
			HttpServletRequest request) {
		try {
			if (errors.hasErrors())
				return new ResponseEntity<List<ErrorService<ObjectError>>>(this.erroresValidacion
						.generarErroresDeValidacion(errors.getAllErrors(), "PUT editVehiculo (Vehiculo)"),
						HttpStatus.BAD_REQUEST);
			if (rpVehiculo.findOne(vehiculo.getId()) == null)
				throw new Exception("No se puedo encontrar los datos");
			return new ResponseEntity<Vehiculo>(this.rpVehiculo.saveAndFlush(vehiculo), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ErrorService<Exception>>(new ErrorService<Exception>(e.getMessage(),
					ErrorService.APP, "PUT editVehiculo (Vehiculo)", "Exeption", e), HttpStatus.BAD_REQUEST);
		}

	}
}
