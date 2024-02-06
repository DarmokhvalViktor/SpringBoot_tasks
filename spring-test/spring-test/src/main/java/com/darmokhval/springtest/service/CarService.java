package com.darmokhval.springtest.service;

import com.darmokhval.springtest.dto.CarDTO;
import com.darmokhval.springtest.dto.SearchCriteriaDTO;
import com.darmokhval.springtest.entity.Car;
import com.darmokhval.springtest.error.CarNotFoundException;
import com.darmokhval.springtest.error.CarsNotFoundException;
import com.darmokhval.springtest.mapper.CarMapper;
import com.darmokhval.springtest.repository.CarRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private String uploadDirectory;
    private final MailService mailService;
    @Value("${spring.mail.username}")
    private String mailTo;

    public CarService(CarRepository carRepository, CarMapper carMapper, MailService mailService) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.mailService = mailService;
    }


    public List<CarDTO> getCars() {
        List<Car> cars = carRepository.findAll();
        if(cars.isEmpty()) {
            throw new CarsNotFoundException();
        }
        return cars.stream().map(carMapper::convertToDTO).toList();
    }

    public CarDTO getCar(Integer id) {
        Optional<Car> car = carRepository.findById(id);
        return car.map(carMapper::convertToDTO).orElseThrow(() ->new CarNotFoundException(id));
    }

    @Transactional
    public CarDTO saveCar(CarDTO carDTO, MultipartFile multipartFile) {
        saveFile(multipartFile);
        Car car = carMapper.convertToEntity(carDTO);
        car.setPhotoPath(multipartFile.getOriginalFilename());
        Car savedCar = carRepository.save(car);
        sendCreatedCarMail(car);
        return carMapper.convertToDTO(savedCar);
    }

    private void sendCreatedCarMail(Car car) {
        mailService.sendEmail(mailTo, "New car created successfully", "Car model %s and producer %s with power %d was created. Photo: %s "
                .formatted(car.getModel(), car.getProducer(), car.getPower(), car.getPhotoPath()));
    }


    private void saveFile(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String filePath = uploadDirectory + File.separator + fileName;
        try {
            multipartFile.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file: " + fileName, e);
        }
    }

    @Transactional
    public CarDTO deleteCar(Integer id) {
        CarDTO deletedCar = getCar(id);
        carRepository.deleteById(id);
        mailService.sendEmail(mailTo, "Car deleted successfully", "Car with id %d, producer %s, model %s, power %d deleted successfully"
                .formatted(deletedCar.getId(), deletedCar.getProducer(), deletedCar.getModel(), deletedCar.getPower()));
        return deletedCar;
    }

    public List<CarDTO> getCarsByPower(Integer power) {
        List<Car> carsByPower = carRepository.findCarsByPower(power);
        if(carsByPower.isEmpty()) {
            throw new CarsNotFoundException(power);
        }
        return carsByPower.stream().map(carMapper::convertToDTO).toList();
    }


    public List<CarDTO> getCarsByProducer(String producer) {
        List<Car> carsByProducer = carRepository.findCarsByProducer(producer);
        if(carsByProducer.isEmpty()) {
            throw new CarsNotFoundException(producer);
        }
        return carsByProducer.stream().map(carMapper::convertToDTO).toList();
    }

    public List<CarDTO> findAllByParams(SearchCriteriaDTO searchCriteriaDTO) {
        Car car = new Car();
        car.setModel(searchCriteriaDTO.getModel());
        car.setPower(searchCriteriaDTO.getPower());
        car.setProducer(searchCriteriaDTO.getProducer());
        List<Car> carsList = carRepository.findAll(Example.of(car));
        if(carsList.isEmpty()) {
            throw new CarsNotFoundException(searchCriteriaDTO);
        }
        return carsList.stream().map(carMapper::convertToDTO).toList();
    }


    @PostConstruct
    private void createUploadDirectoryIfNotExists() {
        String directoryPath = System.getProperty("user.home") + File.separator + "photos";
        File folder = new File(directoryPath);
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                System.out.println("Folder created successfully");
            } else {
                System.out.println("Failed to create folder");
            }
        } else {
            System.out.println("Folder already exists");
        }
        uploadDirectory = String.valueOf(folder);
    }
}
