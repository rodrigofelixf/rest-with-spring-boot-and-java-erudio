package br.com.erudio.controller;


import br.com.erudio.unittests.mockito.services.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MathController {




    @Autowired
    MathService mathService;


    @GetMapping(path = "/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        return mathService.sum(numberOne, numberTwo);
    }

    @GetMapping(path = "/subtraction/{numberOne}/{numberTwo}")
    public Double minus(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        return mathService.minus(numberOne, numberTwo);
    }

    @GetMapping(path = "/multi/{numberOne}/{numberTwo}")
    public Double multi(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        return mathService.multi(numberOne, numberTwo);
    }

    @GetMapping(path = "/div/{numberOne}/{numberTwo}")
    public Double div(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        return mathService.div(numberOne, numberTwo);
    }

    @GetMapping(path = "/average/{numberOne}/{numberTwo}")
    public Double averageNumber(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) {
        return mathService.averageNumber(numberOne, numberTwo);
    }

    @GetMapping(path = "/squareroot/{number}")
    public Double squareRoot(
            @PathVariable(value = "number") String number
    ) {
        return mathService.squareRoot(number);
    }


}
