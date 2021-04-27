package model.payments;

public enum PayMethods {
    MAIL("Send check for mail."),
    HANDS("Handing check in hand."),
    BANKDEPOSIT("Bank deposit.");

    private String method;

    PayMethods(String method){
        this.method = method;
    }

    public String getMethod(){
        return this.method;
    }

}
