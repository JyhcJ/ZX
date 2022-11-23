import Config.DmConfig;
import Config.TaskConfig;
import com.jacob.com.Dispatch;

import javax.swing.*;
import java.util.ResourceBundle;

public class KeyPerson implements Runnable{
    private final String[] keyConfig={"2","7000","3"};

    private final int Handle;

    private DmConfig config;

    public KeyPerson(){
        this.config=new DmConfig();
        TaskConfig taskConfig=new TaskConfig();
        String[] temp= taskConfig.getHandle();

        for (String s : temp) {
            System.out.print(s + "?");
        }

        if(temp[0].equals("")){
            System.out.println("游戏未启动!");
            String str="游戏未启动!";
            JOptionPane.showMessageDialog(null, str, str, JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
        this.Handle=Integer.parseInt(temp[0]);

        this.config = new DmConfig();

    }

    @Override
    public void run() {
        String BuffIndex=keyConfig[0] ;
        int delay= Integer.parseInt(keyConfig[1]);
        String Keyout= keyConfig[2];

        BindWindow();


        Dispatch.call(config.getDm(), "SetWindowState",Handle,12);

//        Dispatch.call(config.getDm(),"SendString",Handle,"我爱你");
//
//        for (int i = 0; i < 900; i++) {
//            Dispatch.call(config.getDm(),"SendString",Handle,"我爱你");
//        }
        Dispatch.call(config.getDm(), "Delay", 1500);
        Dispatch.call(config.getDm(), "KeyDownChar", BuffIndex);
        Dispatch.call(config.getDm(), "Delay", delay);

        Dispatch.call(config.getDm(), "KeyDownChar", Keyout);

        //选怪,BUFF,输出
        //选怪


        //buff

        System.out.println("Key:Run执行完毕");
       config.getDm().invoke("UnBindWindow");


    }

    public void BindWindow(){
        //this.Handle= Dispatch.call(config.getDm(), "FindWindowByProcess", ProcessName,"","").getInt();
        //System.out.println("句柄:"+Handle);
        int res=Dispatch.call(config.getDm(), "BindWindowEx", Handle, "normal", "normal", "dx.keypad.state.api","", 0).getInt();
        Dispatch.call(config.getDm(), "Delay", 2000);
        String msg=Dispatch.call(config.getDm(), "GetWindowTitle",Handle).toString();
        System.out.println("(主角色)正在绑定窗口：" + msg+":"+res);

    }


    public  void GetMousePointWindow(){
        //Thread.sleep();

    }

}
