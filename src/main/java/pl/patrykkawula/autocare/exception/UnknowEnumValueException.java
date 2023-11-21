package pl.patrykkawula.autocare.exception;

class UnknowEnumValueException extends RuntimeException {
    public UnknowEnumValueException(String costType) {
        super("Cost type %s doesn't exist".formatted(costType));

    }
}

//todo
//wyjątek nie jest łapany przez advisora

