/**
 * 
 */
package vehiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vehiculo.model.TipoVehiculo;

/**
 * @author mtituana
 *
 */
@Repository
public interface TipoVehiculoRepo extends JpaRepository<TipoVehiculo, Integer> {
}
