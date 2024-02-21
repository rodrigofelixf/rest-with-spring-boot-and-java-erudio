package br.com.erudio.restwithspringbootandjavaerudio.service;

import br.com.erudio.restwithspringbootandjavaerudio.exceptions.UnsupportedMathOperationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


@Service
public class MathService {

    public Double sum(String numberOne, String numberTwo) throws UnsupportedMathOperationException {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException();
        }
        return convertToDouble(numberOne) + convertToDouble(numberTwo);

    }

    public Double minus(String numberOne, String numberTwo) throws UnsupportedMathOperationException {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException();
        }
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }


    public Double multi(String numberOne, String numberTwo) throws UnsupportedMathOperationException {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException();
        }
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    public Double div(String numberOne, String numberTwo) throws UnsupportedMathOperationException {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo) || isZero(numberOne) || isZero(numberTwo)) {
            throw new UnsupportedMathOperationException();
        }
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    public Double averageNumber(String numberOne, String numberTwo) throws UnsupportedMathOperationException {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException();
        }
        return average(numberOne, numberTwo);
    }

    public Double squareRoot(String number) throws UnsupportedMathOperationException {
        if (!isNumeric(number)) {
            throw new UnsupportedMathOperationException();
        }
        return Math.sqrt(convertToDouble(number));
    }


    private Double convertToDouble(String strNumber) {
        if (strNumber == null) return 0D;
        String number = strNumber.replace(",", ".");
        if (isNumeric(strNumber)) return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;
        String number = strNumber.replace(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    private boolean isZero(String strNumber) {
        Double numberDouble = convertToDouble(strNumber);
        return numberDouble == 0;
    }

    private Double average(String numberOne, String numberTwo) {
        Double numberOneConverted = convertToDouble(numberOne);
        Double numberTwoConverted = convertToDouble(numberTwo);

        return (numberOneConverted + numberTwoConverted) / 2;


    }


}
