package hanati.snmp.mibparser.smi;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import hanati.snmp.mibparser.phase.xref.XRefProblemReporter;
import hanati.snmp.mibparser.util.location.Location;
import hanati.snmp.mibparser.util.token.IdToken;

public abstract class SmiSymbol implements Serializable, Comparable {
    private static final long serialVersionUID = -5181126829777112918L;
    private IdToken idToken;
    private SmiModule module;
    private Map<Object, Object> userData;

    public SmiSymbol(IdToken idToken, SmiModule module) {
        if (module == null) {
            throw new IllegalArgumentException();
        } else {
            this.idToken = idToken;
            this.module = module;
        }
    }

    public SmiSymbol(SmiModule module) {
        if (module == null) {
            throw new IllegalArgumentException();
        } else {
            this.module = module;
        }
    }

    public String getId() {
        return this.idToken != null ? this.idToken.getId() : null;
    }

    public IdToken getIdToken() {
        return this.idToken;
    }

    public void setIdToken(IdToken idToken) {
        this.idToken = idToken;
    }

    public String getCodeId() {
        return null;
    }

    public String getFullCodeId() {
        return this.module.getMib().getCodeNamingStrategy().getFullCodeId(this);
    }

    public SmiModule getModule() {
        return this.module;
    }

    public Location getLocation() {
        return this.idToken != null ? this.idToken.getLocation() : null;
    }

    public String getUcId() {
        return SmiUtil.ucFirst(this.getId());
    }

    public String toString() {
        return this.module.getId() + ": " + this.getId();
    }

    public int hashCode() {
        return this.idToken != null ? this.idToken.getId().hashCode() : super.hashCode();
    }

    public boolean equals(Object obj) {
        if (this.idToken != null && obj instanceof SmiSymbol) {
            SmiSymbol other = (SmiSymbol)obj;
            return this.module.equals(other.module) && other.getId().equals(this.getId());
        } else {
            return super.equals(obj);
        }
    }

    public int compareTo(Object o) {
        return this.compareTo((SmiSymbol)o);
    }

    public int compareTo(SmiSymbol other) {
        int result = this.getModule().getId().compareTo(other.getModule().getId());
        if (result == 0) {
            result = this.getId().compareTo(other.getId());
        }

        return result;
    }

    public void resolveReferences(XRefProblemReporter reporter) {
    }

    public Map<Object, Object> getUserData() {
        return this.userData == null ? Collections.emptyMap() : this.userData;
    }

    public void addUserData(Object key, Object value) {
        if (this.userData == null) {
            this.userData = new HashMap();
        }

        this.userData.put(key, value);
    }

    public Object findUserData(Object key) {
        return this.userData == null ? null : this.userData.get(key);
    }

    public <T> T findUserData(Class<T> key) {
        return this.userData == null ? null : (T) this.userData.get(key);
    }
}
