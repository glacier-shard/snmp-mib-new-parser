package hanati.snmp.mibparser.smi;

import hanati.snmp.mibparser.phase.xref.XRefProblemReporter;
import hanati.snmp.mibparser.util.token.IdToken;
import hanati.snmp.mibparser.util.token.IntegerToken;
import hanati.snmp.mibparser.util.token.Token;

public class OidComponent {
    private final OidComponent parent;
    private OidComponent child;
    private final IdToken idToken;
    private final IntegerToken valueToken;
    private SmiOidNode node;
    private boolean isResolved = false;

    public OidComponent(OidComponent parent, IdToken idToken, IntegerToken intToken) {
        this.parent = parent;
        if (this.parent != null) {
            this.parent.child = this;
        }

        this.idToken = idToken;
        this.valueToken = intToken;
    }

    public IdToken getIdToken() {
        return this.idToken;
    }

    public IntegerToken getValueToken() {
        return this.valueToken;
    }

    public SmiOidNode getNode() {
        return this.node;
    }

    private Token getToken() {
        return (Token)(this.idToken != null ? this.idToken : this.valueToken);
    }

    private boolean isFirst() {
        return this.parent == null;
    }

    private boolean isLast() {
        return this.child == null;
    }

    public SmiOidNode resolveNode(SmiModule module, XRefProblemReporter reporter) {
        assert this.node == null;

        if (!this.isResolved) {
            SmiOidNode parentNode = null;
            if (this.parent != null) {
                parentNode = this.parent.resolveNode(module, reporter);
                if (parentNode == null) {
                    System.out.println("couldn't find parent for: " + this.parent.getToken());
                }
            }

            this.node = this.doResolve(module, parentNode, reporter);
            if (this.node == null) {
                if (this.isLast()) {
                    if (parentNode != null) {
                        if (this.valueToken != null) {
                            this.node = new SmiOidNode(parentNode, this.valueToken.getValue());
                        } else {
                            System.out.println("valueToken missing for last subid: " + this.getToken());
                        }
                    } else {
                        System.out.println("parent missing for last subid: " + this.getToken());
                    }
                } else {
                    System.out.println("couldn't resolve non-last subid " + this.getToken());
                }
            }

            this.isResolved = true;
        }

        return this.node;
    }

    private SmiOidNode doResolve(SmiModule module, SmiOidNode parent, XRefProblemReporter reporter) {
        SmiOidNode node;
        if (this.idToken != null && !this.isLast()) {
            SmiSymbol symbol = module.resolveReference(this.idToken, (XRefProblemReporter)null);
            if (symbol != null) {
                if (symbol instanceof SmiOidValue) {
                    SmiOidValue oidValue = (SmiOidValue)symbol;
                    node = oidValue.resolveOid(reporter);
                    if (node != null && this.valueToken != null) {
                    }
                } else {
                    reporter.reportFoundSymbolButWrongType(this.idToken, SmiOidValue.class, symbol.getClass());
                    node = null;
                }
            } else if (parent != null && this.valueToken != null) {
                int value = this.valueToken.getValue();
                node = (SmiOidNode)parent.childMap.get(value);
                if (node == null) {
                    node = new SmiOidNode(parent, value);
                }
            } else {
                node = null;
            }
        } else if (this.isFirst()) {
            node = module.getMib().getRootNode().findChild(this.valueToken.getValue());
            if (node == null) {
                node = new SmiOidNode(module.getMib().getRootNode(), this.valueToken.getValue());
            }
        } else {
            if (parent == null) {
                return null;
            }

            node = parent.findChild(this.valueToken.getValue());
            if (node == null) {
                node = new SmiOidNode(parent, this.valueToken.getValue());
            }
        }

        return node;
    }
}
