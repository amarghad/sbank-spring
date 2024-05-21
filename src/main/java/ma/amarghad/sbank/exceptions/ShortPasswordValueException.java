package ma.amarghad.sbank.exceptions;

public class ShortPasswordValueException extends RuntimeException {

    public ShortPasswordValueException(int minLength) {
        super(
                String.format("Password length must be at least %d characters", minLength)
        );
    }
}
