package Config;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import java.util.ResourceBundle;

public class DmConfig {

    private final static String version = "xjxvu03v0kfzib7";
    private final static String license = ResourceBundle.getBundle("Config").getString("license");
    /**
     * 大漠插件执行组件
     */
    private final ActiveXComponent dm = new ActiveXComponent("dm.dmsoft");

    public DmConfig() {

    }
    public DmConfig(int flag){
       if (flag==1){
           this.version();
           this.register();
       }
    }

    public void version() {
        System.out.println("大漠插件版本：" + dm.invoke("Ver").getString());
    }

    public void register() {
        int success = Dispatch.call(dm, "Reg", license, version).getInt();
        System.out.println("正在注册dm插件：" + (success == 1 ? "注册成功" : "注册失败"));
    }

    public ActiveXComponent getDm() {
        return dm;
    }

}