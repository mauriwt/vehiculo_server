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


import vehiculo.model.TipoVehiculo;
import vehiculo.repository.TipoVehiculoRepo;
import vehiculo.utilities.ErrorService;

@RestController
@RequestMapping(value = "tipoVehiculo")
@CrossOrigin
public class TipoVehiculoRESTController {
	@Autowired
	private TipoVehiculoRepo rpTipoVehiculo;
	@Autowired
	private ErrorService<ObjectError> erroresValidacion;

	public TipoVehiculoRESTController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<TipoVehiculo> getfindAllIncidentes() {
		return this.rpTipoVehiculo.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public TipoVehiculo getOneVehiculo(@PathVariable("id") int id) {
		return this.rpTipoVehiculo.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@Transactional
	public ResponseEntity<?> addTipoVehivulo(@Valid @RequestBody TipoVehiculo tpvehiculo, Errors errors,
			HttpServletRequest request) {
		try {
			if (errors.hasErrors())
				return new ResponseEntity<List<ErrorService<ObjectError>>>(this.erroresValidacion
						.generarErroresDeValidacion(errors.getAllErrors(), "POST addTipoVehivulo (TipoVehiculo)"),
						HttpStatus.BAD_REQUEST);
			return new ResponseEntity<TipoVehiculo>(this.rpTipoVehiculo.saveAndFlush(tpvehiculo), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ErrorService<Exception>>(new ErrorService<Exception>(e.getMessage(),
					ErrorService.APP, "POST addTipoVehivulo (TipoVehiculo)", "Exeption", e), HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<?> editTipoVehiculo(@Valid @RequestBody TipoVehiculo tpvehiculo, Errors errors,
			HttpServletRequest request) {
		try {
			if (errors.hasErrors())
				return new ResponseEntity<List<ErrorService<ObjectError>>>(this.erroresValidacion
						.generarErroresDeValidacion(errors.getAllErrors(), "PUT editTipoVehiculo (Vehiculo)"),
						HttpStatus.BAD_REQUEST);
			if (rpTipoVehiculo.findOne(tpvehiculo.getId()) == null)
				throw new Exception("No se puedo encontrar los datos");
			return new ResponseEntity<TipoVehiculo>(this.rpTipoVehiculo.saveAndFlush(tpvehiculo), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ErrorService<Exception>>(new ErrorService<Exception>(e.getMessage(),
					ErrorService.APP, "PUT editTipoVehiculo (Vehiculo)", "Exeption", e), HttpStatus.BAD_REQUEST);
		}

	}
}
