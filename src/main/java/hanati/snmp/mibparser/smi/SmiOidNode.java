package hanati.snmp.mibparser.smi;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SmiOidNode {
    private final SmiOidNode parentNode;
    Map<Integer, SmiOidNode> childMap = new TreeMap();
    private List<SmiOidValue> values = new ArrayList();
    private final int value;
    private int[] oidInArray;
    private String oidStr;

    public SmiOidNode(SmiOidNode parent, int value) {
        this.parentNode = parent;
        this.value = value;
        if (this.parentNode != null) {
            if (this.parentNode.childMap.get(value) != null) {
                throw new IllegalStateException();
            }

            this.parentNode.childMap.put(value, this);
        }

    }

    public SmiOidNode getParent() {
        return this.parentNode;
    }

    public Collection<? extends SmiOidNode> getChildren() {
        return this.childMap.values();
    }

    public List<SmiOidValue> getValues() {
        return this.values;
    }

    public int[] getOid() {
        return this.oidInArray;
    }

    public String getOidStr() {
        return this.oidStr;
    }

    public int getTotalChildCount() {
        int result = this.childMap.size();

        SmiOidNode child;
        for(Iterator i$ = this.childMap.values().iterator(); i$.hasNext(); result += child.getTotalChildCount()) {
            child = (SmiOidNode)i$.next();
        }

        return result;
    }

    public void dumpTree(PrintStream w, String indent) {
        w.print(indent);
        w.print(this.value);
        Iterator i$ = this.values.iterator();

        while(i$.hasNext()) {
            SmiOidValue value = (SmiOidValue)i$.next();
            w.print(":");
            w.print(value.getId());
        }

        i$ = this.childMap.values().iterator();

        while(i$.hasNext()) {
            SmiOidNode child = (SmiOidNode)i$.next();
            child.dumpTree(w, indent + " ");
        }

    }

    public static SmiOidNode createRootNode() {
        return new SmiOidNode((SmiOidNode)null, -1);
    }

    public SmiOidNode findChild(int value) {
        return (SmiOidNode)this.childMap.get(value);
    }

    public <T extends SmiOidValue> T getSingleValue(Class<T> clazz) {
        if (this.values.size() != 1) {
            throw new AssertionError("expected only a single value");
        } else {
            return (T) clazz.cast(this.values.get(0));
        }
    }

    public <T extends SmiOidValue> T getSingleValue(Class<T> clazz, SmiModule module) {
        T result = null;
        Iterator i$ = this.values.iterator();

        while(i$.hasNext()) {
            SmiOidValue value = (SmiOidValue)i$.next();
            if (value.getModule() == module && clazz.isInstance(value)) {
                if (result != null) {
                    throw new IllegalArgumentException("more than one found");
                }

                result = (T) clazz.cast(value);
            }
        }

        return result;
    }

    public SmiOidValue getSingleValue() {
        if (this.values.size() != 1) {
            throw new AssertionError("expected only a single value");
        } else {
            return (SmiOidValue)this.values.get(0);
        }
    }

    public SmiOidNode getRootNode() {
        SmiOidNode parent;
        for(parent = this; parent.getParent() != null; parent = parent.getParent()) {
        }

        return parent;
    }

    public void dumpAncestors(PrintStream out) {
        for(SmiOidNode oidValue = this; oidValue != null; oidValue = oidValue.getParent()) {
            out.print(oidValue.value);
            Iterator i$ = this.values.iterator();

            while(i$.hasNext()) {
                SmiOidValue value = (SmiOidValue)i$.next();
                out.print(",");
                out.print(value.getId());
            }

            if (oidValue.getParent() != null) {
                out.print(": ");
            }
        }

        out.println();
    }

    public int[] determineFullOid() {
        if (this.oidInArray == null) {
            SmiOidNode parent = this.getParent();
            if (parent != null) {
                int[] parentOid = parent.determineFullOid();
                if (parentOid != null) {
                    this.oidInArray = new int[parentOid.length + 1];
                    System.arraycopy(parentOid, 0, this.oidInArray, 0, parentOid.length);
                    this.oidInArray[this.oidInArray.length - 1] = this.value;
                    this.oidStr = parent.getOidStr() + "." + this.value;
                } else {
                    this.oidInArray = new int[]{this.value};
                    this.oidStr = String.valueOf(this.value);
                }
            }
        }

        return this.oidInArray;
    }

    public boolean contains(SmiOidNode node) {
        return this.childMap.containsValue(node);
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return this.getClass().getSimpleName() + ":" + this.oidStr;
    }
}
