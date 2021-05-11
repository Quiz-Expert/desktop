package app;

public class RegisterData extends LoginData {

    String name, password_confirmation;

    public RegisterData(String email, String name, String password, String password_confirmation) {
        super(email, password);
        this.name = name;
        this.password_confirmation = password_confirmation;
    }
}
