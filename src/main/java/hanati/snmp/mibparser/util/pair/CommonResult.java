package hanati.snmp.mibparser.util.pair;

public class CommonResult {
    private int resultCode;
    private String msg;
    private Object data;

    public static final int SUCCESS = 1;
    public static final int FAIL = 0;
    public static final int ERROR = -1;
    public static final int INVALID_MIB_FILE_ERROR = 100;
    public static final int SYNTAX_ERROR = 101;
    public static final int IMPORT_ERROR = 102;
    public static final int UNKNOWN_ERROR = 103;

    public CommonResult(int resultCode) {
        this.resultCode = resultCode;
        this.msg = null;
        this.data = null;
    }

    public CommonResult(int resultCode, String msg) {
        this.resultCode = resultCode;
        this.msg = msg;
        this.data = null;
    }

    public CommonResult(int resultCode, Object data) {
        this.resultCode = resultCode;
        this.msg = null;
        this.data = data;
    }

    public CommonResult(int resultCode, String msg, Object data) {
        this.resultCode = resultCode;
        this.msg = msg;
        this.data = data;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getResultCode() {
        return (int) this.resultCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return (String) this.msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return (Object) this.data;
    }
}
