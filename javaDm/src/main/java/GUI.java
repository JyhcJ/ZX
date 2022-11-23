
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI {
    public static void main(String[] args) throws InterruptedException {

        Frame f = new Frame("初始化中");


        mController mController1= new mController();

        f.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent arg0) {
                    System.exit(0);
                }
            });
            /*
             * 这里只是在内存里面创建了一个窗口对象 还不能真正显示出来然我们看到
             */
            //f.setBackground(Color.blue);// 设置窗体的背景颜色
            // blue是Color类里面的一个静态常量，可以使用“类名.静态常量名”来访问
        f.setVisible(true);// 设置窗体是否可见
            /*
             * 要想看到在内存里面创建出来的窗口对象， 必须调用setVisble()方法，并且把参数true传入才能看得见窗体 如果传入的参数是false，那么窗体也是看不见的
             */
            f.setSize(400, 200);// 设置窗体的初始大小
            f.setLocation(200, 200);// 设置窗体出现时的位置，如果不设置则默认在左上角(0,0)位置显示
            f.setResizable(true);
            /*
             * 设置窗体能否被改变大小，设置为false后表示不能改变窗体的显示大小 这里将窗体显示的大小设置为200X200，那么窗体的显示只能是这个大小了，不能再使用鼠标拖大或者缩小
             */
            Button btn1 = new Button("button1");
            Button btn2 = new Button("button2");
            Button btn3 = new Button("button3");
            Button btn4 = new Button("button4");
            /* setLayout方法的定义：public void setLayout(LayoutManager mgr) */
            f.setLayout(new GridLayout(2, 2));

            f.add(btn1);// 把创建出来的按钮放置到Frame窗体中
            f.add(btn2); // 这里并没有设置按钮的大小与位置
            f.add(btn3); // 设置按钮的大小与位置都是由布局管理器来做的
            f.add(btn4); // 设置按钮的大小与位置都是由布局管理器来做的
            f.setVisible(true);

        mController.Monitor11 m1 = new mController.Monitor11();
        mController.Monitor12 m2 = new mController.Monitor12();
        mController.Monitor13 m3 = new mController.Monitor13();
        mController.Monitor14 m4 = new mController.Monitor14();

            btn2.setEnabled(true);
            btn3.setEnabled(false);
            btn1.addActionListener(m1);
            btn2.addActionListener(m2);

            btn3.addActionListener(m3);
            btn4.addActionListener(m4);
            f.setTitle("初始化完成!");

            while(true){
                if (m2.flag){
                    btn2.setEnabled(false);
                    btn3.setEnabled(true);
                    f.setTitle("跟随:确认对象中");
                    break;
                }
                Thread.sleep(700);
            }


    }
}






    /**学习JAVA的GUI编程编写的第一个图形界面窗口*/



