package hanati.snmp.mibparser.smi;

import hanati.snmp.mibparser.util.token.IdToken;

public class SmiTextualConvention extends SmiType {
    private String displayHint;
    private StatusV2 statusV2;
    private String description;
    private String reference;

    public SmiTextualConvention(IdToken idToken, SmiModule module, String displayHint, StatusV2 statusV2, String description, String reference) {
        super(idToken, module);
        this.displayHint = displayHint;
        this.statusV2 = statusV2;
        this.description = description;
        this.reference = reference;
    }

    public String getDisplayHint() {
        return this.displayHint;
    }

    public void setDisplayHint(String displayHint) {
        this.displayHint = displayHint;
    }

    public StatusV2 getStatusV2() {
        return this.statusV2;
    }

    public void setStatusV2(StatusV2 statusV2) {
        this.statusV2 = statusV2;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
