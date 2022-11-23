package Methods;

import Config.DmConfig;
import com.jacob.com.Dispatch;


public class HangUp implements Runnable{
    private DmConfig dm;
    private int Handle;

    public HangUp(DmConfig dm,int handle) {
        this.dm = dm;
        this.Handle=handle;
    }

    @Override
    public void run() {
        int res=Dispatch.call(dm.getDm(), "BindWindowEx", Handle, "normal", "normal", "dx.keypad.state.api","dx.public.disable.window.show", 0).getInt();
        Dispatch.call(dm.getDm(), "Delay",1000);
        Dispatch.call(dm.getDm(), "KeyDown",86);
    }
}
