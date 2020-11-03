import javax.swing.*;

public class CheckerP extends Piece{
	private boolean side;
	private final ImageIcon red = new ImageIcon(CheckerP.class.getResource("red.png"));
	private final ImageIcon grn = new ImageIcon(CheckerP.class.getResource("green.png"));
	private int kills;
	private String name;
	
	private String[] namae = {"a", "e", "i", "o", "u",
			"ka", "ke", "ki", "ko", "ku",
			"sa", "se", "shi", "so", "su"
	};
	
	public CheckerP(boolean color, int k, String n){
		side = color;
		kills = k;
		name = n;
		makeName();
	}
	
	public void makeName(){
		name = namae[(int) (Math.random()*namae.length)] + 
				namae[(int) (Math.random()*namae.length)] +
				namae[(int) (Math.random()*namae.length)];
	}
	
	public boolean isKing(){
		return false;
	}
	
	public int getKills(){
		return kills;
	}
	
	public String getName(){
		return name;
	}
	
	public void addKill(){
		kills = kills+1;
	}
	
	public ImageIcon getChecker(){
		if(side)
			return red;
		else return grn;
	}
	
	public boolean whichSide(){
		return side;
	}
	
	public boolean legalMove(Tile a, Tile b, Tile[][] bd){
		if(side){
			if(Math.abs(b.getCol() - a.getCol()) == 1 && a.getRow()-1 == b.getRow() && !b.present())
				return true;
			if(a.getCol()+2 == b.getCol() && a.getRow()-2 == b.getRow() && !b.present()){
				if(bd[a.getCol()+1][a.getRow()-1].present() && 
						bd[a.getCol()+1][a.getRow()-1].getPiece().whichSide() != a.getPiece().whichSide()){
					bd[a.getCol()+1][a.getRow()-1].removePiece(); addKill();
					return true;
				}else return false;
					
			}
			if(a.getCol()-2 == b.getCol() && a.getRow()-2 == b.getRow() && !b.present()){
				if(bd[a.getCol()-1][a.getRow()-1].present() && 
						bd[a.getCol()-1][a.getRow()-1].getPiece().whichSide() != a.getPiece().whichSide()){
					bd[a.getCol()-1][a.getRow()-1].removePiece(); addKill();
					return true;
				}else return false;
			}
			
			else return false;
		}
		else{
			if(Math.abs(b.getCol() - a.getCol()) == 1 && a.getRow()+1 == b.getRow() && !b.present())
				return true;
			if(a.getCol()+2 == b.getCol() && a.getRow()+2 == b.getRow() && !b.present()){
				if(bd[a.getCol()+1][a.getRow()+1].present() && 
						bd[a.getCol()+1][a.getRow()+1].getPiece().whichSide() != a.getPiece().whichSide()){
					bd[a.getCol()+1][a.getRow()+1].removePiece(); addKill();
					return true;
				}else return false;
					
			}
			if(a.getCol()-2 == b.getCol() && a.getRow()+2 == b.getRow() && !b.present()){
				if(bd[a.getCol()-1][a.getRow()+1].present() && 
						bd[a.getCol()-1][a.getRow()+1].getPiece().whichSide() != a.getPiece().whichSide()){
					bd[a.getCol()-1][a.getRow()+1].removePiece(); addKill();
					return true;
				}else return false;
			}
			
			else return false;
		}
	}
}
