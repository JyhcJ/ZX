package Methods;

import Config.DmConfig;
import com.jacob.com.Dispatch;

import javax.swing.*;

public class Follow implements Runnable {
    private DmConfig dmConfig;
    private String rbxAddr;
    private String allocaddr;
    private String Followaddr;
    private final int Handle;

    public Follow(DmConfig dm,int handle) {
        this.dmConfig=dm;
        this.Handle = handle;
        if(handle==0){
            return;
        }

        sortAddr(allocaddr());
        writeData();
        action();


    }

    String allocaddr(){

        long temp=Dispatch.call(dmConfig.getDm(),"VirtualAllocEx",Handle,0,512,0).getLong();
        //VirtualAllocEx(hwnd,addr,size,type)
        long temp_rbxaddr=temp+37;
        long temp_Followaddr=temp+45;
        this.rbxAddr= Long.toHexString(temp_rbxaddr);
        this.allocaddr=Long.toHexString(temp);
        this.Followaddr=Long.toHexString(temp_Followaddr);

        if ("0".equals(allocaddr)){
            return "-1";
        }
        return  allocaddr;
    }

    String sortAddr(String addr){
        //小端排序嘛?

        StringBuilder addrBuilder = new StringBuilder(addr);
        while(addrBuilder.length()%2!=0){
              addrBuilder.insert(0, "0");
          }

        addr = addrBuilder.toString();

        //还可能是9个地址!!!
        StringBuilder sortAllocaddr= new StringBuilder();
        for (int i = 1; i <= addr.length()/2; i++) {
            for (int i1 = 0; i1 < 2; i1++) {
                sortAllocaddr = new StringBuilder("" + sortAllocaddr + addr.charAt(addr.length() - 2 * i + i1));
            }
        }

        return sortAllocaddr.toString();
    }

    void writeData(){
        //1申请的内存中的汇编指令
        //2jmp
        String callAddr = "140701F43";
        String data1="4C8B03488BD6488BCB48891C25"+sortAddr(rbxAddr)+"FF2500000000"+sortAddr(callAddr)+"000000";
        String data2="48C7C0"+sortAddr(allocaddr)+"FFE0";
        long hookAddrL=Long.parseLong(callAddr,16);
        hookAddrL-=9;
        String hookAddrH=Long.toHexString(hookAddrL);


        int result0=Dispatch.call(dmConfig.getDm(),"WriteData",Handle,allocaddr,data1).getInt();
        int result1=Dispatch.call(dmConfig.getDm(),"WriteData",Handle,hookAddrH,data2).getInt();
        int result2=Dispatch.call(dmConfig.getDm(),"WriteString",Handle,Followaddr,0,"Follow").getInt();



    }

    void action(){
        /*String actionAss="4883EC7048C7C3"+sortAddr(rbxAddr)+"4889D948C7C2"+sortAddr(Followaddr)+"4C8B0341FF50704883C470";
        String MASM= Dispatch.call(dmConfig.getDm(),"DisAssemble",actionAss);*/
                //000000001E590025

            Dispatch.call(dmConfig.getDm(), "AsmAdd", "sub rsp,70");
            Dispatch.call(dmConfig.getDm(), "AsmAdd", "mov rbx, qword ptr ds:["+rbxAddr+"]");
            Dispatch.call(dmConfig.getDm(), "AsmAdd", "mov rcx,rbx");
            Dispatch.call(dmConfig.getDm(), "AsmAdd", "mov rdx,"+Followaddr);
            Dispatch.call(dmConfig.getDm(), "AsmAdd", "mov r8,qword ptr ds:[rbx]");
            Dispatch.call(dmConfig.getDm(), "AsmAdd", "call qword ptr ds:[r8+70]");
            Dispatch.call(dmConfig.getDm(), "AsmAdd", "add rsp,70");


    }

    @Override
    public void run() {
        int rs=Dispatch.call(dmConfig.getDm(), "ReadInt",Handle,rbxAddr,0).getInt();
        if(rs==0){
            JOptionPane.showMessageDialog(null, "str", "一个账号一个错误", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        Dispatch.call(dmConfig.getDm(), "AsmCall", Handle,1);
    }
}
