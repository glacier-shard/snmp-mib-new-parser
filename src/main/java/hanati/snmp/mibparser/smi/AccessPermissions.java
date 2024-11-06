package hanati.snmp.mibparser.smi;

public interface AccessPermissions {
    boolean isReadable();

    boolean isWritable();

    boolean isCreateWritable();
}
