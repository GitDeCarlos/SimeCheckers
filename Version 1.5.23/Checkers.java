import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Checkers extends JFrame implements MouseListener, ActionListener, MouseMotionListener{
	private Container contentPane;
	private Tile board[][];
	private Tile selected;
	private Color backg;
	private boolean moving;
	private boolean turn = true;
	private JLabel killLabel;
	private JLabel nameLabel;
	
	private JMenu gameMenu;
	private JMenu charMenu;
	
	public Checkers(){
		contentPane = getContentPane();
		getContentPane().setBackground(new Color(186,134,91));
		setLayout(null);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(500,100);
		setSize(600,422);
		
		setUp();
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.addMouseListener(this);
		setJMenuBar(menuBar);
		
		createGameMenu();
		createCharMenu();
		
		menuBar.add(gameMenu);
		menuBar.add(charMenu);
		setColor(menuBar);
		
	}
	
	public void setUp()
	{
		board = new Tile[8][8];
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				board[x][y] = new Tile(x,y);
				board[x][y].setBounds(50*x, 50*y, 50, 50);
				board[x][y].setBorder(BorderFactory.createRaisedBevelBorder());
				board[x][y].setOpaque(true);
				board[x][y].addMouseListener(this);
				contentPane.add(board[x][y]);
				
				if((x+y)%2 == 0){
					board[x][y].setBackground(new Color(186,134,91));
				}
				
				else board[x][y].setBackground(new Color(89,59,49));
				
			}
		}
		
		for(int c = 0; c < 8; c++){
			for(int r = 0; r < 3; r++){
				if((c+r)%2 != 0){
					board[c][r].setPiece(new CheckerP(false, 0, ""));
				}
			}
			for(int r2 = 5; r2 < 8; r2++){
				if((c+r2)%2 != 0){
					board[c][r2].setPiece(new CheckerP(true, 0, ""));
				}
			}
		}
		
		killLabel = new JLabel("Kills: ");
		killLabel.setBounds(450,150,100,100);
		killLabel.setBorder(BorderFactory.createLineBorder(new Color(89,59,49), 5));
//		killLabel.setOpaque(true);
		contentPane.add(killLabel);
		
		nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(450,100,100,50);
		nameLabel.setBorder(BorderFactory.createLineBorder(new Color(89,59,49), 5));
//		nameLabel.setOpaque(true);
		contentPane.add(nameLabel);
	}
	
	public void setColor(JComponent c){
		c.setForeground(Color.red);
		c.setBorder(BorderFactory.createLineBorder(new Color(89,59,49), 5));
		c.setBackground(new Color(186,134,91));
	}
	
	public void createGameMenu(){
		gameMenu = new JMenu("Game");
		JMenuItem item;
		
		item = new JMenuItem("Restart");
		item.addActionListener(this);
		gameMenu.add(item);
		
		item = new JMenuItem("Quit");
		item.addActionListener(this);
		gameMenu.add(item);
	}
	
	public void createCharMenu(){
		charMenu = new JMenu("Character");
		JMenuItem item;
		
		item = new JMenuItem("Kills");
		item.addActionListener(this);
		charMenu.add(item);
	}
	
	public static void main(String args[]){
		try 
		{
	        UIManager.setLookAndFeel(
	        		UIManager.getCrossPlatformLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) 
		{}
	    catch (ClassNotFoundException e) 
		{}
	    catch (InstantiationException e) 
		{}
	    catch (IllegalAccessException e) 
		{}
		
		Checkers frame = new Checkers();
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() instanceof JMenuItem){
			switch(arg0.getActionCommand()){
			case"Restart":
				break;
			case"Quit": System.exit(0);
				break;
			}
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource() instanceof Tile){
			Tile entered = (Tile)arg0.getSource();
			
			backg = entered.getBackground();
			entered.setBackground(backg.brighter());
		}
	}

	public void mouseExited(MouseEvent arg0) {
		if(arg0.getSource() instanceof Tile){
			Tile exited = (Tile)arg0.getSource();
			
				exited.setBackground(backg);
		}
	}

	public void mousePressed(MouseEvent arg0) {
		
	}

	public void mouseReleased(MouseEvent arg0) {
		if(arg0.getSource() instanceof Tile){
			Tile clicked = (Tile)arg0.getSource();
			
			if(moving){
				if(!clicked.present()){
					if(selected.getPiece().legalMove(selected,clicked, board)){
						clicked.placePiece(selected.getPiece(), selected);
						moving = !moving;
						turn = !turn;
						killLabel.setText("Kills: ");
						nameLabel.setText("Name: ");
						clicked.clearHighlights(board);
//						if(clicked.doubleCheck()){
					}
					else{
						moving = false;
						killLabel.setText("Kills: ");
						nameLabel.setText("Name: ");
						clicked.clearHighlights(board);
					}
				}
				else{
					moving = false;
					killLabel.setText("Kills: ");
					nameLabel.setText("Name: ");
					clicked.clearHighlights(board);
				}
			}
			else{
				if(clicked.present() && clicked.getPiece().whichSide() == turn){
					moving = true; 
					killLabel.setText("Kills: "+clicked.getPiece().getKills());
					nameLabel.setText("Name: "+clicked.getPiece().getName());
					selected = clicked;
					clicked.highlight(board);
				}
			}
			repaint();
		}
	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}

}
