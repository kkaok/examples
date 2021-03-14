package eblo.example.aop.log.domain;

public enum ReqType {
    READ("R")
    ,DETAIL("T")
    ,CREATE("C")
    ,UPDATE("U")
    ,SAVE("S")
    ,DELETE("D");
    
    private final String value;
    
    ReqType(String value) {
       this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
}
