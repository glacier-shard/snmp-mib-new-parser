package hanati.snmp.mibparser.smi;

public class SmiOptions {
    private boolean convertV1ImportsToV2;

    public SmiOptions() {
    }

    public boolean isConvertV1ImportsToV2() {
        return this.convertV1ImportsToV2;
    }

    public void setConvertV1ImportsToV2(boolean convertV1ImportsToV2) {
        this.convertV1ImportsToV2 = convertV1ImportsToV2;
    }
}
