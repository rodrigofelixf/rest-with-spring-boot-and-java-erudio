package br.com.erudio.service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class MathService {

    public Double sum(String numberOne, String numberTwo) throws ResourceNotFoundException {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new ResourceNotFoundException("Put a numeric valid");
        }
        return convertToDouble(numberOne) + convertToDouble(numberTwo);

    }

    public Double minus(String numberOne, String numberTwo) throws ResourceNotFoundException {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new ResourceNotFoundException("Put a numeric valid");
        }
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }


    public Double multi(String numberOne, String numberTwo) throws ResourceNotFoundException {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new ResourceNotFoundException("Put a numeric valid");
        }
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    public Double div(String numberOne, String numberTwo) throws ResourceNotFoundException {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo) || isZero(numberOne) || isZero(numberTwo)) {
            throw new ResourceNotFoundException("Put a numeric valid");
        }
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    public Double averageNumber(String numberOne, String numberTwo) throws ResourceNotFoundException {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new ResourceNotFoundException("Put a numeric valid");
        }
        return average(numberOne, numberTwo);
    }

    public Double squareRoot(String number) throws ResourceNotFoundException {
        if (!isNumeric(number)) {
            throw new ResourceNotFoundException("Put a numeric valid");
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
