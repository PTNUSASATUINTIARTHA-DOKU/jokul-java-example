package enums;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public enum ResponseCode {
    SUCCESS("0000","Success"),
    UNKNOWN("0099","Internal Server Error");

    private final String code;
    private final String message;

    ResponseCode(String code, String message){
        this.code = code;
        this.message = message;
    }
    public String code(){
        return code;
    }

    public String message(){
        return message;
    }

    public static ResponseCode byCode(String code) {
        Predicate<ResponseCode> isEqual = responseCode -> Optional.ofNullable(responseCode)
                .map(thisObject -> thisObject.code)
                .map(code::equals)
                .orElse(false);
        return Stream.of(values())
                .filter(isEqual)
                .findAny()
                .orElse(null);
    }

}
