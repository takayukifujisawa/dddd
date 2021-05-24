package omu.dddd.domain;

public class PartyMemberDuplicatedException extends ApiHandlableException {

    private static String message = "すでに所属しています";

    public PartyMemberDuplicatedException() {
        super(message);
    }    
}

