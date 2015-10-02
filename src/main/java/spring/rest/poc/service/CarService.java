package spring.rest.poc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.rest.poc.entity.Car;
import spring.rest.poc.exception.BadRequestException;
import spring.rest.poc.repository.CarRepository;


@Service
public class CarService extends AbstractService<Car> {

    private static final Logger logger = LoggerFactory.getLogger(CarService.class);

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car create(Car car) {
        checkDuplicateRecord(car.getId());
        return carRepository.save(car);
    }

    @Override
    public List<Car> getAll() {
        return (List<Car>) carRepository.findAll();
    }

    @Override
    public Car findById(Long id) {
        return recordFound(carRepository.findOne(id));
    }

    @Override
    public void update(Car car) {
        if (car.getId() == null) {
            throw new BadRequestException("Id cannot be null");
        }
        recordFound(carRepository.findOne(car.getId()));
        carRepository.save(car);
    }

    @Override
    public void delete(Long id) {
        Car car = recordFound(carRepository.findOne(id));
        carRepository.delete(car);
    }

    public Car getCarByMakeAndModel(String make, String model) {
        return recordFound(carRepository.findByMakeAndModel(make, model));
    }
}
