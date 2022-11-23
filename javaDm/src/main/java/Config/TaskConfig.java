package Config;

import com.jacob.com.Dispatch;
import java.util.ResourceBundle;

public class TaskConfig {
    private int threadNum;
    private String ProcessName;
    private String[] Handle;

    public TaskConfig(){
        DmConfig dmConfig = new DmConfig();
        //ResourceBundle Bundle= ResourceBundle.getBundle("config");

        this.ProcessName=ResourceBundle.getBundle("Config").getString("ProcessName");

        Handle= Dispatch.call(dmConfig.getDm(), "EnumWindowByProcess", ProcessName,"","",8+16+32).getString().split(",");
        threadNum= Handle.length;
        String keyHandle = Dispatch.call(dmConfig.getDm(), "GetPointWindow", "15", "15").toString();
        SortByKey(Handle, keyHandle);

    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public String getProcessName() {
        return ProcessName;
    }

    public void setProcessName(String processName) {
        ProcessName = processName;
    }

    public String[] getHandle() {
        return Handle;
    }

    public void setHandle(String[] handle) {
        Handle = handle;
    }

    public void SortByKey(String[] Handle,String KeyHandle){
        if(!KeyHandle.equals(Handle[0])){
            for (int i = 1; i < Handle.length; i++) {
                if (KeyHandle.equals(Handle[i])){
                    String temp=Handle[0];
                    Handle[0]=KeyHandle;
                    Handle[i]=temp;
                }
            }

        }

    }
}
