package cz.nevimjak.mathexpressionsolver.structure;

public enum ErrorEnum {

    PARAMETER_COUNT_ERROR("Nesprávný počet parametrů"),
    DIVIDE_ZERO("Dělení nulou není povoleno"),
    NOT_DEFINED_VALUE("Nedefinovaná hodnota"),
    ONLY_INTEGERS_ALLOWED("Do použité funkce lze vložit pouze celá čísla"),
    NOT_VALID_EXPRESSION("Neplatný výraz");

    ErrorEnum(String errorString) {
        this.errorString = errorString;
    }

    private String errorString;

    public String getErrorString() {
        return this.errorString;
    }

}
