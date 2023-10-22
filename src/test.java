import javax.swing.*;
import java.awt.*;

public class test extends JFrame {
    public void main(String[] args) {
        this.setSize(603, 680);
        this.setTitle("2048 v1.0");//标题
        getContentPane().setBackground(new Color(66,136,83));//窗口大小
        setDefaultCloseOperation(GameJFrame.EXIT_ON_CLOSE);//关闭进程
        setLocationRelativeTo(null);//打开窗口居中
    }
}
