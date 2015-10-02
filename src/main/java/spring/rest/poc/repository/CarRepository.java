package spring.rest.poc.repository;

import org.springframework.data.repository.CrudRepository;

import spring.rest.poc.entity.Car;


public interface CarRepository extends CrudRepository<Car, Long> {

    /**
     * Delegate CRUD operations against the data source
     */

    Car findByMakeAndModel(String make, String model);
}
