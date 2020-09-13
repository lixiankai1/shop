package SupermarketCashRegister;

import com.bean.SupermarketUser;
import com.dao.DaoSupermarketUser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SupermarketCash extends JFrame implements ActionListener {
    private JPanel jp_north;
    private  JTextField jt_username;
    private JPasswordField jt_userPassword;
    private JRadioButton jr_administrator;
    private JButton jb_login;
    private JButton jb_clear;
    private  JLabel label;
    private DaoSupermarketUser daoSmu;

    public static void main(String[] args) {
        SupermarketCash logIn = new SupermarketCash();
        logIn.interfaces1();
    }

    public void interfaces1() {
        this.setSize(454, 650);
        this.setResizable(false); // 禁止最大化
        this.setLocation(500, 80);// 界面的显示位置 相对于屏幕左上角位置
        this.setTitle("用户登录"); // 设置标题

        Container con = this.getContentPane();// 大箱子一号

        // 上部图片放置处
        jp_north = new JPanel();
        jp_north.setPreferredSize(new Dimension(454, 175));
        label=new JLabel(new ImageIcon("src/images/top.jpg"));


       // label = new JLabel(new ImageIcon("src/images/top.jpg"));//用图片构造一个JLabel标签
        jp_north.add(label);//再把标签加到小箱子里
        con.add(jp_north, BorderLayout.NORTH); // 小箱子放到大箱子中

        // 中间的登录模块
        JPanel jp_center = new JPanel(null);
        jp_center.setPreferredSize(new Dimension(454, 350));

        JLabel jl_username = new JLabel("用户名:");
        jl_username.setBounds(80, 80, 40, 25);
        jt_username = new JTextField();
        jt_username.setBounds(140, 80, 200, 25);

        JLabel jl_userPassword = new JLabel("密码:");
        jl_userPassword.setBounds(80, 130, 40, 25);
        jt_userPassword = new JPasswordField();
        jt_userPassword.setBounds(140, 130, 200, 25);

        JLabel jl_select = new JLabel("账户选择:");
        jl_select.setBounds(60, 220, 80, 25);
        ButtonGroup bg = new ButtonGroup();
        JRadioButton jr_cashier = new JRadioButton("收银员", false);
        jr_cashier.setBounds(290, 220, 80, 25);

        jr_administrator = new JRadioButton("管理员", true);
        jr_administrator.setBounds(170, 220, 80, 25);


        bg.add(jr_administrator);
        bg.add(jr_cashier);
        jp_center.add(jl_username);
        jp_center.add(jt_username);
        jp_center.add(jl_userPassword);
        jp_center.add(jt_userPassword);
        jp_center.add(jl_select);
        jp_center.add(jr_cashier);
        jp_center.add(jr_administrator);
        con.add(jp_center, BorderLayout.CENTER);

        // 下方选择按钮
        JPanel jp_south = new JPanel(null);
        jp_south.setPreferredSize(new Dimension(454, 125));
        jb_login = new JButton("登录");
        jb_login.addActionListener(this);
        jb_login.setBounds(100, 0, 80, 35);
        jb_clear = new JButton("清空");
        jb_clear.addActionListener(this);
        jb_clear.setBounds(280, 0, 80, 35);
        jp_south.add(jb_login);
        jp_south.add(jb_clear);
        con.add(jp_south, BorderLayout.SOUTH);
        con.add(jp_south, BorderLayout.SOUTH);

        this.setVisible(true);  // 使界面显示
    }

    /*
    事件发生之后，要处理的代码
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // 判断点击的按钮
//         String text = ((JButton)e.getSource()).getText();
//         System.out.println(text);
        Object source = e.getSource();
        if (source == jb_login) {
            // 获取用户名、密码、角色
            String username = jt_username.getText();
            String passworld = jt_userPassword.getText();
            Boolean isAdmin = jr_administrator.isSelected();
            String urole;
            if (isAdmin){
                urole = "管理员";
            }else{
                urole = "收银员";
            }
            // 判断用户名、密码、角色
            // 判断是否为空
            if (username == null || username.equals("")) {
//                System.out.println("用户名不能为空");
                // 显示对话框
                JOptionPane.showMessageDialog(null,"用户名不能为空");
            }
            if (passworld == null || passworld.equals("")) {
//                System.out.println("密码不能为空");
                JOptionPane.showMessageDialog(null,"密码不能为空");
            }
            // 调用控制层的方法，进行数据查询
            daoSmu = new SupermarketUser();
            SupermarketUser user = new SupermarketUser(username,null,passworld,urole);
            boolean flag =  daoSmu.selectForLogin(user);
            if (flag){
                // 用户密码正确
            	dispose();
                MainFrame mf = new MainFrame();
                mf.setLocationRelativeTo(null); // 显示在屏幕的中间
                mf.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,"用户名，密码或角色错误");
            }

//            if (username.equals() && passworld.equals("123456") && isAdmin == true) {
//                System.out.println("正确，进去主界面");
//            } else {
//                System.out.println("错误，请检查");
//            }
        } else {
            // 清空
            jt_username.setText("");
            jt_userPassword.setText("");
        }


    }
}
