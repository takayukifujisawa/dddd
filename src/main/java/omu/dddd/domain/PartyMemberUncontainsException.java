package omu.dddd.domain;

public class PartyMemberUncontainsException extends ApiHandlableException {

    private static String message = "冒険者はそのパーティに所属していません";

    public PartyMemberUncontainsException() {
        super(message);
    }    
}

