import Config.DmConfig;
import Config.TaskConfig;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class mController {

    static DmConfig dmConfig = new DmConfig(1);//注册!
    static TaskConfig taskConfig = new TaskConfig();
    static KeyPerson k = new KeyPerson();
    static BuffPerson[] b = new BuffPerson[5];

    mController() {
        for (int i = 0; i < taskConfig.getThreadNum() - 1; i++) {
            //String value = new String(temp[i].getBytes("ISO8859-1"),"UTF-8");
            b[i] = new BuffPerson();
        }


   //     Scanner scanner = new Scanner(System.in);
       /* while (true) {
            if (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if ("9".equals(str)) {
                    System.exit(0);
                }

            }
        }*/
    }

    static class Monitor11 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("开怪执行中");
            new Thread(k).start();
            for (int i = 0; i < taskConfig.getThreadNum() - 1; i++) {
                //String value = new String(temp[i].getBytes("ISO8859-1"),"UTF-8");
                new Thread(b[i]).start();
            }

        }
    }

        static class Monitor12 implements ActionListener {
            boolean flag =false;
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("跟随检测");
                for (int i = 0; i < taskConfig.getThreadNum() - 1; i++) {
                    //String value = new String(temp[i].getBytes("ISO8859-1"),"UTF-8");
                    b[i].FollowCreat();
                }
                flag=true;
            }
        }

        static class Monitor13 implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (int i = 0; i < taskConfig.getThreadNum() - 1; i++) {
                    b[i].FollowAction();
                }


            }

        }

        static class Monitor14 implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (int i = 0; i < taskConfig.getThreadNum() - 1; i++) {
                    b[i].Hangup();
                }
            }
        }
    }




