package hanati.snmp.mibparser.smi;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public enum SmiVersion {
    V1,
    V2;

    public static final Set<SmiVersion> V1_SET = Collections.singleton(V1);
    public static final Set<SmiVersion> V2_SET = Collections.singleton(V2);
    public static final Set<SmiVersion> ALL_SET = Collections.unmodifiableSet(EnumSet.allOf(SmiVersion.class));

    private SmiVersion() {
    }
}
