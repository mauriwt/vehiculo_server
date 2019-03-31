/**
 * 
 */
package vehiculo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vehiculo.model.Vehiculo;

/**
 * @author mtituana
 *
 */
@Repository
public interface VehiculoRepo extends JpaRepository<Vehiculo, Integer> {

	@Query("SELECT v FROM Vehiculo v ORDER BY estado ASC")
	public List<Vehiculo> getVehiculosByEstado();
}
