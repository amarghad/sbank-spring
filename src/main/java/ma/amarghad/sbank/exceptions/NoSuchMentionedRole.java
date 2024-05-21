package ma.amarghad.sbank.exceptions;

public class NoSuchMentionedRole extends RuntimeException {

    public NoSuchMentionedRole(String roleName) {
        super("Given role " + roleName + " doesn't exist");
    }
}
