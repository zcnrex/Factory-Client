package czeng_CSCI201_assignment5b;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class FactoryOrderPanel extends JPanel implements Runnable{
	private JScrollPane jsp;
	private JPanel jspPanel = new JPanel(), borderPanel = new JPanel();
	private JButton backButton = new JButton();
	private Factory factory;
	private JLabel money, moneyLabel;
	private Vector<FactoryOrderPiece> fop = new Vector<FactoryOrderPiece>();
	private int id = 0;

	public FactoryOrderPanel(Factory factory){
		this.factory = factory;
		setLayout(null);
//		factory.startOrder();
		
		backButton.setBounds(70, 30, 50, 50);
		ImageIcon icon = new ImageIcon("images/Back.png");
		backButton.setIcon(icon);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				factory.getOuterPanel().repaint();
				CardLayout c1 = (CardLayout)factory.getOuterPanel().getLayout();
				c1.show(factory.getOuterPanel(), "mainPanel");
			}
		});
		add(backButton);
		
		moneyLabel = new JLabel("Money");
		moneyLabel.setBounds(15, 30, 50, 20);
		add(moneyLabel);
		money = new JLabel("$"+factory.getTasks().getMoney());
		money.setBounds(15, 50, 50, 20);
		add(money);
		
		factory.getTaskPool().getTasks().size();
		jspPanel.setLayout(new BoxLayout(jspPanel, BoxLayout.Y_AXIS));
		
		for (Task t : factory.getTaskPool().getTasks()){
			fop.add(new FactoryOrderPiece(t));
			fop.get(id).setBounds(0, (id)*100, 800, 100);
			jspPanel.add(fop.get(id));
			id++;
		}
		jsp = new JScrollPane(jspPanel);
		jsp.setBackground(null);
		jsp.setBorder(null);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		borderPanel.setLayout(new BorderLayout());
		borderPanel.add(new JLabel(), BorderLayout.NORTH);
		borderPanel.add(jsp, BorderLayout.CENTER);
		borderPanel.setBounds(0, 100, 800, 450);
		add(borderPanel);
	}
	
	public void newOrder(){
		System.out.println("Task number: " + factory.getTaskPool().getSize());
		fop.add(new FactoryOrderPiece(factory.getTaskPool().getRecentTask()));
		System.out.println("Task name: " + factory.getTaskPool().getRecentTask().getName());
		fop.get(id).setBounds(0, (id+1)*100, 800, 100);
		fop.get(id).setVisible(true);
		id++;
		jspPanel.add(fop.lastElement());
	}

	@Override
	public void run() {

		while(true){
			revalidate();
//			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i = 0; i < fop.size(); i++){
				if(fop.get(i).getOrder().equals("accept")){
					factory.startWorker();
					factory.getTasks().addMoney(fop.get(i).getCost());
					money.setText("$"+factory.getTasks().getMoney());
					factory.setTaskToJSP();
					factory.getServer().sendMessage("accept");
					fop.get(i).setVisible(false);
					jspPanel.remove(fop.get(i));
					fop.get(i).setOrder("");
					fop.remove(i);
				}
				else if(fop.get(i).getOrder().equals("decline")){
					factory.getServer().sendMessage("denied");
					fop.get(i).setVisible(false);
					jspPanel.remove(fop.get(i));
					fop.get(i).setOrder("");
					fop.remove(i);
				}
			}
		}
		
	}
}
