import Config.DmConfig;
import Config.TaskConfig;
import Methods.Follow;
import Methods.HangUp;
import com.jacob.com.Dispatch;
import java.util.ResourceBundle;



public class BuffPerson implements Runnable {
    private final String[]  BuffPersonConfig=ResourceBundle.getBundle("config").getString("BuffAction").split(",");
    private final int Handle;
    private static int ThreadNum=1;
    private Follow follow;
    private HangUp hangUp;
    private DmConfig config;


    public BuffPerson(){
        TaskConfig taskConfig=new TaskConfig();
        String[] temp=taskConfig.getHandle();
        this.Handle=Integer.parseInt(temp[ThreadNum]);
        if (Handle==0){
            return;
        }

        //
        config = new DmConfig();
        ThreadNum++;
    }

    @Override
    public  void run() {
        String KeyIndex=BuffPersonConfig[0];
        String BuffIndex=BuffPersonConfig[1] ;
        int BuffDelay= Integer.parseInt(BuffPersonConfig[2]);
        int HangUp= Integer.parseInt(BuffPersonConfig[3]);


        try {
            BindWindow();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }


     /*   for (int i = 0; i < 9; i++) {
            Dispatch.call(config.getDm(), "SetWindowState",Handle,12);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Dispatch.call(config.getDm(), "KeyDownChar", "F5");
        }*/
        Dispatch.call(config.getDm(), "SetWindowState",Handle,12);

        Dispatch.call(config.getDm(), "KeyDownChar", KeyIndex);
        Dispatch.call(config.getDm(), "Delay", 500);

        Dispatch.call(config.getDm(), "KeyDownChar", BuffIndex);
        Dispatch.call(config.getDm(), "Delay", BuffDelay);
        Dispatch.call(config.getDm(), "KeyDownChar", "F7");
        Dispatch.call(config.getDm(), "Delay", 200);
        if (HangUp==1) { Dispatch.call(config.getDm(), "Keydown", 86);}

        //选中主角色,增益,选怪挂机
        ThreadNum=1;
        System.out.println(Thread.currentThread().getName()+"执行完毕");
        config.getDm().invoke("UnBindWindow");

    }

    public  void BindWindow() throws InterruptedException {
        //System.out.println("句柄:"+Handle);
        int res=Dispatch.call(config.getDm(), "BindWindowEx", Handle, "normal", "normal", "dx.keypad.state.api","", 0).getInt();
        Thread.sleep(1500);
        System.out.println(Thread.currentThread().getName()+" " +":"+res);

    }

    public void FollowCreat(){
        this.follow=new Follow(config,Handle);    //创建Follow功能.
    }

    public void FollowAction(){
        follow.run();
    }

    public void Hangup(){
        this.hangUp=new HangUp(config,Handle);
        hangUp.run();
    }
}
