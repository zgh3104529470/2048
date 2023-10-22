import java.awt.*;

public class Card {
    private int x = 83;
    private int y = 134;
    private int w = 105;
    private int h = 105;
    private int i = 0;
    private int j = 0;
    int start = 10;
    private int num = 0;
    int[][] data = new int[4][4];
    private boolean merge = false;

    public Card(int i, int j) {
        this.i = i;
        this.j = j;
        cal();
    }

    private void cal() {
        this.x = 83 + j * w + (j + 1) * 5;
        this.y = 134 + i * h + (i + 1) * 5;

    }

    public void initDraw(Graphics g) {
        Color color = getColor();
        Color ocolor = getColor();
        g.setColor(color);
        g.fillRoundRect(x, y, w, h, 4, 4);
        g.setColor(ocolor);

        if (num != 0) {
            g.setColor(new Color(125, 78, 51));
            Font font = new Font("思源宋体", Font.BOLD, 40);
            g.setFont(font);
            String text = num + "";
            int tLen = gerWordWidth(font, text, g);
            int ty = y + 60;
            int tx = x + (w - tLen) / 2;
            g.drawString(text, tx, ty);
        }
    }

    public static int gerWordWidth(Font font, String content, Graphics g) {
        FontMetrics metrics = g.getFontMetrics(font);
        int width = 0;
        for (int i = 0; i < content.length(); i++) {
            width += metrics.charWidth(content.charAt(i));
        }
        return width;
    }

    private Color getColor() {
        Color color = null;
        switch (num) {
            case 2:
                color = new Color(238, 244, 234);
                break;
            case 4:
                color = new Color(222, 236, 200);
                break;
            case 8:
                color = new Color(174, 213, 130);
                break;
            case 16:
                color = new Color(142, 201, 75);
                break;
            case 32:
                color = new Color(111, 148, 48);
                break;
            case 64:
                color = new Color(76, 174, 124);
                break;
            case 128:
                color = new Color(60, 180, 144);
                break;
            case 256:
                color = new Color(45, 130, 120);
                break;
            case 512:
                color = new Color(9, 97, 26);
                break;
            case 1024:
                color = new Color(242, 177, 121);
                break;
            case 2048:
                color = new Color(223, 185, 0);
                break;
            default:
                color = new Color(92, 151, 117);
                break;
        }
        return color;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return this.num;
    }

    public void moveTop(Card[][] cards) {
        if (i == 0) {
            return;
        }
        Card prev = cards[i - 1][j];
        if (prev.getNum() == 0) {
            prev.num = this.num;
            this.num = 0;
            prev.moveTop(cards);
        } else if (prev.getNum() == num && !prev.merge) {
            prev.merge = true;
            prev.num = this.num*2;
            this.num = 0;
        }
    }

    public void moveRight(Card[][] cards) {
        if (j == 3) {
            return;
        }
        Card prev = cards[i][j + 1];
        if (prev.getNum() == 0) {
            prev.num = this.num;
            this.num = 0;
            prev.moveRight(cards);
        } else if (prev.getNum() == num && !prev.merge) {
            prev.merge = true;
            prev.num = this.num*2;
            this.num = 0;
        }
    }

    public void moveDown(Card[][] cards) {
        if (i == 3) {
            return;
        }
        Card prev = cards[i + 1][j];
        if (prev.getNum() == 0) {
            prev.num = this.num;
            this.num = 0;
            prev.moveDown(cards);
        } else if (prev.getNum() == num && !prev.merge) {
            prev.merge = true;
            prev.num = this.num*2;
            this.num = 0;
        }
    }
    public void moveLeft(Card[][] cards) {
        if (j == 0) {
            return;
        }
        Card prev = cards[i][j - 1];
        if (prev.getNum() == 0) {
            prev.num = this.num;
            this.num = 0;
            prev.moveLeft(cards);
        } else if (prev.getNum() == num && !prev.merge) {
            prev.merge = true;
            prev.num = this.num*2;
            this.num = 0;
        }
    }
    public void setMerge(boolean b) {
        this.merge=b;
    }
}
