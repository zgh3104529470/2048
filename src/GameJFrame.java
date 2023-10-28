import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, MouseListener, ActionListener {

    int x = 0;
    int y = 0;
    private Card[][] cards = new Card[4][4];
    int soccer = 0;
    JMenuItem newgame = new JMenuItem("新游戏");
    JMenuItem pause = new JMenuItem("暂停");
    JMenuItem exit = new JMenuItem("退出");
    JMenuItem ophelp = new JMenuItem("操作帮助");
    JMenuItem winoption = new JMenuItem("获胜条件");
    private String gameFlag = "start";

    public GameJFrame() {
        //初始化界面
        initJFrame();

        //在这个界面中添加内容
        init2JFrame();

        //得分界面
        initImage();

        //让当前界面显示出来
        this.setVisible(true);

        //建卡片
        initCard();
        
        //随机数
        createRnum();


    }

    private void moveCard(int dir) {
        ClearCard();
        Card card;
        if(dir==1){
            for (int i = 1;i < 4; i++){
                for (int j = 0;j < 4;j++){
                    card = cards[i][j];
                    if(card.getNum()!=0){
                        card.moveTop(cards);
                    }

                }
            }
        }else if(dir==2){
            for (int i = 0;i < 4; i++){
                for (int j = 2;j >= 0;j--){
                    card = cards[i][j];
                    if(card.getNum()!=0){
                        card.moveRight(cards);
                    }

                }
            }
        }else if(dir==3){
            for (int i = 2;i >= 0; i--){
                for (int j = 0;j < 4;j++){
                    card = cards[i][j];
                    if(card.getNum()!=0){
                        card.moveDown(cards);
                    }

                }
            }

        }else if(dir==4){
            for (int i = 0;i < 4; i++){
                for (int j = 1;j < 4;j++){
                    card = cards[i][j];
                    if(card.getNum()!=0){
                        card.moveLeft(cards);
                    }

                }
            }
        }
        createRnum();
        initImage();
    }
    private void ClearCard() {
        Card card;
        for (int i = 0; i < 4;i++) {
            for (int j = 0; j < 4; j++) {
                card = cards[i][j];
                card.setMerge(false);
            }
        }
    }

    //随机出现数字
    private void createRnum() {
        int num=0;
        Random random = new Random();
        int n = random.nextInt(5)+1;
        if (num==1) {
            num = 4;
        }else {
            num = 2;
        }
        soccer = num + soccer;
        if (cardIsFull()){
            return;
        }
        Card card = getRCard(random);
        if (card!=null) {
            card.setNum(num);
        }
    }

    private boolean cardIsFull() {
        Card card;
        for (int i = 0; i < 4;i++){
            for (int j = 0;j < 4;j++){
                card =cards[i][j];
                if (card.getNum()==0){
                    return false;
                }
            }
        }
        return  true;
    }

    private Card getRCard(Random random) {
        int i = random.nextInt(4);
        int j = random.nextInt(4);
        Card card = cards[i][j];

        if (card.getNum()==0){
            return card;
        }
        return getRCard(random);
    }

    private void initCard() {
        Card card;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                card = new Card(i,j);
                cards[i][j] = card;
            }
        }
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        drawCard(g);
    }

    private void drawCard(Graphics g) {
        Card card;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                card = cards[i][j];
                card.initDraw(g);
            }
        }

    }

    private void initJFrame() {
        this.setSize(603, 680);
        this.setTitle("2048 v1.0");//标题
        this.getContentPane().setBackground(new Color(66,136,83));//窗口背景
        this.setDefaultCloseOperation(GameJFrame.EXIT_ON_CLOSE);//关闭进程
        this.setLocationRelativeTo(null);//打开窗口居中
        this.setResizable(false);

        this.setLayout(null);

        this.addKeyListener(this);
    }
    private void init2JFrame(){
        Font font = new Font("微软雅黑",Font.BOLD,18);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu game = new JMenu("游戏");
        game.setFont(font);
        JMenu function = new JMenu("帮助");
        function.setFont(font);
        JMenu aboutus = new JMenu("关于我们");
        aboutus.setFont(font);

        newgame.setFont(font);
        exit.setFont(font);
        pause.setFont(font);
        ophelp.setFont(font);
        winoption.setFont(font);

        jMenuBar.add(game);
        jMenuBar.add(function);
        jMenuBar.add(aboutus);
        game.add(newgame);
        game.add(pause);
        game.add(exit);
        function.add(ophelp);
        function.add(winoption);

        //菜单事件监听
        newgame.addActionListener(this);
        pause.addActionListener(this);
        exit.addActionListener(this);
        ophelp.addActionListener(this);
        winoption.addActionListener(this);


        this.setJMenuBar(jMenuBar);

    }
    private void initImage(){
        getContentPane().removeAll();
        //得分
        JLabel soccerCount = new JLabel("得分：" + soccer);
        soccerCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(soccerCount);
        this.getContentPane().repaint();
    }



    private boolean victory(){
        return false;


    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!"start".equals(gameFlag)){
            return;
        }
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                moveCard(1);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                moveCard(2);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                moveCard(3);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                moveCard(4);
                break;
        };
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == newgame) {
            int soccer = 0;
            new GameJFrame();
        }else if (obj == exit) {
            Object[] options={"确定","取消"};
            int res =JOptionPane.showOptionDialog(this,"你确定要退出游戏吗","",
                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
            if (res == 0) {
                System.exit(0);
            }
        } else if (obj == ophelp) {
            JOptionPane.showMessageDialog(null,"通过键盘的上下左右来移动，相同数字会合并",
                    "提示",JOptionPane.INFORMATION_MESSAGE);

        }else if (obj == winoption) {
            JOptionPane.showMessageDialog(null,"得到数字2048获得胜利",
                    "获胜条件",JOptionPane.INFORMATION_MESSAGE);
        } else if (obj == pause) {
            Object[] options={"退出","继续"};
            int res =JOptionPane.showOptionDialog(this,"游戏暂停中,你可以点击继续游戏","",
                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
            if (res == 0) {
                System.exit(0);
            }
        }
    }
}