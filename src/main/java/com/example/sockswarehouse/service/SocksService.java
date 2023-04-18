package com.example.sockswarehouse.service;

import com.example.sockswarehouse.entity.SocksEntity;
import com.example.sockswarehouse.operation.Operation;
import com.example.sockswarehouse.repository.SocksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service

public class SocksService {

    private final Logger logger = LoggerFactory.getLogger(SocksService.class);

    private final SocksRepository socksRepository;

    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    /**
     * Поступление носков на склад.
     * Используется метод репозитория {@link com.example.sockswarehouse.repository.SocksRepository#findSocksByColorAndCottonPart(String, Integer)}
     *
     * @param color   цвет носков
     * @param cottonPart количество хлопка
     * @param quantity количество носков в приход
     */
    public SocksEntity incomeSocks(String color, Integer cottonPart, Integer quantity) {
        logger.info("Invoke method addSocks");
        if (socksRepository.findSocksByColorAndCottonPart(color, cottonPart) == null) {
            SocksEntity socks = new SocksEntity();
            socks.setColor(color);
            socks.setCottonPart(cottonPart);
            socks.setQuantity(quantity);
            return socksRepository.save(socks);
        } else {
            SocksEntity socks = socksRepository.findSocksByColorAndCottonPart(color, cottonPart);
            int numbers = socks.getQuantity();
            socks.setQuantity(socks.getQuantity() + numbers);
            return socksRepository.save(socks);
        }
    }

    /**
     * Метод отпуска носков.
     * Используется метод репозитория {@link com.example.sockswarehouse.repository.SocksRepository#findSocksByColorAndCottonPartEquals(String, Integer)}
     * Используется метод репозитория {@link com.example.sockswarehouse.repository.SocksRepository#findSocksByColorAndCottonPartLessThan(String, Integer)}
     * Используется метод репозитория {@link com.example.sockswarehouse.repository.SocksRepository#findSocksByColorAndCottonPartGreaterThan(String, Integer)}
     *
     * @param color   цвет носков
     * @param operation значение запроса по составу хлопка
     * @param cottonPart количество хлопка
     */
    public List<SocksEntity> getSocks(String color, Operation operation, Integer cottonPart) {
        logger.info("Invoke method getSocks");
        List<SocksEntity> findSocks = new ArrayList<>();
        switch (operation) {
            case equal -> findSocks = socksRepository.findSocksByColorAndCottonPartEquals(color, cottonPart);
            case lessThan -> findSocks = socksRepository.findSocksByColorAndCottonPartLessThan(color, cottonPart);
            case moreThan -> findSocks = socksRepository.findSocksByColorAndCottonPartGreaterThan(color, cottonPart);
        }
        return findSocks;
    }

    /**
     * Метод отпуска носков.
     * Используется метод репозитория {@link com.example.sockswarehouse.repository.SocksRepository#findSocksByColorAndCottonPart(String, Integer)}
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#delete(Object)}
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     *
     * @param color   цвет носков
     * @param cottonPart количество хлопка
     * @param quantity количество носков в отпуск
     */
    public SocksEntity outcomeSocks(String color, Integer cottonPart, int quantity) {
        logger.info("Invoke method outcomeSocks");
        SocksEntity socks = socksRepository.findSocksByColorAndCottonPart(color, cottonPart);
        int numbersSocks = socks.getQuantity();
        if (numbersSocks < quantity) {
            throw new ArithmeticException("This quantity is not in stock");
        } else if (numbersSocks == quantity) {
            socksRepository.delete(socks);
        } else {
            socks.setQuantity(numbersSocks - quantity);
        }
        return socksRepository.save(socks);
    }
}
