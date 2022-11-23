
import Config.DmConfig;
import com.jacob.com.Dispatch;


public class Test {
    public static void main(String[] args) {
        DmConfig dm=new DmConfig(1);
        int res= Dispatch.call(dm.getDm(), "BindWindowEx", 6359386, "normal", "normal", "dx.keypad.state.api","dx.public.down.cpu", 0).getInt();
        System.out.println(res);
        Dispatch.call(dm.getDm(), "KeyDownChar", "2");
        Dispatch.call(dm.getDm(), "DownCpu",0,100);

    }
}
